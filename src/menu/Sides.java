package menu;
/**
 * Represent a side dish that is served in this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class Sides {
	/**
	 * Name of side dish.
	 */
    private String name;
    /**
     * Price of side dish
     */
    private double price;
    /**
     * Description of side dish.
     */
    private String description;
    /**
     * Create a new sides object with its name, cost and description.
     * @param name this side name.
     * @param price this side price.
     * @param description side drink description.
     */
    public Sides(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    /**
     * Gets the name of this side dish.
     * @return this side dish name.
     */
    public String getName() {
        return name;
    }
    /**
     * Changes the name of this side dish.This is usually done when the staff
     * wants to update the side dish name.
     * @param name new side name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the price of this side dish.
     * @return this side dish price.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Changes the price of this side.This is usually done when the staff
     * wants to update the side price.
     * @param price new side price.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Gets the description of this side dish.
     * @return this side dish description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Changes the description of this side.This is usually done when the staff
     * wants to update the side description.
     * @param description new side description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
