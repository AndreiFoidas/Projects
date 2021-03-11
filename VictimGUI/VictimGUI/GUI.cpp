#include "GUI.h"
#include <qlayout.h>
#include <qformlayout.h>
#include <qgridlayout.h>
#include <qmessagebox.h>

using namespace std;

GUI::GUI(Service& _service) : service{_service}
{
	this->initialiseGUI();
	this->populateList();
	this->connectSignalsAndSlots();
}

void GUI::initialiseGUI()
{
	this->victimListWidget = new QListWidget{};

	this->nameLineEdit = new QLineEdit{};
	this->placeOfOriginLineEdit = new QLineEdit{};
	this->ageLineEdit = new QLineEdit{};
	this->photographLineEdit = new QLineEdit{};
	this->fileNameLineEdit = new QLineEdit{};

	this->addButton = new QPushButton{ "Add" };
	this->deleteButton = new QPushButton{ "Delete" };
	this->updateButton = new QPushButton{ "Update" };
	this->setFileButton = new QPushButton{ "Set file" };

	QVBoxLayout* mainLayout = new QVBoxLayout{ this };

	QHBoxLayout* fileLayout = new QHBoxLayout{};

	QFormLayout* fileDetailLayout = new QFormLayout{};
	fileDetailLayout->addRow("File", this->fileNameLineEdit);

	fileLayout->addLayout(fileDetailLayout);
	fileLayout->addWidget(this->setFileButton);

	mainLayout->addLayout(fileLayout);

	mainLayout->addWidget(this->victimListWidget);

	QFormLayout* victimDetailLayout = new QFormLayout{};
	victimDetailLayout->addRow("Name", this->nameLineEdit);
	victimDetailLayout->addRow("Place of origin", this->placeOfOriginLineEdit);
	victimDetailLayout->addRow("Age", this->ageLineEdit);
	victimDetailLayout->addRow("Photograph", this->photographLineEdit);

	mainLayout->addLayout(victimDetailLayout);

	QGridLayout* buttonsLayout = new QGridLayout{};

	buttonsLayout->addWidget(this->addButton, 0, 0);
	buttonsLayout->addWidget(this->deleteButton, 0, 1);
	buttonsLayout->addWidget(this->updateButton, 0, 2);

	mainLayout->addLayout(buttonsLayout);


	QPalette* palette = new QPalette{};

	QColor stale = QColor(98, 109, 113);
	QColor latte = QColor(221, 188, 149);
	QColor ceramic = QColor(205, 205, 192);
	QColor black = QColor(0, 0, 0);

	palette->setColor(QPalette::Window, stale);
	palette->setColor(QPalette::Base, ceramic);
	palette->setColor(QPalette::Text, black);
	palette->setColor(QPalette::Button, latte);
	palette->setColor(QPalette::WindowText, black);
	palette->setColor(QPalette::ButtonText, black);

	this->nameLineEdit->setPalette(*palette);
	this->placeOfOriginLineEdit->setPalette(*palette);
	this->ageLineEdit->setPalette(*palette);
	this->photographLineEdit->setPalette(*palette);
	this->fileNameLineEdit->setPalette(*palette);
	
	this->addButton->setAutoFillBackground(true);
	this->deleteButton->setAutoFillBackground(true);
	this->updateButton->setAutoFillBackground(true);
	this->setFileButton->setAutoFillBackground(true);
	this->addButton->setPalette(*palette);
	this->deleteButton->setPalette(*palette);
	this->updateButton->setPalette(*palette);
	this->setFileButton->setPalette(*palette);

	setPalette(*palette);

	QFont font("Times New Roman", 13);

	this->victimListWidget->setFont(font);
	this->nameLineEdit->setFont(font);
	this->placeOfOriginLineEdit->setFont(font);
	this->photographLineEdit->setFont(font);
	this->fileNameLineEdit->setFont(font);
	this->ageLineEdit->setFont(font);
	this->addButton->setFont(font);
	this->deleteButton->setFont(font);
	this->updateButton->setFont(font);
	this->setFileButton->setFont(font);
	
}

void GUI::populateList()
{
	this->victimListWidget->clear();
	vector<Victim> allVictims = this->service.getAllVictims();
	for (auto victimInVector : allVictims)
		this->victimListWidget->addItem(QString::fromStdString(victimInVector.toString()));

	this->fileNameLineEdit->setText(QString::fromStdString(this->service.getFileLocationName()));
}

void GUI::connectSignalsAndSlots()
{
	QObject::connect(this->victimListWidget, &QListWidget::itemSelectionChanged, [this]() {
		int selectedIndex = this->getSelectedIndex();
		if (selectedIndex < 0)
			return;
		Victim victim = this->service.getAllVictims()[selectedIndex];
		this->nameLineEdit->setText(QString::fromStdString(victim.getName()));
		this->placeOfOriginLineEdit->setText(QString::fromStdString(victim.getPlaceOfOrigin()));
		this->ageLineEdit->setText(QString::fromStdString(to_string((victim.getAge()))));
		this->photographLineEdit->setText(QString::fromStdString(victim.getPhotograph()));
		});

	QObject::connect(this->addButton, &QPushButton::clicked, this, &GUI::addVictim);

	QObject::connect(this->deleteButton, &QPushButton::clicked, this, &GUI::deleteVictim);

	QObject::connect(this->updateButton, &QPushButton::clicked, this, &GUI::updateVictim);

	QObject::connect(this->setFileButton, &QPushButton::clicked, this, &GUI::setFile);
}

int GUI::getSelectedIndex() const
{
	QModelIndexList selectedIndexes = this->victimListWidget->selectionModel()->selectedIndexes();
	if (selectedIndexes.size() == 0)
	{
		this->nameLineEdit->clear();
		this->placeOfOriginLineEdit->clear();
		this->ageLineEdit->clear();
		this->photographLineEdit->clear();

		return -1;
	}
	
	int selectedIndex = selectedIndexes.at(0).row();
	return selectedIndex;
}

void GUI::addVictim()
{
	string name, placeOfOrigin, age, photograph;
	name = this->nameLineEdit->text().toStdString();
	placeOfOrigin = this->placeOfOriginLineEdit->text().toStdString();
	age = this->ageLineEdit->text().toStdString();
	photograph = this->photographLineEdit->text().toStdString();

	try
	{
		this->service.addVictim(name, placeOfOrigin, age, photograph);
	}
	catch (RepositoryException error)
	{
		QMessageBox::critical(this, "Error", error.what());
		return;
	}
	catch (ServiceException error)
	{
		QMessageBox::critical(this, "Error", error.what());
		return;
	}
	catch (ValidationException error)
	{
		QMessageBox::critical(this, "Error", error.what());
		return;
	}

	this->populateList();

	int lastElementIndex = this->service.getAllVictims().size() - 1;
	this->victimListWidget->setCurrentRow(lastElementIndex);
}

void GUI::deleteVictim()
{
	int selectedIndex = this->getSelectedIndex();
	if (selectedIndex < 0)
	{
		QMessageBox::critical(this, "Error", "ERROR: No victim was selected!");
		return;
	}

	Victim victim = this->service.getAllVictims()[selectedIndex];
	try
	{
		this->service.removeVictim(victim.getName());
	}
	catch (RepositoryException error)
	{
		QMessageBox::critical(this, "Error", error.what());
		return;
	}
	catch (ServiceException error)
	{
		QMessageBox::critical(this, "Error", error.what());
		return;
	}
	catch (ValidationException error)
	{
		QMessageBox::critical(this, "Error", error.what());
		return;
	}

	this->populateList();
}

void GUI::updateVictim()
{
	string name, placeOfOrigin, age, photograph;
	name = this->nameLineEdit->text().toStdString();
	placeOfOrigin = this->placeOfOriginLineEdit->text().toStdString();
	age = this->ageLineEdit->text().toStdString();
	photograph = this->photographLineEdit->text().toStdString();

	try
	{
		this->service.updateVictim(name, placeOfOrigin, age, photograph);
	}
	catch (RepositoryException error)
	{
		QMessageBox::critical(this, "Error", error.what());
		return;
	}
	catch (ServiceException error)
	{
		QMessageBox::critical(this, "Error", error.what());
		return;
	}
	catch (ValidationException error)
	{
		QMessageBox::critical(this, "Error", error.what());
		return;
	}

	this->populateList();

	int elementIndex, counter;
	auto victimVector = this->service.getAllVictims();
	counter = 0;
	for (auto victimInVector : victimVector)
	{
		if (victimInVector.getName() == name)
			break;
		counter++;
	}
	elementIndex = counter;
	this->victimListWidget->setCurrentRow(elementIndex);
}

void GUI::setFile()
{
	string fileName;
	fileName = this->fileNameLineEdit->text().toStdString();
	this->service.fileLocationName(fileName);

	this->populateList();
}