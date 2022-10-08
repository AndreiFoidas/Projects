import random

import names
import urllib.request

word_url = "https://www.mit.edu/~ecprice/wordlist.10000"


class JSON:
    def __init__(self):
        self.fileNameOpenSearch = "personsOS.json"
        self.fileNameElectricSearch = "personsES.json"
        self.length = 100000
        self.index = "persons"
        self.type = "person"
        self.mainName = "name"
        self.wordList = "hobbies"
        self.nameList = "friends"
        self.intName = "birthYear"
        self.mainWord = "likes"

        response = urllib.request.urlopen(word_url)
        long_txt = response.read().decode()
        self.words = long_txt.splitlines()


    def createJSON(self):
        fileOS = open(self.fileNameOpenSearch, "a")
        fileES = open(self.fileNameElectricSearch, "a")

        for i in range(self.length):
            print(i)
            random_genre1 = self.words[random.randint(0, len(self.words))-1]
            random_genre2 = self.words[random.randint(0, len(self.words))-1]
            random_tile = self.words[random.randint(0, len(self.words))-1] + " " + self.words[random.randint(0, len(self.words))-1]
            random_year = random.randint(1900, 2022)

            fileOS.write('{ "index" : { "_index": "' + self.index + '", "_id" : "' + str(i) + '" } }\n')
            fileES.write('{ "index" : { "_index": "' + self.index + '", "_type": "' + self.type + '", "_id" : "' + str(i) + '" } }\n')

            stringMainName = '{"' + self.mainName + '": "' + names.get_full_name() + '", '
            fileOS.write(stringMainName)
            fileES.write(stringMainName)

            stringWordList = '"' + self.wordList + '": ["' + random_genre1 + '", "' + random_genre2 + '"], '
            fileOS.write(stringWordList)
            fileES.write(stringWordList)

            stringIntName = '"' + self.intName + '": ' + str(random_year) + ', '
            fileOS.write(stringIntName)
            fileES.write(stringIntName)

            stringNameList = '"' + self.nameList + '": ["' + names.get_full_name() + '", "' + names.get_full_name() + '", "' + names.get_full_name() + '"], '
            fileOS.write(stringNameList)
            fileES.write(stringNameList)

            stringMainWord = '"' + self.mainWord + '": "' + random_tile + '"}\n'
            fileOS.write(stringMainWord)
            fileES.write(stringMainWord)


if __name__ == '__main__':
    j = JSON()
    j.createJSON()


