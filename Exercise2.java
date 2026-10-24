
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise2 {
    public static class Temperature {
        double F;
        double C;

        Temperature(double C) {
            this.C = C;
        }

        void convert() {
            F = (C * 9) / 5 + 32;
        }
    }
    public static void main(String[] args) {
        ArrayList <Temperature> temps = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number of Cities: ");
        int num = input.nextInt();
        for (int i = 0; i < num; i ++) {
            System.out.print("+ City " + (i + 1) + " Temperature in Celsius: ");
            double temp = input.nextDouble();
            temps.add(new Temperature(temp));
        }
        int i = 1;
        System.out.println("=".repeat(30));
        for (Temperature temp : temps) {
            temp.convert();
            System.out.println("+ City " + i + ": ");
            System.out.println("Celsius: " + temp.C);
            System.out.println("Fahrenheit: " + temp.F + "\n");
        }
        System.out.println("=".repeat(30));
    }
}
