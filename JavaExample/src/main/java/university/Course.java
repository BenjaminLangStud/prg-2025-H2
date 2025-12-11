package university;

import utils.StudentException;
import utils.TeacherException;

import java.util.ArrayList;

public class Course {
    String name;
    String description;
    Degree forDegree;
    ArrayList<Teacher> teachers;
    ArrayList<Student> students;

    public Course(String name, String description, Degree forDegree) {
        this.name = name;
        this.description = description;
        this.forDegree = forDegree;
        this.teachers = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void enrollStudent(Student student) throws StudentException {
        if (student.courses.contains(this)) {
            throw new StudentException("Student is already enrolled in " + this.name);
        }
    }

    protected void AddTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    protected void RemoveTeacher(Teacher teacher) throws TeacherException {
        if (!teachers.contains(teacher)) throw new TeacherException("Teacher does not teach this course and can therefore not be removed from this course.");
        teachers.remove(teacher);
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Teacher> GetTeachers() {
        return this.teachers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Degree getForDegree() {
        return forDegree;
    }

    public void setForDegree(Degree forDegree) {
        this.forDegree = forDegree;
    }
}
