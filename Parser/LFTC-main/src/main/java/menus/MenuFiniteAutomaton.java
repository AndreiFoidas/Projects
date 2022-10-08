package menus;

import scanner.FiniteAutomaton;

import java.util.Scanner;

public class MenuFiniteAutomaton {
    private FiniteAutomaton finiteAutomaton;

    public MenuFiniteAutomaton(FiniteAutomaton finiteAutomaton){
        this.finiteAutomaton = finiteAutomaton;
    }

    public void displayMenu(){
        System.out.println();

        System.out.println("1. Display FA");
        System.out.println("2. Display states");
        System.out.println("3. Display alphabet");
        System.out.println("4. Display transitions");
        System.out.println("5. Display initial state");
        System.out.println("6. Display final states");
        System.out.println("7. Check is FA is valid");
        System.out.println("8. Check if FA is deterministic");
        System.out.println("9. Check if a sequence is accepted by the FA");
        System.out.println("10. Read FA from file");
        System.out.println("0. Exit");
    }

    public void run(){
        Scanner in = new Scanner(System.in);
        while (true){
            this.displayMenu();
            System.out.print("->");
            String command = in.nextLine();
            if (command.equals("1")){
                System.out.println(this.finiteAutomaton);
            }
            else if (command.equals("2")){
                System.out.println(this.finiteAutomaton.getStates());
            }
            else if (command.equals("3")){
                System.out.println(this.finiteAutomaton.getAlphabet());
            }
            else if (command.equals("4")){
                System.out.println(this.finiteAutomaton.getTransitions());
            }
            else if (command.equals("5")){
                System.out.println(this.finiteAutomaton.getInitialState());
            }
            else if (command.equals("6")){
                System.out.println(this.finiteAutomaton.getFinalStates());
            }
            else if (command.equals("7")){
                if (this.finiteAutomaton.isValid()){
                    System.out.println("FA is valid!");
                }
                else{
                    System.out.println("FA is NOT valid!");
                }
            }
            else if (command.equals("8")){
                if (this.finiteAutomaton.isDeterministic()){
                    System.out.println("FA is deterministic!");
                }
                else{
                    System.out.println("FA is NOT deterministic!");
                }
            }
            else if (command.equals("9")){
                String sequence = in.nextLine();
                if (this.finiteAutomaton.acceptsSequence(sequence)){
                    System.out.println("FA accepts " + sequence + "!");
                }
                else{
                    System.out.println("FA DOES NOT accept " + sequence + "!");
                }
            }
            else if (command.equals("10")){
                String fileName = in.nextLine();
                this.finiteAutomaton.readFromFile(fileName);
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
