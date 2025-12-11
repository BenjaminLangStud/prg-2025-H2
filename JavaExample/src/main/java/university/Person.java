package university;

public class Person {
    String firstName;
    String lastName;
    String prefix;
    int age;

    public Person(String firstName, String lastName, int age) {
        this(firstName, lastName, age, null);
    }

    public Person(String firstName, String lastName, int age, String prefix) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.prefix = prefix;
    }

    /**
     * @return Returns the formal address to this person
     */
    public String GetFormalName() {
        if (prefix == null) {
            return firstName + " " + lastName;
        }
        return prefix + " " + firstName + " " + lastName;
    }

    /**
     * @return Get the first name
     */
    public String FirstName() { return firstName; }
}
