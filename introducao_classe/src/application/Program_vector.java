package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program_vector {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        
        System.out.print("How many products will you register? ");
        int n = sc.nextInt();
        sc.nextLine();
        System.out.println();
        Product[] vect = new Product[n];
        
        
        for(int i=0; i<n; i++){
            System.out.println("Product #" + (i+1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            System.out.print("Quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            Product product = new Product(name, price, quantity);
            
            vect[i] = product;
        }
        
        
        System.out.println();
        System.out.println("Products:");
        
        for(Product obj: vect){
            System.out.println(obj.toString());
        }
        sc.close();
    }
}
