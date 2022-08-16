package entities;

public class Employee2_out extends Employee2 {
    public double additional;
    
    public Employee2_out(){
        super();
    }
    
    public Employee2_out(String name, Integer hours, Double value, Double additional){
        super(name, hours, value);
        this.additional = additional;
    }
    
    public Double getAdditional(){
        return this.additional;
    }
    
    public void setAdditional(Double additional){
        this.additional = additional;
    }
    
    @Override
    public double payment() {
        return super.payment() + (additional * 1.1);
    }
}
