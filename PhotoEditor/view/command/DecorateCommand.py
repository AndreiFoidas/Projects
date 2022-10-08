
from view.command.Command import Command
from decorator.Line import Line
from decorator.Ellipse import Ellipse
from decorator.TextDecorator import TextDecorator
from decorator.MonochromeDecorator import MonochromeDecorator
from singleton.Logger import Logger
from singleton.ImageRepository import ImageRepository
from PIL import Image

class DecorateCommand(Command):
    def __init__(self, key, description):
        super().__init__(key, description)
        self.__logger = Logger().get_logger()
        self.__repository = ImageRepository()

    def __print_decorate_shape_menu(self):
        print("1. Draw Line")
        print("2. Draw Ellipse")

    def __print_decorate_extra_menu(self):
        print("1. Add Text")
        print("2. Add Monochrome Filter")
        print("0. Don't add anything")

    def __select_photo(self):
        images = self.__repository.get_all_images()

        for i in range(len(images)):
            print(str(i) + " - " + str(images[i]))

        choice = input("Select an image: ")
        try:
            int_choice = int(choice)
            if int_choice < 0 or int_choice > len(images):
                raise Exception("Invalid input")
        except Exception:
            print("Invalid input")
            return ""

        path = images[int(choice)].path
        return path

    def execute(self):
        shape = None
        path = self.__select_photo()
        if path == "":
            return
        logText = "Drew "
        self.__print_decorate_shape_menu()
        choice = input("Choose what to draw: ")
        if choice == "1":
            shape = Line()
            logText = logText + "Line "
        elif choice == "2":
            shape = Ellipse()
            logText = logText + "Ellipse "
        else:
            print("Invalid Choice!")
            return

        self.__print_decorate_extra_menu()
        choice = input("Choose what to add: ")
        if choice == "1":
            shape = TextDecorator(shape)
            logText = logText + "with Text "
        elif choice == "2":
            shape = MonochromeDecorator(shape)
            logText = logText + "with Monochrome Filter "
        elif choice == "0":
            pass
        else:
            print("Invalid Choice!")
            return

        shape.draw(path)
        logText = logText + "on: " + path
        self.__logger.log(logText)
        with Image.open(path).convert("RGBA") as image:
            image.show()

