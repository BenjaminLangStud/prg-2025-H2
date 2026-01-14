package university.db;

import java.sql.*;
import java.util.ArrayList;
import university.Course;
import university.Professor;
import university.Student;

public class DatabaseConnector {

    private final String url;
    private final String user;
    private final String password;

    public DatabaseConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // ---------- Course ----------
    public Course insertCourse(Course course) throws Exception {
        // Tabelle und Spalten gemäß uni-schema.sql (Schema uni, Tabelle course, Spalte forDegree)
        String sql = "INSERT INTO uni.course(name, description, forDegree) VALUES(?,?,?)";
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setString(3, course.getForDegree());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Inserting course failed, no rows affected.");
            }
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    Course inserted = getCourseById(newId);
                    if (inserted == null) throw new Exception("Inserted course not found after insert.");
                    return inserted;
                } else {
                    throw new Exception("Inserting course failed, no ID obtained.");
                }
            }
        }
    }

    public Course updateCourse(Course course) throws Exception {
        String sql = "UPDATE uni.course SET name = ?, description = ?, forDegree = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setString(3, course.getForDegree());
            ps.setInt(4, course.getId());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Updating course failed, no rows affected.");
            }
            Course updated = getCourseById(course.getId());
            if (updated == null) throw new Exception("Updated course not found after update.");
            return updated;
        }
    }

    private Course getCourseById(int id) throws SQLException {
        String sql = "SELECT id, name, description, forDegree FROM uni.course WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("forDegree")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    // getCourses
    public ArrayList<Course> getCourses() throws SQLException {
        String sql = "SELECT id, name, description, forDegree FROM uni.course";
        ArrayList<Course> courses = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("forDegree")
                );
                courses.add(course);
            }
        }
        return courses;
    }

    // ---------- Professor ----------
    public Professor insertProfessor(Professor professor) throws Exception {
        // Tabelle uni.professor, Spalten firstName, lastName, officeNumber
        String sql = "INSERT INTO uni.professor(firstName, lastName, officeNumber) VALUES(?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, professor.getFirstName());
            ps.setString(2, professor.getLastName());
            ps.setString(3, professor.getOfficeNumber());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Inserting professor failed, no rows affected.");
            }
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    Professor inserted = getProfessorById(newId);
                    if (inserted == null) throw new Exception("Inserted professor not found after insert.");
                    return inserted;
                } else {
                    throw new Exception("Inserting professor failed, no ID obtained.");
                }
            }
        }
    }

    public Professor updateProfessor(Professor professor) throws Exception {
        String sql = "UPDATE uni.professor SET firstName = ?, lastName = ?, officeNumber = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, professor.getFirstName());
            ps.setString(2, professor.getLastName());
            ps.setString(3, professor.getOfficeNumber());
            ps.setInt(4, professor.getId());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Updating professor failed, no rows affected.");
            }
            Professor updated = getProfessorById(professor.getId());
            if (updated == null) throw new Exception("Updated professor not found after update.");
            return updated;
        }
    }

    // get professors
    public ArrayList<Professor> getProfessors() throws SQLException {
        String sql = "SELECT id, firstName, lastName, officeNumber FROM uni.professor";
        ArrayList<Professor> professors = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Professor professor = new Professor(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("officeNumber")
                );
                professors.add(professor);
            }
        }
        return professors;
    }

    private Professor getProfessorById(int id) throws SQLException {
        String sql = "SELECT id, firstName, lastName, officeNumber FROM uni.professor WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Professor(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("officeNumber")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    // ---------- Student ----------
    public Student insertStudent(Student student) throws Exception {
        // Tabelle uni.student, Spalten firstName, lastName, age
        String sql = "INSERT INTO uni.student(firstName, lastName, age) VALUES(?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Inserting student failed, no rows affected.");
            }
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int newId = keys.getInt(1);
                    Student inserted = getStudentById(newId);
                    if (inserted == null) throw new Exception("Inserted student not found after insert.");
                    return inserted;
                } else {
                    throw new Exception("Inserting student failed, no ID obtained.");
                }
            }
        }
    }

    public Student updateStudent(Student student) throws Exception {
        String sql = "UPDATE uni.student SET firstName = ?, lastName = ?, age = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            ps.setInt(4, student.getId());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Updating student failed, no rows affected.");
            }
            Student updated = getStudentById(student.getId());
            if (updated == null) throw new Exception("Updated student not found after update.");
            return updated;
        }
    }

    public ArrayList<Student> getStudents() throws SQLException {
        String sql = "SELECT id, firstName, lastName, age FROM uni.student";
        ArrayList<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student student = new Student(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("age")
                );
                students.add(student);
            }
        }
        return students;
    }

    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT id, firstName, lastName, age FROM uni.student WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    // ---------- Assignment methods ----------
    // Professor ↔ Course: create assignment
    public Professor assignCourseToProfessor(int professorId, int courseId) throws Exception {
        String sql = "INSERT INTO uni.professor_course_assignment(professorId, courseId) VALUES(?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, professorId);
            ps.setInt(2, courseId);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Creating professor-course assignment failed, no rows affected.");
            }
            Professor p = getProfessorWithCoursesById(professorId);
            if (p == null) throw new Exception("Professor not found after creating assignment.");
            return p;
        }
    }

    // Professor ↔ Course: delete assignment
    public Professor deleteCourseFromProfessor(int professorId, int courseId) throws Exception {
        String sql = "DELETE FROM uni.professor_course_assignment WHERE professorId = ? AND courseId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, professorId);
            ps.setInt(2, courseId);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Deleting professor-course assignment failed, no rows affected.");
            }
            Professor p = getProfessorWithCoursesById(professorId);
            if (p == null) throw new Exception("Professor not found after deleting assignment.");
            return p;
        }
    }

    // Student ↔ Course: create assignment
    public Student assignCourseToStudent(int studentId, int courseId) throws Exception {
        String sql = "INSERT INTO uni.student_course_assignment(studentId, courseId) VALUES(?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Creating student-course assignment failed, no rows affected.");
            }
            Student s = getStudentWithCoursesById(studentId);
            if (s == null) throw new Exception("Student not found after creating assignment.");
            return s;
        }
    }

    // Student ↔ Course: delete assignment
    public Student deleteCourseFromStudent(int studentId, int courseId) throws Exception {
        String sql = "DELETE FROM uni.student_course_assignment WHERE studentId = ? AND courseId = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new Exception("Deleting student-course assignment failed, no rows affected.");
            }
            Student s = getStudentWithCoursesById(studentId);
            if (s == null) throw new Exception("Student not found after deleting assignment.");
            return s;
        }
    }

    // ---------- Helper loaders ----------
    private Professor getProfessorWithCoursesById(int id) throws SQLException {
        // lade Basisdaten
        String profSql = "SELECT id, firstName, lastName, officeNumber FROM uni.professor WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(profSql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                Professor p = new Professor(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("officeNumber")
                );
                // lade Kurse
                String courseSql = "SELECT c.id, c.name, c.description, c.forDegree "
                                 + "FROM uni.course c "
                                 + "JOIN uni.professor_course_assignment a ON c.id = a.courseId "
                                 + "WHERE a.professorId = ?";
                try (PreparedStatement cps = conn.prepareStatement(courseSql)) {
                    cps.setInt(1, id);
                    try (ResultSet crs = cps.executeQuery()) {
                        while (crs.next()) {
                            Course c = new Course(
                                crs.getInt("id"),
                                crs.getString("name"),
                                crs.getString("description"),
                                crs.getString("forDegree")
                            );
                            p.addCourseTeaching(c);
                        }
                    }
                }
                return p;
            }
        }
    }

    private Student getStudentWithCoursesById(int id) throws SQLException {
        // lade Basisdaten
        String studSql = "SELECT id, firstName, lastName, age FROM uni.student WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(studSql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("age")
                );
                // lade Kurse
                String courseSql = "SELECT c.id, c.name, c.description, c.forDegree "
                                 + "FROM uni.course c "
                                 + "JOIN uni.student_course_assignment a ON c.id = a.courseId "
                                 + "WHERE a.studentId = ?";
                try (PreparedStatement cps = conn.prepareStatement(courseSql)) {
                    cps.setInt(1, id);
                    try (ResultSet crs = cps.executeQuery()) {
                        while (crs.next()) {
                            Course c = new Course(
                                crs.getInt("id"),
                                crs.getString("name"),
                                crs.getString("description"),
                                crs.getString("forDegree")
                            );
                            s.enrollCourse(c);
                        }
                    }
                }
                return s;
            }
        }
    }

    public void cleanDB() {
        String[] tables = {
            "uni.professor_course_assignment",
            "uni.student_course_assignment",
            "uni.professor",
            "uni.student",
            "uni.course"
        };
        try (Connection conn = getConnection()) {
            for (String table : tables) {
                String sql = "DELETE FROM " + table;
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage (adjust connection parameters as needed)
        DatabaseConnector db = new DatabaseConnector("jdbc:postgresql://localhost:5432/prg", "postgres", "admin");
        try {

            // Insert a new course
            Course newCourse = new Course(0, "Database Systems", "Introduction to Databases", "CS");
            Course insertedCourse = db.insertCourse(newCourse);
            System.out.println("Inserted Course ID: " + insertedCourse.getId());

            // Insert a new professor
            Professor newProf = new Professor(0, "John", "Doe", "Room 101");
            Professor insertedProf = db.insertProfessor(newProf);
            System.out.println("Inserted Professor ID: " + insertedProf.getId());

            // Assign course to professor
            Professor updatedProf = db.assignCourseToProfessor(insertedProf.getId(), insertedCourse.getId());
            System.out.println("Professor now teaches " + updatedProf.getCoursesTeaching().size() + " courses.");
            db.cleanDB();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
