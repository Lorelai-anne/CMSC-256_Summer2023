package Projects.Project02;
/*
 * Project 02 - Inheritance
 * This class extends the Employee class with
 * addition to a title variable
 * Lorelai Davis
 * CMSC 256 Section: C01
 * 26 May 2023
 **/

public class Staff extends Employee{
    private String title;

    public Staff(){
        super();
        title = "Unknown";
    }
    //parameterized constructor initializes both its own variable and its inherited ones
    public Staff(String first,String middle,String last,Address address,String phone,
                 String email,String office,int salary,int day,int month,int year,String title){
        super(first,middle,last,address,phone,email,office,salary,day,month,year);
        this.title = title;
    }
    public String getTitle(){
        return title;
    }public void setTitle(String title){
        this.title = title;
    }
    @Override
    public String toString() {
        return super.toString()+"\nTitle: "+getTitle();
    }
}