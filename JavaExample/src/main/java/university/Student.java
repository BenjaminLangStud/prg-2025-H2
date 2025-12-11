package university;

import java.util.ArrayList;

public class Student extends Person {
    String firstName;
    String lastName;
    int age;
    ArrayList<Course> courses;

    public Student(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
        this.courses = new ArrayList<>();
    }

    public void printInfos() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        for (Course course : courses) {
            System.out.println("Enrolled Course: " + course.getName());
        }
    }

    public static void main(String[] args) {
        Student student = new Student("John", "Doe", 20);
        student.enrollInCourse(new Course("Mathematics", "Basic Math Course", new Degree("Master")));
        student.enrollInCourse(new Course("Computer Science", "Advance Computer Science", new Degree("Master")));
        student.printInfos();
    }

    public void enrollInCourse(Course course) {
        courses.add(course);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void GetStudyForTitle() {

    }
}
