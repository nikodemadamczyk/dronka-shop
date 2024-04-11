package pl.edu.agh.dronka.shop.model;

public class ElectronicsItem extends Item {
    private boolean mobile;
    private boolean warranty;

    public ElectronicsItem() {
    }

    public ElectronicsItem(String name, Category category, int price, int quantity, boolean mobile, boolean warranty) {
        super(name, category, price, quantity);
        this.mobile = mobile;
        this.warranty = warranty;
    }

    public boolean isMobile() {
        return mobile;
    }

    public void setMobile(boolean mobile) {
        this.mobile = mobile;
    }

    public boolean isWarranty() {
        return warranty;
    }

    public void setWarranty(boolean warranty) {
        this.warranty = warranty;
    }
}