

public class MyHashWithLinearProbing  <T>{

    private Object hash_Table[];

    private static final Object EMPTY = null;

    private static final Object DELETED = Integer.MIN_VALUE; 

    public int length;

    public int size;

    public double loadFactor;

    
    public MyHashWithLinearProbing(){

        this.length = 19;

        this.hash_Table = new Object [this.length];

        loadFactor =0;

        size = 0;

    }


    public MyHashWithLinearProbing( int length){

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

        if((int)this.loadFactor == 1) {

            Object temp [] = new Object[this.length];

            for(int i=0; i<this.length; i++){

               temp[i] = this.hash_Table[i];

            }

            this.length *=2;

            this.hash_Table = new Object[this.length];

            for(int i=0; i<temp.length; i++){

                this.hash_Table[i] = temp[i];

            }

            loadFactor = (double) size / length;

        }

        int index = this.hash(data);

        while(this.hash_Table[index]!=EMPTY && !this.hash_Table[index].equals(DELETED)){

            if(this.hash_Table[index].equals(data)) return false;

            index = (index + 1) % this.length;

        }

        hash_Table[index] = data;
        size++;

        loadFactor = (double)size / this.length;

        return true;

    }



    public boolean contains(T data){

        int index = this.hash(data);

        int hash_index = index;

        if(data.equals(this.hash_Table[index])) return true;

        do{

            if(this.hash_Table[index].equals(data)) return true;

            index = (index+1) % this.length;

        }while(this.hash_Table[index] !=EMPTY && index !=hash_index);

        return false;
    }

    
    public boolean remove(T data){

        if(!this.contains(data)) return false;

        int index = this.hash(data);

        int hash_index = index;

        if(this.hash_Table[index].equals(data)){

            this.hash_Table[index] = DELETED;

            size--;

            this.loadFactor = (double)size / length;

            return true;

        }

        
        do{

            if(this.hash_Table[index].equals(data)){

                this.hash_Table[index] = DELETED;

                size--;

                this.loadFactor = (double) size / length;

            return true;

            }

            index = (index+1) % this.length;

        }while(hash_Table[index] != EMPTY && index !=hash_index);


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


        MyHashWithLinearProbing <Integer > hash = new MyHashWithLinearProbing<>(4);

        hash.add(15);
        hash.add(19);
        hash.add(18);
        hash.add(0);
        hash.add(19);
        hash.add(38);
        hash.add(1012);
        hash.add(1);
        hash.add(1500);

        System.out.println(hash.loadFactor);
        

        hash.print_hash_table();
        hash.print();
        //System.out.println(con);

        
    }






    
    
}
