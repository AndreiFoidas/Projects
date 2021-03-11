#pragma once

#include "Victim.h"

typedef Victim TElement;

#define DefaultCapacity 16

template <typename TemplateObject>
class DynamicVector
{
private:
	TemplateObject* elements;
	int size;
	int capacity;
public:
	//default constructor for a Dynamic Vector
	DynamicVector(int capacity = DefaultCapacity);

	//copy constructor
	DynamicVector(const DynamicVector& dynamicVector);

	//destructor
	~DynamicVector();

	//assignemnt operator for a Dynamic Vector
	DynamicVector& operator=(const DynamicVector& dynamicVector);

	//Adds an element to the current Dynamic Vector
	void add(const TemplateObject& element);

	//Removes an element from a given position (must be valid)
	void removeAtIndex(int index);

	//getter for size
	int getSize() const;

	//returns the element found at a given index (must be valid)
	TemplateObject getIndex(int index);

	//modifies the element from a given index
	void setAtIndex(int index, const TemplateObject& newElement);

private:
	//resizes the current Dynamic Vector, multiplying the capacity with a given factor (real number)
	void resize(double factor = 2);
};

template <typename TemplateObject>
DynamicVector<TemplateObject>::DynamicVector(int capacity)
{
	this->size = 0;
	this->capacity = capacity;
	this->elements = new TemplateObject[this->capacity];
}

template <typename TemplateObject>
DynamicVector<TemplateObject>::DynamicVector(const DynamicVector<TemplateObject>& dynamicVector)
{
	this->size = dynamicVector.size;
	this->capacity = dynamicVector.capacity;
	this->elements = new TemplateObject[this->capacity];
	for (int i = 0; i < this->size; ++i)
		this->elements[i] = dynamicVector.elements[i];
}

template <typename TemplateObject>
DynamicVector<TemplateObject>::~DynamicVector()
{
	delete[] this->elements;
}

template <typename TemplateObject>
DynamicVector<TemplateObject>& DynamicVector<TemplateObject>::operator=(const DynamicVector<TemplateObject>& dynamicVector)
{
	if (this == &dynamicVector)
		return *this;

	this->size = dynamicVector.size;
	this->capacity = dynamicVector.capacity;

	delete[] this->elements;
	this->elements = new TemplateObject[this->capacity];
	for (int i = 0; i < this->size; ++i)
		this->elements[i] = dynamicVector.elements[i];

	return *this;
}

template <typename TemplateObject>
void DynamicVector<TemplateObject>::add(const TemplateObject& element)
{
	if (this->size == this->capacity)
		this->resize();

	this->elements[this->size] = element;
	this->size++;
}

template <typename TemplateObject>
void DynamicVector<TemplateObject>::removeAtIndex(int index)
{
	for (int i = index; i < this->size - 1; ++i)
		this->elements[i] = this->elements[i + 1];

	this->size--;
}

template <typename TemplateObject>
void DynamicVector<TemplateObject>::resize(double factor)
{
	this->capacity = static_cast<int>(this->capacity * factor);

	TemplateObject* newResizedDynamicVector = new TemplateObject[this->capacity];
	for (int i = 0; i < this->size; i++)
		newResizedDynamicVector[i] = this->elements[i];

	delete[] this->elements;
	this->elements = newResizedDynamicVector;
}

template <typename TemplateObject>
int DynamicVector<TemplateObject>::getSize() const
{
	return this->size;
}

template <typename TemplateObject>
TemplateObject DynamicVector<TemplateObject>::getIndex(int index)
{
	return this->elements[index];
}

template <typename TemplateObject>
void DynamicVector<TemplateObject>::setAtIndex(int index, const TemplateObject& newElement)
{
	this->elements[index] = newElement;
}