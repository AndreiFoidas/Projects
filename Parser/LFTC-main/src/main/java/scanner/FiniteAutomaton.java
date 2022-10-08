package scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class FiniteAutomaton {
    private List<String> states;
    private List<String> alphabet;
    private List<String> finalStates;
    private String initialState;
    private Map<AbstractMap.SimpleEntry<String, String>, List<String>> transitions;
    private String fileName;

    public FiniteAutomaton(String filename){
        this.states = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.transitions = new HashMap<>();
        this.fileName = filename;

        readFromFile(filename);
    }

    public FiniteAutomaton(List<String> states, List<String> alphabet, List<String> finalStates, String initialState, Map<AbstractMap.SimpleEntry<String, String>, List<String>> transitions) {
        this.states = states;
        this.alphabet = alphabet;
        this.finalStates = finalStates;
        this.initialState = initialState;
        this.transitions = transitions;
    }

    public void readFromFile(String fileName){
        this.states = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.transitions = new HashMap<>();
        this.initialState = "";
        this.fileName = fileName;
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line = br.readLine();
            if (line != null){
                this.states.addAll(Arrays.asList(line.split(" ")));
            }
            line = br.readLine();
            if (line != null){
                this.alphabet.addAll(Arrays.asList(line.split(" ")));
            }
            line = br.readLine();
            if (line != null){
                this.initialState = line.strip();
            }
            line = br.readLine();
            if (line != null){
                this.finalStates.addAll(Arrays.asList(line.split(" ")));
            }
            line = br.readLine();
            while (line != null){
                String[] tokens = line.split(" ");
                AbstractMap.SimpleEntry<String, String> key = new AbstractMap.SimpleEntry<>(tokens[0], tokens[1]);
                if(this.transitions.containsKey(key)){
                    if(!this.transitions.get(key).contains(tokens[2]))
                        this.transitions.get(key).add(tokens[2]);
                }
                else{
                    ArrayList<String> value = new ArrayList<>(Collections.singletonList(tokens[2]));
                    this.transitions.put(key, value);
                }
                line = br.readLine();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isDeterministic(){
        var keys = this.transitions.keySet();
        for (AbstractMap.SimpleEntry<String, String> key: keys){
            if (this.transitions.get(key).size() > 1)
                return false;
        }
        return true;
    }

    public boolean isValid(){
        if (!this.states.contains(this.initialState))
            return false;
        for (String fState: this.finalStates){
            if(!this.states.contains(fState))
                return false;
        }
        var keys = this.transitions.keySet();
        for (AbstractMap.SimpleEntry<String, String> key: keys){
            if (!this.states.contains(key.getKey()))
                return false;
            if (!this.alphabet.contains(key.getValue()))
                return false;
            List<String> list = this.transitions.get(key);
            for(String token:list){
                if(!this.states.contains(token))
                    return false;
            }
        }
        return true;
    }

    public boolean acceptsSequence(String sequence){
        if(this.isDeterministic()){
            if(this.finalStates.contains(initialState) && (sequence.equals("\n") || sequence.length() ==0 ))
                return true;

            String state = this.initialState;
            for(char c: sequence.toCharArray()){
                var key = new AbstractMap.SimpleEntry<>(state, String.valueOf(c));
                if(this.transitions.containsKey(key)){
                    state = this.transitions.get(key).get(0);
                }
                else {
                    return false;
                }
            }

            return this.finalStates.contains(state);
        }
        else{
            System.out.println("This is not a DFA!");
            return false;
        }
    }

    public List<String> getStates() {
        return this.states;
    }

    public List<String> getAlphabet() {
        return this.alphabet;
    }

    public List<String> getFinalStates() {
        return this.finalStates;
    }

    public String getInitialState() {
        return this.initialState;
    }

    public Map<AbstractMap.SimpleEntry<String, String>, List<String>> getTransitions() {
        return this.transitions;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("FiniteAutomaton:" +
                "\nstates=" + this.states +
                "\nalphabet=" + this.alphabet +
                "\nfinalStates=" + this.finalStates +
                "\ninitialState='" + this.initialState + '\'' +
                "\ntransitions=" + this.transitions);
        return stringBuilder.toString();
    }
}
