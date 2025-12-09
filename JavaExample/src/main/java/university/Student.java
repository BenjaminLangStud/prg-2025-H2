package university;

import java.util.ArrayList;

public class Student {
    String firstName;
    String lastName;
    int age;
    ArrayList<String> courses;

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    public void printInfos() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        for (String course : courses) {
            System.out.println("Enrolled Course: " + course);
        }
    }

    public static void main(String[] args) {
        Student student = new Student("John", "Doe", 20);
        student.enrollCourse("Mathematics");
        student.enrollCourse("Computer Science");
        student.printInfos();
    }

    public void enrollCourse(String courseName) {
        courses.add(courseName);
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

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
}
