package menu;
/**
 * Represent a promotional set that is served in this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class PromotionalSet extends FoodItem{
	/**
	 * Main course object.
	 */
    private MainCourse main;
    /**
     * Side dish object.
     */
    private Sides side;
    /**
     * Drink object.
     */
    private Drinks drink;
    /**
     * Create a new promotional object with its set name and combination of main,sides,drink
     * with a overall discounted price.
     * @param name this promotional set name.
     * @param main this main course dish object that is included in this set.
     * @param side this side dish object that is included in this set.
     * @param drink this drink object that is included in this set.
     */
    public PromotionalSet(String name,MainCourse main, Sides side, Drinks drink) {
        super(name,0.0,"default");
        this.main = main;
        this.side = side;
        this.drink = drink;
        this.setPrice();
        this.setDesription();

    }
    /**
     * Gets the reference of main course object of this promotional set.
     * @return this main course object reference.
     */
    public MainCourse getMain() {
        return main;
    }
    /**
     * Changes the main course for this promotional set.
     * @param main new main course.
     */
    public void setMain(MainCourse main) {
        this.main = main;
    }
    /**
     * Gets the reference of side dish object of this promotional set.
     * @return this side dish object reference.
     */
    public Sides getSide() {
        return side;
    }
    /**
     * Changes the side dish for this promotional set.
     * @param side new sides.
     */
    public void setSide(Sides side) {
        this.side = side;
    }
    /**
     * Gets the reference of drink object of this promotional set.
     * @return this drink object reference.
     */
    public Drinks getDrink() {
        return drink;
    }
    /**
     * Changes the drink for this promotional set.
     * @param drink new drink.
     */
    public void setDrink(Drinks drink) {
        this.drink = drink;
    }
    /**
     * Update the price of this promotional set.
     * Cost of promotional set = floor((price of main + price of side + price of drink)*0.9)
     */
    public void setPrice() {
        double price = Math.floor((main.getPrice() + drink.getPrice() + side.getPrice())*0.9);
        super.setPrice(price);
    }
    /**
     * Update the description of this promotional set.
     * Description of this promotional set lists down the items that belongs to this promotional set.
     */
    public void setDesription() {

        String desription = main.getName() +"\n" + side.getName() + "\n" + drink.getName();
        super.setDescription(desription);
    }
    /**
     * Gets the name of this promotionalSet.
     * @return this promotionalSet name.
     */
    @Override
    public String getName() {
        return super.getName();
    }
    /**
     * Gets the price of this promotionalSet.
     * @return this promotionalSet price.
     */
    @Override
    public double getPrice() {
        return super.getPrice();
    }
    /**
     * Gets the description of this promotionalSet.
     * @return this promotionalSet description.
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
