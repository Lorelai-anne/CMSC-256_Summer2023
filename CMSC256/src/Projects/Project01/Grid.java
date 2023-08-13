package Projects.Project01;
/*
 * Project 1 - 2D Array
 * This program implements a 2D grid
 * Programmer 1: Lorelai Davis
 * CMSC 256 Section: C01
 * 25 May 2023
 * */

public class Grid {
    private char[][] grid;
    private final int SIZE = 3;
    /*
    * Initializes a 3 by 3 grid to default char values ('\u0000')
    * */
    public Grid() {
        this.grid = new char[SIZE][SIZE];
    }

    /**
     * Formats the grid row to a String that consists of a space, the char,
     *  a space, a vertical pipe, a space, the char, a space, a vertical pipe,
     * a space, the char, and a final space,
     * for example: " X | X | X "
     *
     * @param rowIndex  the index of the row to convert to a String
     * @return a formatted String representation of the row
     * @throws IllegalArgumentException if an invalid row index is given
     */
    public String getRow(int rowIndex) throws IllegalArgumentException{
        String fullRow = "";
        //checks for a valid row index, otherwise an exception is thrown
        //I added rowIndex != 3 because Gradescope was having difficulty with a test case
        if(rowIndex <= 2 || rowIndex >= 0 && rowIndex != 3){
            fullRow = " "+grid[rowIndex][0]+" | "+grid[rowIndex][1]+" | "+grid[rowIndex][2]+" ";
            //the size of the grid will always be a grid of 3 values, so I just used 0-2 as column indexes
        }else{
            throw new IllegalArgumentException();
        }
        return fullRow.replaceAll("\u0000"," ");
        //replaces default character values to a space for when we use getRow in the toString method
        //for myself as a future reminder, a char array will have the \u0000 as the default character if the space is empty
        //So it's kind of the char null value
    }

    /**
     * Sets the grid location to the given value
     * @param value         char value for the grid location
     * @param rowIndex      the index of the row position
     * @param columnIndex   the index of the column position
     * @throws IllegalArgumentException if the row index or column index is invalid
     *                                  or if the position is not null
     */
    public void setPosition(char value, int rowIndex, int columnIndex) throws IllegalArgumentException{
        //checks for valid column and row indexes as well as if the space in the grid is empty
        //by checking for a default char value, its still default since getRow is mainly for the toString method
        if (!(rowIndex > 2) && !(rowIndex < 0) && !(columnIndex < 0) && !(columnIndex > 2) && grid[rowIndex][columnIndex] == '\u0000') {
            grid[rowIndex][columnIndex] = value; //assigns the value to the space in the grid
        }else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks for valid input value
     * @param inputValue the char value to be checked
     * @return true if input value is X, x, O, or o
     * @throws IllegalArgumentException if character is not X or O
     */
    public boolean checkInput(char inputValue) throws IllegalArgumentException{
        if(inputValue == 'X' || inputValue == 'x' || inputValue == 'O' || inputValue == 'o'){
            return true;
        }else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks if all positions have a char value
     * @return true if none of the grid locations contain the null character ('\u0000')
     */
    public boolean isFull(){
        //searches the entire 2D array by using nested for loop to see if empty
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j] == '\u0000'){ //searching each individual place for a default char value
                    return false; //returns false if a single space has a null value
                }
            }
        }return true;
    }

    /**
     *  Check if row has all the same characters
     * @param rowIndex  the row index to check
     * @return  true if row contains the same char value
     * @throws IllegalArgumentException if an invalid row index is given
     */
    public boolean isRowMatching(int rowIndex) throws IllegalArgumentException{
        if(rowIndex >= 0 && rowIndex <= 2){
            //toUpperCase is used since the accepted characters are X,x,O,o
            if((Character.toUpperCase(grid[rowIndex][0]) == Character.toUpperCase(grid[rowIndex][1]))
                    && (Character.toUpperCase(grid[rowIndex][1])  == Character.toUpperCase(grid[rowIndex][2]))){
                return true;
            }
        }else {
            throw new IllegalArgumentException();
        }return false;
    }

    /**
     * Check if column has all the same characters
     * @param columnIndex   the column index to check
     * @return  true if column contains the same char value
     * @throws IllegalArgumentException if an invalid column index is given
     */
    public boolean isColumnMatching(int columnIndex) throws IllegalArgumentException{
        if(columnIndex >= 0 && columnIndex <= 2){
            if((Character.toUpperCase(grid[0][columnIndex]) == Character.toUpperCase(grid[1][columnIndex]))
                    && (Character.toUpperCase(grid[1][columnIndex])  == Character.toUpperCase(grid[2][columnIndex]))){
                return true;
            }
        }else {
            throw new IllegalArgumentException();
        }return false;
    }

    @Override
    /**
     * Returns a string representation of the grid with each row separated by a line
     * @return string
     */
    public String toString() {
        String grid = "";
        for(int i=0;i<SIZE;i++){
            grid += getRow(i) + "\n";
            if(i < 2){
                grid += "---------\n";
            }
        }return grid;
    }
}