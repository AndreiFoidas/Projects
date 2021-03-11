/********************************************************************************
** Form generated from reading UI file 'VictimGUI.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_VICTIMGUI_H
#define UI_VICTIMGUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTabWidget>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_VictimGUIClass
{
public:
    QWidget *centralWidget;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QTabWidget *modeTabWidget;
    QWidget *tabModeA;
    QGridLayout *gridLayout;
    QListWidget *victimListWidget;
    QFormLayout *formLayout;
    QLabel *label;
    QLineEdit *nameLineEdit;
    QLabel *label_2;
    QLineEdit *placeOfOriginLineEdit;
    QLabel *label_3;
    QLineEdit *ageLineEdit;
    QLabel *label_4;
    QLineEdit *photographLineEdit;
    QHBoxLayout *horizontalLayout_2;
    QPushButton *addButton;
    QPushButton *updateButton;
    QPushButton *deleteButton;
    QHBoxLayout *horizontalLayout_4;
    QPushButton *undoButton;
    QPushButton *redoButton;
    QWidget *tabModeB;
    QGridLayout *gridLayout_2;
    QFormLayout *formLayout_3;
    QLabel *label_9;
    QLineEdit *nextLineEdit;
    QHBoxLayout *horizontalLayout_5;
    QPushButton *openWidgetButton;
    QPushButton *openButton;
    QHBoxLayout *horizontalLayout_3;
    QPushButton *nextButton;
    QPushButton *saveButton;
    QWidget *tabFilter;
    QGridLayout *gridLayout_3;
    QPushButton *filterButton;
    QFormLayout *formLayout_2;
    QLabel *label_5;
    QLineEdit *filterLineEditPlaceOfOtigin;
    QLineEdit *filterLineEditAge;
    QLabel *label_6;
    QListWidget *filterListWidget;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *VictimGUIClass)
    {
        if (VictimGUIClass->objectName().isEmpty())
            VictimGUIClass->setObjectName(QString::fromUtf8("VictimGUIClass"));
        VictimGUIClass->resize(517, 502);
        centralWidget = new QWidget(VictimGUIClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        verticalLayout = new QVBoxLayout(centralWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        modeTabWidget = new QTabWidget(centralWidget);
        modeTabWidget->setObjectName(QString::fromUtf8("modeTabWidget"));
        QFont font;
        font.setPointSize(12);
        modeTabWidget->setFont(font);
        tabModeA = new QWidget();
        tabModeA->setObjectName(QString::fromUtf8("tabModeA"));
        gridLayout = new QGridLayout(tabModeA);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        victimListWidget = new QListWidget(tabModeA);
        victimListWidget->setObjectName(QString::fromUtf8("victimListWidget"));
        victimListWidget->setFont(font);

        gridLayout->addWidget(victimListWidget, 0, 0, 1, 1);

        formLayout = new QFormLayout();
        formLayout->setSpacing(6);
        formLayout->setObjectName(QString::fromUtf8("formLayout"));
        label = new QLabel(tabModeA);
        label->setObjectName(QString::fromUtf8("label"));
        label->setFont(font);

        formLayout->setWidget(0, QFormLayout::LabelRole, label);

        nameLineEdit = new QLineEdit(tabModeA);
        nameLineEdit->setObjectName(QString::fromUtf8("nameLineEdit"));
        nameLineEdit->setFont(font);

        formLayout->setWidget(0, QFormLayout::FieldRole, nameLineEdit);

        label_2 = new QLabel(tabModeA);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setFont(font);

        formLayout->setWidget(1, QFormLayout::LabelRole, label_2);

        placeOfOriginLineEdit = new QLineEdit(tabModeA);
        placeOfOriginLineEdit->setObjectName(QString::fromUtf8("placeOfOriginLineEdit"));
        placeOfOriginLineEdit->setFont(font);

        formLayout->setWidget(1, QFormLayout::FieldRole, placeOfOriginLineEdit);

        label_3 = new QLabel(tabModeA);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setFont(font);

        formLayout->setWidget(2, QFormLayout::LabelRole, label_3);

        ageLineEdit = new QLineEdit(tabModeA);
        ageLineEdit->setObjectName(QString::fromUtf8("ageLineEdit"));
        ageLineEdit->setFont(font);

        formLayout->setWidget(2, QFormLayout::FieldRole, ageLineEdit);

        label_4 = new QLabel(tabModeA);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setFont(font);

        formLayout->setWidget(3, QFormLayout::LabelRole, label_4);

        photographLineEdit = new QLineEdit(tabModeA);
        photographLineEdit->setObjectName(QString::fromUtf8("photographLineEdit"));
        photographLineEdit->setFont(font);

        formLayout->setWidget(3, QFormLayout::FieldRole, photographLineEdit);


        gridLayout->addLayout(formLayout, 1, 0, 1, 1);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        addButton = new QPushButton(tabModeA);
        addButton->setObjectName(QString::fromUtf8("addButton"));
        addButton->setFont(font);

        horizontalLayout_2->addWidget(addButton);

        updateButton = new QPushButton(tabModeA);
        updateButton->setObjectName(QString::fromUtf8("updateButton"));
        updateButton->setFont(font);

        horizontalLayout_2->addWidget(updateButton);

        deleteButton = new QPushButton(tabModeA);
        deleteButton->setObjectName(QString::fromUtf8("deleteButton"));
        deleteButton->setFont(font);

        horizontalLayout_2->addWidget(deleteButton);


        gridLayout->addLayout(horizontalLayout_2, 2, 0, 1, 1);

        horizontalLayout_4 = new QHBoxLayout();
        horizontalLayout_4->setSpacing(6);
        horizontalLayout_4->setObjectName(QString::fromUtf8("horizontalLayout_4"));
        undoButton = new QPushButton(tabModeA);
        undoButton->setObjectName(QString::fromUtf8("undoButton"));

        horizontalLayout_4->addWidget(undoButton);

        redoButton = new QPushButton(tabModeA);
        redoButton->setObjectName(QString::fromUtf8("redoButton"));

        horizontalLayout_4->addWidget(redoButton);


        gridLayout->addLayout(horizontalLayout_4, 3, 0, 1, 1);

        modeTabWidget->addTab(tabModeA, QString());
        tabModeB = new QWidget();
        tabModeB->setObjectName(QString::fromUtf8("tabModeB"));
        gridLayout_2 = new QGridLayout(tabModeB);
        gridLayout_2->setSpacing(6);
        gridLayout_2->setContentsMargins(11, 11, 11, 11);
        gridLayout_2->setObjectName(QString::fromUtf8("gridLayout_2"));
        formLayout_3 = new QFormLayout();
        formLayout_3->setSpacing(6);
        formLayout_3->setObjectName(QString::fromUtf8("formLayout_3"));
        label_9 = new QLabel(tabModeB);
        label_9->setObjectName(QString::fromUtf8("label_9"));
        label_9->setFont(font);

        formLayout_3->setWidget(0, QFormLayout::LabelRole, label_9);

        nextLineEdit = new QLineEdit(tabModeB);
        nextLineEdit->setObjectName(QString::fromUtf8("nextLineEdit"));

        formLayout_3->setWidget(0, QFormLayout::FieldRole, nextLineEdit);


        gridLayout_2->addLayout(formLayout_3, 0, 0, 1, 1);

        horizontalLayout_5 = new QHBoxLayout();
        horizontalLayout_5->setSpacing(6);
        horizontalLayout_5->setObjectName(QString::fromUtf8("horizontalLayout_5"));
        openWidgetButton = new QPushButton(tabModeB);
        openWidgetButton->setObjectName(QString::fromUtf8("openWidgetButton"));

        horizontalLayout_5->addWidget(openWidgetButton);

        openButton = new QPushButton(tabModeB);
        openButton->setObjectName(QString::fromUtf8("openButton"));
        openButton->setFont(font);

        horizontalLayout_5->addWidget(openButton);


        gridLayout_2->addLayout(horizontalLayout_5, 1, 0, 1, 1);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setSpacing(6);
        horizontalLayout_3->setObjectName(QString::fromUtf8("horizontalLayout_3"));
        nextButton = new QPushButton(tabModeB);
        nextButton->setObjectName(QString::fromUtf8("nextButton"));
        nextButton->setFont(font);

        horizontalLayout_3->addWidget(nextButton);

        saveButton = new QPushButton(tabModeB);
        saveButton->setObjectName(QString::fromUtf8("saveButton"));
        saveButton->setFont(font);

        horizontalLayout_3->addWidget(saveButton);


        gridLayout_2->addLayout(horizontalLayout_3, 2, 0, 1, 1);

        modeTabWidget->addTab(tabModeB, QString());
        tabFilter = new QWidget();
        tabFilter->setObjectName(QString::fromUtf8("tabFilter"));
        gridLayout_3 = new QGridLayout(tabFilter);
        gridLayout_3->setSpacing(6);
        gridLayout_3->setContentsMargins(11, 11, 11, 11);
        gridLayout_3->setObjectName(QString::fromUtf8("gridLayout_3"));
        filterButton = new QPushButton(tabFilter);
        filterButton->setObjectName(QString::fromUtf8("filterButton"));
        filterButton->setFont(font);

        gridLayout_3->addWidget(filterButton, 2, 0, 1, 1);

        formLayout_2 = new QFormLayout();
        formLayout_2->setSpacing(6);
        formLayout_2->setObjectName(QString::fromUtf8("formLayout_2"));
        label_5 = new QLabel(tabFilter);
        label_5->setObjectName(QString::fromUtf8("label_5"));
        label_5->setFont(font);

        formLayout_2->setWidget(0, QFormLayout::LabelRole, label_5);

        filterLineEditPlaceOfOtigin = new QLineEdit(tabFilter);
        filterLineEditPlaceOfOtigin->setObjectName(QString::fromUtf8("filterLineEditPlaceOfOtigin"));
        filterLineEditPlaceOfOtigin->setFont(font);

        formLayout_2->setWidget(0, QFormLayout::FieldRole, filterLineEditPlaceOfOtigin);

        filterLineEditAge = new QLineEdit(tabFilter);
        filterLineEditAge->setObjectName(QString::fromUtf8("filterLineEditAge"));
        filterLineEditAge->setFont(font);

        formLayout_2->setWidget(1, QFormLayout::FieldRole, filterLineEditAge);

        label_6 = new QLabel(tabFilter);
        label_6->setObjectName(QString::fromUtf8("label_6"));
        label_6->setFont(font);

        formLayout_2->setWidget(1, QFormLayout::LabelRole, label_6);


        gridLayout_3->addLayout(formLayout_2, 1, 0, 1, 1);

        filterListWidget = new QListWidget(tabFilter);
        filterListWidget->setObjectName(QString::fromUtf8("filterListWidget"));

        gridLayout_3->addWidget(filterListWidget, 0, 0, 1, 1);

        modeTabWidget->addTab(tabFilter, QString());

        horizontalLayout->addWidget(modeTabWidget);


        verticalLayout->addLayout(horizontalLayout);

        VictimGUIClass->setCentralWidget(centralWidget);
        mainToolBar = new QToolBar(VictimGUIClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        VictimGUIClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(VictimGUIClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        VictimGUIClass->setStatusBar(statusBar);

        retranslateUi(VictimGUIClass);
        QObject::connect(addButton, SIGNAL(clicked()), VictimGUIClass, SLOT(addVictim()));

        modeTabWidget->setCurrentIndex(0);


        QMetaObject::connectSlotsByName(VictimGUIClass);
    } // setupUi

    void retranslateUi(QMainWindow *VictimGUIClass)
    {
        VictimGUIClass->setWindowTitle(QCoreApplication::translate("VictimGUIClass", "VictimGUI", nullptr));
        label->setText(QCoreApplication::translate("VictimGUIClass", "Name:", nullptr));
        label_2->setText(QCoreApplication::translate("VictimGUIClass", "Place of origin:", nullptr));
        label_3->setText(QCoreApplication::translate("VictimGUIClass", "Age:", nullptr));
        label_4->setText(QCoreApplication::translate("VictimGUIClass", "Photograph:", nullptr));
        addButton->setText(QCoreApplication::translate("VictimGUIClass", "Add", nullptr));
        updateButton->setText(QCoreApplication::translate("VictimGUIClass", "Update", nullptr));
        deleteButton->setText(QCoreApplication::translate("VictimGUIClass", "Delete", nullptr));
        undoButton->setText(QCoreApplication::translate("VictimGUIClass", "Undo", nullptr));
        redoButton->setText(QCoreApplication::translate("VictimGUIClass", "Redo", nullptr));
        modeTabWidget->setTabText(modeTabWidget->indexOf(tabModeA), QCoreApplication::translate("VictimGUIClass", "Mode A", nullptr));
        label_9->setText(QCoreApplication::translate("VictimGUIClass", "Victim:", nullptr));
        openWidgetButton->setText(QCoreApplication::translate("VictimGUIClass", "Open Victim Widget", nullptr));
        openButton->setText(QCoreApplication::translate("VictimGUIClass", "Open external", nullptr));
        nextButton->setText(QCoreApplication::translate("VictimGUIClass", "Next", nullptr));
        saveButton->setText(QCoreApplication::translate("VictimGUIClass", "Save", nullptr));
        modeTabWidget->setTabText(modeTabWidget->indexOf(tabModeB), QCoreApplication::translate("VictimGUIClass", "Mode B", nullptr));
        filterButton->setText(QCoreApplication::translate("VictimGUIClass", "Filter", nullptr));
        label_5->setText(QCoreApplication::translate("VictimGUIClass", "Place of origin:", nullptr));
        label_6->setText(QCoreApplication::translate("VictimGUIClass", "Age:", nullptr));
        modeTabWidget->setTabText(modeTabWidget->indexOf(tabFilter), QCoreApplication::translate("VictimGUIClass", "Filter", nullptr));
    } // retranslateUi

};

namespace Ui {
    class VictimGUIClass: public Ui_VictimGUIClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_VICTIMGUI_H
