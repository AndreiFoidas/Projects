#pragma once

#include "Victim.h"
#include "DynamicVector.h"
#include <vector>

class RepositoryException :public std::exception 
{
private:
	std::string errorMessage;
public:
	RepositoryException(const std::string& message) : errorMessage{message} {}

	const char* what() const throw() override
	{
		return this->errorMessage.c_str();
	}
};

class Repository
{
public:
	virtual ~Repository() {};
	virtual bool addVictim(const Victim& victim) = 0;
	virtual bool removeVictim(const std::string& name)=0;
	virtual bool updateVictim(const Victim& victim)=0;
	virtual TElement next()=0;
	virtual int getLength()=0;
	virtual std::vector<TElement> getAllVictims() const=0;
	virtual void setFileLocation(const std::string& fileNameLocation)=0;
	virtual std::string getFileLocation() = 0;
};

