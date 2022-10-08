
from strategy.Filter import Filter
from PIL import Image, ImageFilter

class MedianFilter(Filter):
    def __init__(self, path):
        super().__init__(path)

    def apply(self):
        image = Image.open(self._path)

        image = image.filter(ImageFilter.MedianFilter(size=3))
        image.show()
