#pragma once

#include "Repository.h"
#include "AssistantRepository.h"
#include "HTMLAssistantRepository.h"
#include "CSVAssistantRepository.h"
#include "MemoryRepository.h"
#include "MemoryAssistantRepository.h"
#include "VictimValidator.h"
#include "FileRepository.h"
#include "Action.h"
#include <memory>
#include <cctype>
#include <string>
#include "Observer.h"

using namespace std;

class ServiceException :public std::exception
{
private:
	std::string errorMessage;
public:
	ServiceException(const std::string& message) : errorMessage{ message } {}

	const char* what() const throw() override
	{
		return this->errorMessage.c_str();
	}
};

class Service: public Subject
{
private:
	Repository& repository;
	AssistantRepository* assistantRepository;
	vector<unique_ptr<Action>> undoStack{};
	vector<unique_ptr<Action>> redoStack{};
public:
	//constructor for service
	Service(Repository& victimRepository) : repository{ victimRepository } { this->assistantRepository = new MemoryAssistantRepository(); }
	Service(Repository& victimRepository, AssistantRepository* assistantRepository) : repository{ victimRepository } { this->assistantRepository = assistantRepository; }
	~Service();

	//returns all the victims in the repository as a Dynamic Vector
	std::vector<TElement> getAllVictims() const;

	std::vector<TElement> getAllAssistantVictims() const;

	/*
		Adds a victim to the repository with the given data
		Input:	- victim given through arguments of type std::strings
		Outpurt:- true if the victim was succesfuly addded
				- false otherwise
	*/
	bool addVictim(const std::string& name, const std::string& placeOfOrigin, const std::string& age, const std::string& photograph);

	/*
	Updates a victim from the repository (with the given data)
	Input:	- victim given through arguments of type std::strings
	Outpurt:- true if the victim was succesfuly updated
			- false otherwise
	*/
	bool updateVictim(const std::string& name, const std::string& placeOfOrigin, const std::string& age, const std::string& photograph);

	bool saveVictim(const std::string& name);

	bool undo();

	bool redo();

	TElement next();

	/*
	Removes a victim from the repository given through the name
	Input:	- victim name (std::string)
	Outpurt:- true if the victim was succesfuly removed
			- false otherwise
	*/
	bool removeVictim(const std::string& name);

	void fileLocationName(const std::string& fileNameLocation);

	std::string getFileLocationName();

	std::string getMylistFileLocation();

	void mylistLocationName(const std::string& mylistNameLocation);

	void DeleteMemory();

private:
	/*
	Checks to see if a given std::string is a number
	Input:	- string an std::string
	Outpurt:- true if the string represents a number
			- false otherwise
	*/
	bool isNumber(const std::string& string);
};
