package Projects.Project02;
/*
 * Project 02 - Inheritance
 * This class extends the Person class with addition to
 * extra office, salary, and dateHired variables
 * Lorelai Davis
 * CMSC 256 Section: C01
 * 26 May 2023
 **/

//import for the variable HireDate
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Employee extends Person{
    private String office;
    private LocalDate HireDate;
    private int salary;

    public Employee(){
        super();
        office = "Unassigned";
        salary = 0;
        HireDate = null;
    }
    //initializes its own variables as well as its parents variables
    public Employee(String firstName,String middleName,String lastName,Address address,
                    String phone,String email,String office,int salary,int month,int day,int year) throws IllegalArgumentException{
        super(firstName,middleName,lastName,address,phone,email);
        this.office = office;
        if(salary >= 0){
            this.salary = salary;
        }else{
            throw new IllegalArgumentException();
        }
        this.HireDate = LocalDate.of(year,month,day);
    }
    public String getOffice(){
        return office;
    }public int getSalary(){
        return salary;
    }public LocalDate getHireDate() {
        return HireDate;
    }
    public void setOffice(String office){
        this.office = office;
    }public void setSalary(int salary){
        if(salary >= 0){
            this.salary = salary;
        }else{
            throw new IllegalArgumentException();
        }
    }public void setHireDate(LocalDate HireDate){
        this.HireDate = HireDate;
    }

    @Override
    public String toString() {
        return super.toString()+"\nOffice: "+getOffice()+"\nSalary: "+getSalary()+"\nDate Hired: "+getHireDate();
    }
}
