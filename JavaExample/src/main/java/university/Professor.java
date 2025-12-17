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
        this.officeNumber = officeNumber;
    }

    public void addCourseTeaching(Course course) {
        if (coursesTeaching == null) {
            coursesTeaching = new ArrayList<>();
        }
        coursesTeaching.add(course);
    }

    public void getCoursesTeaching() {
        if (coursesTeaching != null) {
            for (Course course : coursesTeaching) {
                System.out.println("Teaching Course: " + course.getName());
            }
        } else {
            System.out.println("No courses assigned.");
        }
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }
}