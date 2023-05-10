import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Creating an ArrayList from the ground up in the form of MinneoArrayList. This class also implements the Collection and Iterable interfaces.
 * As of the moment, I haven't tested every method. I was in a rush to create all the methods, and thus only a few of the basic ones are tested. This statement will be updated when I go through and test everything. There will also be a few more methods that ArrayLists have along with some methods I wish to add to my version of the ArrayList.
 *
 * @author ryanm
 * @version 1.0
 */
public class MinneoArrayList<T> implements Collection<T>, Iterable<T>
{
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] theData;

    //Constructors

    /**
     * Default constructor, creates the ArrayList with a capacity of 10.
     */
    public MinneoArrayList()
    {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates the ArrayList with a capacity as determined by user input.
     * @param capacity
     */
    public MinneoArrayList(int capacity)
    {
        theData = (T[]) new Object[capacity];
    }

    //Methods

    /**
     * A small method that is generally just for use inside of this class file. Will ensure that the number of elements is smaller than the array length.
     * Amortized O(1)
     */
    public void ensureCapacity()
    {
        if (size == theData.length)
        {
            int newCapacity = theData.length * 2;
            theData = Arrays.copyOf(theData, newCapacity);
        }
    }

    /**
     * Checks to make sure that the inputted element is greater than the existing size of the MinneoArrayList. After doing so, theData will become a copy of itself where it's capacity is set to the inputted element.
     * O(N)
     * @param minCapacity
     */
    public void ensureCapacity(int minCapacity)
    {
        if(minCapacity > size)
        {
            theData = Arrays.copyOf(theData, minCapacity);
        }
    }

    /**
     * Adds an element to the end of the ArrayList. If the adding succeeds it will return true. If not, return false.
     * Amortized O(1)
     *
     * @param inputtedElement
     * @return true/false
     */
    public boolean add(T inputtedElement)
    {
        ensureCapacity();
        theData[size++] = inputtedElement;

        if(theData[size-1].equals(inputtedElement)) return true;
        else return false;
    }

    /**
     * Adds an element to the ArrayList at the specified index. Since this method does have to adjust the elements over one index, the time complexity must be O(N), though the best case would be O(1) where the element is added to the last index and the capacity isn't
     * @param index
     * @param inputtedElement
     */
    public void add(int index, T inputtedElement)
    {
        ensureCapacity();
        for(int i = theData.length - 2; i >= index; i--)
        {
            theData[i+1] = theData[i];
        }
        size++;
        theData[index] = inputtedElement;
    }

    /**
     * Adds all elements from a collection into a MinneoArrayList
     * O(M)
     * @param c collection containing elements to be added to this collection
     * @return result
     */
    public boolean addAll(Collection c)
    {
        boolean result = false;
        for(Object element : c)
        {
            if(add((T) element)) result = true;
        }
        return result;
    }

    public void clear()
    {

    }


    /**
     * Clones the Arraylist to make another ArrayList with the same values
     * O(N)
     * @return newList
     */
    public MinneoArrayList clone()
    {
        MinneoArrayList newList = new MinneoArrayList(size);
        for (T element : theData)
        {
            newList.add(element);
        }
        return newList;
    }

    /**
     * Checks to see if the element specified is in the MinneoArrayList
     * O(N)
     * @param inputtedElement
     * @return true/false
     */
    public boolean contains(Object inputtedElement)
    {
        for(T element : theData)
        {
            if (element.equals(inputtedElement)) return true;
        }
        return false;
    }

    /**
     * Tests to see whether every element in a collection is also in the MinneoArrayList.
     * O(N * M)
     * @param c collection to be checked for containment in this collection
     * @return true/false
     */
    public boolean containsAll(Collection c)
    {
        boolean tmp = false;

        for(Object collectionElement : c)
        {
            for(T dataElement : theData)
            {
                if(collectionElement.equals(dataElement)) tmp = true;
            }
            if(tmp == false) return false;
        }
        return true;
    }

    /**
     * Returns the element found at the specified index. Will throw an IndexOutOfBoundsException if the index is out of bounds.
     * O(1)
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) throws IndexOutOfBoundsException
    {
        return theData[index];
    }

    /**
     * Returns the index of the specified element. If the element is not in the MinneoArrayList, this method will return -1
     * O(N)
     * @param element
     * @return i / -1
     */
    public int indexOf(T element)
    {
        for(int i = 0; i < size; i++)
        {
            if(theData[i].equals(element)) return i;
        }
        return -1;
    }

    /**
     * Checks to see if the MinneoArrayList is empty or not. Will return true if it is empty.
     * O(1)
     *
     * @return true/false
     */
    public boolean isEmpty()
    {
        if (size > 0) return false;
        return true;
    }

    /**
     * Removes the element at the specified index.
     * O(N)
     * @param index
     * @throws IndexOutOfBoundsException
     */
    public void remove(int index) throws IndexOutOfBoundsException
    {
        for(int i = index; i < size-1; i++)
        {
            theData[i] = theData[i+1];
        }
        size--;
    }

    public boolean remove(Object element)
    {
        int i = indexOf((T) element);
        if(i == -1) return false;

        remove(i);
        return true;
    }

    /**
     * Goes through all of theData and another collection, and removes the elements in theData that are also in the collection. Will return true once the method completes.
     * O(N * M)
     *
     * @param c
     * @return true
     */
    public boolean removeAll(Collection c)
    {
        for (T dataElement : theData)
        {
            for (Object collectionElement : c)
            {
                if (dataElement.equals(collectionElement)) remove(indexOf(dataElement));
            }
        }
        return true;
    }

    /**
     * Retains all the common elements between the MinneoArrayList and the collection.
     * O(N * M)
     * @param c collection containing elements to be retained in this collection
     * @return result
     */
    public boolean retainAll(Collection c)
    {
        boolean result = false;
        boolean tmp = false;

        for(T dataElement : theData)
        {
            for(Object collectionElement : c)
            {
                if(dataElement.equals(collectionElement)) tmp = true;
            }

            if(tmp == false)
            {
                remove(indexOf(dataElement));
                result = true;
            }
            tmp = false;
        }

        return result;
    }

    /**
     * Sets the specified index to the specified element.
     * O(1)
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException
     */
    public void set(int index, T element) throws IndexOutOfBoundsException
    {
        theData[index] = element;
    }

    /**
     * Returns the length of the ArrayList. This is pretty much a getter for the size variable.
     * O(1)
     * @return size
     */
    public int size()
    {
        return size;
    }

    /**
     * Copies the MinneoArrayList into an Array of objects and returns it.
     * @return arr
     */
    public Object[] toArray()
    {
        Object[] arr = new Object[size];

        for(int i = 0; i < size - 1; i++)
        {
            arr[i] = theData[i];
        }

        return arr;
    }

    /**
     * Another toArray method that will create an Array of the same type. This code is one that I generated using ChatGPT. I understand the code now, though it was entirely too confusing for me to try and make this on my own. I needed this method in order to successfully implement the Collection interface to this class.
     * O(N)
     * @param input the array into which the elements of this collection are to be
     *        stored, if it is big enough; otherwise, a new array of the same
     *        runtime type is allocated for this purpose.
     * @return input - After input has been modified.
     * @param <T>
     */
    public <T> T[] toArray(T[] input)
    {
        if (input.length < size)
        {
            return Arrays.copyOf(theData, size, (Class<? extends T[]>) input.getClass());
        }
        else
        {
            System.arraycopy(theData, 0, input, 0, size);
            if (input.length > size)
            {
                input[size] = null;
            }
        }

        return input;
    }

    /**
     * Returns a string version of the ArrayList with a ', ' in between each element.
     * O(N)
     * @return output
     */
    public String toString()
    {
        String output = "";

        for (int i = 0; i < size; i++)
        {
            if (i == size-1)
            {
                output += theData[i] + "";
            }
            else
            {
                output += theData[i] + ", ";
            }
        }
        return output;
    }

    /**
     * A method that will create a new MinneoArrayListIterator which can be used to iterate through a MinneoArrayList object.
     * @return new MinneoArrayListIterator()
     */
    public Iterator<T> iterator()
    {
        return new MinneoArrayListIterator();
    }

    /**
     * A new nested class in MinneoArrayList that functions as an Iterator for MinneoArrayList.
     *
     * @author ryanm
     * @version 1.0
     */
    private class MinneoArrayListIterator implements Iterator<T>
    {
        private int currentIndex;

        /**
         * Will test to see if there is another element in the MinneoArrayList after the element at the currentIndex.
         * @return true/false
         */
        public boolean hasNext()
        {
            return currentIndex < size && theData[currentIndex] != null;
        }

        /**
         * Iterates to the next element in the MinneoArrayList.
         * @return nextElement
         */
        public T next()
        {
            return theData[currentIndex++];
        }

        /**
         * Removes the element at the currentIndex in the instance of MinneoArrayList.
         */
        public void remove()
        {
            if(currentIndex > 0 && currentIndex <= size)
            {
                MinneoArrayList.this.remove(currentIndex-1);
                currentIndex--;
            }
        }

    }

}
