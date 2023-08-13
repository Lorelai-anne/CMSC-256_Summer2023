package Projects.Project03;
/*
 * Project 03 - Transpositional List
 * This class implements a Linked List with a self organizing
 * transpose method that is used after the getEntry method is called
 * Lorelai Davis
 * CMSC 256 Section C01
 * 9 June 2023
 */

public class TranspositionalList<E> implements ListInterface<E>{
    static class Link<E>{ //a class represents an individual node in the list
        private E value; //generic variable for the node
        private Link<E> next; //variable to point toward the next node

        Link(E value){ //constructor for the node
            this.value = value;
            next = null;
        }
    }

    private Link<E> head; //points to list header
    private Link<E> tail; //points to the last element in the list
    private Link<E> curr; //points to the current value
    private int size; //size of the list

    //setter and getter methods for the size variable
    public int getSize() {
        return size;
    }public void setSize(int size) {
        this.size = size;
    }

    /**
     * swaps the node being called in getEntry with the node directly preceding it
     * @param index - index of the node
     * @param val - value of the node in relation to the index
     */
    public void transpose(int index, E val){
        int i=1;
        curr = head; //sets the current node to the head
        E temp = curr.value; //initializing temp variable
        while(i <= index){
            if(i == index-1){ //if the index pointer is one before the desired index
                temp = curr.value; //assign temp value to current value
                curr.value = val; //set current value to the value of the index value
                curr = curr.next; ///sets current node to next
                i++;
            }else if(i == index){
                curr.value = temp; //sets current node value to temp value
                i++;
            }else{ //if index doesn't match index parameter or one before it
                curr = curr.next;
                i++;
            }
        }
    }

    @Override
    public void add(E newEntry) {
        //creating a new node for the list
        Link<E> newLink = new Link<>(newEntry);
        if(newEntry == null){ //if the new entry is null then an exception is thrown
            throw new NullPointerException();
        }else if(head == null){
            //if the head is null, the list is empty to the newEntry node
            //will point to both the head and the tail
            head = newLink;
            tail = newLink;
            size++;
        }else{
            //the new node will be added after the current tail so the tail.next will
            //have the newEntry value
            tail.next = newLink;
            //the newEntry node will now be the new tail
            tail = newLink;
            size++;
        }
    }

    @Override
    public void clear() {
        head = null; //setting the head of the list to null will mean the list is empty
        size = 0; //setting the size of the list to zero
    }

    @Override
    public E getEntry(int givenPosition) {
        int i = 1; //linked lists start with an index of 1
        curr = head; //setting our current value to the first node in the list
        //using a while loop to change the current value until we get to a specific index of the list
        while(i < givenPosition){
            curr = curr.next; //current value is equal to the value after it
            i++; //increment the index pointer
        }
        E temp = curr.value;
        transpose(givenPosition,temp);
        return temp; //returns the value of the node
    }

    @Override
    public boolean contains(E anEntry) {
        int i = 1;
        curr = head; //curr is equal to the first value of the list
        while(i <= size){
            //if the current node value is equal to our value it returns true
            if(curr.value == anEntry){
                return true;
            }else{ //otherwise the current node will change
                curr = curr.next;
                i++;
            }
        }return false; //returns false if the entry is not in the list
    }

    @Override
    public int size() {
        return size; //returns the size, which is incremented in the add method
    }

    @Override
    public boolean isEmpty() {
        return size == 0; //if the size of the list is zero, its considered empty
    }
}
