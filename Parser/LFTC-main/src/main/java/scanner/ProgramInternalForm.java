package scanner;

import domain.Pair;

import java.util.*;

public class ProgramInternalForm {
    private final HashMap<Integer, Pair<Integer, Integer>> pif = new HashMap<>();
    private final List<String> tokens = new ArrayList<>();

    public ProgramInternalForm() {}

    public void add(int code, Pair<Integer, Integer> position){
        pif.put(code, position);
    }

    public void addToTokens(String token){
        tokens.add(token);
    }

    public HashMap<Integer, Pair<Integer, Integer>> getPIF() {
        return this.pif;
    }

    public List<String> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return "ProgramInternalForm{" +
                "pif=" + pif +
                '}';
    }
}
