package Labs.Lab10;

import java.util.Iterator;

public class HashTableQuadraticProbing<K,V> extends HashTableOpenAddressing<K,V> {
    public int quadraticProbe(int hashIndex, K keyIn){
        int increment = 0;
        boolean found = false; //flag variable used to search keys
        int removedStateIndex = -1; //Index of first removed location
        //table is a protected instance variable in the HashTableOpenAddressing class,
        //so it can be accessed directly
        if(table[hashIndex] == null){
            return hashIndex;
        }
        while(!found && table[hashIndex] != null){
            if(table[hashIndex].isIn()){ //if this location is occupied
                if(keyIn.equals(table[hashIndex].getKey())){ //and the key matches
                    found = true;
                }else{ //skip entries that were removed
                    //save index of first location in removed state
                    hashIndex = (int) ((hashIndex+(Math.pow(increment,2)))%table.length);
                    increment++;
                }
            }else{
                if(removedStateIndex == -1){
                    removedStateIndex = hashIndex;
                    hashIndex = (int) ((hashIndex+(Math.pow(increment,2)))%table.length);
                    increment++;
                }
            }
        }
        if(found || (removedStateIndex == -1)){
            return hashIndex;
        }else{
            return removedStateIndex;
        }
    }
    @Override
    public V put(K keyIn, V valueIn) {
        if(keyIn == null || valueIn == null){
            throw new IllegalArgumentException();
        }
        V oldValue = null;
        int index = super.getHashIndex(keyIn);
        index = quadraticProbe(index,keyIn);
        if(table[index] == null || table[index].isRemoved()){
            table[index] = new Entry<>(keyIn,valueIn);
            numEntries++;
        }else{
            oldValue = table[index].getValue();
            table[index] = new Entry<>(keyIn,valueIn);
        }
        if(isFull()){
            enlargeHashTable();
        }
        return oldValue;
    }

    @Override
    public V remove(K key) {
        V removedValue = null;
        int index = super.getHashIndex(key);
        index = quadraticProbe(index,key);
        boolean found = false;
        if(table[index].isIn()){ //if this location is occupied
            if(key.equals(table[index].getKey())){ //and the key matches
                found = true;
                removedValue = table[index].getValue();
                table[index].setToRemoved();
                numEntries--;
            }
        }
        return removedValue;
    }

    @Override
    public V getValue(K key) {
        int index = super.getHashIndex(key);
        index = quadraticProbe(index,key);
        Entry<K,V> item = table[index];
        if ((item != null) && item.isIn()) {
            return item.getValue();
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        int index = super.getHashIndex(key);
        for (int i=0;i<table.length;i++){
            if(table[i].getValue().equals(index)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HashTableQuadraticProbing<String,Integer> purchases = new HashTableQuadraticProbing<>();
        String[] names = {"Pax","Eleven","Angel","Abigail","Jack"};
        purchases.put(names[0],654);
        purchases.put(names[1],341);
        purchases.put(names[2],70);
        purchases.put(names[3],867);
        purchases.put(names[4],5309);

        System.out.println("Contents with linear probing:\n"+purchases.toString());
        System.out.println("Replaced old value was "+purchases.put(names[1],170));
        System.out.println("Contents after changing Eleven to 170:\n"+purchases.toString());
        System.out.println("Calling getValue() on Pax, Eleven, & Angel");
        System.out.println("\tPax: "+purchases.getValue(names[0]));
        System.out.println("\tEleven: "+purchases.getValue(names[1]));
        System.out.println("\tAngel: "+purchases.getValue(names[2]));
        purchases.remove(names[0]);
        purchases.remove(names[2]);
        System.out.println("Contents after removing Pax & Angel:\n"+purchases);
        purchases.put("Gino",348);
        System.out.println("Contents after adding Gino:\n"+purchases);

        Iterator<String> keyIter = purchases.getKeyIterator();
        Iterator<Integer> valueIter = purchases.getValueIterator();
        System.out.println("Contents of the hash table:");
        while (keyIter.hasNext()) {
            System.out.println("Key-"+keyIter.next()+" : Value-"+valueIter.next());
        }
    }
}
