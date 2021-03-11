#pragma once

#include "AssistantRepository.h"
#include <vector>
#include <sstream>
#include <iostream>
#include <fstream>

class HTMLAssistantRepository :public AssistantRepository
{
private:
	std::string HTMLfileName;
public:
	HTMLAssistantRepository() {};
	HTMLAssistantRepository(const std::string& HTMLfileName);

	bool saveVictim(const Victim& newVictim) override;

	void setFileLocation(const std::string& mylistNameLocation);

	std::vector<TElement> getAllAssistantVictims() const override;
	std::string getFileLocation() override;
};

