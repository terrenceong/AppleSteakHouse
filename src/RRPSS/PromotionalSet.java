package RRPSS;


public class PromotionalSet {
    private MainCourse main;
    private Sides side;
    private Drinks drink;
    private String name;
    private double price;
    private String desription;

    public PromotionalSet(String name,MainCourse main, Sides side, Drinks drink) {
        this.main = main;
        this.side = side;
        this.drink = drink;
        this.name = name;
        this.price = Math.floor((main.getPrice() + drink.getPrice() + side.getPrice())*0.9);
        this.desription = main.getName() +"\n" + side.getName() + "\n" + drink.getName();

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

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = Math.floor((main.getPrice() + drink.getPrice() + side.getPrice())*0.9);
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription() {
        this.desription = main.getName() +"\n" + side.getName() + "\n" + drink.getName();
    }
}
