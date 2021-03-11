#pragma once

#include "Victim.h"
#include "DynamicVector.h"
#include "Repository.h"
#include <vector>

class AssistantRepository
{
public:
	virtual ~AssistantRepository() {};
	virtual bool saveVictim(const Victim& victim) = 0;
	virtual void setFileLocation(const std::string& mylistNameLocation)=0;
	virtual std::string getFileLocation() = 0;
	virtual std::vector<TElement> getAllAssistantVictims() const=0;
};

