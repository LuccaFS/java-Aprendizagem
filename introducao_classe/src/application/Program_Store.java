package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program_Store {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        Product product = new Product();
        System.out.println("Enter product data:");
        System.out.print("Name: ");
        product.name = sc.nextLine();
        System.out.print("Price: ");
        product.price = sc.nextDouble();
        System.out.print("Quantity: ");
        product.quantity = sc.nextInt();
        
        System.out.println();
        System.out.println("Product data: " + product.toString());
        
        System.out.println();
        System.out.print("Enter the numbers of products to be added in stock: ");
        int qtd = sc.nextInt();
        product.addProduct(qtd);
        
        System.out.println();
        System.out.println("Updated data: " + product.toString());
        
        System.out.println();
        System.out.print("Enter the numbers of products to be removed from stock: ");
        qtd = sc.nextInt();
        product.removeProduct(qtd);
        
        System.out.println();
        System.out.println("Updated data: " + product.toString());
        
        sc.close();
    }
}
