#include "myListModel.h"
#include <QFont>

int MyListModel::rowCount(const QModelIndex& parent) const
{
    return this->service.getAllAssistantVictims().size();
}

QVariant MyListModel::data(const QModelIndex& index, int role) const
{
    int indexRow = index.row();
    Victim victim = this->service.getAllAssistantVictims()[indexRow];
    
    if (role == Qt::DisplayRole)
    {
        string line;
        line = victim.getName() + " " + to_string(victim.getAge()) + " " + victim.getPlaceOfOrigin() + " " + victim.getPhotograph();
        return QString::fromStdString(line);
    }
    if (role == Qt::FontRole)
    {
        QFont font("MS Shell Dlg 2", 12);
        return font;

    }

    return QVariant();
}
