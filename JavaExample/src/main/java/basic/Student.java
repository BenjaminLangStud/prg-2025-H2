package basic;
import java.util.ArrayList;

/**
 * Represents a student with first name, last name, age and a list of enrolled courses.
 * Minimal documentation focused on structure and runtime behaviour.
 */
public class Student {
    String firstName;
    String lastName;
    int age;
    ArrayList<String> courses;

    public Student() {
        // initialize with default values
        this.firstName = "John";
        this.lastName = "Doe";
        this.age = 18;
        this.courses = new ArrayList<String>();
    }

    /**
     * Constructs a Student with all fields provided.
     *
     * @param firstName student first name
     * @param lastName  student last name
     * @param age       student age
     * @param courses   list of enrolled courses
     */
    public Student(String firstName, String lastName, int age, ArrayList<String> courses) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.courses = courses;
    }

    /**
     * Demonstration entry point that creates example students and prints their info.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String args[]) {
        ArrayList<String> courseList = new ArrayList<String>();
        courseList.add("Mathematics");
        courseList.add("Physics");
        courseList.add("Programming");

        // student with provided values
        Student alice = new Student("Alice", "Smith", 20, courseList);
        // student with defaults
        Student john = new Student();

        alice.printStudentInfos();
        System.out.println("-----");
        john.printStudentInfos();
    }

    /**
     * Prints the student's name, age and each enrolled course to the console.
     */
    public void printStudentInfos() {
        System.out.println("Student: " + firstName + " " + lastName + ", Age: " + age);
        for (String course : courses) {
            System.out.println("Enrolled in course: " + course);
        }
    }

    // Standard getters/setters (self-explanatory)
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
}
