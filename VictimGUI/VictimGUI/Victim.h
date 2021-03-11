#pragma once

#include <iostream>
#include <sstream>
#include <iostream>
#include <fstream>
#include <vector>
#include <string>

class Victim
{
private:
	std::string name;
	std::string placeOfOrigin;
	int age;
	std::string photograph;
public:

	//default constructor
	Victim();

	//constructor with parameters
	Victim(const std::string& name, const std::string& placeOfOrigin, const int age, const std::string& photograph);

	//getters
	std::string getName() const;

	std::string getPlaceOfOrigin() const;

	int getAge() const;

	std::string getPhotograph() const;

	//setters
	void setName(const std::string& name);

	void setPlaceOfOrigin(const std::string& placeOfOrigin);

	void setAge(const int age);

	void setPhotograph(const std::string& photograph);

	//converts the info of a victim into a string (mainly for printing)
	std::string toString();

	friend std::istream& operator>>(std::istream& inputStream, Victim& victim);
	friend std::ostream& operator<<(std::ostream& outputStream, const Victim& victim);
};