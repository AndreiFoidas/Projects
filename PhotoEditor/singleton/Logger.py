
import logging
import logging.handlers
import datetime
from singleton.Singleton import Singleton

class Logger(metaclass=Singleton):
    def __init__(self):
        self.__logger = None
        self.__handler = None
        self.__init_log()

    def __init_log(self):
        self.__handler = logging.getLogger()
        self.__handler.setLevel(logging.INFO)
        fileHandler = logging.FileHandler("files/logFile.log")
        self.__handler.addHandler(fileHandler)
        self.__handler.info(datetime.datetime.now())
        self.__handler.info("Logger has been initialised.")

    def get_logger(self):
        if self.__logger is None:
            self.__logger = Logger()

        return self.__logger

    def log(self, data):
        data = data + "\n"
        self.__handler.info(data)


