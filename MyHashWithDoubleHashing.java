import java.util.Arrays;


public class MyHashWithDoubleHashing {

    public static final int DELETED = Integer.MIN_VALUE;

    public static final int EMPTY = Integer.MAX_VALUE;

    public int length;

    public int size;

    public Object [] control = {false,-1};

    private int [] hashTable = null;

    public MyHashWithDoubleHashing(){

        this.length = 19;

        this.size = 0;

        this.hashTable = new int[this.length];

        Arrays.fill(hashTable,EMPTY);

    }

    public MyHashWithDoubleHashing(int length){

        this.length = length;

        this.size = 0;

        this.hashTable = new int[this.length];

        Arrays.fill(hashTable,EMPTY);

    }

    public int firstHasFunction(int data){
        return data % this.length;
    }

    public int secondHashFunction(int data){
        return (this.length - 1 - ( data % (this.length - 1)));
    }

    public boolean reccurenceInsertControl(int data, int iteration,int probe){

        if(iteration > this.length) return false;

        if(this.hashTable[probe] == EMPTY || this.hashTable[probe] == DELETED) return true;

        probe = (firstHasFunction(data) + iteration * secondHashFunction(data)) % this.length;

        control[1] = probe;

        return reccurenceInsertControl(data,iteration+1,probe);

    }

    public boolean reccurenceSearchControl(int data, int iteration,int probe){

        if(iteration > this.length) return false;

        if(this.hashTable[probe] == data) return true;

        probe = (firstHasFunction(data) + iteration * secondHashFunction(data)) % this.length;

        control[1] = probe;

        return reccurenceSearchControl(data,iteration+1,probe);

    }

    public boolean insert(int data){

        if(this.size == this.length)
            return false;

        if(search(data)) return false;

        int probe = firstHasFunction(data), offset = secondHashFunction(data);

        if(hashTable[probe] == EMPTY){
            hashTable[probe] = data;
            size++;
            return true;
        }

        int i=1;

        while (hashTable[probe] != EMPTY && i<=this.length){

            probe = (probe + offset) % this.length;
            i++;

        }

        if(i > this.length){

            control[0] = reccurenceInsertControl(data,1,firstHasFunction(data));

            if((Boolean) control[0]){
                this.hashTable[(int) control[1]] = data;
                size++;
                return true;
            }
            else return false;

        }
        else{
            this.hashTable[probe] = data;
            size++;
            return true;
        }

    }

    public boolean search(Integer data){

        int probe = firstHasFunction(data), offset = secondHashFunction(data);

        if(this.hashTable[probe] == data) return true;

        int i=1;

        while (this.hashTable[probe] != EMPTY && i<= this.length){

            probe = (probe + offset) % this.length;

            if(hashTable[probe] == data)
                return true;

            i++;

        }

        if(i > length) {

            control[0] = reccurenceSearchControl(data,1,firstHasFunction(data));

            if((Boolean)control[0])
                return true;
            else{
                return false;
            }

        }

        return false;
    }

    public boolean remove(Integer data){

        if(!this.search(data)) return false;

        int probe = firstHasFunction(data), offset = secondHashFunction(data);

        if(this.hashTable[probe] == data){
            this.hashTable[probe] = DELETED;
            size--;
            return true;
        }

        int i=1;

        while (this.hashTable[probe] != EMPTY && i<=length){

            probe = (probe + offset) % this.length;

            if(hashTable[probe] == data){

                this.hashTable[probe] = DELETED;
                size--;
                return true;
            }

            i++;

        }

        if(i > this.length){

            control[0] = reccurenceSearchControl(data,1,firstHasFunction(data));

            if((Boolean)control[0]){
                this.hashTable[(int) control[1]] = DELETED;
                size--;
                return true;
            }

            else{
                return false;
            }

        }

        return false;
    }

    public void showValues(){

        System.out.print("VALUE : [ ");

        for(int i=0; i<this.length; i++){
            if(this.hashTable[i] == EMPTY || this.hashTable[i] == DELETED)
                System.out.print(0+", ");
            else
                System.out.print(this.hashTable[i]+", ");
        }

        System.out.println("]");




    }




}
