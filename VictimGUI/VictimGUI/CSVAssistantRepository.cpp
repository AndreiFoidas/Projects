
#include "CSVAssistantRepository.h"
#include <sstream>
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <exception>

using namespace std;

CSVAssistantRepository::CSVAssistantRepository(const std::string& CVSfileName)
{
	this->CVSfileName = CVSfileName;
}

bool CSVAssistantRepository::saveVictim(const Victim& newVictim)
{
	std::string name=newVictim.getName();

	ifstream fileRead(this->CVSfileName);
	Victim victim;
	while (fileRead >> victim)
	{
		if (victim.getName() == name)
		{
			fileRead.close();
			throw RepositoryException{ "ERROR: Duplicate victim!" };
			//return false;
		}
	}
	fileRead.close();

	ofstream fileAppend(this->CVSfileName, ios::out | ios::app);
	fileAppend << newVictim;
	fileAppend.close();
	return true;
}

void CSVAssistantRepository::setFileLocation(const std::string& mylistNameLocation)
{

	this->CVSfileName = mylistNameLocation;
	fstream file;
	file.open(this->CVSfileName, ios::out);

	file.close();
}

std::vector<TElement> CSVAssistantRepository::getAllAssistantVictims() const
{
	std::vector<Victim> victimVector;
	Victim victim;
	ifstream fileRead(this->CVSfileName);
	while (fileRead >> victim)
	{
		victimVector.push_back(victim);
	}
	fileRead.close();

	return victimVector;
}

std::string CSVAssistantRepository::getFileLocation()
{
	return this->CVSfileName;
}