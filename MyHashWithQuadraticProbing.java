public class MyHashWithQuadraticProbing <T>{

    private Object hash_Table[];

    private static final Object EMPTY = null;

    private static final Object DELETED = Integer.MIN_VALUE; 

    public int length;

    public int size;

    public double loadFactor;

    public MyHashWithQuadraticProbing (){

        this.length = 19;

        this.hash_Table = new Object [this.length];

        loadFactor =0;

        size = 0;

    }

    public MyHashWithQuadraticProbing(int length){

        this.length = length;

        this.hash_Table = new Object[this.length];

        loadFactor =0;

        size =0;

    }

    public int hash(Object data){

        int index = -1;

        if(data instanceof Integer){

            Integer integerData = (Integer) data;

            index = integerData % this.length;

        }

        return index;

    }


    public boolean add(T data){

        int index = this.hash(data);

        int iteration = 1;

        while(this.hash_Table[index]!=EMPTY && !this.hash_Table[index].equals(DELETED)){

            if(this.hash_Table[index].equals(data)) return false;

            index = (index + iteration * iteration) % this.length;

            iteration++;
        }

        if(loadFactor <= 0.5 && iteration <= this.length){

            hash_Table[index] = data;
            size++;

            loadFactor = (double)size / this.length;

            return true;

        }


        return false;

    }

    public boolean contains(T data){

        int index = this.hash(data);

        int hash_index = index;

        int iteration = 1;

        if(data.equals(this.hash_Table[index])) return true;

        do{

            index = (index+iteration*iteration) % this.length;

            if(data.equals(this.hash_Table[index])) return true;

            iteration++;

        }while(iteration <= this.length);

    

        return false;
    }

    
    public boolean remove(T data){

        if(!this.contains(data)) return false;

        int index = this.hash(data);

        int hash_index = index;

        int iteration = 0;

        if(this.hash_Table[index] != EMPTY && this.hash_Table[index].equals(data)){

            this.hash_Table[index] = DELETED;

            size--;

            this.loadFactor = (double)size / length;

            return true;

        }

        
        do{

            if(data.equals(this.hash_Table[index])){

                this.hash_Table[index] = DELETED;

                size--;

                this.loadFactor = (double) size / length;

            return true;

            }

            index = (index+iteration*iteration) % this.length;

            iteration++;

        }while(iteration < this.length);


        return false;
    }
































    public void print_hash_table(){

        System.out.println("-----------------------");

        for(int i=0; i<this.length; i++){

            if(this.hash_Table[i] == EMPTY)
               System.out.println(""+i+" --> "+"EMPTY");
            else if(this.hash_Table[i].equals(DELETED))
               System.out.println(""+i+"-->"+"DELETED");
            else
               System.out.println(""+i+" --> "+this.hash_Table[i]);


        }

        System.out.println("-----------------------");

    }


    public void print(){

        System.out.print("[ ");

        for(int i=0; i<this.length; i++){

            if(hash_Table[i] != null && !hash_Table[i].equals(DELETED))
              System.out.print(this.hash_Table[i]+" ");

        }

        System.out.println(" ]");

    }



    public static void main(String[] args) {


        MyHashWithQuadraticProbing <Integer > hash = new MyHashWithQuadraticProbing<>(8);

        hash.add(15);
        hash.add(19);
        hash.add(18);
        hash.add(0);

        hash.print_hash_table();


        hash.remove(19);
        System.out.println(hash.contains(15));
        hash.add(0);
        System.out.println(hash.loadFactor);
        hash.add(4);
        hash.remove(4);
        //hash.add(23);

        boolean con = hash.contains(4);
        System.out.println(con);
       

       

       System.out.println(hash.contains(15)); 

        
        

        hash.print_hash_table();
        hash.print();
        

        
    }



    
}