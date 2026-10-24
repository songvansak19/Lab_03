
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise3 {
    public static class Employee {
        int id;
        String name;
        double salary;
        double gross_salary = 0;

        Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }
        void calculate() {
            gross_salary = salary + 0.3 * salary;
        }
    }

    public static void main(String[] args) {
        ArrayList <Employee> employees = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter Number of Employees: ");
        int num = input.nextInt();
        System.out.println("=".repeat(30));
        for(int i = 0; i < num; i ++) {
            System.out.println("+ Employee " + (i + 1) + ":");
            System.out.print("Please Enter Employee ID: ");
            int id = input.nextInt();
            input.nextLine();
            System.out.print("Please Enter Employee Name: ");
            String name = input.nextLine();
            System.out.print("Please Enter Employee Salary: ");
            double salary = input.nextDouble();
            employees.add(new Employee(id, name, salary));
        }
        System.out.println("=".repeat(30));
        input.close();
        int i = 1;
        System.out.println("=".repeat(30));
        for(Employee employee : employees) {
            employee.calculate();
            System.out.printf("+ Employee %s: \n- Name: %s\n- ID: %d\nSalary: %f\n- Gross Salary: %f", i, employee.name, employee.id, employee.salary, employee.gross_salary);
            System.out.println();
            i ++;
        }
        System.out.println("=".repeat(30));
    }
}
