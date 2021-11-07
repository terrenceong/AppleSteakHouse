package menu;
/**
 * Represent a main course that is served in this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class MainCourse extends FoodItem {

    /**
     * Create a new main course object with its name, cost and description.
     * @param name this main course's dish name.
     * @param price this price of the main course dish.
     * @param description this description of the main course dish.
     */
    public MainCourse(String name, double price, String description) {
        super(name,price,description);
    }

}
