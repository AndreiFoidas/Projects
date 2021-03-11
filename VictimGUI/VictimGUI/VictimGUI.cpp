#include "VictimGUI.h"
#include "qmessagebox.h"
#include <windows.h>
#include <qshortcut>

using namespace std;

VictimGUI::VictimGUI(Service& _service, QWidget *parent)
	: QMainWindow(parent), service{_service}
{
	ui.setupUi(this);
	
	this->setColor();
	this->connectSignalsAndSlots();
	this->populateList();

}

void VictimGUI::setColor()
{
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

	this->ui.modeTabWidget->setPalette(*palette);
	this->ui.tabModeA->setPalette(*palette);
	this->ui.centralWidget->setPalette(*palette);

	this->ui.nameLineEdit->setPalette(*palette);
	this->ui.placeOfOriginLineEdit->setPalette(*palette);
	this->ui.ageLineEdit->setPalette(*palette);
	this->ui.photographLineEdit->setPalette(*palette);
	this->ui.filterLineEditAge->setPalette(*palette);
	this->ui.filterLineEditPlaceOfOtigin->setPalette(*palette);
	this->ui.nextLineEdit->setPalette(*palette);

	this->ui.addButton->setAutoFillBackground(true);
	this->ui.deleteButton->setAutoFillBackground(true);
	this->ui.updateButton->setAutoFillBackground(true);
	this->ui.saveButton->setAutoFillBackground(true);
	this->ui.nextButton->setAutoFillBackground(true);
	this->ui.filterButton->setAutoFillBackground(true);
	this->ui.openButton->setAutoFillBackground(true);
	this->ui.undoButton->setAutoFillBackground(true);
	this->ui.redoButton->setAutoFillBackground(true);
	this->ui.openWidgetButton->setAutoFillBackground(true);
	this->ui.addButton->setPalette(*palette);
	this->ui.deleteButton->setPalette(*palette);
	this->ui.updateButton->setPalette(*palette);
	this->ui.saveButton->setPalette(*palette);
	this->ui.openButton->setPalette(*palette);
	this->ui.nextButton->setPalette(*palette);
	this->ui.filterButton->setPalette(*palette);
	this->ui.undoButton->setPalette(*palette);
	this->ui.redoButton->setPalette(*palette);
	this->ui.openWidgetButton->setPalette(*palette);

	setPalette(*palette);
}

void VictimGUI::connectSignalsAndSlots()
{
	QObject::connect(this->ui.victimListWidget, &QListWidget::itemSelectionChanged, [this]() {
		int selectedIndex = this->getSelectedIndex();
		if (selectedIndex < 0)
			return;
		Victim victim = this->service.getAllVictims()[selectedIndex];
		this->ui.nameLineEdit->setText(QString::fromStdString(victim.getName()));
		this->ui.placeOfOriginLineEdit->setText(QString::fromStdString(victim.getPlaceOfOrigin()));
		this->ui.ageLineEdit->setText(QString::fromStdString(to_string((victim.getAge()))));
		this->ui.photographLineEdit->setText(QString::fromStdString(victim.getPhotograph()));
		});

	QObject::connect(this->ui.deleteButton, &QPushButton::clicked, this, &VictimGUI::deleteVictim);
	QObject::connect(this->ui.updateButton, &QPushButton::clicked, this, &VictimGUI::updateVictim);
	QObject::connect(this->ui.nextButton, &QPushButton::clicked, this, &VictimGUI::next);
	QObject::connect(this->ui.saveButton, &QPushButton::clicked, this, &VictimGUI::saveVictim);
	QObject::connect(this->ui.openButton, &QPushButton::clicked, this, &VictimGUI::openFile);
	QObject::connect(this->ui.filterButton, &QPushButton::clicked, this, &VictimGUI::filterList);
	QObject::connect(this->ui.undoButton, &QPushButton::clicked, this, &VictimGUI::undo);
	QObject::connect(this->ui.redoButton, &QPushButton::clicked, this, &VictimGUI::redo);
	QObject::connect(this->ui.openWidgetButton, &QPushButton::clicked, this, &VictimGUI::openModeBWidget);

	QShortcut* shortcutUndo = new QShortcut(QKeySequence(Qt::CTRL + Qt::Key_Z), this);
	QObject::connect(shortcutUndo, &QShortcut::activated, this, &VictimGUI::undo);
	QShortcut* shortcutRedo = new QShortcut(QKeySequence(Qt::CTRL + Qt::Key_Y), this);
	QObject::connect(shortcutRedo, &QShortcut::activated, this, &VictimGUI::redo);
}

void VictimGUI::populateList()
{
	this->ui.victimListWidget->clear();

	vector<Victim> allVictims = this->service.getAllVictims();
	for (auto victimInVector : allVictims)
		this->ui.victimListWidget->addItem(QString::fromStdString(victimInVector.toString()));

	this->ui.nextLineEdit->clear();
	this->ui.filterListWidget->clear();
}

void VictimGUI::filterList()
{
	this->ui.filterListWidget->clear();

	vector<Victim> allVictims = this->service.getAllVictims();
	vector<Victim> filteredVictims;
	for (auto victimInVector : allVictims)
	{
		if (this->ui.filterLineEditPlaceOfOtigin->text().toStdString().size() != 0)
		{
			if (victimInVector.getPlaceOfOrigin() == this->ui.filterLineEditPlaceOfOtigin->text().toStdString())
			{
				if (this->ui.filterLineEditAge->text().toStdString().size() != 0)
				{
					if (victimInVector.getAge() < stoi(this->ui.filterLineEditAge->text().toStdString()))
						filteredVictims.push_back(victimInVector);
				}
				else
					filteredVictims.push_back(victimInVector);
			}
		}
		else
			filteredVictims.push_back(victimInVector);
	}

	for (auto victimInVector : filteredVictims)
	{
		this->ui.filterListWidget->addItem(QString::fromStdString(victimInVector.toString()));
	}


}

void VictimGUI::undo()
{
	bool result;
	result = this->service.undo();
	this->populateList();
	if(result==false)
		QMessageBox::critical(this, "Error", "No more undos!");
}

void VictimGUI::redo()
{
	bool result;
	result = this->service.redo();
	this->populateList();
	if (result == false)
		QMessageBox::critical(this, "Error", "No more redos!");
}

void VictimGUI::openModeBWidget()
{
	this->myListGui = new MyListGUI{ this->service };
	this->myListGui->show();
	this->service.addObserver(this->myListGui);
}

int VictimGUI::getSelectedIndex() const
{
	QModelIndexList selectedIndexes = this->ui.victimListWidget->selectionModel()->selectedIndexes();
	if (selectedIndexes.size() == 0)
	{
		this->ui.nameLineEdit->clear();
		this->ui.placeOfOriginLineEdit->clear();
		this->ui.ageLineEdit->clear();
		this->ui.photographLineEdit->clear();

		return -1;
	}

	int selectedIndex = selectedIndexes.at(0).row();
	return selectedIndex;
}

void VictimGUI::addVictim()
{

	string name, placeOfOrigin, age, photograph;
	name = this->ui.nameLineEdit->text().toStdString();
	placeOfOrigin = this->ui.placeOfOriginLineEdit->text().toStdString();
	age = this->ui.ageLineEdit->text().toStdString();
	photograph = this->ui.photographLineEdit->text().toStdString();

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
	this->ui.victimListWidget->setCurrentRow(lastElementIndex);
}

void VictimGUI::deleteVictim()
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

void VictimGUI::updateVictim()
{
	string name, placeOfOrigin, age, photograph;
	name = this->ui.nameLineEdit->text().toStdString();
	placeOfOrigin = this->ui.placeOfOriginLineEdit->text().toStdString();
	age = this->ui.ageLineEdit->text().toStdString();
	photograph = this->ui.photographLineEdit->text().toStdString();

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
	this->ui.victimListWidget->setCurrentRow(elementIndex);
}

void VictimGUI::saveVictim()
{
	string name, victim;
	victim = this->ui.nextLineEdit->text().toStdString();
	if (victim.size() == 0)
	{
		QMessageBox::critical(this, "Error", "ERROR: No victim was selected!");
		return;
	}
	int indexEnding=0;
	while (victim[indexEnding] != ' ')
		indexEnding++;
	name = victim.substr(0, indexEnding);

	try
	{
		this->service.saveVictim(name);
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

}

void VictimGUI::next()
{
	Victim victim=this->service.next();

	this->ui.nextLineEdit->setText(QString::fromStdString(victim.toString()));
}

void VictimGUI::openFile()
{
	if (this->service.getMylistFileLocation() == "")
	{
		QMessageBox::warning(this, "Warning", "My list is stored in memory!");
		return;

	}
	string fileName = this->service.getMylistFileLocation();
	int periodLocation = fileName.find(".");
	string extention = fileName.substr(periodLocation + 1);
	if (extention == "html")
	{
		ShellExecuteA(NULL, "open", fileName.c_str(), NULL, NULL, SW_SHOWNORMAL);
	}
	if (extention == "csv")
	{
		string callPath = "notepad \"" + fileName + "\"";
		system(callPath.c_str());
	}
}
