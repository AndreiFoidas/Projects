
#include "HTMLAssistantRepository.h"
#include <sstream>
#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <exception>
#include <regex> 

using namespace std;

#define SKIP_HTML_BEGINNING 177
#define SKIP_HTML_ENDING 22
#define TABLE_ELEMENT_LENGTH 4
#define TABLE_ELEMENT_ENDING_LENGTH 9

HTMLAssistantRepository::HTMLAssistantRepository(const std::string& HTMLfileName)
{
	this->HTMLfileName = HTMLfileName;
}

std::vector<TElement> readFromHTML(std::string& fileContents)
{
	std::vector<Victim> victimVector;
	int indexBeginning = 0;
	indexBeginning += SKIP_HTML_BEGINNING;
	int indexEnding = fileContents.length();
	indexEnding -= SKIP_HTML_ENDING;
	int size;
	size = indexEnding - indexBeginning;
	if (size < 0)
		return victimVector;
	std::string tableRow = fileContents.substr(indexBeginning, size);
	//cout << stringg;
	std::string name, placeOfOrigin, age, photograph;
	int intAge;

	regex pattern("(<td>)(.*)(</td>)");
	indexBeginning = 0;
	int i = 0;
	while (true)
	{
		indexBeginning += TABLE_ELEMENT_LENGTH;
		i = 0;
		while (true)
		{
			if (regex_match(tableRow.begin() + indexBeginning, tableRow.begin() + indexBeginning + i, pattern))
			{
				name = tableRow.substr(indexBeginning + TABLE_ELEMENT_LENGTH, i - TABLE_ELEMENT_ENDING_LENGTH);
				indexBeginning += i;
				i = 0;
				break;
			}
			i++;
		}
		while (true)
		{
			if (regex_match(tableRow.begin() + indexBeginning, tableRow.begin() + indexBeginning + i, pattern))
			{
				placeOfOrigin = tableRow.substr(indexBeginning + TABLE_ELEMENT_LENGTH, i - TABLE_ELEMENT_ENDING_LENGTH);
				indexBeginning += i;
				i = 0;
				break;
			}
			i++;
		}
		while (true)
		{
			if (regex_match(tableRow.begin() + indexBeginning, tableRow.begin() + indexBeginning + i, pattern))
			{
				age = tableRow.substr(indexBeginning + TABLE_ELEMENT_LENGTH, i - TABLE_ELEMENT_ENDING_LENGTH);
				intAge = stoi(age);
				indexBeginning += i;
				i = 0;
				break;
			}
			i++;
		}
		while (true)
		{
			if (regex_match(tableRow.begin() + indexBeginning, tableRow.begin() + indexBeginning + i, pattern))
			{
				photograph = tableRow.substr(indexBeginning + TABLE_ELEMENT_LENGTH, i - TABLE_ELEMENT_ENDING_LENGTH);
				indexBeginning += i;
				i = 0;
				break;
			}
			i++;
		}
		victimVector.push_back(Victim(name, placeOfOrigin, intAge, photograph));
		//cout << Victim(name, placeOfOrigin, intAge, photograph);
		indexBeginning+= TABLE_ELEMENT_LENGTH+1;
		if (indexBeginning == tableRow.length())
			break;
	}

	return victimVector;
}

bool HTMLAssistantRepository::saveVictim(const Victim& newVictim)
{
	std::vector<Victim> victimVector;
	std::string name = newVictim.getName();

	ifstream fileRead(this->HTMLfileName);
	std::string fileContents;
	std::getline(fileRead, fileContents);
	victimVector = readFromHTML(fileContents);
	Victim victim;
	fileRead.close();

	bool result = false;
	for (auto& victimInVector : victimVector)
	{
		if (name == victimInVector.getName())
		{
			result = true;
			break;
		}

	}
	if (result == false)
		victimVector.push_back(newVictim);

	ofstream fileWrite(this->HTMLfileName, ios::out);

	fileWrite << "<!DOCTYPE html><html><head><title>Victim Assistant Repository</title></head><body>";
	fileWrite << "<table border=\"1\"><tr><td>Name</td><td>Place Of Origin</td><td>Age</td><td>Photograph</td></tr>";
	for (auto& victimInVector : victimVector)
	{
		fileWrite << "<tr><td>" << victimInVector.getName() << "</td>" << "<td>" << victimInVector.getPlaceOfOrigin() << "</td>";
		fileWrite << "<td>" << victimInVector.getAge() << "</td>"<<"<td>" << victimInVector.getPhotograph() << "</td></tr>";
	}
	fileWrite << "</table></body></html>";
	fileWrite.close();

	if(result==true)
		throw RepositoryException{ "ERROR: Duplicate victim!" };
	return true;
}

void HTMLAssistantRepository::setFileLocation(const std::string& mylistNameLocation)
{

	this->HTMLfileName = mylistNameLocation;
	fstream file;
	file.open(this->HTMLfileName, ios::out);
	file.close();
}

std::vector<TElement> HTMLAssistantRepository::getAllAssistantVictims() const
{
	std::vector<Victim> victimVector;
	ifstream fileRead(this->HTMLfileName);
	std::string fileContents;
	std::getline(fileRead, fileContents);
	victimVector = readFromHTML(fileContents);

	fileRead.close();

	return victimVector;
}

std::string HTMLAssistantRepository::getFileLocation()
{
	return this->HTMLfileName;
}