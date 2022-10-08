
from decorator.Shape import Shape
from PIL import Image, ImageDraw

class Line(Shape):
    def __init__(self):
        super().__init__()

    def draw(self, path):
        with Image.open(path) as image:
            draw = ImageDraw.Draw(image)
            draw.line((0, 0) + image.size, fill=128)

            image.save(path)
