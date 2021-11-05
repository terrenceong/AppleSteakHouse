package menu;
/**
 * Represent a drink that is served in this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class Drinks {
	/**
	 * Name of drink.
	 */
    private String name;
    /**
     * Price of drink.
     */
    private double price;
    /**
     * Description of drink.
     */
    private String description;
    /**
     * Create a new drinks object with its name, cost and description.
     * @param name this drink name.
     * @param price this drink price.
     * @param description this drink description.
     */
    public Drinks(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    /**
     * Gets the name of this drink.
     * @return this drink name.
     */
    public String getName() {
        return name;
    }
    /**
     * Changes the name of this drink.This is usually done when the staff
     * wants to update the drink name.
     * @param name new drink name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the price of this drink.
     * @return this drink price.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Changes the price of this drink.This is usually done when the staff
     * wants to update the drink price.
     * @param price new drink price.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Gets the description of this drink.
     * @return this drink description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Changes the description of this drink.This is usually done when the staff
     * wants to update the drink description.
     * @param description new drink description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
