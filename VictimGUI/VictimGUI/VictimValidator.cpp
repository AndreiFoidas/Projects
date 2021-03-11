
#include "VictimValidator.h"

using namespace std;

void VictimValidator::validate(const Victim& victim)
{
	string errors;
	if (victim.getName() == "")
		errors += "The name should not be empty\n";
	if (victim.getPlaceOfOrigin() == "")
		errors += "The place of origin should not be empty\n";
	if (victim.getAge()<0)
		errors += "The age shold be a positive integer\n";
	if (victim.getPhotograph() == "")
		errors += "The photograph should not be empty\n";

	if (errors.size() > 0)
		throw ValidationException(errors);
}