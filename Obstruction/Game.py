
from Computer import *

class Game:
    def __init__(self,board,computer):
        self._board=board
        self._computer=computer

    def playerMove(self,x,y):
        self._board.move(x,y,'X')

    def computerMove(self):
        square=self._computer.nextMove(self._board)
        self._board.move(square[0],square[1],'O')


    def getBoard(self):
        return self._board
    
