package menu;
/**
 * Represent a food item that is served in this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-07-11
 */

public abstract class FoodItem {
    /**
     * Name of food item.
     */
    private String name;
    /**
     * Price of food item.
     */
    private double price;
    /**
     * Description of food item.
     */
    private String description;
    /**
     * Create a new food item object with its name, cost and description.
     * @param name this food item name.
     * @param price this food item price.
     * @param description this food item description.
     */
    public FoodItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    /**
     * Gets the name of this food item.
     * @return this food item name.
     */
    public String getName() {
        return name;
    }
    /**
     * Changes the name of this fooditem.This is usually done when the staff
     * wants to update the food item name.
     * @param name new food item name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the price of this food item.
     * @return this food item price.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Changes the price of this fooditem.This is usually done when the staff
     * wants to update the food item price.
     * @param price new food item price.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Gets the description of this food item.
     * @return this food item description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Changes the description of this fooditem.This is usually done when the staff
     * wants to update the food item description.
     * @param description new food item description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
