package university;

public class Degree {
    public String name;

    public Degree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Degree BACHELOR = new Degree("Bachelor");
    public static Degree MASTER = new Degree("Master");
}
