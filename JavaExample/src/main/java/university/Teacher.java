package university;

import utils.TeacherException;

import java.util.ArrayList;

public class Teacher extends Person {
    ArrayList<Course> courses = new ArrayList<>();

    public Teacher(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    public void teachCourse(Course course) {
        this.courses.add(course);
        course.AddTeacher(this);
    }

    public void stopTeachingCourse(Course course) throws TeacherException {
        if (!courses.contains(course)) {
            throw new TeacherException("Teacher is not teaching this course and can therefore not stop teaching it!");
        }

        courses.remove(course);
    }

}
