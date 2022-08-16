package entities;

public class Employee2 {
    private String name;
    private int hours;
    private double value;
    
    public Employee2(){ };
    
    public Employee2(String name, int hours, double value){
        this.name = name;
        this.hours = hours;
        this.value = value;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Integer getHours(){
        return this.hours;
    }
    
    public void setHours(Integer hours){
        this.hours = hours;
    }
    
    public Double getValue(){
        return this.value;
    }
    
    public void setValue(Double value){
        this.value = value;
    }
    
    public double payment(){
        return hours * value;
    }
}
