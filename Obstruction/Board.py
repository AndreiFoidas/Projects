
from texttable import Texttable

class GameWonException(Exception):
    pass

class Board:
    def __init__(self,r,c):
        self._rows=r
        self._columns=c
        self._data=self._initt(r,c)
        self._moves=0

    def _initt(self,rows,columns):
        '''
        Given the dimentions, this returns the matrix (and the board) of the game
        input:
            - rows
            - columns
        output:
            - res: a matrix with "rows" number of rows and "columns" number of columns
        '''
        res=[]
        
        for i in range(rows):
            res.append([0]*columns)
        #print(res)

        return res

    #some getters
    def get(self,x,y):
        return self._data[x][y]
    
    def getDim(self):
        return (self._rows,self._columns)

    def isWon(self):
        '''
        Checks if the game is over (there are no more free spaces)
        input:
            - none (but I use the no of rows and columns)
        output:
            - True: the game is over
            - False: otherwise
        '''
        if self._moves==self._rows*self._columns:
            return True

        return False

    def move(self,x,y,symbol):
        '''
        Move validates the input then makes a move through _moveOnBoard
        Also checks if the game is over after each move
        input:
            -x
            -y    (the coordinates)
            -symbol (X or O)
        output:
            - raises an error for invalid input
            - GameWonException if the game is over
            - none: the game continues after this move
        '''
        if x<0 or x>self._rows-1 or y<0 or y>self._columns-1:
            raise ValueError('Move outside board!')

        if self._data[x][y]!=0:
            raise ValueError('Invalid square to move!')

        if symbol not in ['X','O']:
            raise ValueError('Incorrect symbol!')

        d={'X':1,'O':2}
        self._moveOnBoard(x,y,d[symbol])

        if self.isWon():
            raise GameWonException()


    def _moveOnBoard(self,x,y,symbol):
        '''
        Makes the move on board:
            - puts the symbol
            - makes the 8 squares around it unplayable
        input:
            -x
            -y    (the coordinates)
            -symbol (1 or 2)
        output:
            -none
        '''
        self._data[x][y]=symbol
        self._moves+=1
        di=[0,1,1,1,0,-1,-1,-1]
        dj=[-1,-1,0,1,1,1,0,-1]
        for k in range(0,8):
            newx=x+di[k]
            newy=y+dj[k]
            if newx>=0 and newy>=0 and newx<=self._rows-1 and newy<=self._columns-1:
                if self._data[newx][newy]==0:
                    self._data[newx][newy]=-1
                    self._moves+=1

    def fakeMove(self,x,y,symbol):
        history=[]
        self._data[x][y]=symbol
        history.append((x,y))
        self._moves+=1
        di=[0,1,1,1,0,-1,-1,-1]
        dj=[-1,-1,0,1,1,1,0,-1]
        for k in range(0,8):
            newx=x+di[k]
            newy=y+dj[k]
            if newx>=0 and newy>=0 and newx<=self._rows-1 and newy<=self._columns-1:
                if self._data[newx][newy]==0:
                    self._data[newx][newy]=-1
                    history.append((newx,newy))
                    self._moves+=1
        #print(self)
        return history
    
    def delMove(self,history):
        for i in range(len(history)):
            self._data[history[i][0]][history[i][1]]=0
            self._moves-=1

    def tryMove(self,x,y):
        newMoves=1
        di=[0,1,1,1,0,-1,-1,-1]
        dj=[-1,-1,0,1,1,1,0,-1]
        for k in range(0,8):
            newx=x+di[k]
            newy=y+dj[k]
            if newx>=0 and newy>=0 and newx<=self._rows-1 and newy<=self._columns-1:
                if self._data[newx][newy]==0:
                    newMoves+=1

        return newMoves


    def __str__(self):
        '''
        Prints the game through a texttable
        '''
        t=Texttable()
        d={0:' ',1:'X',2:'O',-1:'#'}

        for i in range(self._rows):
            row=self._data[i][:]
            for j in range(self._columns):
                row[j]=d[row[j]]
            t.add_row(row)

        return t.draw()

import random
help(sum)
