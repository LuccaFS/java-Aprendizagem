package entities;

public class Product {
    public String name;
    public double price;
    public int quantity;
    
    public Product(){ }
    
    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    
    public double totalValueInSotck(){
      return quantity * price;  
    };
    
    public void addProduct(int qtd){
        quantity += qtd;
    }
    
    public void removeProduct(int qtd){
        quantity -= qtd;
    }
    
    public String toString(){
        return name 
            + ", $ "
            + String.format("%.2f", price)
            + ", "
            + quantity
            + " units | Total: $ "            
            + String.format("%.2f", totalValueInSotck());
    }
}
