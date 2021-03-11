
#include "FileRepository.h"
#include <sstream>
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <exception>

using namespace std;

FileRepository::FileRepository()
{
	this->indexVictimList = -1;
}

FileRepository::FileRepository(const std::string& fileName)
{
	this -> indexVictimList = -1;
	this->fileName = fileName;
}

bool FileRepository::addVictim(const Victim& newVictim)
{
	std::string name = newVictim.getName();

	ifstream fileRead(this->fileName);
	Victim victim;
	while (fileRead >> victim)
	{
		if (victim.getName() == name)
		{
			fileRead.close();
			throw RepositoryException{"ERROR: Duplicate victim!"};
			//return false;
		}
	}
	fileRead.close();

	ofstream fileAppend(this->fileName,ios::out | ios::app);

	fileAppend << newVictim;
	fileAppend.close();
	return true;
}

bool FileRepository::removeVictim(const std::string& name)
{
	ifstream fileRead(this->fileName);
	Victim victim;
	vector<Victim> victimVector;
	while (fileRead >> victim)
	{
		victimVector.push_back(victim);
	}
	fileRead.close();

	ofstream fileWrite(this->fileName);

	bool result=false;
	for (auto victimInVector : victimVector)
	{
		if (victimInVector.getName() != name)
			fileWrite << victimInVector;
		else
			result = true;
	}
	fileWrite.close();
	if(result==false)
		throw RepositoryException{ "ERROR: Victim does not exist!" };
	return result;
}

bool FileRepository::updateVictim(const Victim& newVictim)
{
	ifstream fileRead(this->fileName);
	Victim victim;
	vector<Victim> victimVector;
	while (fileRead >> victim)
	{
		victimVector.push_back(victim);
	}
	fileRead.close();

	ofstream fileWrite(this->fileName);

	bool result = false;
	for (auto victimInVector : victimVector)
	{
		if (victimInVector.getName() != newVictim.getName())
			fileWrite << victimInVector;
		else
		{
			fileWrite << newVictim;
			result = true;
		}
	}
	fileWrite.close();
	if (result == false)
		throw RepositoryException{ "ERROR: Victim does not exist!" };
	return result;
}

TElement FileRepository::next()
{
	this->indexVictimList++;
	if (this->indexVictimList >= this->getLength())
		this->indexVictimList = 0;

	ifstream fileRead(this->fileName);
	Victim victim;
	vector<Victim> victimVector;
	int currentIndex = 0;
	while (fileRead >> victim)
	{
		if (currentIndex == this->indexVictimList)
		{
			fileRead.close();
			return victim;
		}
		currentIndex++;
	}
}

int FileRepository::getLength()
{
	ifstream fileRead(this->fileName);
	Victim victim;
	vector<Victim> victimVector;
	int currentSize = 0;
	while (fileRead >> victim)
	{
		currentSize++;
	}
	fileRead.close();

	return currentSize;
}

void FileRepository::setFileLocation(const std::string& fileNameLocation)
{

	this->fileName = fileNameLocation;
	fstream file;
	file.open(this->fileName, ios::out);
	/*
	if (!file)
		cout << "Error in creating file!!!"<<endl;
	else
		cout << "File created successfully."<<endl;
	*/
	file.close();
}

string FileRepository::getFileLocation()
{
	return this->fileName;
}

std::vector<TElement> FileRepository::getAllVictims() const
{
	ifstream fileRead(this->fileName);
	Victim victim;
	vector<Victim> victimVector;

	while (fileRead >> victim)
	{
		victimVector.push_back(victim);
	}
	fileRead.close();

	return victimVector;
}
