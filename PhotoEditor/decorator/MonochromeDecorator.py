
from decorator.BaseDecorator import BaseDecorator
from PIL import Image, ImageDraw

class MonochromeDecorator(BaseDecorator):
    def __init__(self, shape):
        super().__init__(shape)

    def draw(self, path):
        super().draw(path)
        self.extra(path)

    def extra(self, path):
        with Image.open(path) as image:
            image = image.convert("L")
            image.save(path)
