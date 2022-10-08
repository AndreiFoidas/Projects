package parser;

import domain.Pair;
import scanner.Scanner;

import java.util.*;

public class Parser {
    private final Grammar grammar;
    private final LR0Table table;
    private List<List<LR0Item>> canonicalCollection;

    public Parser(Grammar grammar){
        this.grammar = grammar;
        this.table = new LR0Table();

        this.buildLR0Table();
    }

    public List<LR0Item> goTo(List<LR0Item> state, String symbol){
        List<LR0Item> items = new ArrayList<>();
        for(LR0Item item: state){
            if(item.getDotPosition() < item.getContent().size() && item.getContent().get(item.getDotPosition()).equals(symbol)){
                items.add(item);
            }
        }

        for(int i = 0; i < items.size(); ++i){
            LR0Item item = items.get(i);
            LR0Item newItem = new LR0Item(item.getNonTerminal(), item.getDotPosition() + 1, item.getContent());
            items.set(i, newItem);
        }

        return this.closure(items);
    }

    public List<LR0Item> closure(List<LR0Item> items){
        List<LR0Item> closure = new ArrayList<>(items);

        int index = 0;
        while (index < closure.size()){
            LR0Item item = closure.get(index);
            int dotPosition = item.getDotPosition();
            if (dotPosition < item.getContent().size()) {
                String symbol = item.getContent().get(dotPosition);
                if (this.grammar.getNonTerminals().contains(symbol)) {
                    for (List<String> production : this.grammar.getProductionForNonTerminal(symbol)) {
                        LR0Item newItem = new LR0Item(symbol, 0, production);
                        if (!closure.contains(newItem))
                            closure.add(newItem);
                    }
                }
            }

            index++;
        }

        return closure;
    }

    public List<List<LR0Item>> canonicalCollection(){
        List<List<LR0Item>> collection = new ArrayList<>();

        LR0Item startingItem = new LR0Item("S'", 0, Collections.singletonList(this.grammar.getStartingSymbol()));

        List<LR0Item> startingState = new ArrayList<>();
        startingState.add(startingItem);
        List<LR0Item> s0 = closure(startingState);
        collection.add(s0);

        System.out.println("s0 = " + s0);

        int index = 0;
        while(index < collection.size()){
            List<LR0Item> state = collection.get(index);
            for (String symbol: this.grammar.getTerminalsAndNonTerminals()){
                List<LR0Item> result = this.goTo(state, symbol);
                if(!result.isEmpty() && !collection.contains(result)){

                    System.out.println("s" + collection.size() + " = goTo(s" + collection.indexOf(state) + ", " + symbol + ") = " + result);
                    collection.add(result);
                }
            }

            index++;
        }

        return collection;
    }

    public void buildLR0Table(){
        this.canonicalCollection = this.canonicalCollection();

        for(int i=0; i<this.canonicalCollection.size(); i++){
            List<LR0Item> state = this.canonicalCollection.get(i);

            LR0TableEntry entry = new LR0TableEntry();
            entry.stateIndex = i;

            String action = "";
            if (state.stream().anyMatch(it -> !it.dotIsLast()))
                action = "shift";
            else if(state.stream().anyMatch(it -> it.getNonTerminal().equals("S'") && it.dotIsLast()))
                action = "accept";
            else if (state.stream().anyMatch(LR0Item::dotIsLast)){
                LR0Item production = state.stream().filter(LR0Item::dotIsLast).findAny().orElse(null);
                entry.reduceNonTerminal = production.getNonTerminal();
                entry.reduceContent = production.getContent();
                action = "reduce " + production.toProductionString();
            }

            if (action.equals(""))
                action = "error";

            this.checkForConflicts(state);

            entry.action = action;

            List<Pair<String, Integer>> goTos = new ArrayList<>();
            for(String symbol: this.grammar.getTerminalsAndNonTerminals()){
                List<LR0Item> result = this.goTo(state, symbol);
                if(!result.isEmpty()){
                    int stateIndex = this.canonicalCollection.indexOf(result);
                    goTos.add(new Pair<>(symbol, stateIndex));
                }
            }

            entry.shifts = goTos;
            this.table.entries.add(entry);

            System.out.println(this.table.entries.get(i));
        }
    }

    public void parse(Stack<String> inputStack){
        Stack<Pair<String, Integer>> workingStack = new Stack<>();
        Stack<String> outputStack = new Stack<>();
        Stack<Integer> outputNumberStack = new Stack<>();


        String lastSymbol = "";
        int stateIndex = 0;
        boolean sem = true;
        workingStack.push(new Pair<>(lastSymbol, stateIndex));
        LR0TableEntry lastEntry = null;
        String onError = null;
        try{
            do{
                if (!inputStack.isEmpty())
                    onError = inputStack.peek();
                lastEntry = this.table.entries.get(stateIndex);
                LR0TableEntry entry = this.table.entries.get(stateIndex);

                if (entry.action.equals("shift")){
                    String c = inputStack.pop();
                    var state = entry.shifts.stream()
                            .filter(it -> it.getFirst().equals(c)).findAny().orElse(null);
                    stateIndex = state.getSecond();
                    lastSymbol = c;
                    workingStack.push(new Pair<>(lastSymbol, stateIndex));
                } else if (entry.action.equals("reduce " + entry.reduceProductionString())) {
                    List<String> reduceContent = new ArrayList<>();
                    reduceContent.addAll(entry.reduceContent);
                    while (reduceContent.contains(workingStack.peek().getFirst()) && !workingStack.isEmpty()){
                        reduceContent.remove(workingStack.peek().getFirst());
                        workingStack.pop();
                    }
                    var state = this.table.entries.get(workingStack.peek().getSecond()).shifts.stream()
                            .filter(it -> it.getFirst().equals(entry.reduceNonTerminal)).findAny().orElse(null);
                    stateIndex = state.getSecond();
                    lastSymbol = entry.reduceNonTerminal;
                    workingStack.push(new Pair<>(lastSymbol, stateIndex));
                    outputStack.push(entry.reduceProductionString());
                    var index = new Pair<>(List.of(entry.reduceNonTerminal), entry.reduceContent);
                    int productionNumber = this.grammar.getProductionOrder().indexOf(index);
                    outputNumberStack.push(productionNumber);
                } else {
                    if (entry.action.equals("accept")){
                        List<String> output = new ArrayList<>(outputStack);
                        Collections.reverse(output);
                        List<Integer> numberOutput = new ArrayList<>(outputNumberStack);
                        Collections.reverse(numberOutput);

                        System.out.println("\nACCEPTED");
                        System.out.println("Productions string: " + output);
                        System.out.println("Production number: " + numberOutput);

                        OutputTree outputTree = new OutputTree(this.grammar);
                        outputTree.generateTreeFromSequence(numberOutput);
                        System.out.println("The output tree: ");
                        outputTree.printTree(outputTree.getRoot());

                        sem = false;
                    }
                    if(entry.action.equals("error")){
                        System.out.println("ERROR at state: " + stateIndex);
                        sem = false;
                    }
                }
            } while (sem);
        } catch (NullPointerException ex) {
            System.out.println("ERROR at state " + stateIndex + " - before symbol " + onError);
            System.out.println(lastEntry);
        }
    }

    public void checkForConflicts(List<LR0Item> state) {
        long countDotOnFinalPosition = state.stream().filter(LR0Item::dotIsLast).count();
        long countDotInMiddle = state.stream().filter(it -> !it.dotIsLast()).count();

        if (countDotOnFinalPosition > 1){
            //throw new RuntimeException("REDUCE - REDUCE conflict for state \n" + state + "\n\nThe given grammar is not LR(0)!");
            System.out.println("REDUCE - REDUCE conflict for state \n" + state + "\n\nThe given grammar is not LR(0)!");
        }
        if(countDotOnFinalPosition == 1 && countDotInMiddle > 0){
            //throw new RuntimeException("SHIFT - REDUCE conflict for state \n" + state + "\n\nThe given grammar is not LR(0)!");
            System.out.println("SHIFT - REDUCE conflict for state \n" + state + "\n\nThe given grammar is not LR(0)!");
        }
    }

    public void parseSequence(String sequence){
        Stack<String> inputStack = new Stack<>();
        Arrays.stream(new StringBuilder(sequence).reverse().toString().split( "" )).forEach(inputStack::push);
        this.parse(inputStack);
    }

    public void parseFile(String fileName){
        Stack<String> inputStack = new Stack<>();

        Scanner scanner = new Scanner();
        boolean correct = scanner.scanFile(fileName);

        if(!correct)
            return;

        ArrayList<String> pifElements = (ArrayList<String>) scanner.getPif().getTokens();

        for(int i = pifElements.size() - 1; i >= 0; i--){
            inputStack.push(pifElements.get(i));
        }
        this.parse(inputStack);
    }
}
