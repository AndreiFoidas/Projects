#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_VictimGUI.h"
#include "Service.h"
#include "MyListGUI.h"

class VictimGUI : public QMainWindow
{
	Q_OBJECT

public:
	VictimGUI(Service& _service, QWidget *parent = Q_NULLPTR);

private:
	Service& service;
	Ui::VictimGUIClass ui;
	MyListGUI* myListGui;

	void setColor();
	void connectSignalsAndSlots();

	void populateList();
	int getSelectedIndex() const;
	void deleteVictim();
	void updateVictim();
	void saveVictim();
	void next();
	void openFile();
	void filterList();
	void undo();
	void redo();
	void openModeBWidget();

public slots:
	void addVictim();
};
