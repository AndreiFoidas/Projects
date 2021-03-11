#pragma once
#include <qabstractitemmodel.h>
#include "Service.h"

class MyListModel :
    public QAbstractListModel
{
private:
    Service& service;
public:
    MyListModel(Service& _service) : service{ _service } {}

    int rowCount(const QModelIndex& parent = QModelIndex()) const;
    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const;
};

