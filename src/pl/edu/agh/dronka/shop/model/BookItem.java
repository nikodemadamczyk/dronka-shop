package pl.edu.agh.dronka.shop.model;

public class BookItem extends Item {
    private int pages;
    private boolean hardcover;

    public BookItem() {
    }
    public BookItem(String name, Category category, int price, int quantity, int pages, boolean hardcover) {
        super(name, category, price, quantity);
        this.pages = pages;
        this.hardcover = hardcover;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isHardcover() {
        return hardcover;
    }

    public void setHardcover(boolean hardcover) {
        this.hardcover = hardcover;
    }
}