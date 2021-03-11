#pragma once

#include <Vector>

using namespace std;

class Observer
{
public:
	virtual void update() = 0;
	virtual ~Observer() {}
};

class Subject
{
private:
	vector<Observer*> observersList;
public:
	void addObserver(Observer* observer)
	{
		this->observersList.push_back(observer);
	}
	void removeObserver(Observer* observer)
	{
		auto found = std::find(this->observersList.begin(), this->observersList.end(), observer);
		if (found != this->observersList.end())
			this->observersList.erase(found);
	}

	void notify()
	{
		for (auto observer : this->observersList)
		{
			observer->update();
		}
	}
};