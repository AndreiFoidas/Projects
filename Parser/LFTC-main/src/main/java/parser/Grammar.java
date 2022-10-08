package parser;

import domain.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Grammar {
    String fileName;
    private final List<String> terminals;
    private final List<String> nonTerminals;
    private String startingSymbol;
    private final HashMap<List<String>, List<List<String>>> productions;
    private final List<Pair<List<String>, List<String>>> productionOrder;

    public Grammar(String fileName){
        this.terminals = new ArrayList<>();
        this.nonTerminals = new ArrayList<>();
        this.productions = new HashMap<>();
        this.fileName = fileName;
        this.productionOrder = new ArrayList<>();

        readGrammar();
    }

    public void readGrammar(){

        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = br.readLine();
            if (line != null){
                this.nonTerminals.addAll(Arrays.asList(line.split(" ")));
            }
            line = br.readLine();
            if (line != null){
                this.terminals.addAll(Arrays.asList(line.split(" ")));
            }
            line = br.readLine();
            if (line != null){
                this.startingSymbol = line.strip();
            }

            line = br.readLine();
            while (line != null){
                if(line.length() == 0){
                    line = br.readLine();
                    continue;
                }

                String[] tokens = line.split("->");


                List<List<String>> rules = new ArrayList<>();
                List<String> allRules = List.of(tokens[1].split(("\\|")));
                List<String> symbols = List.of(tokens[0].split(" "));

                for (String rule: allRules){
                    List<String> toAdd = new ArrayList<>(List.of(rule.split(" ")));
                    toAdd.remove("");
                    rules.add(toAdd);
                    this.productionOrder.add(new Pair<>(symbols, toAdd));
                }

                if(!this.productions.containsKey(symbols)) {
                    this.productions.put(symbols, rules);
                }
                else {
                    for( var r: rules)
                        this.productions.get(symbols).add(r);
                }


                line = br.readLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<List<String>> getProductionForNonTerminal(String nonTerminal){
        List<List<String>> result = new ArrayList<>();
        Set<List<String>> keys = this.productions.keySet();
        for(List<String> key: keys){
            if(key.contains(nonTerminal)){
                result.addAll(this.productions.get(key));
            }
        }

        return result;
    }

    public boolean isValid(){
        if(!this.nonTerminals.contains(this.startingSymbol))
            return false;
        Set<List<String>> keys = this.productions.keySet();
        for(List<String> key: keys){
            for(String each: key){
                if(!this.nonTerminals.contains(each))
                    return false;
            }

            List<List<String>> rules = this.productions.get(key);
            for(List<String> rule: rules){
                for(String term: rule){
                    if(!this.terminals.contains(term) && !this.nonTerminals.contains(term) && !term.equals("epsilon")) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isCFG(){
        Set<List<String>> keys = this.productions.keySet();
        boolean checkStart = false;
        for(List<String> key: keys) {
            if (key.contains(this.startingSymbol)) {
                checkStart = true;
                break;
            }
        }
        if(!checkStart) {
            System.out.println("Failed check start!");
            return false;
        }

        for(List<String> key: keys){
            if(key.size() > 1) {
                System.out.println("More than one on left!");
                return false;
            }
            if(!this.nonTerminals.contains(key.get(0))) {
                System.out.println("NonTerminal doesnt contain key!");
                System.out.println(key.get(0));
                return false;
            }

            List<List<String>> rules = this.productions.get(key);
            for(List<String> rule: rules){
                for(String term: rule){
                    if(!this.terminals.contains(term) && !this.nonTerminals.contains(term) && !term.equals("epsilon")) {
                        System.out.println("Not terminal or nonterminal or epsilon");
                        System.out.println(term);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public Map<List<String>, List<List<String>>> getProductions() {
        return productions;
    }

    public List<Pair<List<String>, List<String>>> getProductionOrder() {
        return productionOrder;
    }

    public List<String> getTerminalsAndNonTerminals(){
        List<String> rez = new ArrayList<>();
        rez.addAll(this.terminals);
        rez.addAll(this.nonTerminals);
        return rez;
    }

    @Override
    public String toString() {
        return "Grammar:" +
                "\nfileName='" + fileName + '\'' +
                "\nnonTerminals=" + nonTerminals +
                "\nterminals=" + terminals +
                "\nstartingSymbol='" + startingSymbol + '\'' +
                "\nproductions=" + productions;
    }
}
