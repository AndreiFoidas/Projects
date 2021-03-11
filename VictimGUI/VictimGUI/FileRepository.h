#pragma once

#include "Victim.h"
#include "Repository.h"
#include <sstream>
#include <iostream>
#include <fstream>
#include <vector>
#include <string>

typedef Victim TElement;

class FileRepository: public Repository
{
private:
	int indexVictimList;
	std::string fileName;
public:
	//default constructor or initializes an object of type FileRepository
	FileRepository();
	FileRepository(const std::string& fileName);

	bool addVictim(const Victim& victim) override;

	bool removeVictim(const std::string& name) override;

	bool updateVictim(const Victim& newVictim) override;

	TElement next() override;

	int getLength() override;

	void setFileLocation(const std::string& fileNameLocation) override;

	std::string getFileLocation() override;

	std::vector<TElement> getAllVictims() const override;
};