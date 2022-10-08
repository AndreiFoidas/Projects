package scanner;

import domain.*;

import java.io.FileWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Scanner {

    private final SymbolTable symbolTable = new SymbolTable(50);
    private final ProgramInternalForm pif = new ProgramInternalForm();
    private final Codification codification = new Codification();
    private final FiniteAutomaton finiteAutomaton = new FiniteAutomaton("src/files/identifierFA.in");

    public Scanner() {
    }

    public ProgramInternalForm getPif() {
        return pif;
    }

    public boolean scanFile(String fileName) {
        try {
            //System.out.println(codification.getCodes());

            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder output = new StringBuilder();
            int i=0;
            String line;
            boolean lexicallyCorrect = true;
            while((line = br.readLine()) != null){
                List<String> tokenList = this.tokenizeLine(line);
                //System.out.println(tokenList);
                boolean result = this.scanLine(tokenList, i, output);
                lexicallyCorrect = lexicallyCorrect && result;
                i++;
            }

            boolean sem = false;
            if(lexicallyCorrect) {
                System.out.println("Program is lexically correct!");
                sem = true;
            }
            else {
                System.out.println("Program has lexical errors!");
                sem = false;
            }

            //System.out.println(symbolTable);
            //System.out.println(output);
            // System.out.println(this.symbolTable);
            // System.out.println(this.pif);
            this.writeOutputToFile(fileName, output);
            return sem;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void writeOutputToFile(String fileName, StringBuilder output){
        try {
            fileName = "src/files/" + fileName.substring(9, fileName.length() - 4) + "Output.txt";
            FileWriter fw = new FileWriter(fileName);
            fw.write(this.symbolTable.toString());
            fw.write("\n");
            fw.write(output.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> tokenizeLine(String line){
        List<String> tokens = new ArrayList<>();

        int i = 0;
        while (i < line.length()){
            if (line.charAt(i) == '"'){
                StringBuilder stringToken = new StringBuilder("\"");
                i++;
                while (i < line.length() - 1 && line.charAt(i) != '"'){
                    stringToken.append(line.charAt(i));
                    i++;
                }
                stringToken.append(line.charAt(i));
                i++;

                tokens.add(stringToken.toString());
            }
            else if (line.charAt(i) == '\''){
                StringBuilder charToken = new StringBuilder("'");
                i++;
                while (i < line.length() - 1 && line.charAt(i) != '\''){
                    charToken.append(line.charAt(i));
                    i++;
                }
                charToken.append(line.charAt(i));
                i++;

                tokens.add(charToken.toString());
            }
            else if (this.isSeparator(String.valueOf(line.charAt(i)))){
                if (!(line.charAt(i) == ' ') && !(line.charAt(i) == '\t') && !(line.charAt(i) == '\n')){
                    tokens.add(String.valueOf(line.charAt(i)));
                }
                i++;
            }
            else if (this.isOperator(String.valueOf(line.charAt(i)))){
                if(this.isOperator(String.valueOf(line.charAt(i + 1)))){
                    tokens.add(line.charAt(i) + String.valueOf(line.charAt(i + 1)));
                    i = i + 2;
                }
                else {
                    tokens.add(String.valueOf(line.charAt(i)));
                    i++;
                }
            }
            else {
                // other tokens like reserved, constants and identifiers
                StringBuilder token = new StringBuilder(String.valueOf(line.charAt(i)));
                i++;
                while (i < line.length() && !this.isSeparator(String.valueOf(line.charAt(i)))
                        && !this.isOperator(String.valueOf(line.charAt(i)))){
                    token.append(line.charAt(i));
                    i++;
                }
                tokens.add(token.toString());
            }
        }

        return tokens;
    }

    public boolean scanLine(List<String> tokens, int line, StringBuilder output) {
        boolean lexicallyCorrect = true;
        ArrayList<String> specialCase = new ArrayList<>(Arrays.asList("(", "=", "==", "<", ">", "<=", ">=", "!="));
        String lastToken = "";
        for(int i=0; i < tokens.size(); ++i){
            String token = tokens.get(i);
            if (this.isIdentifier(token) && !this.isReservedWord(token)){
                int code = this.codification.getCodes().get("identifier"); // 0
                this.symbolTable.add(token);
                Pair<Integer, Integer> position = this.symbolTable.search(token);
                this.pif.add(code, position);
                this.pif.addToTokens("identifier");

                output.append("Token " + token + " on position: " + position + "\n");
            }
            else if (this.isConstant(token)){
                int code = this.codification.getCodes().get("constant");
                this.symbolTable.add(token);
                Pair<Integer, Integer> position = this.symbolTable.search(token);
                this.pif.add(code, position);
                this.pif.addToTokens("constant");

                output.append("Token " + token + " on position: " + position + "\n");
            }
            else if ((token.equals("-") || token.equals("+")) && (this.isNumber(tokens.get(i + 1))) &&
                    specialCase.contains(lastToken)){
                token += tokens.get(i + 1);
                i++;
                if (!token.equals("-0") && !token.equals("+0")){
                    int code = this.codification.getCodes().get("constant");
                    this.symbolTable.add(token);
                    Pair<Integer, Integer> position = this.symbolTable.search(token);
                    this.pif.add(code, position);
                    this.pif.addToTokens(token);

                    output.append("Token " + token + " on position: " + position + "\n");
                }
                else {
                    System.out.println("Error at line: " + line + ". Invalid token " + token);
                    output.append("Error at line: " + line + ". Invalid token " + token + "\n");
                    lexicallyCorrect = false;
                }
            }
            else if (this.isOperator(token) || this.isSeparator(token) || this.isReservedWord(token)){
                int code = this.codification.getCodes().get(token);
                this.pif.add(code, new Pair<Integer, Integer>(-1, -1));
                this.pif.addToTokens(token);

                output.append("Token " + token + " on position: " + new Pair<Integer, Integer>(-1, -1) + "\n");
            }
            else {
                System.out.println("Error at line: " + line + ". Invalid token " + token);
                output.append("Error at line: " + line + ". Invalid token " + token + "\n");
                lexicallyCorrect = false;
            }
            lastToken = token;
        }

        return lexicallyCorrect;
    }

    public boolean isSeparator(String token) {
        return this.codification.getSeparators().contains(token);
    }

    public boolean isOperator(String token){
        return this.codification.getOperators().contains(token);
    }

    public boolean isReservedWord(String token){
        return this.codification.getReservedWords().contains(token);
    }

    public boolean isIdentifier(String token){
        //String pattern = "^[a-zA-Z_]([a-zA-Z0-9_]*$)";
        //return token.matches(pattern);
        if(!this.finiteAutomaton.getFileName().equals("src/files/identifierFA.in"))
            this.finiteAutomaton.readFromFile("src/files/identifierFA.in");
        return this.finiteAutomaton.acceptsSequence(token);
    }

    public boolean isConstant(String token){
        //String number = "^([1-9][0-9]*)|0$";
        String string = "^\"[a-zA-Z0-9_.:;,?!*' ]*\"$";
        String character = "^\'[a-zA-Z0-9_.:;,?!*\" ]\'$";
        if(!this.finiteAutomaton.getFileName().equals("src/files/integerFA.in"))
            this.finiteAutomaton.readFromFile("src/files/integerFA.in");
        //return token.matches(number) || token.matches(string) || token.matches(character);
        return this.finiteAutomaton.acceptsSequence(token) || token.matches(string) || token.matches(character);
    }

    public boolean isNumber(String token){
        String number = "^([1-9][0-9]*)|0$";
        return token.matches(number);
    }
}
