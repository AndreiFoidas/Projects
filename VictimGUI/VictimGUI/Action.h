#pragma once

#include "Repository.h"
#include <memory>

class Action
{
protected:
	Repository& repository;
public:
	Action(Repository& _repository) :repository(_repository) {}
	virtual ~Action() {};
	virtual void executeUndo() = 0;
	virtual void executeRedo() = 0;
};

class ActionAdd :public Action
{
private:
	Victim addedVictim;
public:
	ActionAdd(Repository& _repository, Victim& _addedVictim) : Action{ _repository }, addedVictim{ _addedVictim } {}
	void executeUndo() override;
	void executeRedo() override;
};


class ActionRemove :public Action
{
private:
	Victim removedVictim;
public:
	ActionRemove(Repository& _repository, Victim& _removedVictim) : Action{ _repository }, removedVictim{ _removedVictim } {}
	void executeUndo() override;
	void executeRedo() override;
};

class ActionUpdate :public Action
{
private:
	Victim oldVictim;
	Victim newVictim;
public:
	ActionUpdate(Repository& _repository, Victim& _oldVictim, Victim& _newVictim) : Action{ _repository }, oldVictim{ _oldVictim }, newVictim{ _newVictim } {}
	void executeUndo() override;
	void executeRedo() override;
};