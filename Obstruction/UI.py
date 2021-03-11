
from Game import *
from Validations import *
import sys

class UI:
    def __init__(self,game,playerStart):
        self._game=game
        self._ps=playerStart

    def _readPlayerMove(self):
        cmd=input('>').split(" ")
        if len(cmd)!=2:
            raise ValueError('Invalid input!')
        if validate_convertion(cmd[0])==True and validate_convertion(cmd[1])==True:
            return (int(cmd[0])-1,int(cmd[1])-1)
        else:
            print('Please insert integer values!')
            return self._readPlayerMove()

    def restart(self):
        print('Do you wish to restart?\n1. Yes\n2. No')
        sem=True
        while sem==True:
            sem=False
            choice=input('>')
            if choice=='1':
                s=Start()
                s.startt()
            elif choice=='2':
                sys.exit()
            else:
                print('Bad command!')
                sem=True

    def start(self):
        b=self._game.getBoard()

        playerMove=self._ps
        while True:
            print('')
            print(b)
            try:  
                if playerMove==True:
                    square=self._readPlayerMove()
                    self._game.playerMove(square[0],square[1])
                else:
                    self._game.computerMove()
                playerMove=not playerMove
            except GameWonException:
                print(b)
                if playerMove==True:
                    print('You won!')
                else:
                    print('You lost!')

                self.restart()
            except ValueError as e:
                print(str(e))
                continue



class Start:
    def __init__(self):
        (r,c)=self._readBoard()
        self._rows=r
        self._columns=c

    def _readBoard(self):
        print('\nInsert the dimensions of the board:')
        rows=input('Rows: ')
        columns=input('Columns: ')
        if validate_convertion(rows)==True and validate_convertion(columns)==True:
            return (int(rows),int(columns))
        else:
            print('Please insert integer values!')
            return self._readBoard()

    def startt(self):
        b=Board(self._rows,self._columns)
        #ai=ComputerLv0()
        #ai=ComputerLv1()
        ai=ComputerLv2()
        #ai=ComputerLv3()
        g=Game(b,ai)
        print('Do you wish to start first?\n1. Yes\n2. No')
        sem=True
        while sem==True:
            sem=False
            choice=input('>')
            if choice=='1':
                ps=True
            elif choice=='2':
                ps=False
            else:
                print('Bad command!')
                sem=True
        ui=UI(g,ps)
        ui.start()

s=Start()
s.startt()


    