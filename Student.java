import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student with an ID, name, and list of enrolled courses.
 * This is the data model (OOP: encapsulation via private fields + getters/setters).
 */
public class Student {

    // Private fields — only accessible through getters/setters
    private String studentId;
    private String name;
    private List<String> courses;

    /**
     * Constructor — initializes a new Student with an ID and name.
     * Courses list starts empty.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.courses = new ArrayList<>(); // Empty course list on creation
    }

    // --- Getters ---

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<String> getCourses() {
        return courses;
    }

    // --- Setters ---

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a single course to this student's course list.
     */
    public void addCourse(String course) {
        if (courses.contains(course)) {
            System.out.println("Error: " + name + " is already enrolled in \"" + course + "\".");
            return;
        }
        courses.add(course);
    }

    /**
     * toString — returns a readable summary of the student.
     * Automatically used when printing a Student object.
     */
    @Override
    public String toString() {
        return "Student ID: " + studentId + " | Name: " + name + " | Courses: " + courses;
    }
}