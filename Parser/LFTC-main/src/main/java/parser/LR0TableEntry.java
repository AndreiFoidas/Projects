package parser;

import domain.Pair;

import java.util.*;

public class LR0TableEntry {
    public int stateIndex;
    public String action;
    public String reduceNonTerminal;
    public List<String> reduceContent = new ArrayList<>();
    public List<Pair<String, Integer>> shifts = new ArrayList<>();

    public String reduceProductionString(){
        return this.reduceNonTerminal + " -> " + this.reduceContent;
    }

    @Override
    public String toString() {
        return "LR0TableEntry: " +
                "stateIndex=" + stateIndex +
                ", action='" + action + '\'' +
                ", reduceNonTerminal='" + reduceNonTerminal + '\'' +
                ", reduceContent=" + reduceContent +
                ", shifts=" + shifts;
    }
}
