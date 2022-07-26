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
    
    public int getNumber(){
        return number;
    }
    
    public void setNumber(int number){
        this.number = number;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public double getBalance(){
        return balance;
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
