package Projects.Project02;
/*
 * Project 02 - Inheritance
 * This class extends the Employee class with addition to
 * an extra rank variable that can be one of five different values
 * Lorelai Davis
 * CMSC 256 Section: C01
 * 26 May 2023
 **/
public class Faculty extends Employee{
    private String rank;

    public Faculty(){
        //default constructor calls the parent class default constructor
        super();
        this.rank = "Instructor";
    }
    //parameterized constructor initializes its own variables and its inherited ones
    //which includes the variables of the class its extending along with any other variables
    //the parent class is extending
    public Faculty(String first,String middle,String last,Address address,String phone,
                   String email,String office,int salary,int day,int month,int year,String rank) throws IllegalArgumentException{
        super(first,middle,last,address,phone,email,office,salary,day,month,year);
        //checks if the given value for rank is one of five specific String values if not an exception is passed
        if(rank.equalsIgnoreCase("Adjunct")||rank.equalsIgnoreCase("Assistant Professor")||rank.equalsIgnoreCase("Instructor")
                ||rank.equalsIgnoreCase("Associate Professor")||rank.equalsIgnoreCase("Professor")){
            this.rank = rank;
        }else{
            throw new IllegalArgumentException();
        }
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) throws IllegalArgumentException{
        //same as parameterized constructor, passed values are inspected and an exception is passed
        //if not acceptable
        if(rank.equalsIgnoreCase("Adjunct")||rank.equalsIgnoreCase("Assistant Professor")
                ||rank.equalsIgnoreCase("Associate Professor")||rank.equalsIgnoreCase("Professor")){
            this.rank = rank;
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return super.toString()+"\nRank: "+getRank();
    }
}
