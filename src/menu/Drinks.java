package menu;
/**
 * Represent a drink that is served in this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class Drinks extends FoodItem {
    /**
     * Create a new drinks object with its name, cost and description.
     * @param name this drink name.
     * @param price this drink price.
     * @param description this drink description.
     */
    public Drinks(String name, double price, String description) {
        super(name,price,description);
    }

}
