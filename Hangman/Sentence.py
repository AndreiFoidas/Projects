
import random

class Sentnece:
    def __init__(self, fileName):
        self._sList=[]
        self._fileName=fileName
        self._loadFile(self._fileName)

    def _loadFile(self,fileName):
        with open(fileName, 'r') as f:
            f1=f.readlines()
            for line in f1:
                line=line[:len(line)-1]
                self._sList.append(line)

    def _saveFile(self, fileName):
        '''
        Saves the new list of sentences in the file
        input:
            fileName - name of the file where it will bw saved
            sList - list of sentences
        '''
        with open(fileName, 'w') as f:
            for s in self._sList:
                f.write(s+'\n')

    def addSentence(self, sentence):
        '''
        We add a sentence to the list of sentences and in the sentences file (only if its not a duplicate)
        input:
            sentence - the sentence that will be added
            sList - the list of sentences
        errors:
            ValueError - if the sentene is a duplicate
        '''
        for s in self._sList:
            if s==sentence:
                raise ValueError('Sentence already exists')
        self._sList.append(sentence)
        self._saveFile(self._fileName)

    def chooseRandom(self):
        return random.choice(self._sList)

