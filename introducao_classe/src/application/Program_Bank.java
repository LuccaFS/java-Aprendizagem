package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Bank;

public class Program_Bank {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Bank account;
        double balance;
        
        System.out.print("Enter account number: ");
        int number = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Enter account holder: ");
        String name = sc.nextLine();
        
        System.out.print("Is there an initial deposit? (y/n):");
        String bool = sc.nextLine();
        if(bool.equals("y")){            
            System.out.print("Enter initial deposit: ");
            balance = sc.nextDouble();
            account = new Bank(number, name, balance);
        }else{
            account = new Bank(number, name);
        }
        
        System.out.println();
        System.out.println("Account data:");
        System.out.println(account.toString());
        
        
        System.out.println();
        System.out.print("Enter a deposit value: ");
        balance = sc.nextDouble();
        account.addBalance(balance);
        System.out.println("Updated account data:");
        System.out.println(account.toString());
        
        System.out.println();
        System.out.print("Enter a withdraw value: ");
        balance = sc.nextDouble();
        account.removeBalance(balance);
        System.out.println("Updated account data:");
        System.out.println(account.toString());
        
        sc.close();
    }
}
