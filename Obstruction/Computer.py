
import random
from Board import *

class ComputerLv0:
    '''
    Level 0 computer
        - makes a move on the first available space
    input:
        - the board
    output:
        - returns the computers next move
    '''
    def nextMove(self,board):
        (r,c)=board.getDim()
        for i in range(r):
            for j in range(c):
                if board.get(i,j)==0:
                    return (i,j)

class ComputerLv1:
    '''
    Level 1 computer
        - makes a random move on the board
        - does not try to win
    input:
        - the board
    output:
        - returns the computers next move
    '''
    def nextMove(self,board):
        candidates=[]
        (r,c)=board.getDim()
        for i in range(r):
            for j in range(c):
                if board.get(i,j)==0:
                    candidates.append((i,j))

        return random.choice(candidates)

class ComputerLv2:
    '''
    Level 1 computer
        - makes a random move on the board
        - will win if possible
    input:
        - the board
    output:
        - returns the computers next move
    '''
    def nextMove(self,board):
        candidates=[]
        (r,c)=board.getDim()
        for i in range(r):
            for j in range(c):
                if board.get(i,j)==0:
                    candidates.append((i,j))
        
        rem=board._rows*board._columns-board._moves
        if rem<=9:
            for ca in candidates:
                (i,j)=ca
                if rem==board.tryMove(i,j):
                    return (i,j)

        return random.choice(candidates)

class ComputerLv3:
    def __init__(self):
        PX=None
        PY=None
    
    def nextMove(self,board):
        (m, px, py) = self.max(board)
        return (px,py)

    def max(self,board):

        (r,c)=board.getDim()
        maxv=-50000000
        px=None
        py=None

        if board.isWon()==True:
            return (board._moves,0,0)

        for i in range(0,r):
            for j in range(0,c):
                if board.get(i,j)==0:
                    h=board.fakeMove(i,j,2)
                    print('max')
                    print(h)
                    #if m==board._rows*board._columns:
                    print(maxv)
                    print(board)
                    (m,min_i,min_j)=self.min(board)
                    if m>maxv:
                        maxv=m
                        px=i
                        py=j
                    board.delMove(h)
        return (maxv,px,py)

    
    def min(self,board):

        (r,c)=board.getDim()
        minv=50000000
        qx=None
        qy=None
        if board.isWon()==True:
            return (board._moves,0,0)

        for i in range(0,r):
            for j in range(0,c):
                if board.get(i,j)==0:
                    h=board.fakeMove(i,j,1)
                    print('min')
                    print(h)
                    print(minv)
                    print(board)
                    (m,max_i,max_j)=self.max(board)
                    if m<minv:
                        minv=m
                        qx=i
                        qy=j
                    board.delMove(h)
        return (minv,qx,qy)
        
