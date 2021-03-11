/********************************************************************************
** Form generated from reading UI file 'MyListGUI.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MYLISTGUI_H
#define UI_MYLISTGUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QListView>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MyListGUI
{
public:
    QGridLayout *gridLayout;
    QListView *myListListView;

    void setupUi(QWidget *MyListGUI)
    {
        if (MyListGUI->objectName().isEmpty())
            MyListGUI->setObjectName(QString::fromUtf8("MyListGUI"));
        MyListGUI->resize(400, 300);
        gridLayout = new QGridLayout(MyListGUI);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        myListListView = new QListView(MyListGUI);
        myListListView->setObjectName(QString::fromUtf8("myListListView"));

        gridLayout->addWidget(myListListView, 0, 0, 1, 1);


        retranslateUi(MyListGUI);

        QMetaObject::connectSlotsByName(MyListGUI);
    } // setupUi

    void retranslateUi(QWidget *MyListGUI)
    {
        MyListGUI->setWindowTitle(QCoreApplication::translate("MyListGUI", "MyListGUI", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MyListGUI: public Ui_MyListGUI {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MYLISTGUI_H
