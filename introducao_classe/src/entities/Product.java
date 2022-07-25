package entities;

public class Product {
    public String name;
    public double price;
    public int quantity;
    
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
