/*
#include <assert.h>
#include <iostream>
#include <string.h>
#include "Test.h"
#include "Victim.h"
#include "DynamicVector.h"
#include "Repository.h"
#include "MemoryRepository.h"
#include "Service.h"
#include "UI.h"
#include "MemoryAssistantRepository.h"
#include "FileRepository.h"

using namespace std;


Victim testCreateVictim()
{
	Victim victim = Victim("Andrei","Medias",20,"image.jpg");
	return victim;
}

void getName_ValidInput_ReturnsName()
{
	Victim victim = testCreateVictim();
	assert(victim.getName()=="Andrei");
}
void getPlaceOfOrigin_ValidInput_ReturnsPlaceOfOrigin()
{
	Victim victim = testCreateVictim();
	assert(victim.getPlaceOfOrigin() == "Medias");
}
void getAge_ValidInput_ReturnsAge()
{
	Victim victim = testCreateVictim();
	assert(victim.getAge() == 20);
}
void getPhotograph_ValidInput_ReturnsPhotograph()
{
	Victim victim = testCreateVictim();
	assert(victim.getPhotograph() == "image.jpg");
}
void setName_ValidInput_ModifiesName()
{
	Victim victim = testCreateVictim();
	victim.setName("Marius");
	assert(victim.getName() == "Marius");
}
void setPlaceOfOrigin_ValidInput_ModifiesPlaceOfOrigin()
{
	Victim victim = testCreateVictim();
	victim.setPlaceOfOrigin("Ighis");
	assert(victim.getPlaceOfOrigin() == "Ighis");
}
void setAge_ValidInput_ModifiesAge()
{
	Victim victim = testCreateVictim();
	victim.setAge(100);
	assert(victim.getAge() == 100);
}
void setPhotograph_ValidInput_ModifiesPhotograph()
{
	Victim victim = testCreateVictim();
	victim.setPhotograph("goryImage.jpg");
	assert(victim.getPhotograph() == "goryImage.jpg");
}
void toString_ValidInput_ConvertsToString()
{
	Victim victim = testCreateVictim();
	assert(victim.toString() == "Andrei Medias 20 image.jpg");
}

void testVictim()
{
	getName_ValidInput_ReturnsName();
	getPlaceOfOrigin_ValidInput_ReturnsPlaceOfOrigin();
	getAge_ValidInput_ReturnsAge();
	getPhotograph_ValidInput_ReturnsPhotograph();
	setName_ValidInput_ModifiesName();
	setPlaceOfOrigin_ValidInput_ModifiesPlaceOfOrigin();
	setAge_ValidInput_ModifiesAge();
	setPhotograph_ValidInput_ModifiesPhotograph();
	toString_ValidInput_ConvertsToString();
}

void add_ValidInput_AddedToDynamicVector()
{
	DynamicVector<TElement> vector = DynamicVector<TElement>(1);
	vector.add(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(vector.getIndex(0).getAge() == 20);
}
void getSize_TestSize_AddedToDynamicVector()
{
	DynamicVector<TElement> vector = DynamicVector<TElement>(1);
	vector.add(Victim());
	assert(vector.getSize() == 1);
}
void resize_CapacityReached_NoErrors()
{
	DynamicVector<TElement> vector = DynamicVector<TElement>(1);
	vector.add(Victim());
	vector.add(Victim());
	assert(vector.getSize() == 2);
}
void getIndex_ValidInput_AddedToDynamicVector()
{
	DynamicVector<TElement> vector = DynamicVector<TElement>(1);
	vector.add(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(vector.getIndex(0).getPhotograph() == "im.jpg");
}
void setAtIndex_ValidInput_ModifiedDynamicVector()
{
	DynamicVector<TElement> vector = DynamicVector<TElement>(1);
	vector.add(Victim("Andrei", "Medias", 20, "im.jpg"));
	vector.setAtIndex(0, Victim("Andrei", "Medias", 20, "im2.jpg"));
	assert(vector.getIndex(0).getPhotograph() == "im2.jpg");
}
void removeAtIndex_ValidInput_SmallerSize()
{
	DynamicVector<TElement> vector = DynamicVector<TElement>();
	vector.add(Victim("Andrei", "Medias", 20, "im.jpg"));
	vector.add(Victim("Andrei", "Medias", 20, "im.jpg"));
	vector.removeAtIndex(0);
	assert(vector.getSize() == 1);
}
void operatorEqual_SameVector_RetursSameVector()
{
	DynamicVector<TElement> vector = DynamicVector<TElement>();
	vector.add(Victim("Andrei", "Medias", 20, "im.jpg"));
	vector.add(Victim("Andrei", "Medias", 20, "im.jpg"));
	vector = vector;
	assert(vector.getSize() == 2);
}
void operatorEqual_ValidInput_OverloadVector()
{
	DynamicVector<TElement> vector1 = DynamicVector<TElement>();
	DynamicVector<TElement> vector2 = DynamicVector<TElement>();
	vector1.add(Victim("Andrei", "Medias", 20, "im.jpg"));
	vector1.add(Victim("Andrei", "Medias", 20, "im.jpg"));
	vector2 = vector1;
	assert(vector2.getSize() == 2);
}

void testDynamicVector()
{
	add_ValidInput_AddedToDynamicVector();
	getSize_TestSize_AddedToDynamicVector();
	resize_CapacityReached_NoErrors();
	getIndex_ValidInput_AddedToDynamicVector();
	setAtIndex_ValidInput_ModifiedDynamicVector();
	removeAtIndex_ValidInput_SmallerSize();
	operatorEqual_SameVector_RetursSameVector();
	operatorEqual_ValidInput_OverloadVector();
}

void getVictimIndexRepositoryByName_ValidInput_returnsValidIndex()
{
	MemoryRepository repository;
	std::string name = "Andrei";
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(repository.getVictimIndexRepositoryByName(name) == 0);
}
void getVictimIndexRepositoryByName_InvalidInput_returnsMinusOne()
{
	MemoryRepository repository;
	std::string name = "Matei";
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(repository.getVictimIndexRepositoryByName(name) == -1);
}
void addVictim_ValidInput_returnsTrue()
{
	MemoryRepository repository;
	assert(repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg")) == true);
}
void addVictim_ValidInput_addsToRepository()
{
	MemoryRepository repository;
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(repository.getLength() == 1);
}
void addVictim_DuplicateName_returnsFalse()
{
	MemoryRepository repository;
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(repository.addVictim(Victim("Andrei", "Medias2", 21, "im2.jpg")) == false);
}
void removeVictim_ValidInput_returnsTrue()
{
	MemoryRepository repository;
	std::string name = "Andrei";
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(repository.removeVictim(name) == true);
}
void removeVictim_ValidInput_removesFromRepository()
{
	MemoryRepository repository;
	std::string name = "Andrei";
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	repository.removeVictim(name);
	assert(repository.getLength() == 0);
}
void removeVictim_VictimDoesntExist_returnsFalse()
{
	MemoryRepository repository;
	std::string name = "Matei";
	assert(repository.removeVictim(name) == false);
}
void updateVictim_VictimDoesntExist_returnsFalse()
{
	MemoryRepository repository;
	assert(repository.updateVictim(Victim("Andrei", "Medias", 20, "im.jpg")) == false);
}
void updateVictim_ValidInput_returnsTrue()
{
	MemoryRepository repository;
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(repository.updateVictim(Victim("Andrei", "Ighis", 22, "im2.jpg")) == true);
}
void updateVictim_ValidInput_constantLength()
{
	MemoryRepository repository;
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	repository.updateVictim(Victim("Andrei", "Ighis", 22, "im2.jpg"));
	assert(repository.getLength() == 1);
}
void getLength_ValidInput_returnsLength()
{
	MemoryRepository repository;
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	repository.addVictim(Victim("Andrei2", "Medias", 20, "im.jpg"));
	repository.addVictim(Victim("Andrei3", "Medias", 20, "im.jpg"));
	assert(repository.getLength() == 3);
}

void getAllVictims_ValidInput_returnsAllVictims()
{
	Victim victim1 = Victim("Andrei", "Medias", 20, "im.jpg");
	Victim victim2 = Victim("Andrei2", "Medias2", 22, "im.jpg");
	std::vector<TElement> vector;
	MemoryRepository repository;
	repository.addVictim(victim1);
	repository.addVictim(victim2);
	vector = repository.getAllVictims();
	assert(vector.size() == 2);

}

void nextRepository_IndexSmallerThanLength_indexIncreases()
{
	Victim victim1 = Victim("Andrei", "Medias", 20, "im.jpg");
	Victim victim2 = Victim("Andrei2", "Medias2", 22, "im.jpg");
	DynamicVector<TElement> vector;
	MemoryRepository repository;
	repository.addVictim(victim1);
	repository.addVictim(victim2);
	repository.next();
	assert(repository.next().getName() == victim2.getName());
}

void nextRepository_IndexBiggerThanLength_indexBecomesZero()
{
	Victim victim1 = Victim("Andrei", "Medias", 20, "im.jpg");
	Victim victim2 = Victim("Andrei2", "Medias2", 22, "im.jpg");
	DynamicVector<TElement> vector;
	MemoryRepository repository;
	repository.addVictim(victim1);
	repository.addVictim(victim2);
	repository.next();
	repository.next();
	assert(repository.next().getName() == victim1.getName());
}
void setFileLocation_ValidInput_createsAFile()
{
	FileRepository repostory = FileRepository("Test.txt");
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repostory, assistantRepository);
	service.fileLocationName("NewTest.txt");
}

void testRepository()
{
	getVictimIndexRepositoryByName_ValidInput_returnsValidIndex();
	getVictimIndexRepositoryByName_InvalidInput_returnsMinusOne();
	addVictim_ValidInput_addsToRepository();
	addVictim_DuplicateName_returnsFalse();
	removeVictim_ValidInput_removesFromRepository();
	addVictim_ValidInput_returnsTrue();
	removeVictim_ValidInput_returnsTrue();
	removeVictim_VictimDoesntExist_returnsFalse();
	updateVictim_VictimDoesntExist_returnsFalse();
	updateVictim_ValidInput_returnsTrue();
	updateVictim_ValidInput_constantLength();
	getLength_ValidInput_returnsLength();
	getAllVictims_ValidInput_returnsAllVictims();
	nextRepository_IndexSmallerThanLength_indexIncreases();
	nextRepository_IndexBiggerThanLength_indexBecomesZero();
}

void addVictim_ValidInput_addsIntoFile()
{
	FileRepository repository("Test.txt");
	std::string name = "Andrei";
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(repository.getLength() == 1);
	repository.removeVictim(name);
}
void addVictim_DuplicateVictimFile_returnsFalse()
{
	FileRepository repository("Test.txt");
	std::string name = "Andrei";
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	assert(repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"))==false);
	repository.removeVictim(name);
}
void removeVictim_ValidInput_removesFromFile()
{
	FileRepository repository("Test.txt");
	std::string name = "Andrei";
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	repository.addVictim(Victim("Andrei2", "Medias", 20, "im.jpg"));
	repository.removeVictim(name);
	assert(repository.getLength() == 1);
	repository.removeVictim("Andrei2");
}
void removeVictim_VictimDoesNotExistInFile_returnsFalse()
{
	FileRepository repository("Test.txt");
	std::string name = "Andrei";
	assert(repository.removeVictim(name) == false);
}
void updateVictim_ValidIput_updatesInFile()
{
	FileRepository repository("Test.txt");
	std::string name = "Andrei";
	Victim newVictim = Victim("Andrei", "Ighis",13, "asdas");
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	repository.addVictim(Victim("Andrei2", "Medias", 20, "im.jpg"));
	repository.updateVictim(newVictim);
	vector<TElement> victimVector = repository.getAllVictims();
	assert(victimVector[0].getPlaceOfOrigin() == "Ighis");
	repository.removeVictim("Andrei2");
	repository.removeVictim("Andrei");
}
void updateVictim_VictimDoesNotExistInFile_returnsFalse()
{
	FileRepository repository("Test.txt");
	std::string name = "Andrei";
	Victim newVictim = Victim("Andrei", "Ighis", 13, "asdas");
	repository.addVictim(Victim("Andrei2", "Medias", 20, "im.jpg"));
	assert(repository.updateVictim(newVictim)==false);
	repository.removeVictim("Andrei2");
}
void next_ValidInput_returnsVictim()
{
	FileRepository repository("Test.txt");
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	repository.addVictim(Victim("Andrei2", "Medias", 20, "im.jpg"));
	repository.next();
	repository.next();
	assert(repository.next().getName()=="Andrei");
	repository.removeVictim("Andrei");
	repository.removeVictim("Andrei2");
}
void getLength_ValidInput_returnsLengthOfFile()
{
	FileRepository repository("Test.txt");
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	repository.addVictim(Victim("Andrei2", "Medias", 20, "im.jpg"));
	assert(repository.getLength() == 2);
	repository.removeVictim("Andrei");
	repository.removeVictim("Andrei2");
}
void getAllVictims_ValidInputFromFile_returnsVector()
{
	FileRepository repository("Test.txt");
	repository.addVictim(Victim("Andrei", "Medias", 20, "im.jpg"));
	repository.addVictim(Victim("Andrei2", "Medias", 20, "im.jpg"));
	vector<TElement> vectorVictim = repository.getAllVictims();
	assert(vectorVictim.size() == 2);
	repository.removeVictim("Andrei");
	repository.removeVictim("Andrei2");
}



void testFileRepository()
{
	addVictim_ValidInput_addsIntoFile();
	addVictim_DuplicateVictimFile_returnsFalse();
	removeVictim_ValidInput_removesFromFile();
	removeVictim_VictimDoesNotExistInFile_returnsFalse();
	updateVictim_ValidIput_updatesInFile();
	updateVictim_VictimDoesNotExistInFile_returnsFalse();
	next_ValidInput_returnsVictim();
	getLength_ValidInput_returnsLengthOfFile();
	getAllVictims_ValidInputFromFile_returnsVector();
	setFileLocation_ValidInput_createsAFile();
}

void addVictimService_ValidInput_returnsTrue()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	assert(service.addVictim("Andrei","Medias","23","image.jpg")==true);
}
void addVictimService_InvalidInput_returnsFalse()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	assert(service.addVictim("Andrei", "Ighis", "12o", "cut.jpg") == false);
}
void updateVictimService_ValidInput_returnsTrue()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	service.addVictim("Andrei", "Ighis", "13", "cut.jpg");
	assert(service.updateVictim("Andrei", "Medias", "124", "cut3.jpg") == true);
}
void updateVictimService_InvalidInput_returnsFalse()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	service.addVictim("Andrei", "Ighis", "13", "cut.jpg");
	assert(service.updateVictim("Andrei", "Medias", "eeq", "cut3.jpg") == false);
}
void removeVictimService_ValidInput_returnsTrue()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	service.addVictim("Andrei", "Ighis", "13", "cut.jpg");
	assert(service.removeVictim("Andrei") == true);
}
void addVictimService_DuplicateVictim_returnsFalse()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	service.addVictim("Andrei", "Ighis", "12", "cut.jpg");
	assert(service.addVictim("Andrei", "Ighis2", "12", "cut.jpg") == false);
}
void updateVictimService_victimDoesntExist_returnsFalse()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	assert(service.updateVictim("Andrei", "Medias", "12", "cut3.jpg") == false);
}
void removeVictimService_VictimDoesntExist_returnsFalse()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	assert(service.removeVictim("Andrei") == false);
}
void getAllVictimsService_testLength_returnsDynamicVecorLength()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	service.addVictim("Andrei", "Ighis", "12", "cut.jpg");
	service.addVictim("Andrei2", "Ighis", "12", "cut.jpg");
	service.addVictim("Andrei3", "Ighis", "12", "cut.jpg");
	std::vector<TElement> victimVector = service.getAllVictims();
	assert(victimVector.size() == 3);
}

void saveVictimService_validInput_retursTrue()
{
	MemoryRepository repostory = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repostory,assistantRepository);
	service.addVictim("Andrei", "Medias", "23", "image.jpg");
	assert(service.saveVictim("Andrei") == true);
}

void saveVictimService_notExistingVictim_retursFalse()
{
	MemoryRepository repostory = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repostory, assistantRepository);
	service.addVictim("Andrei", "Medias", "23", "image.jpg");
	assert(service.saveVictim("Raul") == false);
}

void nextService_ValidInput_reutrnsVictim()
{
	MemoryRepository repostory = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repostory, assistantRepository);
	service.addVictim("Andrei", "Medias", "23", "image.jpg");
	assert(service.next().getName() == "Andrei");
}
void getAllAssistantsVictimsService_testLength_returnsDynamicVecorLength()
{
	MemoryRepository repository = MemoryRepository();
	MemoryAssistantRepository assistantRepository = MemoryAssistantRepository();
	Service service = Service(repository, assistantRepository);
	Victim victim1 = Victim("Andrei", "Medias", 20, "ad");
	Victim victim2 = Victim("Andre2", "Medias", 20, "ad");
	assistantRepository.saveVictim(victim1);
	assistantRepository.saveVictim(victim2);
	std::vector<TElement> victimVector = service.getAllAssistantVictims();
	assert(victimVector.size() == 2);
}

void testService()
{
	addVictimService_ValidInput_returnsTrue();
	addVictimService_InvalidInput_returnsFalse();
	updateVictimService_ValidInput_returnsTrue();
	updateVictimService_InvalidInput_returnsFalse();
	removeVictimService_ValidInput_returnsTrue();
	addVictimService_DuplicateVictim_returnsFalse();
	updateVictimService_victimDoesntExist_returnsFalse();
	removeVictimService_VictimDoesntExist_returnsFalse();
	getAllVictimsService_testLength_returnsDynamicVecorLength();
	saveVictimService_validInput_retursTrue();
	saveVictimService_notExistingVictim_retursFalse();
	nextService_ValidInput_reutrnsVictim();
	getAllAssistantsVictimsService_testLength_returnsDynamicVecorLength();
}

void saveVictim_ValidInput_victimIsSaved()
{
	Victim victim = Victim("Andrei", "Medias", 20, "im.jpg");
	std::vector<TElement> vector;
	MemoryAssistantRepository assistantRepository;
	assistantRepository.saveVictim(victim);
	vector = assistantRepository.getAllAssistantVictims();
	assert(vector.size() == 1);
}

void saveVictim_DuplicateVictim_victimIsNotSaved()
{
	Victim victim1 = Victim("Andrei", "Medias", 20, "im.jpg");
	Victim victim2 = Victim("Andrei", "Medias", 20, "im.jpg");
	std::vector<TElement> vector;
	MemoryAssistantRepository assistantRepository;
	assistantRepository.saveVictim(victim1);
	assistantRepository.saveVictim(victim2);
	vector = assistantRepository.getAllAssistantVictims();
	assert(vector.size() == 1);
}

void saveVictim_ValidInput_returnsTrue()
{
	Victim victim = Victim("Andrei", "Medias", 20, "im.jpg");
	DynamicVector<TElement> vector;
	MemoryAssistantRepository assistantRepository;
	assert(assistantRepository.saveVictim(victim) == true);
}

void saveVictim_DuplicateVictim_returnsFalse()
{
	Victim victim1 = Victim("Andrei", "Medias", 20, "im.jpg");
	Victim victim2 = Victim("Andrei", "Medias", 20, "im.jpg");
	DynamicVector<TElement> vector;
	MemoryAssistantRepository assistantRepository;
	assistantRepository.saveVictim(victim1);
	assert(assistantRepository.saveVictim(victim2) == false);
}

void testAssistantRepository()
{
	saveVictim_ValidInput_victimIsSaved();
	saveVictim_DuplicateVictim_victimIsNotSaved();
	saveVictim_ValidInput_returnsTrue();
	saveVictim_DuplicateVictim_returnsFalse();
}
*/