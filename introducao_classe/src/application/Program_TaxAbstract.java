package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Tax_Company;
import entities.Tax_Person;
import entities.Tax_Abstract;

public class Program_TaxAbstract {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        List<Tax_Abstract> payers = new ArrayList<>();
        
        System.out.print("Enter the number of tax payers: ");
        int n = sc.nextInt();
        for(int i=1; i<=n; i++){
            System.out.println("Tax Payer #"+ i + " data:");
            System.out.print("Individual or Company (i/c)? ");
            char ch = sc.next().charAt(0);
            sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Annual income: ");
            Double income = sc.nextDouble();
            if(ch=='i'){
                System.out.print("Health expenditures: ");
                double health = sc.nextDouble();
                payers.add(new Tax_Person(name, income, health));
            }else{
                System.out.print("Number of employees: ");
                int employees = sc.nextInt();
                payers.add(new Tax_Company(name, income, employees));
            }
        }
        
        double sum = 0;
        System.out.println();
        System.out.println("TAXES PAID:");
        for(Tax_Abstract list: payers){
            System.out.println(list.getName() + String.format(": $%.2f", list.tax()));
            sum += list.tax();
        }
        System.out.println();
        System.out.println(String.format("TOTAL TAXES: $%.2f", sum));
        sc.close();
    }
}
