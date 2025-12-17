package university;

public class Person {
    int id;
    public String firstName;
    public String lastName;

    public Person() {
        this.firstName = "";
        this.lastName = "";
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void printFullName() {
        System.out.println("Full Name: " + firstName + " " + lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
