
from decorator.Shape import Shape
from PIL import Image, ImageDraw

class Ellipse(Shape):
    def __init__(self):
        super().__init__()

    def draw(self, path):
        with Image.open(path) as image:
            draw = ImageDraw.Draw(image)
            draw.ellipse([(10, 10), (50, 50)], outline=128)

            image.save(path)
