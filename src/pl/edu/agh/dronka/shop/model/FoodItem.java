package pl.edu.agh.dronka.shop.model;
import java.util.Date;

public class FoodItem extends Item {
    private Date expirationDate;

    public FoodItem(String name, Category category, int price, int quantity, Date expirationDate) {
        super(name, category, price, quantity);
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}