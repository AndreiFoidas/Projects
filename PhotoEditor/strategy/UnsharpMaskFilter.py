
from strategy.Filter import Filter
from PIL import Image, ImageFilter

class UnsharpMaskFilter(Filter):
    def __init__(self, path):
        super().__init__(path)

    def apply(self):
        image = Image.open(self._path)

        image = image.filter(ImageFilter.UnsharpMask(radius=2, percent=150, threshold=3))
        image.show()
