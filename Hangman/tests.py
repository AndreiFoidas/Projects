
import unittest
from Game import *
from Validations import *

class tests(unittest.TestCase):
    def testValidations(self):
        with self.assertRaises(ValueError):
            validateSentence('as asd asd')
        with self.assertRaises(ValueError):
            validateSentence('  ')
        self.assertEqual(validateSentence('andrei are mere'),'andrei are mere')
        self.assertEqual(validateSentence('    andrei are   mere  '),'andrei are mere')

    def testAddSentence(self):
        s=Sentnece('testSentences.txt')
        nr=len(s._sList)
        with self.assertRaises(ValueError):
            s.addSentence('anna has apples')
        self.assertEqual(nr,len(s._sList))
        #self.assertEqual(s.addSentence('ana has apples'),None)
        #self.assertEqual(nr+1,len(s._sList))

    
        