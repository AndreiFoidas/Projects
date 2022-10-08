
from view.command.Command import Command
from facade.ImageDownloader import ImageDownloader
from singleton.Logger import Logger

class DownloadCommand(Command):
    def __init__(self, key, description):
        super().__init__(key, description)
        self._path = "photos"
        self.__logger = Logger().get_logger()

    def execute(self):
        search_word = input("Enter a search word: ")
        image_downloader = ImageDownloader(search_word, self._path)
        image_downloader.run()
        self.__logger.log("Saved picture of " + search_word)
