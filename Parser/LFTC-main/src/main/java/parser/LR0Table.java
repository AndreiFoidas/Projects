package parser;

import java.util.*;

public class LR0Table {
    public List<LR0TableEntry> entries;

    public LR0Table() {
        this.entries = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "LR0Table: \n" +
                "entries=" + entries;
    }
}
