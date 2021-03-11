#include "Action.h"

void ActionAdd::executeUndo()
{
	this->repository.removeVictim(this->addedVictim.getName());
}

void ActionAdd::executeRedo()
{
	this->repository.addVictim(this->addedVictim);
}


void ActionRemove::executeUndo()
{
	this->repository.addVictim(this->removedVictim);
}

void ActionRemove::executeRedo()
{
	this->repository.removeVictim(this->removedVictim.getName());
}


void ActionUpdate::executeUndo()
{
	this->repository.updateVictim(this->oldVictim);
}

void ActionUpdate::executeRedo()
{
	this->repository.updateVictim(this->newVictim);
}
