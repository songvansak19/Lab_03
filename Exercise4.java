
import java.util.ArrayList;
import java.util.Scanner;

// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;

public class Exercise4 {
    public static class Student {
        int id;
        String name;
        double[] marks;
        char[] grades;
        double sum = 0, avg;

        Student(int id, String name, double[] marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
            grades = new char[marks.length];
        }

        void grade() {
            for(int i = 0; i < grades.length; i ++) {
                double mark = marks[i];
                if (mark >= 90) {
                    grades[i] = 'A';
                } else if (mark >= 75) {
                    grades[i] = 'B';
                } else if (mark >= 50) {
                    grades[i] = 'C';
                } else if (mark < 50) {
                    grades[i] = 'F';
                }
                sum += mark;
            }
            avg = sum / marks.length;
        }
    }
    public static void main(String[] args) {
        // Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ArrayList <Student> students = new ArrayList<>();

        String[] subjects = {"Mathematics", "Physics", "Chemistry", "Programming", "History"};
        double[] marks = new double[subjects.length];

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter number of Students: ");
        int num = input.nextInt();
        System.out.println("=".repeat(30));
        for (int i = 0; i < num; i ++) {
            System.out.println("+ Student " + (i + 1) + ":");
            System.out.print("Please enter student ID: ");
            int id = input.nextInt();
            System.out.print("Please enter student Name: ");
            input.nextLine();
            String name = input.nextLine();
            for (int j = 0; j < subjects.length; j ++) {
                System.out.print(" > Enter " + subjects[j] + " mark: ");
                marks[j] = input.nextDouble();
            }
            students.add(new Student(id, name, marks));
        }
        System.out.println("=".repeat(30));
        System.out.println("=".repeat(30));
        int i = 1;
        for (Student student : students) {
            student.grade();
            System.out.println("+ Student " + (i + 1) + ":");
            System.out.println(" - ID: " + student.id);
            System.out.println(" - Name: " + student.name);
            System.out.println(" - Total Mark: " + student.sum);
            System.out.println(" - Average: " + student.avg);
            for (int j = 0; j < subjects.length; j ++) {
                System.out.println("  > " + subjects[j] + ":\n   - Mark: " + student.marks[j] + "\n   - Grade: " + student.grades[j]);
            }
        }
        // String json = gson.toJson(students);
        System.out.println("=".repeat(30));
        // System.out.println(gson.toJson(json));
    }
}
