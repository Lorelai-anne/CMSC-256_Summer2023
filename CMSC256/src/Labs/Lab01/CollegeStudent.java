package Labs.Lab01;
/*
 * Lab 01 - Inheritance Implementation
 * This class is used to implement the Person class
 * Programmer 1: Lorelai Davis
 * CMSC 256 Section: C01
 * 23 May 2023
 * */
public class CollegeStudent extends Person{
    private String level;

    public CollegeStudent(){
        super();
        // calls the Person class parameterless constructor so there are no parameters when calling super()
        level = "Freshman";
    }

    //parameterized constructor, it has the same parameters as the Person parameterized constsructor
    //with addition to String level
    public CollegeStudent(String firstName,String middleName,String lastName,Address address,String phone,
                          String email,String level) throws IllegalArgumentException{
        super(firstName,middleName,lastName,address,phone,email);
        //checks if the value passed to level is one of five specific values
        //otherwise an IllegalArgumentException is passed
        if(level.equalsIgnoreCase("Freshman")||level.equalsIgnoreCase("Sophomore")||level.equalsIgnoreCase("Junior")
                ||level.equalsIgnoreCase("Senior")||level.equalsIgnoreCase("Graduate")){
            this.level = level;
        }else {
            throw new IllegalArgumentException();
        }
    }
    public void setLevel(String level) throws IllegalArgumentException{
        //checks if the value passed to level is one of five specific values
        //otherwise an IllegalArgumentException is passed
        //equalsIgnoreCase is used to ignore capitalization when checking the string
        if(level.equalsIgnoreCase("Freshman")||level.equalsIgnoreCase("Sophomore")||level.equalsIgnoreCase("Junior")
           ||level.equalsIgnoreCase("Senior")||level.equalsIgnoreCase("Graduate")){
            this.level = level;
        }else {
            throw new IllegalArgumentException();
        }
    }
    public String getLevel(){
        return level;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCollege Level: "+level;
    }
    //toString method for the CollegeStudent class, calls on the Person toString method by
    //using super.toString() and adding the college level to the end
}
