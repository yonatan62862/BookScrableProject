package test;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
    private static DictionaryManager instance;
    private Map<String, Dictionary> books;

    private DictionaryManager() {
        books = new HashMap<>();
    }

    public static DictionaryManager get(){
        if(instance==null){
            instance = new DictionaryManager();
        }
        return instance;
    }


    public boolean query(String...bookNames){
        boolean ok = false;
        for(int i=0;i<bookNames.length-1;i++){
            if(!books.containsKey(bookNames[i]))
                books.put(bookNames[i], new Dictionary(bookNames[i]));
            if(books.get(bookNames[i]).query(bookNames[bookNames.length-1]))
                ok=true;
        }
        return ok;
    }

public boolean challenge(String...bookNames){
    boolean ok = false;
    for(int i=0;i<bookNames.length-1;i++){
        if(!books.containsKey(bookNames[i]))
            books.put(bookNames[i], new Dictionary(bookNames[i]));
        if(books.get(bookNames[i]).challenge(bookNames[bookNames.length-1]))
            ok=true;

    }
    return ok;
}

    public int getSize() {
        return books.size();
    }

}
