
#include "MemoryAssistantRepository.h"

bool MemoryAssistantRepository::saveVictim(const Victim& victim)
{
	std::string name = victim.getName();
	for (int i = 0; i < this->assistantVictimList.size(); ++i)
		if (this->assistantVictimList[i].getName() == name)
		{
			throw RepositoryException{ "ERROR: Victim was already added!" };
		}

	this->assistantVictimList.push_back(victim);

	return true;
}



std::vector<TElement> MemoryAssistantRepository::getAllAssistantVictims() const
{
	return this->assistantVictimList;
}

std::string MemoryAssistantRepository::getFileLocation()
{
	return "";
}