package entities;

public class Tax_Person extends Tax_Abstract {
    private double health;
    
    public Tax_Person(){ }
    
    public Tax_Person(String name, double income, double health){
        super(name, income);
        this.health = health;
    }

    public Double getHealth(){
        return this.health;
    }
    
    public void setHealth(Double health){
        this.health = health;
    }

    @Override
    public double tax() {
        double tax1, tax2 = 0;
        double income = getIncome();
        double health = getHealth();
        if(income < 20000){
            tax1 = 0.15;
        }else{
            tax1 = 0.25;
        }
        
        if(health>0){
            tax2 = health*0.5;
        }
        
        return income * tax1 - tax2;
    }
}
