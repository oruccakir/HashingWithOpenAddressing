
import java.util.Iterator;

public interface SetInterface<E>{

    /**
     * returns hash code of key
     * @return returns hash code of key

    */

    public int hash_code(E key);


    /**
     * returns compression of the key
     * @return compression of the key
     
     */

    public int compress(E key);

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set
     */
    int size();
    
    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements
     */
    boolean isEmpty();

    /**
     * Returns true if this set contains the specified element.
     *
     * @param e the element to be checked for containment in this set
     * @return true if this set contains the specified element
     */
    boolean contains(Object e);
    
    /**
     * Returns an iterator over the elements in this set.
     *
     * @return an iterator over the elements in this set
     */
    Iterator<E> iterator();
    
    /**
     * Adds the specified element to this set if it is not already present.
     *
     * @param e the element to be added to this set
     * @return true if this set did not already contain the specified element
     */
    boolean add(E e);
    
    /**
     * Removes the specified element from this set if it is present.
     *
     * @param o the element to be removed from this set
     * @return true if this set contained the specified element
     */
    boolean remove(Object o);
    
    /**
     * Removes all of the elements from this set.
     */
    void clear();
    
    
}
