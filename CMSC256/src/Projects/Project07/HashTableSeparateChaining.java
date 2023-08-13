/*
 * Project 07 - Hash Table with Separate Chaining Implementation
 * This project implements a Hash Table with Separate Chaining
 * Lorelai Davis
 * CMSC 256 Section C01
 * 7 July 2023
 */
package Projects.Project07;

import java.util.*;

public class HashTableSeparateChaining<K,V> implements MapInterface<K,V> {
    protected int numEntries;
    protected static final int DEFAULT_CAPACITY = 27;
    protected static final int MAX_CAPACITY = 10000;
    protected List<Entry<K, V>> table;
    protected double loadFactor;
    protected static final double DEFAULT_LOAD_FACTOR = 0.5;
    private int currentIndex; //current position in hash table
    private int numberLeft; //number of entries left in iteration to end early
    List<Entry<K, V>> currentList = null; //list at a given table position
    ListIterator<Entry<K, V>> currentListIterator = null; //iterator for current list

    public static class Entry<K, V> {
        private K key;
        private V value;

        private enum States {CURRENT, REMOVED};
        private States state;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            state = States.CURRENT;
        }

        protected K getKey() {
            return key;
        }

        protected V getValue() {
            return value;
        }

        protected void setValue(V newValue) {
            value = newValue;
        }

        // Returns true if this entry is currently in the hash table.
        protected boolean isIn() {
            return state == States.CURRENT;
        }

        // Returns true if this entry has been removed from the hash table.
        protected boolean isRemoved() {
            return state == States.REMOVED;
        }

        // Sets the state of this entry to removed.
        protected void setToRemoved() {
            // Entry not in use, deleted from table and set State to REMOVED
            state = States.REMOVED;
        }

        public String toString() {
            return "Key-" + key + ": Value-" + value;
        }
    }

    public HashTableSeparateChaining() {
        numEntries = 0;
        List<Entry<K, V>> temp = Arrays.asList(new Entry[MAX_CAPACITY]);
        table = temp;
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashTableSeparateChaining(int initialCapacity, double loadFactorIn) {
        numEntries = 0;
        if (loadFactorIn <= 0 || initialCapacity <= 0) {
            throw new IllegalArgumentException();
        } else if (initialCapacity > MAX_CAPACITY) {
            throw new IllegalArgumentException();
        }
        loadFactor = loadFactorIn;
        //sets up hash table
        int tableSize = getNextPrime(initialCapacity);
        List<Entry<K, V>> temp = Arrays.asList(new Entry[MAX_CAPACITY]);
        table = temp;
    }

    //enlarges hash table as needed
    protected void enlargeHashTable() {
        List<Entry<K, V>> oldTable = table;
        int capacity = getNextPrime(oldTable.size() * 2);

        // The case is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        List<Entry<K, V>> temp = Arrays.asList(new Entry[capacity]);
        table = temp;
        numEntries = 0;

        // Rehash dictionary entries from old array to the new
        for (int index = 0; index < oldTable.size(); index++) {
            if ((oldTable.get(index) != null) && oldTable.get(index).isIn())
                put(oldTable.get(index).getKey(), oldTable.get(index).getValue());
        }
    }

    private boolean isPrime(int integer) {
        boolean result;
        boolean done = false;

        // 1 and even numbers are not prime
        if ((integer == 1) || (integer % 2 == 0)) {
            result = false;
        }
        // 2 and 3 are prime
        else if ((integer == 2) || (integer == 3)) {
            result = true;
        } else {                // integer is odd and >= 5
            result = true;    // assume prime
            for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2) {
                if (integer % divisor == 0) {
                    result = false; // divisible; not prime
                    done = true;
                }
            }
        }
        return result;
    }

    private int getNextPrime(int integer) {
        // if even, add 1 to make odd
        if (integer % 2 == 0) {
            integer++;
        }

        // test odd integers
        while (!isPrime(integer)) {
            integer = integer + 2;
        }
        return integer;
    }

    private class ValueIterator implements Iterator<V> {
        ListIterator<Entry<K, V>> currentListIterator;

        private ValueIterator() {
            currentIndex = 0;
            numberLeft = numEntries;
        }

        public boolean hasNext() {
            return numberLeft >= 0;
        }

        public V next() {
            V result = null;
            if (hasNext()) {
                while ((table.get(currentIndex) == null) || table.get(currentIndex).isRemoved()) {
                    currentIndex++;
                }
                result = table.get(currentIndex).getValue();
                numberLeft--;
                currentIndex++;
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
    }

    private class KeyIterator implements Iterator<K> {
        private KeyIterator() {
            currentIndex = 0; //current position in table
            numberLeft = numEntries; //number of entries left in iteration
        }

        public boolean hasNext() {
            return numberLeft >= 0;
        }

        public K next() {
            K result = null;
            //skip table locations that don't contain entry
            if (hasNext() && currentIndex != numEntries) {
                while ((table.get(currentIndex) == null) || table.get(currentIndex).isRemoved()) {
                    currentIndex++;
                }
                result = table.get(currentIndex).getKey();
                numberLeft--;
                currentIndex++;
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private int getIndex(K key) {
        int hashCode = Objects.hashCode(key);
        int index = hashCode % numEntries;
        // key.hashCode() could be negative.
        index = index < 0 ? index * -1 : index;
        return index;
    }

    @Override
    public V put(K key, V value) {
        if(key == null || value == null){
            throw new IllegalArgumentException();
        }
        V oldValue = null;
        int index = getIndex(key);
        return oldValue;
    }

    @Override
    public V remove(K key) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        int index = getIndex(key);
        V removedValue = null;
        boolean found = false;
        if (table.get(index).isIn()) {
            if (table.get(index).key.equals(key)) {
                found = true;
                removedValue = table.get(index).getValue();
                table.get(index).setToRemoved();
                numEntries--;
            }
        }
        return removedValue;
    }

    @Override
    public V getValue(K key) {
        int index = getIndex(key);
        Entry<K, V> in = table.get(index);
        if ((in != null) && in.isIn()) {
            return in.getValue();
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        int index = getIndex(key);
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).isIn()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    @Override
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    //checks if table is empty, if numEntries is zero it is empty
    @Override
    public boolean isEmpty() {
        return numEntries == 0;
    }

    @Override
    public boolean isFull() {
        return numEntries > table.size() * loadFactor;
    }

    @Override
    public int getSize() {
        return numEntries;
    }

    //removes all entries from table
    @Override
    public void clear() {
        List<Entry<K, V>> temp = Arrays.asList(new Entry[table.size()]);
        table = temp;
        numEntries = 0;
    }
}