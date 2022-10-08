package parser;

import java.util.*;

public class LR0Item {
    private final String nonTerminal;
    private final int dotPosition;
    private final List<String> content;

    public LR0Item(String nonTerminal, int dotPosition, List<String> content){
        this.nonTerminal = nonTerminal;
        this.dotPosition = dotPosition;
        this.content = content;
    }

    public String getNonTerminal() {
        return nonTerminal;
    }

    public int getDotPosition() {
        return dotPosition;
    }

    public List<String> getContent() {
        return content;
    }

    public boolean dotIsLast(){
        return this.dotPosition == this.content.size();
    }

    public String toProductionString() {
        return this.nonTerminal + " -> " + this.getContent();
    }

        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LR0Item)) return false;
        LR0Item lr0Item = (LR0Item) o;
        return getDotPosition() == lr0Item.getDotPosition() &&
                Objects.equals(getNonTerminal(), lr0Item.getNonTerminal()) &&
                Objects.equals(getContent(), lr0Item.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNonTerminal(), getDotPosition(), getContent());
    }

    @Override
    public String toString() {
        return "{" +
                nonTerminal + "->" + content +
                ", dot=" + dotPosition +
                '}';
    }
}
