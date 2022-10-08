import menus.MenuFiniteAutomaton;
import menus.MenuGrammar;
import parser.Parser;
import scanner.FiniteAutomaton;
import parser.Grammar;
import scanner.Scanner;
import scanner.SymbolTable;

public class Main {

    public static void testSymbolTable(){
        SymbolTable ht = new SymbolTable(20);
        ht.add("a");
        ht.add("b");
        ht.add("c");
        ht.add("a");
        ht.add("abc");
        ht.add("cba");

        System.out.println(ht.contains("a"));
        System.out.println(ht.contains("b"));
        System.out.println(ht.contains("abc"));
        System.out.println(ht.contains("d"));
        System.out.println(ht.contains("e"));

        System.out.println(ht.search("a"));
        System.out.println(ht.search("b"));
        System.out.println(ht.search("d"));
        System.out.println(ht.search("abc"));
        System.out.println(ht.search("cba"));
    }

    public static void testFiniteAutomaton(){
        FiniteAutomaton finiteAutomaton = new FiniteAutomaton("src/files/FA.in");
        MenuFiniteAutomaton menu = new MenuFiniteAutomaton(finiteAutomaton);
        menu.run();
    }

    public static void testGrammar(){
        Grammar g = new Grammar("src/files/gr.in");
        MenuGrammar menu = new MenuGrammar(g);
        System.out.println(g);
        System.out.println("Is it valid? " + g.isValid());
        System.out.println("Is it cfg? " + g.isCFG());

        menu.run();
    }

    public static void testParser(){
        Grammar g = new Grammar("src/files/grammarBun.in");
        System.out.println(g.isValid());
        System.out.println(g.isCFG());
        Parser p = new Parser(g);
        System.out.println();
        p.parseFile("src/files/p1.txt");
    }

    public static void main(String[] args) {
        //testSymbolTable();
        /*Scanner s = new Scanner();
        s.scanFile("src/files/p1.txt");
        System.out.println();
        Scanner s1 = new Scanner();
        s1.scanFile("src/files/p2.txt");
        System.out.println();
        Scanner s2 = new Scanner();
        s2.scanFile("src/files/p3.txt");
        System.out.println();
        Scanner s3 = new Scanner();
        s3.scanFile("src/files/p1err.txt");
        System.out.println();
        */
        //testFiniteAutomaton();
        //testGrammar();
        Grammar g = new Grammar("src/files/gr.in");
        Parser p = new Parser(g);

        p.parseSequence("abbc");
        // p.parseSequence("(x,())");
       testParser();
    }
}
