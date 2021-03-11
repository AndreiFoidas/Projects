#pragma once
#include <string>
#include "Victim.h"

class ValidationException: public std::exception
{
private:
	std::string errorMessage;

public:
	ValidationException(std::string message) : errorMessage{ message } {}

	const char* what() const throw() override
	{
		return this->errorMessage.c_str();
	}
};

class VictimValidator
{
public:
	static void validate(const Victim& victim);
};

