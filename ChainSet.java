
import java.util.LinkedList;


public class ChainSet<T> implements SetInterface<T> {

    private final static int DEFAULT_CAPACİTY = 53;

    private int size = 0;
    private int hash_capacity = 0;

    private double load_factor = 0;

    private LinkedList<Object>[] hash_table = null;

    
    public ChainSet(int capacity){

        hash_capacity = capacity;

        hash_table = new LinkedList[hash_capacity];

        createHashTable();

    }

    private void createHashTable(){

        for(int i=0; i<hash_table.length; i++) hash_table[i] = new LinkedList<Object>();

    }

    public ChainSet(){

        this(DEFAULT_CAPACİTY);

    }

    @Override
    public int hash_code(T key) {
        return key.hashCode();
    }

    @Override
    public int compress(T key) {
        return hash_code(key) % hash_capacity;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    public boolean isLoadFactor() {
        return load_factor >= 0.75;
    }

    @Override
    public boolean add(T e) {

        if(contains(e)) return false;
        
        if(isLoadFactor()) {

           hash_capacity = 2*hash_capacity;

           LinkedList<Object>[] table = new LinkedList[hash_capacity];

           for(int i=0; i<table.length; i++) table[i] = new LinkedList<>();

           for(int i=0; i<hash_table.length; i++){

             table[i] = hash_table[i];

           }

           hash_table = table;
        
        }

        int index = compress(e);

        hash_table[index].add(e);

        size++;

        load_factor = (double) size / hash_capacity;
    
        return true;

    }

    @Override
    public boolean contains(Object o) {

        int index = 0;

        try{
            index = compress((T)o);
        }
        catch(Exception e){
            return false;
        }

        if(hash_table[index].contains(o)) return true;

        else return false;

    }

    @Override
    public boolean remove(Object o) {

        int index = 0;

        try{
            index = compress((T)o);
        }
        catch(Exception e){
            return false;
        }

        if(contains(o)){
            hash_table[index].remove(o);
            size--;
            load_factor = size / hash_capacity;
            return true;
        }
        else{
            return false;
        }

    }


    @Override
    public void clear() {

        createHashTable();

    }

    public void prinTable(){

        for(int i=0; i<hash_capacity; i++){

            System.out.println(hash_table[i]);

        }

    }


    

    public static void main(String[] args) {

        ChainSet<Integer> set = new ChainSet<>(50);

        for(int i=0; i<32; i++)
        set.add(i);
        set.remove(2);
        set.add(50);
        set.prinTable();
        System.out.println(set.contains(2));
        

    }

    

    

    
    
}
