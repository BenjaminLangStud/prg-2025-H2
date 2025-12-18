package university;

import java.util.ArrayList;

public class Student extends Person implements InformationPrinter{
    int age;
    ArrayList<Course> courses;

    public Student(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    @Override
    public void printInformation() {
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Age: " + age);
        for (Course course : courses) {
            System.out.println("Enrolled Course: " + course.getName());
        }
    }

    public void enrollCourse(Course course) {
        courses.add(course);
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
}
