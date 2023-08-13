package Labs.Lab01;
/*
 * Lab 01 - Inheritance Implementation
 * This class is used to implement the java classes Address and Name
 * Programmer 1: Lorelai Davis
 * CMSC 256 Section: C01
 * 23 May 2023
 * */
public class Person {
    private final int id;
    //has the same value as whatever the recordNumber is, cannot be changed once set in constructor
    private Name name;
    private Address homeAddress;
    private String phoneNumber, email;
    private static int recordNumber;
    //static variable, increases each time a new Person object is created

    public Person(){
        name = new Name();
        homeAddress = new Address();
        phoneNumber = "None given";
        email = "None given";
        id = ++recordNumber;
    }
    public Person(String first,String middle,String last,Address address,String phone,String email){
        name = new Name(first,middle,last);
        phoneNumber = phone;
        homeAddress = address;
        this.email = email;
        id = recordNumber++;
    }

    public void setName(Name name){
        this.name = name;
    }public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }public void setEmail(String email) {
        this.email = email;
    }public static void setRecordNumber(int recordNumber) {
        Person.recordNumber = recordNumber;
    }
    public Name getName(){
        return name;
    }public Address getHomeAddress(){
        return homeAddress;
    }public String getPhoneNumber(){
        return phoneNumber;
    }public String getEmail(){
        return email;
    }public int getRecordNumber(){
        return recordNumber;
    }public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return name+"\nHome Address: "+homeAddress+"\nPhone Number: "+phoneNumber+
                "\nEmail Address: "+email+"\nID: "+id;
    }
}
