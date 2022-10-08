
from view.command.DownloadCommand import DownloadCommand
from view.command.ExitCommand import ExitCommand
from view.command.DecorateCommand import DecorateCommand
from view.command.FilterCommand import FilterCommand

class View:
    def __init__(self):
        self.__commands = {}
        self.__create_all_commands()

    def __add_command(self, command):
        self.__commands[command.get_key()] = command

    def __create_all_commands(self):
        self.__add_command(DownloadCommand("1", "Download Image"))
        self.__add_command(DecorateCommand("2", "Decorate Image"))
        self.__add_command(FilterCommand("3", "Filter Image"))
        self.__add_command(ExitCommand("0", "Exit"))

    def __print_menu(self):
        for _, c in self.__commands.items():
            print(c.get_key() + ". " + c.get_description())

    def run(self):
        while True:
            self.__print_menu()
            choice = input("Select an option: ")
            command = self.__commands[choice]

            if command is None:
                print("Invalid option!")
                continue
            else:
                command.execute()
