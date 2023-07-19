import java.util.Arrays;
import java.util.LinkedList;

public class LinearProbeSet<T> implements SetInterface<T> {

    private static final Object REMOVED = new Object();
    private static final Object EMPTY = null;

    private final static int DEFAULT_CAPACİTY = 53;

    private int size = 0;
    private int hash_capacity = 0;

    private double load_factor = 0;

    private Object[] hash_table = null;

    public LinearProbeSet(int capacity){

        hash_capacity = capacity;

        hash_table = new Object[hash_capacity];
    }

    public LinearProbeSet(){

        this(DEFAULT_CAPACİTY);

    }

    public boolean isLoadFactor() {
        return load_factor >= 0.75;
    }

    @Override
    public int hash_code(T key) {
        return key.hashCode();
    }

    @Override
    public int compress(T key) {
        return key.hashCode() % hash_capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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

        if(hash_table[index] == EMPTY){

            return false;

        }

        else if(hash_table[index].equals(o)){

            return true;

        }

        else{

            int hash_index = index;

            do{

                if(hash_table[index].equals(o)) return true;

                index = (index + 1) % hash_capacity;


            }while(hash_table[index] != EMPTY && index != hash_index);

            return false;

        }


    }

    @Override
    public boolean add(T e) {

        if(contains(e)){

            return false;

        }

        if(isLoadFactor()){

           hash_capacity = 2*hash_capacity;

           Object[] table = new Object[hash_capacity];

           for(int i=0; i<hash_table.length; i++){

             table[i] = hash_table[i];

           }

           hash_table = table;

        }

        int index = compress(e);

        while(hash_table[index] != EMPTY && hash_table[index] != REMOVED){

            index = (index + 1) % hash_capacity;

        }

        hash_table[index] = e;

        size++;

        load_factor = size / hash_capacity;

        return true;

    }

    @Override
    public boolean remove(Object o) {

        if(contains(o) == false) return false;
        
        int index = 0;

        try{
            index = compress((T)o);
        }
        catch(Exception e){
            return false;
        }

        int hash_index = index;

        do{

            if(hash_table[index].equals(o)){

                hash_table[index] = REMOVED;

                size --;

                load_factor = size / hash_capacity;

                return true;

            }

            index = (index + 1) % hash_capacity;

        }while(hash_table[index] != EMPTY && index != hash_index);

        return false;


    }

    @Override
    public void clear() {
        hash_table = new Object[hash_capacity];
    }

    public void printTable(){

        for(int i=0; i<hash_capacity; i++){

            if(hash_table[i] == EMPTY) System.out.print("EMPTY ");

            else if(hash_table[i] == REMOVED) System.out.print("REMOVED ");

            else System.out.print(hash_table[i]+" ");

        
        }

        System.out.println();

    }
    
    public static void main(String[] args) {
        
        LinearProbeSet <Integer> set = new LinearProbeSet<>(10);

        set.add(5);
        set.add(15);
        set.add(0);

        set.remove(0);
        set.remove(15);
        set.remove(5);
        set.printTable();

        System.out.println(set.contains(0));


    }

}
