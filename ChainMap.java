import java.util.LinkedList;

public class ChainMap<K,V> implements MapInterface<K,V> {

    private class MapEntry<K,V> implements Entry<K,V>{

        private K key;
        private V value;

        public MapEntry(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        public String toString(){

            return ""+key+" : "+value;

        }



    }

    private final static int DEFAULT_CAPACİTY = 53;

    private int size = 0;
    private int hash_capacity = 0;

    private double load_factor = 0;

    private LinkedList<MapEntry<K,V>>[] hash_table = null;

    public ChainMap(int capacity){

        hash_capacity = capacity;

        hash_table = new LinkedList[hash_capacity];

        createHashTable();

    }

    private void createHashTable(){

        for(int i=0; i<hash_table.length; i++) hash_table[i] = new LinkedList<MapEntry<K,V>>();

    }

    public ChainMap(){

        this(DEFAULT_CAPACİTY);

    }


    public int hash_code(K key) {
        return key.hashCode();
    }


    public int compress(K key) {
        return hash_code(key) % hash_capacity;
    }

    public boolean isLoadFactor(){
        return load_factor >= 0.75;
    }

    @Override
    public V put(K key, V value) {
        
        if(isLoadFactor()) {

           hash_capacity = 2*hash_capacity;

           LinkedList<MapEntry<K,V>>[] table = new LinkedList[hash_capacity];

           for(int i=0; i<table.length; i++) table[i] = new LinkedList<>();

           for(int i=0; i<hash_table.length; i++){

             table[i] = hash_table[i];

           }

           hash_table = table;
        
        }

        int index = compress(key);

        V temp = null;

        for(MapEntry<K,V> entry : hash_table[index]){

            if(entry.getKey().equals(key)){

                temp = entry.getValue();

                entry.setValue(value);
                
                return temp;

            }

        }

        MapEntry<K,V> entry  = new MapEntry<>(key,value);

        hash_table[index].add(entry);

        size++;

        load_factor = (double) size / hash_capacity;

        return temp;


    }

    @Override
    public V get(K key) {

        int index = compress(key);

        for(MapEntry<K,V> entry : hash_table[index]){

            if(entry.getKey().equals(key)) return entry.getValue();

        }

        return null;

    }

    @Override
    public V remove(K key) {

        int index = compress(key);

        for(MapEntry<K,V> entry : hash_table[index]){

            if(entry.getKey().equals(key)) {

                V temp = entry.getValue();
                
                hash_table[index].remove(entry);

                return temp;

            }

        }

        return null;
        
    }

    @Override
    public boolean containsKey(K key) {

        int index = compress(key);

        for(MapEntry<K,V> entry : hash_table[index]){

            if(entry.getKey().equals(key)) return true;

        }

        return false;
        
    }

    public void printTable(){

        for(LinkedList<MapEntry<K,V>> list : hash_table) System.out.println(list);

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
    public void clear() {
        createHashTable();
    }

    @Override
    public Iterable<K> keySet() {

        LinkedList<K> linkedList = new LinkedList<>();

        for(LinkedList<MapEntry<K,V>> list : hash_table)
           for(MapEntry<K,V> entry : list)
              linkedList.add(entry.key);

        return linkedList;
    }

    @Override
    public Iterable<V> values() {
        
        LinkedList<V> linkedList = new LinkedList<>();

        for(LinkedList<MapEntry<K,V>> list : hash_table)
           for(MapEntry<K,V> entry : list)
              if(linkedList.contains(entry.value) == false) linkedList.add(entry.value);

        return linkedList;

    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {

        LinkedList<Entry<K,V>> linkedList = new LinkedList<>();

        for(LinkedList<MapEntry<K,V>> list : hash_table)
           for(MapEntry<K,V> entry : list)
              linkedList.add(entry);

        return linkedList;

    }

    public static void main(String[] args) {

        ChainMap<Integer,Integer> map = new ChainMap<>(10);

        int arr[] = {1,1,1,2,2,22,2,3,33,3,33,4,4,4,4,4,5};

        for(int i=0; i<arr.length; i++)
         if(map.get(arr[i])== null) map.put(arr[i], 1);
         else map.put(arr[i], map.get(arr[i])+1);


         map.remove(4);
         map.remove(33);
         map.remove(1);

        map.printTable();

        System.out.println(map.keySet());

        System.out.println(map.values());


        
    }
    
}
