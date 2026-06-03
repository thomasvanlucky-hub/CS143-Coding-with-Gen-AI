import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Manages all student records and provides a menu-driven console interface.
 * Uses a HashMap<String, Student> where the key is the student ID.
 */
public class StudentManager {

   // HashMap stores students — key: studentId, value: Student object
   private HashMap<String, Student> studentMap;

   // Scanner reads input from the console
   private Scanner scanner;

   /**
    * Constructor — initializes the map and scanner.
    */
   public StudentManager() {
      studentMap = new HashMap<>();
      scanner = new Scanner(System.in);
   }

   // -------------------------------------------------------
   // MENU
   // -------------------------------------------------------

   /**
    * Displays the main menu and routes user input to the correct method.
    * Loops until the user chooses to exit.
    */
   public void showMenu() {
      int choice = -1;
   
      while (choice != 8) {
         System.out.println("\n===== Student Course Manager =====");
         System.out.println("1. Add Student");
         System.out.println("2. Remove Student");
         System.out.println("3. Search Student by ID");
         System.out.println("4. Display All Students");
         System.out.println("5. Add Course to Student");
         System.out.println("6. View Student's Courses");
         System.out.println("7. Remove Course from Student");
         System.out.println("8. Exit");
         System.out.print("Enter your choice: ");
      
         // Read the menu choice — guard against non-numeric input
         try {
            choice = Integer.parseInt(scanner.nextLine().trim());
         } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 8.");
            continue;
         }
      
         // Route to the correct method
         switch (choice) {
            case 1: addStudent();         
               break;
            case 2: removeStudent();      
               break;
            case 3: searchStudent();      
               break;
            case 4: displayAllStudents(); 
               break;
            case 5: addCourseToStudent(); 
               break;
            case 6: viewStudentCourses(); 
               break;
            case 7: removeCourseFromStudent();
               break;
            case 8: System.out.println("Goodbye!"); 
               break;
            default: System.out.println("Invalid choice. Please try again.");
         }
      }
   
      scanner.close(); // Close scanner when done
   }

   // -------------------------------------------------------
   // FEATURE METHODS
   // -------------------------------------------------------

   /**
    * Prompts the admin to enter a student ID and name, then adds them to the map.
    */
   private void addStudent() {
      System.out.print("Enter Student ID: ");
      String id = scanner.nextLine().trim().toUpperCase();

      // Reject blank ID
      if (id.isEmpty()) {
         System.out.println("Error: Student ID cannot be blank.");
         return;
      }

      // Check for duplicate ID
      if (studentMap.containsKey(id)) {
         System.out.println("Error: A student with ID " + id + " already exists.");
         return;
      }
   
      System.out.print("Enter Student Name: ");
      String name = scanner.nextLine().trim();

      // Reject blank name
      if (name.isEmpty()) {
         System.out.println("Error: Student name cannot be blank.");
         return;
      }

      Student student = new Student(id, name); // Create new Student object
      studentMap.put(id, student);             // Store in HashMap
      System.out.println("Student added: " + student);
   }

   /**
    * Removes a student from the map by ID.
    */
   private void removeStudent() {
      System.out.print("Enter Student ID to remove: ");
      String id = scanner.nextLine().trim().toUpperCase();
   
      if (studentMap.containsKey(id)) {
         studentMap.remove(id);
         System.out.println("Student " + id + " removed successfully.");
      } else {
         System.out.println("Error: No student found with ID " + id);
      }
   }

   /**
    * Searches for a student by ID and prints their details.
    */
   private void searchStudent() {
      System.out.print("Enter Student ID to search: ");
      String id = scanner.nextLine().trim().toUpperCase();
   
      Student student = studentMap.get(id); // HashMap lookup — O(1)
   
      if (student != null) {
         System.out.println("Found: " + student);
      } else {
         System.out.println("No student found with ID: " + id);
      }
   }

   /**
    * Iterates over the HashMap and prints every student.
    */
   private void displayAllStudents() {
      if (studentMap.isEmpty()) {
         System.out.println("No students currently enrolled.");
         return;
      }
   
      System.out.println("\n--- All Students ---");
      for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
         System.out.println(entry.getValue()); // Uses Student's toString()
      }
   }

   /**
    * Adds a course to a specific student's course list.
    */
   private void addCourseToStudent() {
      System.out.print("Enter Student ID: ");
      String id = scanner.nextLine().trim().toUpperCase();
   
      Student student = studentMap.get(id);
   
      if (student == null) {
         System.out.println("Error: No student found with ID " + id);
         return;
      }
   
      System.out.print("Enter Course Name: ");
      String course = scanner.nextLine().trim();
   
      student.addCourse(course);
      System.out.println("Course \"" + course + "\" added to " + student.getName() + ".");
   }

   /**
    * Displays all courses a specific student is enrolled in.
    */
   private void viewStudentCourses() {
      System.out.print("Enter Student ID: ");
      String id = scanner.nextLine().trim().toUpperCase();
   
      Student student = studentMap.get(id);
   
      if (student == null) {
         System.out.println("Error: No student found with ID " + id);
         return;
      }
   
      if (student.getCourses().isEmpty()) {
         System.out.println(student.getName() + " is not enrolled in any courses.");
      } else {
         System.out.println("Courses for " + student.getName() + ":");
         for (String course : student.getCourses()) {
            System.out.println("  - " + course);
         }
      }
   }

   /**
    * Removes a specific course from a student's enrollment list.
    */
   private void removeCourseFromStudent() {
      System.out.print("Enter Student ID: ");
      String id = scanner.nextLine().trim().toUpperCase();

      Student student = studentMap.get(id);

      if (student == null) {
         System.out.println("Error: No student found with ID " + id);
         return;
      }

      if (student.getCourses().isEmpty()) {
         System.out.println(student.getName() + " is not enrolled in any courses.");
         return;
      }

      System.out.println("Courses for " + student.getName() + ":");
      for (String course : student.getCourses()) {
         System.out.println("  - " + course);
      }

      System.out.print("Enter Course Name to remove: ");
      String course = scanner.nextLine().trim();

      student.removeCourse(course);
   }

   // -------------------------------------------------------
   // MAIN — Entry point
   // -------------------------------------------------------

   /**
    * Creates a StudentManager and launches the menu.
    */
   public static void main(String[] args) {
      StudentManager manager = new StudentManager();
      manager.showMenu();
   }
}