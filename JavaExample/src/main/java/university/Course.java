package university;

public class Course {
    public static int courseCount = 0;

    public Course() {
        courseCount += 1;
    }

    public static int getCourseCount() {
        return courseCount;
    }

    public static void main(String[] args) {
        // runs the program
    }
    int id;
    String name;
    String description;
    String forDegree;



    public Course(int id, String name, String description, String forDegree) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
        this.forDegree = forDegree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForDegree() {
        return forDegree;
    }

    public void setForDegree(String forDegree) {
        this.forDegree = forDegree;
    }
}
