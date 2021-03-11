#pragma once

#include "Victim.h"
#include "Repository.h"
#include "DynamicVector.h"
#include <vector>

class MemoryRepository: public Repository
{
private:
	std::vector<TElement> victimList;
	//DynamicVector<TElement> victimList;
	int indexVictimList;
public:

	//default constructor or initializes an object of type repository
	MemoryRepository();

	/*
		Searches for a victim from the repository by name.
		Input: name (std::string)
		Output: - "i" index where it was found (int)
				- -1 if the victim does not exist
	*/
	int getVictimIndexRepositoryByName(const std::string& name);

	/*
		Adds a victim to the repository.
		Input: victim (Victim)
		Output: - true if it was succesful
				- false if the victim already exists
	*/
	bool addVictim(const Victim& victim) override;

	/*
		Removes a victim by name from the repository.
		Input: name (std::string)
		Output: - true if it was succesful
				- false if the victim does not exist
	*/
	bool removeVictim(const std::string& name) override;

	/*
		Updates the data of a victim from the repository.
		Input: victim (Victim)
		Output: - true if it was succesful
				- false if the victim does not exist
	*/
	bool updateVictim(const Victim& victim) override;

	TElement next() override;

	//returns the current length of the repository
	int getLength() override;

	void setFileLocation(const std::string& fileNameLocation)  override {};
	std::string getFileLocation() override { return ""; };

	//returns a Dynamic Vector containing all the victims
	std::vector<TElement> getAllVictims() const override;
};