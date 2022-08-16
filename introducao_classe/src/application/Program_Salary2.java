package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee2;
import entities.Employee2_out;

public class Program_Salary2 {
    public static void main(String[] args) throws Exception {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        List<Employee2> list = new ArrayList<>();
            
        System.out.print("Enter the number of employees:  ");
        int n = sc.nextInt();
        System.out.println();
        for(int i = 1; i<=n; i++){
            System.out.println("Employee #" + i + " data:");
            System.out.print("Outsourced (y/n)? ");
            char c = sc.next().charAt(0);
            System.out.print("Name: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Hours: ");
            int hours = sc.nextInt();
            System.out.print("Value per hour: ");
            double value = sc.nextDouble();
            if(c == 'y'){
                System.out.print("Additional charge: ");
                double additional = sc.nextDouble();
                Employee2 emp = new Employee2_out(name, hours, value, additional);
                list.add(emp);
            } else {
                Employee2 emp = new Employee2(name, hours, value);
                list.add(emp);
            }
        }
        
        System.out.println();
        System.out.println("PAYMENTS:");
        for(Employee2 emp : list){
            System.out.println(emp.getName() + " - $" + emp.payment());
        };
        
        
        
        sc.close();
    }
}
