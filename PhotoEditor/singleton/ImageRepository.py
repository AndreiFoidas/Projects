
from model.Image import Image
from singleton.Singleton import Singleton

class ImageRepository(metaclass=Singleton):
    def __init__(self):
        self._images = []
        self.init_repository()

    def init_repository(self):
        self.add_image(Image("dog", "photos/dog1.jpg"))

    def get_all_images(self):
        return self._images

    def add_image(self, image):
        self._images.append(image)
