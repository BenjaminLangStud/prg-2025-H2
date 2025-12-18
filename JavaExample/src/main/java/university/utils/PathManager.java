package university.utils;


import java.util.Objects;

class PathManagement {

    /**
     * Points to the directory containing all files placed in the directory src/main/resources
     */
    static String ROOT_PATH;

    public static void loadPaths() {
        PathManagement.ROOT_PATH = Objects.requireNonNull(PathManagement.class.getResource("/")).getPath();
    }

    public static void main() {
        PathManagement.loadPaths();

        System.out.println(ROOT_PATH);
    }
}