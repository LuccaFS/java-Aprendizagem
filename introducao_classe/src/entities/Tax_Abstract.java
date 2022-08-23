package entities;

public abstract class Tax_Abstract {
    private String name;
    private double income;
    
    public Tax_Abstract(){ }
    
    public Tax_Abstract(String name, Double income){
        this.name = name;
        this.income = income;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Double getIncome(){
        return this.income;
    }
    
    public void setIncome(Double income){
        this.income = income;
    }
    
    public abstract double tax();
}
