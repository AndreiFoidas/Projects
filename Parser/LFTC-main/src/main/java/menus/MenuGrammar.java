package menus;

import parser.Grammar;

import java.util.Scanner;

public class MenuGrammar {
    private Grammar grammar;

    public MenuGrammar(Grammar grammar){
        this.grammar = grammar;
    }

    public void displayMenu(){
        System.out.println();

        System.out.println("1. Display Grammar");
        System.out.println("2. Display nonterminals");
        System.out.println("3. Display terminals");
        System.out.println("4. Display productions");
        System.out.println("5. Display productions for nonterminal");
        System.out.println("6. Check if Grammar is cfg");
        System.out.println("7. Read Grammar from file");
        System.out.println("0. Exit");
    }

    public void run(){
        Scanner in = new Scanner(System.in);
        while (true){
            this.displayMenu();
            System.out.print("->");
            String command = in.nextLine();
            if (command.equals("1")){
                System.out.println(this.grammar);
            }
            else if (command.equals("2")){
                System.out.println(this.grammar.getNonTerminals());
            }
            else if (command.equals("3")){
                System.out.println(this.grammar.getTerminals());
            }
            else if (command.equals("4")){
                System.out.println(this.grammar.getProductions());
            }
            else if (command.equals("5")){
                System.out.print("->");
                String nonterminal = in.nextLine();
                System.out.println(this.grammar.getProductionForNonTerminal(nonterminal));
            }
            else if (command.equals("6")){
                if (this.grammar.isCFG()){
                    System.out.println("Grammar is cfg!");
                }
                else{
                    System.out.println("Grammar is NOT cfg!");
                }
            }
            else if (command.equals("7")){
                this.grammar.readGrammar();
            }
            else if (command.equals("0")){
                break;
            }
            else{
                System.out.println("Invalid command!");
            }
        }
    }
}
