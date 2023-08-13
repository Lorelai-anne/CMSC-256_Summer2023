package Labs.Lab02;
/*
 * Lab 02 - Interface Implementation
 * This class uses the Comparable<T> interface to create an book object
 * Lorelai Davis
 * CMSC 256 Section C01
 * 25 May 2023
 */

public class MyBook implements Comparable<MyBook>{
    private String title, authorFirstName, authorLastName, ISBN10, ISBN13;

    public MyBook(){
        title = "None Given";
        authorFirstName = "None Given";
        authorLastName = "None Given";
        ISBN10 = "0000000000";
        ISBN13 = "0000000000000";
    }
    public MyBook(String title,String authorFirstName,String authorLastName,String ISBN10,String ISBN13){
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.ISBN10 = ISBN10;
        this.ISBN13 = ISBN13;
    }
    public String getTitle() {
        return title;
    }public String getAuthorFirstName() {
        return authorFirstName;
    }public String getAuthorLastName(){
        return authorLastName;
    }public String getISBN10(){
        return ISBN10;
    }public String getISBN13(){
        return ISBN13;
    }
    public void setTitle(String title){
        if(title != null){
            this.title = title;
        }else {
            throw new IllegalArgumentException();
        }
    }public void setAuthorFirstName(String authorFirstName){
        if(authorFirstName != null){
            this.authorFirstName = authorFirstName;
        }else {
            throw new IllegalArgumentException();
        }
    }public void setAuthorLastName(String authorLastName){
        if(authorLastName != null){
            this.authorLastName = authorLastName;
        }else {
            throw new IllegalArgumentException();
        }
    }public void setISBN10(String ISBN10){
        //checks if the string is 10 characters long, isn't empty, and if it
        //only contains digits with the method isDigit
        if(ISBN10 != null && ISBN10.length() == 10 && isDigit(ISBN10)){
            this.ISBN10 = ISBN10;
        }else {
            throw new IllegalArgumentException();
        }
    }public void setISBN13(String ISBN13){
        //checks if the given string is the appropriate length, isn't empty,
        //and if it only contains digit characters
        if(ISBN13 != null && ISBN13.length() == 13 && isDigit(ISBN13)){
            this.ISBN13 = ISBN13;
        }else {
            throw new IllegalArgumentException();
        }
    }
    public static boolean isDigit(String str){
        //uses for loop to traverse the entire string
        for(int i=0;i<str.length();i++){
            //checks, with Character.isDigit, if at the specified character "i"
            //in the string is a digit and will result false if its not
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }
        }return true;
    }
    @Override
    public int compareTo(MyBook o) {
        //uses the string compareTo method to compare the strings in each object
        int result = getAuthorLastName().compareTo(o.getAuthorLastName());
        //if the result between the two specified strings is zero then
        //they are equal
        if(result == 0){
            result = getAuthorFirstName().compareTo(o.getAuthorFirstName());
        }if(result == 0){
            result = getTitle().compareTo(o.getTitle());
        }return result;
    }
    @Override
    public String toString(){
        return "Title: "+getTitle()+"\nAuthor: "+getAuthorFirstName()+" "+getAuthorLastName()+"\nISBN10: "+
                getISBN10()+"\nISBN13: "+getISBN13();
    }
    @Override
    public boolean equals(Object otherBook){
        //checks if the variables are referencing the same object
        if(this == otherBook){
            return true;
        }
        //checks if the parameter otherBook is a MyBook object
        if(!(otherBook instanceof MyBook)){
            return false;
        }
        //casts the argument to the correct type
        MyBook other = (MyBook)otherBook;
        //checks two ISBNs and will return true if they are the same
        if(other.ISBN10 == this.ISBN10){
            return true;
        }if(other.ISBN13 == this.ISBN13){
            return true;
        }return false;
    }
}