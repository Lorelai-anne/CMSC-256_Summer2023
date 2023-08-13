package Labs.Lab09;

public class MinHeap <E extends Comparable<? super E>>{
    private E[] heap;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 8;

    @SuppressWarnings({"unchecked"})
    public MinHeap(int capacity){
        if(capacity > 0){
            Object[] temp; //create Object array due to type
            temp = new Comparable[capacity]; //type erasure
            heap = (E[]) temp; //cast to generic type
        }else{
            throw new IllegalArgumentException();
        }
    }
    public MinHeap(){
        this(DEFAULT_CAPACITY);
    }
    public int size() {
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    private void expand(){
        @SuppressWarnings({"unchecked"})
        E[] newHeap = (E[]) new Comparable[heap.length*2];
        for(int i=0;i<size();i++){
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }
    private void swapElements(int index1, int index2){
        E temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
    private int getParentIndex(int childIndex){
        if(childIndex % 2 != 0){ //if odd, child is a left node
            return childIndex/2;
        }else { //if even, child is a right node
            return childIndex/2 - 1;
        }
        //can be derived from (2i+1) and (2i+2) indexing of left and right nodes
        //left children will always be found at off indices
    }
    private int smallerChildIndex(int parentIndex){
        int smaller = parentIndex;
        //get left child index by calculation based on parent index
        int leftChild = 2 * parentIndex +1;
        //if left child index is in bounds
        if (leftChild < size()-1) {
            //set smaller to left if left is smaller than parent
            if(heap[leftChild].compareTo(heap[smaller])<0)
                smaller = leftChild;
            int rightChild = leftChild + 1;
            //if right child index is in bounds
            if (rightChild < size() -1) {
                //set smaller to right if right is smaller than value
                if(heap[rightChild].compareTo(heap[smaller]) <0)
                    smaller = rightChild;
            }
        }
        return smaller;
    }
    public void insert(E element){
        int position = size();
        //expand array if position is too large
        if(position == heap.length){
            expand();
        }
        //increment size and add element at the last index
        size++;
        heap[position] = element;
        int parentIndex = getParentIndex(position);
        while(position > 0 && heap[position].compareTo(heap[parentIndex]) < 0){
            //if parent is greater, swap parent and node
            swapElements(position,parentIndex);
            //update position of the new element and find next parent up
            position = parentIndex;
            parentIndex = getParentIndex(position);
        }
    }
    public E remove(){
        if (isEmpty()){
            return null;
        }
        //take out root and place last node at root position
        E min = heap[0];
        heap[0] = heap[size() -1];
        heap[size() -1] = null; //set removed position to null
        size--;
        //position of new root and its smaller child
        int position = 0;
        int smallerChildIndex = smallerChildIndex(position);
        //while there is a smaller child, swap parent and child
        while (smallerChildIndex != position){
            swapElements(position,smallerChildIndex);
            //update position of node and get new smaller child
            position = smallerChildIndex;
            smallerChildIndex = smallerChildIndex(position);
        }return min;
    }
    public static void main(String[] args){
        MinHeap<Integer> mh = new MinHeap<>();
        mh.insert(2);
        mh.insert(4);
        mh.insert(1);
        mh.insert(10);
        mh.insert(3);
        mh.insert(6);
        mh.insert(15);
        mh.insert(12);
        mh.insert(16);
        mh.insert(5);
        while(!mh.isEmpty()){
            System.out.println(mh.remove());
            System.out.println();
        }
    }
}