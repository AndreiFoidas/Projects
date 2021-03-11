
#include "Victim.h"

using namespace std;

Victim::Victim() : name(""), placeOfOrigin(""), age(0), photograph("") {}

Victim::Victim(const std::string& name, const std::string& placeOfOrigin, const int age, const std::string& photograph)
{
	this->name = name;
	this->placeOfOrigin = placeOfOrigin;
	this->age = age;
	this->photograph = photograph;
}

std::string Victim::getName() const
{
	return this->name;
}
std::string Victim::getPlaceOfOrigin() const
{
	return this->placeOfOrigin;
}
int Victim::getAge() const
{
	return this->age;
}
std::string Victim::getPhotograph() const
{
	return this->photograph;
}

void Victim::setName(const std::string& name)
{
	this->name=name;
}
void Victim::setPlaceOfOrigin(const std::string& placeOfOrigin)
{
	this->placeOfOrigin=placeOfOrigin;
}
void Victim::setAge(const int age)
{
	this->age=age;
}
void Victim::setPhotograph(const std::string& photograph)
{
	this->photograph=photograph;
}

std::string Victim::toString()
{
	return this->getName() + " " + this->getPlaceOfOrigin() + " " + std::to_string(this->getAge()) + " " + this->getPhotograph();
}

std::vector<string> separateVictimInput(string line, char delimiter)
{
	vector<string> result;
	stringstream stringStream(line);
	string token;

	while (getline(stringStream, token, delimiter))
		result.push_back(token);

	return result;
}

std::istream& operator>>(std::istream& inputStream, Victim& victim)
{
	std::string line;
	getline(inputStream, line);
	vector<string> victimAttributes = separateVictimInput(line, ',');
	if (victimAttributes.size() != 4)
		return inputStream;

	victim.setName(victimAttributes[0]);
	victim.setPlaceOfOrigin(victimAttributes[1]);
	victim.setPhotograph(victimAttributes[3]);

	victim.setAge(stoi(victimAttributes[2]));

	return inputStream;
}

std::ostream& operator<<(std::ostream& outputStream, const Victim& victim)
{
	outputStream << victim.getName() << "," << victim.getPlaceOfOrigin() << "," << victim.getAge() << "," << victim.getPhotograph() << "\n";

	return outputStream;
}