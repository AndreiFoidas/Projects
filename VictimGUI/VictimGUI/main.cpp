#include "VictimGUI.h"
#include <QtWidgets/QApplication>
#include "Service.h"
#include "VictimGUI.h"
#include "MyListGUI.h"
//#include "GUI.h"

using namespace std;

int main(int argc, char* argv[])
{
	QApplication application(argc, argv);

	string repositoryFile, assistantRepositoryFile;

	ifstream configurationFile("configuration.txt");
	getline(configurationFile, repositoryFile);
	getline(configurationFile, assistantRepositoryFile);

	AssistantRepository* assistantRepository;
	if (assistantRepositoryFile == "inMemory")
		assistantRepository = new MemoryAssistantRepository();
	else
	{
		int fileNameStart = assistantRepositoryFile.find(":");
		string fileLocationName = assistantRepositoryFile.substr(fileNameStart + 2);

		int periodLocation = fileLocationName.find(".");
		string extention = fileLocationName.substr(periodLocation + 1);
		if (extention == "html")
		{
			assistantRepository = new HTMLAssistantRepository{ fileLocationName };
		}
		if (extention == "csv")
		{
			assistantRepository = new CSVAssistantRepository{ fileLocationName };
		}
	}

	if (repositoryFile == "inMemory")
	{
		MemoryRepository repository = MemoryRepository();
		Service service = Service(repository, assistantRepository);
		VictimGUI gui{ service };
		gui.show();
		return application.exec();
	}
	else
	{
		int fileNameStart = repositoryFile.find(":");
		string fileLocationName = repositoryFile.substr(fileNameStart + 2);
		FileRepository repository = FileRepository(fileLocationName);
		Service service = Service(repository, assistantRepository);
		VictimGUI gui{ service };
		gui.show();
		return application.exec();
	}

}
