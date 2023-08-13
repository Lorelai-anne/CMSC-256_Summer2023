/*
 * Project 05 - Stack Implementation
 * This project implements a Stack interface of abstract data types
 * to evaluate a String postfix expression into a real number
 * Lorelai Davis
 * CMSC 256 Section C01
 * 6 June 2023
 */
package Projects.Project05;

public class MathStack<E> implements StackInterface<E>{
    static class Node<E>{ //linked list implementation need an inner node class
        private E value;
        private Node<E> next;
        Node(E value){
            this.value = value;
            next = null;
        }
    }
    private Node<E> topOfStack;
    public MathStack(){ //constructor for the class, sets the top of the stack to null
        this.topOfStack = null;
    }

    public static double evaluateExpression(String expression, String vars){
        //creates a Double MathStack to add values to
        MathStack<Double> stack = new MathStack<>();
        //creating a char array full of char values from the string expression
        //replaces all spaces in the array with empty String values
        char[] ex = expression.replaceAll("\\s","").toCharArray();
        //searches through the array for letters, if encountering one he varsValue method is called
        //and the letter is replaces with the number value returned by the method
        for(int i=0;i<ex.length;i++){
            char c = ex[i];
            if(Character.isLetter(c)){
                char val = varsValue(c,vars);
                ex[i] = val;
            }
        }
        //iterating through the char expression array
        for(int i=0;i<ex.length;i++){
            //if expression index i is a number
            if(Character.isDigit(ex[i])){
                //pushes the value at i into the stack as a double value
                stack.push((double) (ex[i] - '0'));
            }else{
                //if index at I is an operator
                //two elements from stack are popped
                double val1 = stack.pop();
                double val2 = stack.pop();
                switch (ex[i]) { //checks on what the operator is and goes from there
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2-val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                }
            }
        }
        return stack.pop(); //returns the value in the stack
    }

    /**
     * takes a char value and a string and searches the string until the char value
     * is encountered
     * @param letter a letter char value
     * @param vars full string expression such as "a=3 b=2 c=4" (without the "")
     * @return
     */
    public static char varsValue(char letter, String vars){
        //iterating through the string vars
        for(int i=0;i<vars.length();i++){
            char c = vars.charAt(i);
            //if value at vars character at index i is equal to letter
            if(c == letter){
                i += 2; //increment i by 2 to get past the '=' to the number
                return vars.charAt(i); //returns the number
            }
        }return 0;
    }
    @Override
    public void push(E newEntry) {
        if(newEntry == null){
            throw new IllegalArgumentException();
        }
        //adds new entry to top of stack
        Node newTop = new Node(newEntry);
        newTop.next = topOfStack;
        topOfStack = newTop;
    }

    @Override
    public E pop() {
        if(isEmpty()){
            throw new EmptyMathStackException();
        }
        //removes and returns the value of the stacks top entry
        E past = topOfStack.value;
        topOfStack = topOfStack.next;
        return past;
    }

    @Override
    public E peek() {
        //throws custom exception if stack is empty
        if(isEmpty()){
            throw new EmptyMathStackException();
        }return topOfStack.value; //returns stacks top entry
    }

    @Override
    public boolean isEmpty() {
        //if the top of the stack is null then the stack is empty
        return topOfStack == null;
    }

    @Override
    public void clear() {
        //setting the top of the stack to null will empty the stack since
        //this is a linked list implementation of a stack
        topOfStack = null;
    }
}
