
#include "Service.h"

using namespace std;

Service::~Service()
{
	if (this->assistantRepository != nullptr)
		delete this->assistantRepository;
}

std::vector<TElement> Service::getAllVictims() const
{
	return this->repository.getAllVictims();
}

std::vector<TElement> Service::getAllAssistantVictims() const
{
	return this->assistantRepository->getAllAssistantVictims();
}

bool Service::addVictim(const std::string& name,const std::string& placeOfOrigin, const std::string& age, const std::string& photograph)
{
	if (isNumber(age) == false)
		throw ServiceException{ "ERROR: Invalid input!" };
	int ageInteger = std::stoi(age);
	Victim victim = Victim(name,placeOfOrigin,ageInteger,photograph);
	VictimValidator::validate(victim);

	bool result;
	result = this->repository.addVictim(victim);
	if (result == true)
	{
		unique_ptr<Action> addAction = make_unique<ActionAdd>(this->repository, victim);
		undoStack.push_back(move(addAction));
	}
	return result;

}

bool Service::removeVictim(const std::string& name)
{
	Victim victim;
	for (auto victimInVector : this->repository.getAllVictims())
		if (victimInVector.getName() == name)
			victim = victimInVector;
	bool result = this->repository.removeVictim(name);
	if (result == true)
	{
		unique_ptr<Action> removeAction = make_unique<ActionRemove>(this->repository, victim);
		undoStack.push_back(move(removeAction));
	}
	return result;
}

bool Service::updateVictim(const std::string& name, const std::string& placeOfOrigin, const std::string& age, const std::string& photograph)
{
	if (isNumber(age) == false)
		throw ServiceException{ "ERROR: Invalid input!" };
	Victim oldVictim;
	for (auto victimInVector : this->repository.getAllVictims())
		if (victimInVector.getName() == name)
			oldVictim = victimInVector;
	int ageInteger = std::stoi(age);
	Victim newVictim = Victim(name, placeOfOrigin, ageInteger, photograph);
	VictimValidator::validate(newVictim);

	bool result = this->repository.updateVictim(newVictim);
	if (result == true)
	{
		unique_ptr<Action> updateAction = make_unique<ActionUpdate>(this->repository, oldVictim, newVictim);
		this->undoStack.push_back(move(updateAction));
	}
	return result;
}

bool Service::saveVictim(const std::string& name)
{
	Victim victim = Victim();
	std::vector<TElement> victimListModeB = this->getAllVictims();

	for (auto victimInFor : victimListModeB)
		if (victimInFor.getName() == name)
			victim = victimInFor;
	
	if (victim.getName().size()==0)
		throw ServiceException{ "ERROR: Invalid input!" };

	this->notify();
	return this->assistantRepository->saveVictim(victim);
}

bool Service::undo()
{
	if (this->undoStack.size() == 0)
		return false;

	unique_ptr<Action> undoAction = move(this->undoStack.back());
	this->undoStack.pop_back();
	undoAction->executeUndo();
	this->redoStack.push_back(move(undoAction));

	return true;
}

bool Service::redo()
{
	if (this->redoStack.size() == 0)
		return false;

	unique_ptr<Action> redoAction = move(this->redoStack.back());
	this->redoStack.pop_back();
	redoAction->executeRedo();
	this->undoStack.push_back(move(redoAction));

	return true;
}

TElement Service::next()
{
	return this->repository.next();
}

void Service::fileLocationName(const std::string& fileNameLocation)
{
	this->repository.setFileLocation(fileNameLocation);
}

string Service::getFileLocationName()
{
	return this->repository.getFileLocation();
}

std::string Service::getMylistFileLocation()
{
	return this->assistantRepository->getFileLocation();
}

void Service::mylistLocationName(const std::string& mylistNameLocation)
{
	int periodLocation = mylistNameLocation.find(".");
	string extention = mylistNameLocation.substr(periodLocation + 1);
	if (extention == "html")
	{
		delete this->assistantRepository;
		this->assistantRepository = new HTMLAssistantRepository{ mylistNameLocation };
	}
	if (extention == "csv")
	{
		delete this->assistantRepository;
		this->assistantRepository = new CSVAssistantRepository{mylistNameLocation};
	}
	this->assistantRepository->setFileLocation(mylistNameLocation);
}

bool Service::isNumber(const std::string& string)
{
	std::string::const_iterator stringIterator = string.begin();
	while (stringIterator != string.end() && std::isdigit(*stringIterator))
		++stringIterator;
	if (!string.empty() && stringIterator == string.end())
		return true;
	return false;
}

void Service::DeleteMemory()
{
	if(this->assistantRepository!=nullptr)
		delete this->assistantRepository;
}