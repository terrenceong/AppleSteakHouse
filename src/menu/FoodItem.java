package menu;

public abstract class FoodItem {
    /**
     * Name of fooditem.
     */
    private String name;
    /**
     * Price of fooditem.
     */
    private double price;
    /**
     * Description of fooditem.
     */
    private String description;
    /**
     * Create a new fooditem object with its name, cost and description.
     * @param name this fooditem name.
     * @param price this fooditem price.
     * @param description this fooditem description.
     */
    public FoodItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    /**
     * Gets the name of this fooditem.
     * @return this fooditem name.
     */
    public String getName() {
        return name;
    }
    /**
     * Changes the name of this fooditem.This is usually done when the staff
     * wants to update the fooditem name.
     * @param name new fooditem name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the price of this fooditem.
     * @return this fooditem price.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Changes the price of this fooditem.This is usually done when the staff
     * wants to update the fooditem price.
     * @param price new fooditem price.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Gets the description of this fooditem.
     * @return this fooditem description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Changes the description of this fooditem.This is usually done when the staff
     * wants to update the fooditem description.
     * @param description new fooditem description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
