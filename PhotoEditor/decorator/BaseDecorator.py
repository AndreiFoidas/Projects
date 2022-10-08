
from decorator.Shape import Shape
from abc import ABC

class BaseDecorator(Shape, ABC):
    def __init__(self, shape):
        super().__init__()
        self.__shape = shape

    def draw(self, path):
        self.__shape.draw(path)
