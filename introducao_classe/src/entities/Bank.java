package entities;

public class Bank {
    private int number;
    private String name;
    private double balance;
    
    public Bank(){ }
    
    public Bank(int number, String name){
        this.number = number;
        this.name = name;
    }
    
    public Bank(int number, String name, double balance){
        this.number = number;
        this.name = name;
        this.balance = balance;
    }
    
    public void addBalance(double balance){
        this.balance += balance;
    }
    
    public void removeBalance(double balance){
        this.balance -= 5.00 + balance;
    }
    
    public String toString(){
        return "Account " + number
            + ", Holder: " + name
            + ", Balance: " 
            + String.format("%.2f", balance);
    }
    
}
