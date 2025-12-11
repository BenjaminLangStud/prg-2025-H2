import university.Course;
import university.Degree;
import university.Student;

public class HelloCourse {
    public static void main(String[] args) {
        Course programmingCourse = new Course("Programming", "Learn Programming", Degree.BACHELOR);
        Student benjaminLang = new Student("Benjamin", "Lang", 19);
        Student johnDoe = new Student("John", "Doe", -5);

        benjaminLang.enrollInCourse(programmingCourse);
        johnDoe.enrollInCourse(programmingCourse);

        System.out.println("Hello, Programming Course!");
        System.out.println("Congratulations your first java programming is running");
    }
}
