package order;
/**
 * Represent a orderItem belongs to a certain order.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class OrderItems {
	/**
	 * Order item quantity.
	 */
    private int qty;
    /**
     * Unit price of this order item.
     */
    private double price;
    /**
     * Name of order item.
     */
    private String itemName;
    /**
     * Create order item object with item name, price and quantity
     * @param qty quantity of the order item.
     * @param price price of each unit
     * @param itemName this order item name.
     */
    public OrderItems(int qty, double price, String itemName) {
        this.qty = qty;
        this.price = this.qty*price;
        this.itemName = itemName;
    }
    /**
     * Gets quantity of this order item.
     * @return Quantity of the item.
     */
    public int getQty() {
        return qty;
    }
    /**
     * Update the quantity when more of the same Order item is added or remove.
     * @param qty New quantity of this order item.
     */
    public void setQty(int qty) {
        this.qty = qty;
    }
    /**
     * Gets the unit price of this order item.
     * @return Unit price of this order item.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Update unit price of the order item.
     * @param price New price of this order item.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Get name of this order item.
     * @return Name of the order item.
     */
    public String getItemName() {
        return itemName;
    }
    /**
     * Update item name.
     * @param itemName New name of the order item.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
