//TODO:
//  (1) Update this code to meet the style and JavaDoc requirements.
//			Why? So that you get experience with the code for a heap!
//			Also, this happens a lot in industry (updating old code
//			to meet your new standards). We've done this for you in
//			WeissCollection and WeissAbstractCollection.
//  (2) Implement getIndex() method and the related map integration
//			 -- see project description
//  (3) Implement update() method -- see project description
//  (4) Other methods may also need to be updated to integrate with the map

import java.util.Iterator;
import java.util.Comparator;
import java.util.NoSuchElementException;

//You may use the JCF HashMap or the ThreeTenHashTable from Project 3.
//To use the JCF class, uncomment this line:
import java.util.HashMap;


//To use your code, just copy over ThreeTenHashTable from Project 3 
//and DON'T uncomment the line above. 
//You will need to make it a private class and 
//may need to change the class name to use it here.

/**
 * PriorityQueue class implemented via the binary heap.
 * From your textbook (Weiss)
 * 
 * @param <T> any object.
 */
public class WeissPriorityQueue<T> extends WeissAbstractCollection<T>
{

	//you may not have any other class variables, only this one
	//if you make more class variables your priority queue class
	//will receive a 0, no matter how well it works
	/**
	 * The deafauly capacity of the priority queue.
	 */
	private static final int DEFAULT_CAPACITY = 100;

	//you may not have any other instance variables, only these four
	//if you make more instance variables your priority queue class 
	//will receive a 0, no matter how well it works
	/**
	 * Number of items in the heap.
	 */
	private int currentSize;   // Number of elements in heap

	/**
	 * An array to represent the heap.
	 */
	private T [ ] array; // The heap array

	/**
	 * Comparator to compare the items in the array.
	 */
	private Comparator<? super T> cmp;

	/**
	 * A hash map to store the item and index of elements in the heap.
	 */
	private HashMap<T, Integer> indexMap; //JCF or your class
		
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------
	
	/**
	 * Main method for testing.
	 * 
	 * @param args command line arguments.
	 */
	public static void main(String[] args) 
    {
		/**
		 * Class representing a student at GMU.
		 */
		class Student {
			String gnum;
			String name;
			Student(String gnum, String name) { this.gnum = gnum; this.name = name; }
			
			/**
			 * Checks to see if a gmu student is the same as another student.
			 * 
			 * @param o another object/ or student.
			 * @return true if the student is equal, false otherwise.
			 */
			public boolean equals(Object o) {
				if(o instanceof Student) return this.gnum.equals(((Student)o).gnum);
				return false;
			}
			public String toString() { return name + "(" + gnum + ")"; }
			public int hashCode() { return gnum.hashCode(); }
		}
		
		Comparator<Student> comp = new Comparator<>() {
			public int compare(Student s1, Student s2) {
				return s1.name.compareTo(s2.name);
			}
		};
		
		
		//TESTS FOR INDEXING -- you'll need more testing...
		
		WeissPriorityQueue<Student> q = new WeissPriorityQueue<>(comp);
		q.add(new Student("G00000000", "Robert"));
		System.out.print(q.getIndex(new Student("G00000001", "Cindi")) + " "); //-1, no index
		System.out.print(q.getIndex(new Student("G00000000", "Robert")) + " "); //1, at root
		System.out.println();
		
		q.add(new Student("G00000001", "Cindi"));
		System.out.print(q.getIndex(new Student("G00000001", "Cindi")) + " "); //1, at root
		System.out.print(q.getIndex(new Student("G00000000", "Robert")) + " "); //2, lower down
		System.out.println();
		
		q.remove(); //remove Cindi
		System.out.print(q.getIndex(new Student("G00000001", "Cindi")) + " "); //-1, no index
		System.out.print(q.getIndex(new Student("G00000000", "Robert")) + " "); //1, at root
		System.out.println();
		System.out.println();
		
		
		//TESTS FOR UPDATING -- you'll need more testing...
		
		q = new WeissPriorityQueue<>(comp);
		Student s1 = new Student("G00000000", "Robert");
		q.add(s1);
		Student s2 = new Student("G00000001", "Cindi");
		q.add(s2);
		
		for(Student s : q) System.out.print(q.getIndex(s) + " "); //1 2
		System.out.println();
		for(Student s : q) System.out.print(s.name + " "); //Cindi Robert
		System.out.println();
		
		s1.name = "Bobby";
		q.update(s1);
		
		for(Student s : q) System.out.print(q.getIndex(s) + " "); //1 2
		System.out.println();
		for(Student s : q) System.out.print(s.name + " ");  //Bobby Cindi
		System.out.println();
		
		s1.name = "Robert";
		q.update(s1);
		
		for(Student s : q) System.out.print(q.getIndex(s) + " "); //1 2
		System.out.println();
		for(Student s : q) System.out.print(s.name + " "); //Cindi Robert
		System.out.println();
		
		

        WeissPriorityQueue<Student> queue = new WeissPriorityQueue<>(comp);

        Student tajh = new Student("0123", "Tajh");
        Student tiara = new Student("012", "Tiara");
        Student adam = new Student("01234", "Adam");
        Student bill = new Student("0293", "Bill");
        Student zach = new Student("9292", "Zach");
        Student cat = new Student("1221", "Cat");
        Student james = new Student("329907", "James");
        
        queue.add(tajh);
        queue.add(tiara);
        queue.add(adam);
        queue.add(bill);
        queue.add(zach);
        queue.add(cat);
        queue.add(james);
      
        Student kevin = new Student("327", "Kevin");
        Student sarah = new Student("0101", "Sarah");
        Student rob = new Student("3227", "Rob");
        Student ashley = new Student("32887", "Ashley");

        Student toya = new Student("212312", "Toya");
        Student usman = new Student("111122", "Uzi");
        Student fredy = new Student("327", "Fredy");
        Student david = new Student("2029029", "David");

        queue.add(kevin);
        queue.add(sarah);
        queue.add(rob);
        queue.add(ashley);
        queue.add(toya);
        queue.add(usman);
        queue.add(fredy);
        queue.add(david);

        rob.name = "A";
        queue.update(rob);
	}

	//--------------------------------------------------------
	// end of testing code
	//--------------------------------------------------------
	
	//you implement this
	/**
	 * Gets the index of a given value.
	 * 
	 * @param x the value to get the index.
	 * @return the index or -1 if the value wasnt found.
	 */
	public int getIndex(T x) 
    {
        //Checks to see if the item is in the index map.
        if(indexMap.get(x) == null)
            return -1; //Returns -1, since the item is not in the index map.
        else
		    return indexMap.get(x); //Returns the index of the item since it was found.
		
	}
	
	/**
	 * Updates a given value in the heap.
	 * 
	 * @param x the value to update.
	 * @return true if the value was updated successfully.
	 */
	public boolean update(T x) 
    {
        //O(lg n) average case
        //or O(lg n) worst case if getIndex() is guarenteed O(1)
        //return false if x is not in heap
        //otherwise check whether the location of x still satisfies the heap order
        //Note that item x might might have its priority changed
        //perform necessary location adjustment and return true

        //Index of x
        int index = getIndex(x);

        //Checks if the index of x was found.
        if(index > -1)
        {
            //Updates x to the given value.
            array[index] = x;

            //Gets the index of x's child
            int child = index * 2;

            //Checks if the child index is larger than the current size
            if(child > size())
            {
                //Checks if x is less than its parrent
                if(compare(array[index], array[index/2]) < 0)
                {
                    //Percolates every parent of x
                    for(int i = index/2; i >= 1; i /= 2)
                        percolateDown(i);
                }
                else
                {
                    //Updates x in the index map.
                    indexMap.replace(x, index);
                }
            }
            //checks if x is less than its parent,
            else if (compare(array[index], array[index/2]) < 0)
            {
                //Percolates every parent of x.
                for(int i = index/2; i >= 1; i /= 2)
                    percolateDown(i);
            }
            else 
            {
                //Percolates the children of x.
                for(int i = index; i <= size(); i *=2)
                    percolateDown(i);
            }
            return true;
            
        }
        return false; //dummy return, make sure to replace this!
		
	}
	
	
	/**
	 * Construct an empty PriorityQueue.
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue( )
	{
		currentSize = 0;
		cmp = null;
		array = (T[]) new Object[ DEFAULT_CAPACITY + 1 ];
        indexMap = new HashMap<>();
	}
	
	/**
	 * Construct an empty PriorityQueue with a specified comparator.
	 * 
	 * @param c the comparator to used.
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue( Comparator<? super T> c )
	{
		currentSize = 0;
		cmp = c;
		array = (T[]) new Object[ DEFAULT_CAPACITY + 1 ];
        indexMap = new HashMap<>();
	}
	
	 
	/**
	 * Construct a PriorityQueue from another Collection.
	 * 
	 * @param coll the collection to use.
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue( WeissCollection<? extends T> coll )
	{
		cmp = null;
		currentSize = coll.size( );
		array = (T[]) new Object[ ( currentSize + 2 ) * 11 / 10 ];
        indexMap = new HashMap<>();
		
		int i = 1;
		for( T item : coll )
			array[ i++ ] = item;
		buildHeap( );
	}
	
	/**
	 * Compares lhs and rhs using comparator if
	 * provided by cmp, or the default comparator.
	 * 
	 * @param lhs any object.
	 * @param rhs any object.
	 * @return an int below 0 if lhs is les than rhs, 0 if theyre equal, or greater than 0 if lhs is greater than rhs.
	 */
	@SuppressWarnings("unchecked")
	private int compare( T lhs, T rhs )
	{
		if( cmp == null )
			return ((Comparable)lhs).compareTo( rhs );
		else
			return cmp.compare( lhs, rhs );	
	}
	
	/**
	 * Adds an item to this PriorityQueue.
	 * @param x any object.
	 * @return true.
	 */
	public boolean add( T x )
	{
        if( currentSize + 1 == array.length )
            doubleArray( );

        // Percolate up

        //Makes the hole the size plus one.
        int hole = ++currentSize;
        //Sets the first element in array to x.
        array[ 0 ] = x;

        //Loops keeps going untill the x comes before the parent of hole.
        for( ; compare( x, array[ hole / 2 ] ) < 0; hole /= 2 ) 
        {
            //Sets the element at the hole to the parent.
            array[ hole ] = array[ hole / 2 ];
            indexMap.replace(array[hole/2], hole);
        }
        
        array[ hole ] = x;

        //Adds the element into the index map.
        indexMap.put(x, hole);

        return true;
	}
	
	/**
	 * Returns the number of items in this PriorityQueue.
	 * @return the number of items in this PriorityQueue.
	 */
	public int size( )
	{
		return currentSize;
	}
	
	/**
	 * Make this PriorityQueue empty.
	 */
	public void clear( )
	{
		currentSize = 0;
	}
	
	/**
	 * Returns an iterator over the elements in this PriorityQueue.
	 * The iterator does not view the elements in any particular order.
	 * 
	 * @return returns an iterator for the priority queue.
	 */
	public Iterator<T> iterator( )
	{
		return new Iterator<T>( )
		{
			int current = 0;
			
			public boolean hasNext( )
			{
				return current != size( );
			}
			
			@SuppressWarnings("unchecked")
			public T next( )
			{
				if( hasNext( ) )
					return array[ ++current ];
				else
					throw new NoSuchElementException( );
			}
			
			public void remove( )
			{
				throw new UnsupportedOperationException( );
			}
		};
	}
	 
	/**
	 * Returns the smallest item in the priority queue.
	 * @return the smallest item.
	 * @throws NoSuchElementException if empty.
	 */
	public T element( )
	{
		if( isEmpty( ) )
			throw new NoSuchElementException( );
		return array[ 1 ];
	}
	
	/**
	 * Removes the smallest item in the priority queue.
	 * @return the smallest item.
	 * @throws NoSuchElementException if empty.
	 */
	public T remove( )
	{
		T minItem = element( );
        //Removes minItem from indexMap.
        indexMap.remove(minItem);
		array[ 1 ] = array[ currentSize-- ];
		percolateDown( 1 );
		

		return minItem;
	}


	/**
	 * Establish heap order property from an arbitrary
	 * arrangement of items. Runs in linear time.
	 */
	private void buildHeap( )
	{
		for( int i = currentSize / 2; i > 0; i-- )
			percolateDown( i );
	}


	/**
	 * Internal method to percolate down in the heap.
	 * @param hole the index at which the percolate begins.
	 */
	private void percolateDown( int hole )
	{
        int child;
        T tmp = array[ hole ];

        //Goes untill the the child of hole is the end of the current size.
        for( ; hole * 2 <= currentSize; hole = child )
        {
            //Gets holes left most child
            child = hole * 2;
            //Checks to see that child is less than currentSize and if the right child breaks the heap order.
            if( child != currentSize && compare( array[ child + 1 ], array[ child ] ) < 0 )
                //Makes the child variable the right child.
                child++;
            //Checks to see if the element at the child index comes before the temporary value. (array[hole])
            if( compare( array[ child ], tmp ) < 0 ) 
            {
                //Swaps hole with its child.
                array[ hole ] = array[ child ];
                indexMap.replace(array[child], hole);
            }
            else
                break; //Breaks if the heap order is not broken.
        }

        array[ hole ] = tmp;

        indexMap.replace(tmp, hole);
	}
	
	/**
	 * Internal method to extend array.
	 */
	@SuppressWarnings("unchecked")
	private void doubleArray( )
	{
		T [ ] newArray;

		newArray = (T []) new Object[ array.length * 2 ];
		for( int i = 0; i < array.length; i++ )
			newArray[ i ] = array[ i ];
		array = newArray;
	}
}
