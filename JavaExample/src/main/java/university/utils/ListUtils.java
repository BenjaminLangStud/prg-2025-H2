package university.utils;


import university.Course;
import university.Professor;
import university.Student;

import java.util.List;

public class ListUtils {
    public static Student findStudentById(int id, List<Student> students) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public static Professor findProfessorById(int id, List<Professor> professors) {
        for (Professor professor : professors) {
            if (professor.getId() == id) {
                return professor;
            }
        }
        return null;
    }

    public static Course findCourseById(int id, List<Course> courses) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }
}

