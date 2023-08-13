package Projects.Project03;

/** An interface for the ADT list.
	Entries in a list have positions that begin with 1.
 */
public interface ListInterface<T>{
	/** Adds a new entry to the end of this list.
   Entries currently in the list are unaffected.
   The list's size is increased by 1.
   @param newEntry  The object to be added as a new entry. */
	public void add(T newEntry);

	/** Removes all entries from this list. */
	public void clear();

	/** Retrieves the entry at a given position in this list.
   @param givenPosition  An integer that indicates the position of
                         the desired entry.
   @return  A reference to the indicated entry.
   @throws  IndexOutOfBoundsException if either
            givenPosition < 1 or givenPosition > size. */
	public T getEntry(int givenPosition);

	/** Sees whether this list contains a given entry.
   @param anEntry  The object that is the desired entry.
   @return  True if the list contains anEntry, or false if not. */
	public boolean contains(T anEntry);

	/** Gets the number of elements in this list.
   @return  The integer number of entries currently in the list. */
	public int size();

	/** Sees whether this list is empty.
   @return  True if the list is empty, or false if not. */
	public boolean isEmpty();
} 
