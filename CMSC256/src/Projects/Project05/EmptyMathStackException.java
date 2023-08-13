/*
 * Project 05 - Custom Runtime exception
 * This class implements a custom emptyStackException for the MathStack class
 * Lorelai Davis
 * CMSC 256 Section C01
 * 6 June 2023
 */package Projects.Project05;

public class EmptyMathStackException extends RuntimeException{
    //no parameters for the exception so it can be thrown on its own
    public EmptyMathStackException(){
    }
}
