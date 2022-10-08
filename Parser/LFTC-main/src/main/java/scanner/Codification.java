package scanner;

import java.util.*;
import java.util.Arrays;
import java.util.HashMap;

public class Codification {

    private final String[] separators = {"[", "]", "(", ")", "{", "}", ":", ";", " ", ",", "\n", "\t"};
    private final String[] operators = {"+", "-", "/", "*", "%", "=", "<", "<=", "==", ">=", ">", "!=", "!", "&&", "||"};
    private final String[] reservedWords = {"int", "add", "sub", "mul", "div", "mod", "pow", "string", "bool", "char", "return", "if", "else", "while", "do", "read", "write", "struct", "START", "END."};

    private HashMap<String, Integer> codes;

    public Codification(){
        initialiseCodes();
    }

    private void initialiseCodes(){
        this.codes = new HashMap<>();

        codes.put("identifier", 0);
        codes.put("constant", 1);

        int i = 2;
        for(String s: separators){
            codes.put(s, i);
            i++;
        }
        for(String o: operators){
            codes.put(o, i);
            i++;
        }
        for(String rw: reservedWords){
            codes.put(rw, i);
            i++;
        }
    }

    public List<String> getSeparators() {
        return Arrays.asList(separators);
    }

    public List<String> getOperators() {
        return Arrays.asList(operators);
    }

    public List<String> getReservedWords() {
        return Arrays.asList(reservedWords);
    }

    public HashMap<String, Integer> getCodes() {
        return codes;
    }
}
