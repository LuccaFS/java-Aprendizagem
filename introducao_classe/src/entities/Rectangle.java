package entities;

public class Rectangle {
    public double a;
    public double b;
    
    public double area(){
        return a * b;
    }
    
    public double perimeter(){
        return (a + b) * 2;
    }
    
    public double diagonal(){
        return Math.sqrt((a*a) + (b*b));
    }
}
