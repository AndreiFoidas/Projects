
from view.command.Command import Command
from singleton.ImageRepository import ImageRepository
from strategy.GaussianBlurFilter import GaussianBlurFilter
from strategy.MedianFilter import MedianFilter
from strategy.UnsharpMaskFilter import UnsharpMaskFilter
from singleton.Logger import Logger


class FilterCommand(Command):
    def __init__(self, key, description):
        super().__init__(key, description)
        self.__logger = Logger().get_logger()
        self.__repository = ImageRepository()

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

    def __print_filter_menu(self):
        print("1. Gaussian Blur Filter")
        print("2. Median Filter")
        print("3. Unsharp Mask Filter")

    def execute(self):
        path = self.__select_photo()
        if path == "":
            return
        logText = "Filtered " + path + " with "
        self.__print_filter_menu()
        choice = input("Choose a filter: ")
        if choice == "1":
            GaussianBlurFilter(path).apply()
            logText = logText + "Gaussian Blur Filter"
        elif choice == "2":
            MedianFilter(path).apply()
            logText = logText + "Median Filter"
        elif choice == "3":
            UnsharpMaskFilter(path).apply()
            logText = logText + "Unsharp Mask Filter"
        else:
            print("Invalid Choice!")
            return

        self.__logger.log(logText)
