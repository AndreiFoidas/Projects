#pragma once
#include <qwidget.h>
#include "Service.h"
#include <qlistwidget.h>
#include <qlineedit.h>
#include <qpushbutton.h>

class GUI: public QWidget
{
private:
	Service& service;
public:
	GUI(Service& _service);

	//graphical elements
	QListWidget* victimListWidget;
	QLineEdit* nameLineEdit, *placeOfOriginLineEdit, *ageLineEdit, *photographLineEdit, *fileNameLineEdit;
	QPushButton* addButton, * deleteButton, *updateButton, *setFileButton;


private:
	void initialiseGUI();
	void populateList();
	void connectSignalsAndSlots();

	int getSelectedIndex() const;
	void addVictim();
	void deleteVictim();
	void updateVictim();
	void setFile();
};

