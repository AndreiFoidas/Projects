#pragma once

#include <QWidget>
#include "ui_MyListGUI.h"
#include "myListModel.h"

class MyListGUI : public QWidget, public Observer
{
	Q_OBJECT

public:
	MyListGUI(Service& service, QWidget *parent = Q_NULLPTR);
	~MyListGUI();

private:
	Ui::MyListGUI ui;
	Service& service;
	MyListModel* model;

	void setColor();
	void update() override;
};
