
import sys
from view.command.Command import Command
from singleton.Logger import Logger

class ExitCommand(Command):
    def __init__(self, key, description):
        super().__init__(key, description)
        self.__logger = Logger().get_logger()

    def execute(self):
        self.__logger.log("Exiting...")
        sys.exit()
