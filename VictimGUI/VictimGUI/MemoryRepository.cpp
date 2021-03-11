
#include "MemoryRepository.h"

MemoryRepository::MemoryRepository()
{
	this->indexVictimList = -1;
}

int MemoryRepository::getVictimIndexRepositoryByName(const std::string& name)
{
	int counter = 0;
	for (auto victim : victimList)
	{
		if (victim.getName() == name)
			return counter;
		counter++;
	}
	return -1;
}

bool MemoryRepository::addVictim(const Victim& victim)
{
	std::string name = victim.getName();
	if (this->getVictimIndexRepositoryByName(name) != -1)
		throw RepositoryException{ "ERROR: Duplicate victim!" };

	this->victimList.push_back(victim);
	return true;
}

bool MemoryRepository::removeVictim(const std::string& name)
{
	int index;
	index = this->getVictimIndexRepositoryByName(name);
	if (index == -1)
		throw RepositoryException{ "ERROR: Victim does not exist!" };

	this->victimList.erase(this->victimList.begin() + index);
	return true;
}

bool MemoryRepository::updateVictim(const Victim& victim)
{
	std::string name = victim.getName();
	int index;
	index = this->getVictimIndexRepositoryByName(name);
	if (index == -1)
		throw RepositoryException{ "ERROR: Victim does not exist!" };

	this->victimList[index] = victim;
	return true;
}

TElement MemoryRepository::next()
{
	this->indexVictimList++;
	if (this->indexVictimList >= this->victimList.size())
		this->indexVictimList = 0;

	return this->victimList[indexVictimList];
}

int MemoryRepository::getLength()
{
	return this->victimList.size();
}

std::vector<TElement> MemoryRepository::getAllVictims() const
{
	return this->victimList;
}
