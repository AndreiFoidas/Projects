#include "MyListGUI.h"

MyListGUI::MyListGUI(Service& _service, QWidget *parent)
	: QWidget(parent), service{_service}
{
	ui.setupUi(this);
	
	this->setColor();
	this->model = new MyListModel{ this->service };
	this->ui.myListListView->setModel(this->model);
}

MyListGUI::~MyListGUI()
{
	delete this->model;
}

void MyListGUI::setColor()
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

	this->ui.myListListView->setPalette(*palette);

	setPalette(*palette);
}

void MyListGUI::update()
{
	delete this->model;
	this->model = new MyListModel{ this->service };
	this->ui.myListListView->setModel(this->model);
}
