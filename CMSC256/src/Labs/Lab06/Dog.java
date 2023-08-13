package Labs.Lab06;

import java.util.Objects;

public class Dog implements Comparable<Dog>{
    private String dogName;
    private int count;

    public Dog(){
        dogName = "";
        count = 0;
    }
    public Dog(String dogName, int count){
        this.dogName = dogName;
        this.count = count;
    }

    public int getCount() {
        return count;
    }public String getDogName() {
        return dogName;
    }
    public void setCount(int count) {
        this.count = count;
    }public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    @Override
    public int compareTo(Dog otherDog) {
        return this.getDogName().compareTo(otherDog.getDogName());
    }
    @Override
    public String toString() {
        return "\n "+getDogName()+" is registered "+getCount()+" times";
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
