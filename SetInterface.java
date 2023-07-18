import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;

public interface SetInterface<E> extends Collection<E> {
    
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
     * @param o the element to be checked for containment in this set
     * @return true if this set contains the specified element
     */
    boolean contains(Object o);
    
    /**
     * Returns an iterator over the elements in this set.
     *
     * @return an iterator over the elements in this set
     */
    Iterator<E> iterator();
    
    /**
     * Returns an array containing all of the elements in this set.
     *
     * @return an array containing all of the elements in this set
     */
    Object[] toArray();
    
    /**
     * Returns an array containing all of the elements in this set;
     * the runtime type of the returned array is that of the specified array.
     *
     * @param a the array into which the elements of this set are to be stored,
     *          if it is big enough; otherwise, a new array of the same runtime
     *          type is allocated for this purpose.
     * @return an array containing all of the elements in this set
     */
    <T> T[] toArray(T[] a);
    
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
     * Returns true if this set contains all of the elements of the specified collection.
     *
     * @param c the collection to be checked for containment in this set
     * @return true if this set contains all of the elements of the specified collection
     */
    boolean containsAll(Collection<?> c);
    
    /**
     * Adds all of the elements in the specified collection to this set if they're not already present.
     *
     * @param c the collection containing elements to be added to this set
     * @return true if this set changed as a result of the call
     */
    boolean addAll(Collection<? extends E> c);
    
    /**
     * Retains only the elements in this set that are contained in the specified collection.
     *
     * @param c the collection containing elements to be retained in this set
     * @return true if this set changed as a result of the call
     */
    boolean retainAll(Collection<?> c);
    
    /**
     * Removes from this set all of its elements that are contained in the specified collection.
     *
     * @param c the collection containing elements to be removed from this set
     * @return true if this set changed as a result of the call
     */
    boolean removeAll(Collection<?> c);
    
    /**
     * Removes all of the elements from this set.
     */
    void clear();
    
    /**
     * Creates a {@link Spliterator} over the elements in this set.
     *
     * @return a {@code Spliterator} over the elements in this set
     */
    Spliterator<E> spliterator();
}
