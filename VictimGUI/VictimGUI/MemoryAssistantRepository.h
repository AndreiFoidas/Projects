#pragma once

#include "DynamicVector.h"
#include "AssistantRepository.h"
#include <vector>

class MemoryAssistantRepository:public AssistantRepository
{
private:
	std::vector<TElement> assistantVictimList;
public:
	//default constructor or initializes an object of type assistant repository
	MemoryAssistantRepository() {}

	bool saveVictim(const Victim& victim) override;

	void setFileLocation(const std::string& mylistNameLocation) {};

	std::vector<TElement> getAllAssistantVictims() const override;
	std::string getFileLocation() override;
};