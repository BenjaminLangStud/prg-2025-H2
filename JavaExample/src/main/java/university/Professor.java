package university;

import java.util.ArrayList;

public class Professor extends Person {
    public String officeNumber;
    public ArrayList<Course> coursesTeaching;

    public Professor() {
        super();
        this.officeNumber = "";
    }


    public Professor(int id, String firstName, String lastName, String officeNumber) {
        super(firstName, lastName);
        this.id = id;
        this.officeNumber = officeNumber;
    }

    public void addCourseTeaching(Course course) {
        if (coursesTeaching == null) {
            coursesTeaching = new ArrayList<>();
        }
        coursesTeaching.add(course);
    }

    public ArrayList<Course> getCoursesTeaching() {
        return this.coursesTeaching;
    }

    public void printInformation() {
        System.out.println("Professor Information:");
        super.printFullName();
        System.out.println("Office Number: " + this.officeNumber);
        for (Course course : coursesTeaching) {
            System.out.println("Teaching Course: " + course.getName());
        }
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }
}