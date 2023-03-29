public interface DoublyLinkedListInterface <T extends PubliclyCloneable>  extends PubliclyCloneable{


    public void print();

    public void reversePrint();

    public void add(T data);

    public void add(int index, T data);

    public T get(int index);

    public void set(int index, T data);

    public void remove();

    public void remove(int index);

    public boolean contains(T data);

    public boolean equals(DoublyLinkedList <T>list);

    public void clear();

    public Object[] toArray();

    public T[] toArray(T []array);

    public int indexOf(T data);

    public int lastIndexOf(T data);

}
interface PubliclyCloneable extends Cloneable{
    public Object clone();
}


