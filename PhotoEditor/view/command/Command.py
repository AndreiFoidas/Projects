
from abc import ABC, abstractmethod

class Command(ABC):
    def __init__(self, key, description):
        self.__key = key
        self.__description = description

    @abstractmethod
    def execute(self):
        pass

    def get_key(self):
        return self.__key

    def get_description(self):
        return self.__description
