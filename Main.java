
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    // Student class definition
    public static class Student {
        // Fields to store student information
        String firstName;
        String lastName;
        int registration;
        int grade;
        int year;

        // Default constructor initializes fields with default values
        public Student() {
            firstName = "";
            lastName = "";
            registration = 0;
            grade = 0;
            year = 0;
        }

        // Parameterized constructor to set values for all fields
        public Student(String firstName, String lastName, int registration, int grade, int year) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.registration = registration;
            this.grade = grade;
            this.year = year;
        }

        // Another constructor with fewer parameters (grade and year set to default values)
        public Student(String firstName, String lastName, int registration) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.registration = registration;
            grade = 0;
            year = 0;
        }

        // Method to print full name of the student
        public void printFullName() {
            System.out.println(firstName + " " + lastName);
        }

        // Method to check if the student is approved (grade >= 60)
        public boolean isApproved() {
            return grade >= 60;
        }

        // Method to increment the year if the student is approved
        public void changeYearIfApproved() {
            if (isApproved()) {
                year++;
                System.out.println("Congratulations!");
            }
        }
    }

    // Course class definition
    public static class Course {
        // Fields to store course information
        String courseName;
        String professorName;
        int year;
        ArrayList<Student> students;

        // Constructor to initialize course fields and create an empty list of students
        public Course(String courseName, String professorName, int year) {
            this.courseName = courseName;
            this.professorName = professorName;
            this.year = year;
            students = new ArrayList<Student>();
        }

        // Method to enroll a student in the course
        public void enroll(Student student) {
            students.add(student);
        }

        // Method to unenroll a student from the course
        public void unEnroll(Student student) {
            students.remove(student);
        }

        // Method to count the number of students in the course
        public int countStudents() {
            return students.size();
        }

        // Method to find the best grade among all students in the course
        public int bestGrade() {
            int best = 0;
            for (Student student : students) {
                if (student.grade > best) {
                    best = student.grade;
                }
            }
            return best;
        }

        // Method to calculate the average grade of all students in the course
        public double averageGrade() {
            double sum = 0;
            for (Student student : students) {
                sum += student.grade;
            }
            return sum / countStudents();
        }

        // Method to display the ranking of students based on grades
        public void ranking() {
            Collections.sort(students, new Comparator<Student>() {
                public int compare(Student s1, Student s2) {
                    return s2.grade - s1.grade;
                }
            });
            System.out.println("Ranking of " + courseName + ":");
            for (int i = 0; i < countStudents(); i++) {
                Student student = students.get(i);
                System.out.println((i + 1) + ". " + student.firstName + " " + student.lastName + " - " + student.grade);
            }
        }

        // Method to compare each student's grade to the average and display the result
        public void compareAverage() {
            double average = averageGrade();
            System.out.println("Comparison of " + courseName + " average:");
            for (Student student : students) {
                System.out.print(student.firstName + " " + student.lastName + " - ");
                if (student.grade > average) {
                    System.out.println("Above average");
                } else if (student.grade < average) {
                    System.out.println("Below average");
                } else {
                    System.out.println("Equal to average");
                }
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        // Scanner for user input
        Scanner input = new Scanner(System.in);

        // Prompt user to enter course details
        System.out.println("Enter course name:");
        String courseName = input.nextLine();

        System.out.println("Enter professor name:");
        String professorName = input.nextLine();

        System.out.println("Enter course year:");
        int year = input.nextInt();
        input.nextLine();

        // Create a Course object with the entered details
        Course c1 = new Course(courseName, professorName, year);

        // Prompt user to enter the number of students to enroll
        System.out.println("How many students do you want to enroll?");
        int numStudents = input.nextInt();
        input.nextLine();

        // Loop to input details for each student and enroll them in the course
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter student " + (i + 1) + " details (firstName lastName registration grade year):");
            String[] studentDetails = input.nextLine().split(" ");

            // Check if the correct number of details is provided for the student
            if (studentDetails.length != 5) {
                System.out.println("Invalid input. Please enter all details for the student.");
                continue;
            }

            try {
                // Create a Student object and enroll them in the course
                Student student = new Student(studentDetails[0], studentDetails[1],
                        Integer.parseInt(studentDetails[2]),
                        Integer.parseInt(studentDetails[3]),
                        Integer.parseInt(studentDetails[4]));

                c1.enroll(student);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numeric values for registration, grade, and year.");
            }
        }

        // Close the input scanner
        input.close();

        // Display course statistics
        System.out.println("Number of students in " + c1.courseName + ": " + c1.countStudents());
        System.out.println("Best grade in " + c1.courseName + ": " + c1.bestGrade());
        System.out.println("Average grade in " + c1.courseName + ": " + c1.averageGrade());

        // Display ranking and comparison of each student's grade to the average
        c1.ranking();
        c1.compareAverage();
    }
}