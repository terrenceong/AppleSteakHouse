package RRPSS;

public class PromotionalSet {
    private MainCourse main;
    private Sides side;
    private Drinks drink;
    private String name;

    public PromotionalSet(String name,MainCourse main, Sides side, Drinks drink) {
        this.main = main;
        this.side = side;
        this.drink = drink;
        this.name = name;
    }

    public MainCourse getMain() {
        return main;
    }

    public void setMain(MainCourse main) {
        this.main = main;
    }

    public Sides getSide() {
        return side;
    }

    public void setSide(Sides side) {
        this.side = side;
    }

    public Drinks getDrink() {
        return drink;
    }

    public void setDrink(Drinks drink) {
        this.drink = drink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
