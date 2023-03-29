
public class DoublyLinkedList <T extends PubliclyCloneable> implements DoublyLinkedListInterface <T>{


   private TwoWayNode head;

    public TwoWayNode getHead() {
        return head;
    }

    public int length;

   public DoublyLinkedList(){
       head = null;
       length = 0;
   }

   
    @Override
    public Object clone() {

       DoublyLinkedList<T> newList = new DoublyLinkedList <>();

       newList.head = (DoublyLinkedList.TwoWayNode) this.head.clone(); // same as (TwoWayNode) this.head.clone();

        TwoWayNode tempThis = this.head;
        


        while (tempThis != null){

            newList.add((T) tempThis.data.clone());
            tempThis = tempThis.next;
        }

       return newList;
    }

    @Override
    public void print() {
        System.out.print("[ ");
        TwoWayNode temp = this.head;
        while (temp != null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.print("]\n");
    }

    @Override
    public void reversePrint() {
        System.out.print("[ ");
        TwoWayNode temp = this.getNode(this.length-1);
        while (temp != null){
            System.out.print(temp.data+" ");
            temp=temp.previous;
        }
        System.out.print("]\n");
    }

    @Override
    public void add(T data) {

       TwoWayNode newNode = new TwoWayNode(data,null,null);

       if(length == 0) {
           this.head = newNode;
       }

       else{

           TwoWayNode temp = this.head;

           while (temp.next!=null){
               temp = temp.next;
           }

           newNode.setPrevious(temp);
           temp.setNext(newNode);

       }
       length++;

    }

    @Override
    public void add(int index, T data) {

       TwoWayNode newNode = new TwoWayNode(data,null,null);

       if(length == 0 && index == 0) this.head = newNode;

       else if(index == 0){

           newNode.setNext(this.head);
           this.head.setPrevious(newNode);
           this.head = newNode;

       }
       else {

           TwoWayNode temp = this.head;

           int currentIndex = 0;

           while(temp!=null && currentIndex!=index) {
               temp = temp.next;
               currentIndex++;
           }

           if(temp == null){
               this.add(data);
               length--;
           }
           else{
               newNode.setNext(temp);
               newNode.setPrevious(temp.getPrevious());
               temp.getPrevious().setNext(newNode);
               temp.setPrevious(newNode);
           }


       }

       length++;
    }

    @Override
    public T get(int index) {

        TwoWayNode temp = this.head;
        int currIndex = 0;

        while(temp!=null && currIndex!=index){
            temp=temp.next;
            currIndex++;
        }

        return temp.data;
    }

    public TwoWayNode getNode(int index) {

        TwoWayNode temp = this.head;
        int currIndex = 0;

        while(temp!=null && currIndex!=index){
            temp=temp.next;
            currIndex++;
        }

        return temp;
    }

    @Override
    public void set(int index, T data) {

       this.getNode(index).data = data;

    }

    @Override
    public void remove() {

       TwoWayNode temp = this.head;

       while (temp.next!=null){
           temp = temp.next;
       }

       temp.previous.setNext(null);
       temp.previous = null;
       temp.next = null;

       length--;

    }

    @Override
    public void remove(int index) {

       if(index == 0){
           this.head=head.next;
           this.head.setPrevious(null);
       }
       else if (index == length-1){
           remove();
           length++;
       }
       else{
           TwoWayNode removed = this.getNode(index);
           removed.previous.setNext(removed.next);
           removed.next.setPrevious(removed.previous);
       }

       length--;

    }

    @Override
    public boolean contains(T data) {

        if(this.indexOf(data)==-1) return false;

        return true;
    }

    @Override
    public boolean equals(DoublyLinkedList<T> list) {

       if(this.length != list.length) return false;


       TwoWayNode thisTemp = this.head;
       TwoWayNode listTemp = list.head;

       while (thisTemp!=null && listTemp!=null){

           if(!thisTemp.data.equals(listTemp.data)) return false;

           thisTemp=thisTemp.next;
           listTemp=listTemp.next;

       }

        return true;
    }

    @Override
    public void clear() {
       this.head = null;
    }

    @Override
    public Object[] toArray() {

       Object temp[] = new Object[length];
       TwoWayNode current = this.head;

       int index =0;

       while (current!=null && index<length){
           temp[index] =current.data;
           index++;
           current = current.next;
       }

        return temp;
    }


    @Override
    public T[] toArray(T[] array) {

        TwoWayNode current = this.head;

        int index =0;

        while (current!=null && index<length){
            array[index] =current.data;
            index++;
            current = current.next;
        }
        return array;
    }

    @Override
    public int indexOf(T data) {

       TwoWayNode temp = this.head;
       int index = 0;

       while (temp!=null){

           if(temp.data.equals(data))
               return index;

           temp=temp.next;

           index++;

       }

        return -1;
    }

    @Override
    public int lastIndexOf(T data) {

       TwoWayNode temp = this.getNode(length-1);

       int index = length-1;

       while (temp!=null){

           if(temp.data.equals(data))
               return index;

           index--;
           temp=temp.previous;

       }
        return -1;
    }

     public class TwoWayNode implements PubliclyCloneable{

        private T data;
        private TwoWayNode next;
        private TwoWayNode previous;

        public TwoWayNode(){
            data = null;
            next = null;
            previous = null;
        }

        public TwoWayNode(T data, TwoWayNode previous, TwoWayNode next){
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

         public TwoWayNode(TwoWayNode other){
            this.data = (T) other.data.clone();
            this.next = null;
            this.previous = null;
         }

         @Override
         public Object clone() {
            return new TwoWayNode(this);
         }

         public String toString(){
            return ""+data;
        }



        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public TwoWayNode getNext() {
            return next;
        }

        public TwoWayNode getPrevious() {
            return previous;
        }

        public void setNext(TwoWayNode next) {
            this.next = next;
        }

        public void setPrevious(TwoWayNode previous) {
            this.previous = previous;
        }

    }




}
