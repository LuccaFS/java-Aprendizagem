package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Rectangle;

public class Program_Rectangle {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        Rectangle x = new Rectangle();
        
        System.out.println("Enter the rectangle width and height:");
        x.a = sc.nextDouble();
        x.b = sc.nextDouble();
        
        System.out.println();
        System.out.println("Area: " + String.format("%.2f", x.area()));
        System.out.println("Perimeter: " +  String.format("%.2f", x.perimeter()));
        System.out.println("Diagonal: " +  String.format("%.2f", x.diagonal()));
        
        sc.close();
    }
}
