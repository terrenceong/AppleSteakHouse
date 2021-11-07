package menu;
/**
 * Represent a side dish that is served in this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class Sides extends FoodItem {
    /**
     * Create a new sides object with its name, cost and description.
     * @param name this side name.
     * @param price this side price.
     * @param description side drink description.
     */
    public Sides(String name, double price, String description) {
        super(name,price,description);
    }

}
