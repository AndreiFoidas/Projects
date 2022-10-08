
from abc import ABC, abstractmethod

class Filter(ABC):
    def __init__(self, path):
        self._path = path

    @abstractmethod
    def apply(self):
        pass
