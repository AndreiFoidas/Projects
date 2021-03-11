
from Game import *
from Validations import *
import unittest

class testBoard(unittest.TestCase):
    
    def test_initt(self):
        b=Board(3,4)
        self.assertEqual(b._data,[[0,0,0,0],[0,0,0,0],[0,0,0,0]])
        ab=Board(3,3)
        self.assertEqual(ab._data,[[0,0,0],[0,0,0],[0,0,0]])
    
    def testGet(self):
        b=Board(4,4)
        b._data[1][2]=1
        self.assertEqual(b._data[1][2],b.get(1,2))
        self.assertEqual((4,4),b.getDim())

    def testIsWon(self):
        b=Board(2,2)
        self.assertFalse(b.isWon())
        b._moves=4
        self.assertTrue(b.isWon())

    def testMove(self):
        b=Board(4,3)
        with self.assertRaises(ValueError):
            b.move(5,5,'X')
        with self.assertRaises(ValueError):
            b.move(2,2,'Z')
        #print(b._data)
        b._data[2][2]=1
        with self.assertRaises(ValueError):
            b.move(2,2,'O')
        
        ab=Board(2,2)
        with self.assertRaises(GameWonException):
            ab.move(0,0,'X')

    def test_moveOnBoard(self):
        b=Board(5,5)
        b._moveOnBoard(3,3,1)
        self.assertEqual(b._moves,9)
        self.assertEqual(b._data[3][3],1)
        self.assertEqual(b._data[3][2],-1)
        self.assertEqual(b._data[3][4],-1)
        self.assertEqual(b._data[2][2],-1)
        self.assertEqual(b._data[4][2],-1)
        self.assertEqual(b._data[4][4],-1)
    
class testGameCompuer(unittest.TestCase):

    def testComputerLv0(self):
        b=Board(4,4)
        ai=ComputerLv0()
        g=Game(b,ai)
        g.computerMove()
        self.assertEqual(g._board._data[0][0],2)
        self.assertEqual(g._board._data[0][1],-1)
        self.assertEqual(g._board._data[1][1],-1)
        self.assertEqual(g._board._data[1][0],-1)
        self.assertEqual(g._board._moves,4)
        g.computerMove()
        self.assertEqual(g._board._data[0][2],2)

    def testComputerLv1(self):
        b=Board(3,5)
        ai=ComputerLv1()
        g=Game(b,ai)
        g.computerMove()
        self.assertGreaterEqual(g._board._moves,4)
        self.assertLessEqual(g._board._moves,9)

    def testPlayerMove(self):
        b=Board(4,5)
        ai=ComputerLv1()
        g=Game(b,ai)
        g.playerMove(2,2)
        self.assertEqual(g._board._data[2][2],1)
        self.assertEqual(g._board._moves,9)
        with self.assertRaises(ValueError):
            g.playerMove(1,1)
    
    def testGetBoard(self):
        b=Board(3,5)
        ai=ComputerLv1()
        g=Game(b,ai)
        self.assertEqual(g.getBoard(),b)

class testValidations(unittest.TestCase):

    def testValidation(self):
        x=3
        self.assertTrue(validate_convertion(x))
        x='4'
        self.assertTrue(validate_convertion(x))
        x='trei'
        self.assertFalse(validate_convertion(x))
        x='r'
        self.assertFalse(validate_convertion(x))

if __name__=='__main__':
    unittest.main()
