package Model.adt;
import Exception.InterpretorException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Dictionary<T1,T2> implements IDictionary<T1,T2> {
    private HashMap<T1, T2> dictionary;

    public Dictionary() {
        this.dictionary = new HashMap<T1,T2>();
    }

    @Override
    public void add(T1 v1, T2 v2) {
        this.dictionary.put(v1,v2);
    }

    @Override
    public void update(T1 v1, T2 v2) {
        this.dictionary.put(v1,v2);
    }

    @Override
    public void remove(T1 id) throws InterpretorException {
        if(!isDefined(id))
            throw new InterpretorException("Key does not exist!");
        else{
            this.dictionary.remove(id);
        }
    }

    @Override
    public T2 lookup(T1 id) {
        return this.dictionary.get(id);
    }

    @Override
    public HashMap<T1, T2> getDictionary() {
        return this.dictionary;
    }

    @Override
    public Set<T1> getKeySet() {
        return this.dictionary.keySet();
    }

    @Override
    public Collection<T2> getValues() {
        return this.dictionary.values();
    }

    @Override
    public boolean isDefined(T1 id) {
        return this.dictionary.containsKey(id);
    }

    @Override
    public String toString(){
        StringBuilder string=new StringBuilder();
        for(HashMap.Entry<T1,T2> element:this.dictionary.entrySet()){
            string.append("(");
            string.append(element.getKey());
            string.append("=");
            string.append(element.getValue());
            string.append(")");
            string.append(",");
        }
        if(string.length()!=0)
            string.deleteCharAt(string.length()-1);
        return string.toString();
    }

    public String toStringFile(){
        StringBuilder string=new StringBuilder();
        for(HashMap.Entry<T1,T2> element:this.dictionary.entrySet()){
            string.append("(");
            string.append(element.getKey());
            string.append(")");
            string.append(",");
        }
        if(string.length()!=0)
            string.deleteCharAt(string.length()-1);
        return string.toString();
    }

    @Override
    public void clearAll() {
        this.dictionary=new HashMap<T1,T2>();
    }

    @Override
    public String toFileFormat() {
        StringBuilder string=new StringBuilder();
        for(HashMap.Entry<T1,T2> element:this.dictionary.entrySet()){
            string.append(element.getKey());
            string.append(" -> ");
            string.append(element.getValue());
            string.append(System.getProperty("line.separator"));
        }
        return string.toString();
    }

    @Override
    public Dictionary<T1,T2> clone(){
        var clonedDictionary = this.dictionary.clone();
        var newDictionary = new Dictionary<T1, T2>();
        newDictionary.dictionary = (HashMap<T1, T2>) clonedDictionary;
        return newDictionary;
    }
}
