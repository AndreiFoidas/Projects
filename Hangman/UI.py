
from Game import *
from Validations import *

class UI:
    def __init__(self, game):
        self._game=game

    def printMenu(self):
        print("Menu:")
        print('1. Add a sentence')
        print('2. Start a game')
        print('0. Exit')

    def addSentenceUI(self):
        print("Add the sentence:")
        s=input('>')
        s=validateSentence(s)
        self._game.addS(s)

    def startGameUI(self):
        self._game.startGame()

    def gameLoopUI(self):
        while True:
            print(self._game.output())
            c=input('Guess: ')
            if len(c)!=1:
                print('Bad input')
            else:
                self._game.gameLoop(c)

    def start(self):
        
        while True:
            self.printMenu()
            cmd=input('>')
            if cmd=='1':
                try:
                    self.addSentenceUI()
                except ValueError as e:
                    print(str(e))
            elif cmd=='2':
                try:
                    self.startGameUI()
                    self.gameLoopUI()
                except GameWonException:
                    print(self._game.output())
                    print('YOU WON!')
                    self._game._letterH=-1
                    self._game._hangman=[]
                except GameLostException:
                    print(self._game.output())
                    print('YOU LOST!')
                    self._game._letterH=-1
                    self._game._hangman=[]
            elif cmd=='0':
                return
            else:
                print('Bad command')
        




b=Board()
s=Sentnece('sentences.txt')
g=Game(b,s)
ui=UI(g)
ui.start()