package entities;

public class Tax_Company extends Tax_Abstract{
    
    private int employees;
    
    public Tax_Company(){ }
    
    public Tax_Company(String name, double income, int employees){
        super(name, income);
        this.employees = employees;
    }
    
    public Integer getEmployees(){
        return this.employees;
    }
    
    public void setEmployees(Integer employees){
        this.employees = employees;
    }

    @Override
    public double tax() {
        double tax;
        double income = getIncome();
        if(this.employees <= 10){
            tax = (income * 0.16);
        }else{
            tax = (income * 0.14);
        }
        return tax;
    }
    
}
