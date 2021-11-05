package menu;
/**
 * Represent a main course that is served in this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class MainCourse {
	/**
	 * Name of main course.
	 */
    private String name;
    /**
     * Price of main course
     */
    private double price;
    /**
     * Description of main course.
     */
    private String description;
    /**
     * Create a new main course object with its name, cost and description.
     * @param name this main course's dish name.
     * @param price this price of the main course dish.
     * @param description this description of the main course dish.
     */
    public MainCourse(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
    /**
     *  Gets the name of this main course dish.
     * @return this main course dish's name.
     */
    public String getName() {
        return name;
        
    }
    /**
     * Changes the name of this main course dish.This is usually done when the staff
     * wants to update the dish name.
     * @param name new main course dish's name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the price of this main course dish
     * @return this main course dish's price.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Changes the price of this main course dish.This is usually done when the staff
     * wants to update the dish price.
     * @param price new main course dish's price.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Gets the description of this main course dish
     * @return this main course dish's description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Changes the description of this main course dish.This is usually done when the staff
     * wants to update the dish description.
     * @param description new main course dish's description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
