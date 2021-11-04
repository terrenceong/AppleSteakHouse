package order;

public class OrderItems {
    private int qty;
    private double price;
    private String itemName;

    public OrderItems(int qty, double price, String itemName) {
        this.qty = qty;
        this.price = this.qty*price;
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
