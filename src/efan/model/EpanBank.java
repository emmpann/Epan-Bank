package efan.model;

public class EpanBank {
    
    private double money;

    public EpanBank(){
    }

    public EpanBank(double money){
        this.money = money;
    }

    public void setMoney(double money){
        this.money = money;
    }

    public double getMoney(){
        return this.money;
    }

}