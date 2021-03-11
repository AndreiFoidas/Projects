#pragma once

#include "AssistantRepository.h"
#include <vector>
#include <sstream>
#include <iostream>
#include <fstream>

class CSVAssistantRepository:public AssistantRepository
{
private:
	std::string CVSfileName;
public:
	CSVAssistantRepository() {};
	CSVAssistantRepository(const std::string& CVSfileName);

	bool saveVictim(const Victim& newVictim) override;

	void setFileLocation(const std::string& mylistNameLocation);

	std::vector<TElement> getAllAssistantVictims() const override;
	std::string getFileLocation() override;
};

