package Labs.Lab00;
/*
 * This program is used to show how to find errors in your program
 * Programmer 1: Lorelai Davis
 * CMSC 256 Section: C01
 * Lines with syntax errors: 26(added a semicolon), 27(changed the variable type to double), 29(added semicolon and parenthesis)
 *                           65(deleted the int), 37(changed 1000 to 100)
 * Runtime errors: 28(changed 0 to 3.0), 31(changed referenced array index to 6), 32(added parenthesis), , 43(switched < to >)
 *                 3 lines in the inRange method, added !(//lines of code//) and switched the true/false return statements
 */
import java.util.*;

public class DebuggingPractice {
    private static int evenPerfectSquareNumbers = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //declare variables
        double number;
        double average, value;
        String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};
        int[] nums = {23, 3, 14, -5, 44, 78, 6, 98, 25};
        String myName = "Lorelai Davis";
        value = 3.75; //was missing semicolon (;)
        number = value; //assigned a double data type variable to a int data type variable, compiler error
        average = (3 + 5 + 8) / 3.0; //dividing by zero
        System.out.println(" *** I am " + myName + " *** "); //was missing semicolon (;) and right parenthesis (")")
                System.out.println("The first day of the week is: "+ daysOfTheWeek[1]);
        System.out.println("The last day of the week is: "+ daysOfTheWeek[6]); //array index 7 is out of bounds
        average = (3 + 5 + 8) / 3.0; //missing parenthesis
        System.out.printf("expected average is 5.33, actual average is: %.2f", average); //changed println to printf to format to 2 decimal places
        System.out.println("\nexpected max is 98, actual max is: " + max(nums));
        System.out.println("expected type of number is double: " + number);
        System.out.println("calling countPerfectSquares(100)");
        countPerfectSquares(100); //was calling countPerfectSquares 1000
    }

    // Returns the maximum value in the array parameter
    public static int max(int[] a) {
        int result = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] > result) { //compiler error
                result = a[i];
            }
        }
        return result;
    }

    // Returns true if all values in the parameter array fall with the
    // range define by parameters low and high
    public static boolean inRange (int[] array , int low , int high ) {
        for (int i = 0 ; i < array.length ; i++) {
            if (!(array[i] > low && array[i] < high)) {
                return false;
            }
        }
        return true ;
    }

    // Displays the counts the total perfect squares and
    // //even perfect squares for a given number
    public static void countPerfectSquares(int num) {
        num = 100; //already defined in the parameters
        System.out.println("Total Perfect Squares: " + calculateCount(num));
        System.out.println("Even Perfect Squares : " + evenPerfectSquareNumbers);
    }

    public static int calculateCount(int i) {
        int perfectSquaresCount = 0;
        for (int number = 1; number <= i; number++) {
            if (isPerfectSquare(number)) {
                perfectSquaresCount++;
                if (number % 2 == 0) {
                    evenPerfectSquareNumbers++;
                }
            }
        }
        return perfectSquaresCount;
    }

    private static boolean isPerfectSquare(int number) {
        double sqrt = Math.sqrt(number);
        return sqrt - Math.floor(sqrt) == 0;
    }

    public static void setEvenPerfectSquareNumbers(int evenPerfectSquareNumbers) {
        DebuggingPractice.evenPerfectSquareNumbers = evenPerfectSquareNumbers;
    }
    public static int getEvenPerfectSquareNumbers() {
        return evenPerfectSquareNumbers;
    }
}
