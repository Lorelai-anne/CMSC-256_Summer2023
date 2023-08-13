package Labs.Lab07;

public class ArrayBasedStack<T> implements StackInterface<T> {
    private T[] data;
    private int topOfStack;
    private static final int INITIAL_CAPACITY = 5;

    public ArrayBasedStack(){
        T[] tempStack = (T[]) new Object[INITIAL_CAPACITY];
        data = tempStack;
        topOfStack = -1;
    }
    public ArrayBasedStack(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("Array initial size error");
        }
        @SuppressWarnings("Unchecked")
        T[] tempStack = (T[]) new Object[capacity];
        data = tempStack;
        topOfStack = -1;
    }

    private void expandArray(){
        if(data == null){
            throw new NullPointerException();
        }else if(data.length <= -1){
            throw new NegativeArraySizeException();
        }
        int length = data.length;
        T[] tempStack = (T[]) new Object[length*2];
        data = tempStack;
    }
    private boolean isArrayFull(){
        if(topOfStack >= data.length-1){
            return true;
        }
        return false;
    }
    @Override
    public void push(T newEntry) {
        if(isArrayFull()){
            expandArray();
        }
        topOfStack += 1;
        data[topOfStack] = newEntry;
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T curr = data[topOfStack];
        data[topOfStack] = null;
        topOfStack -= 1;
        return curr;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }return data[topOfStack];
    }

    @Override
    public boolean isEmpty() {
        if(topOfStack < 0){
            return true;
        }return false;
    }

    @Override
    public void clear() {
        @SuppressWarnings({"unchecked"})
        T[] tempStack = (T[]) new Object[INITIAL_CAPACITY];
        data = tempStack;
        topOfStack = -1;
    }
}
