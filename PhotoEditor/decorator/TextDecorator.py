
from decorator.BaseDecorator import BaseDecorator
from PIL import Image, ImageDraw, ImageFont


class TextDecorator(BaseDecorator):
    def __init__(self, shape):
        super().__init__(shape)

    def draw(self, path):
        super().draw(path)
        self.extra(path)

    def extra(self, path):
        with Image.open(path).convert("RGBA") as base:
            text = Image.new("RGBA", base.size, (255, 255, 255, 0))
            # font = ImageFont.truetype("Pillow/Tests/fonts/FreeMono.ttf", 40)

            draw = ImageDraw.Draw(text)
            draw.text((10, 10), "I am Mr. Text", fill=(255, 255, 255, 128))

            image = Image.alpha_composite(base, text)
            image = image.convert("RGB")
            image.save(path)
