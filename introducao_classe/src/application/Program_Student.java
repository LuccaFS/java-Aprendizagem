package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Student;

public class Program_Student {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        Student student = new Student();
        
        student.name = sc.nextLine();
        student.nota1 = sc.nextDouble();
        student.nota2 = sc.nextDouble();
        student.nota3 = sc.nextDouble();
        
        double total = student.notaF();
        System.out.println("FINAL GRADE: " + total);
        if(total > 60.00){
            System.out.println("APPROVED");
        }else{
            System.out.println("FAILED");
            System.out.println("MISSING " + student.recupera(total) + " POINTS");
        }
        
        sc.close();
    }
}
