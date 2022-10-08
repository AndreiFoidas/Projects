
from singleton.ImageRepository import ImageRepository, Image
import requests
import os
from tqdm import tqdm
from bs4 import BeautifulSoup as bs
from urllib.parse import urljoin, urlparse
import re


class ImageDownloader:
    def __init__(self, search_word, path):
        self.__path = path
        self.__search_word = search_word
        self.__url = "https://en.wikipedia.org/wiki/" + self.__search_word
        # self._url = "https://burst.shopify.com/photos/search?utf8=%E2%9C%93&q=" + self._search_word
        # self._url = "https://www.flickr.com/search/?text=" + self._search_word
        # self._url = "https://commons.wikimedia.org/w/index.php?search=" + self._search_word + "&title=Special:MediaSearch&go=Go&type=image"
        self.__repository = ImageRepository()

    def __is_valid(self, img_url):
        """
        Checks whether `url` is a valid URL.
        """
        parsed = urlparse(img_url)
        return bool(parsed.netloc) and bool(parsed.scheme)  and bool(re.search(self.__search_word, img_url, re.IGNORECASE))

    def __get_all_images(self):
        """
        Returns all image URLs on a single `url`
        """
        soup = bs(requests.get(self.__url).content, "html.parser")

        urls = []
        for img in tqdm(soup.find_all("img"), "Extracting images"):
            img_url = img.attrs.get("src")
            if not img_url:
                # if img does not contain src attribute, just skip
                continue

            # make the URL absolute by joining domain with the URL that is just extracted
            img_url = urljoin(self.__url, img_url)

            try:
                pos = img_url.index("?")
                img_url = img_url[:pos]
            except ValueError:
                pass

            # finally, if the url is valid
            if self.__is_valid(img_url):
                urls.append(img_url)

        return urls

    def __download(self, url):
        """
        Downloads a file given an URL and puts it in the folder `pathname`
        """
        # if path doesn't exist, make that path dir
        if not os.path.isdir(self.__path):
            os.makedirs(self.__path)
        # download the body of response by chunk, not immediately
        response = requests.get(url, stream=True)
        # get the total file size
        file_size = int(response.headers.get("Content-Length", 0))
        # get the file name
        filename = os.path.join(self.__path, url.split("/")[-1])
        # progress bar, changing the unit to bytes instead of iteration (default by tqdm)
        progress = tqdm(response.iter_content(1024), f"Downloading {filename}", total=file_size, unit="B",
                        unit_scale=True, unit_divisor=1024)
        with open(filename, "wb") as f:
            for data in progress.iterable:
                # write data read to the file
                f.write(data)
                # update the progress bar manually
                progress.update(len(data))

        self.__repository.add_image(Image(self.__search_word, filename))

    def run(self):
        # get all images
        images = self.__get_all_images()
        ct = 0
        for img in images:
            # for each image, download it
            self.__download(img)
            ct = ct + 1
            if ct == 3:
                break
                pass
