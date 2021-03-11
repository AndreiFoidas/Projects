
from Board import *
from Sentence import *

class GameWonException(Exception):
    pass
class GameLostException(Exception):
    pass

class Game:
    def __init__(self, board, sentence):
        self._board=board
        self._sentence=sentence
        self._hangman=[]
        self._letterH=-1

    def addS(self, s):
        '''
        Calls the addSentence function from the Sentence module
        '''
        self._sentence.addSentence(s)

    def _markLetterStart(self, c):
        for i in range(len(self._board._sen)):
            if c==self._board._sen[i]:
                self._board._rev[i]=1

    def _markLetter(self, c):
        ct=0
        for i in range(len(self._board._sen)):
            if c==self._board._sen[i] and self._board._rev[i]!=1:
                self._board._rev[i]=1
                ct=1
        
        return ct

    def startGame(self):
        self._board._sen=self._sentence.chooseRandom()
        self._board._rev=[0]*len(self._board._sen)
        
        ''' 
        print(self._board._sen)
        print(self._board._rev)
        '''

        for i in range(len(self._board._sen)):
            if i==0 or i==len(self._board._sen)-1:
                self._markLetterStart(self._board._sen[i])
            if i>2 and i<len(self._board._sen)-2:
                if self._board._sen[i]==" " or self._board._sen[i-1]==" " or self._board._sen[i+1]==" ":
                    self._markLetterStart(self._board._sen[i])

        '''
        print(self._board._rev)
        self._output()
        '''

    def gameLoop(self, c):
        h='hangman '
        if self._markLetter(c)==0:
            self._letterH+=1
            self._hangman.append(h[self._letterH])
            if self._letterH==6:
                raise GameLostException()
        else:
                sem=1
                for i in range(len(self._board._sen)):
                    if self._board._rev[i]==0:
                        sem=0

                if sem==1:
                    raise GameWonException()

    def output(self):
        sir=""
        for i in range(len(self._board._sen)):
            if self._board._rev[i]==1:
                sir=sir+self._board._sen[i]
            else:
                if sir[len(sir)-1]==' ':
                    sir=sir+'_ '
                else:
                    sir=sir+' _ '
        sir=sir+' - '
        for i in range(self._letterH+1):
            sir=sir+self._hangman[i]
        
        return sir
