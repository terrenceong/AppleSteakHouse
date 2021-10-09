package RRPSS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RRPSSApp {
    private static Scanner sc = new Scanner(System.in);
    private static List<MainCourse> mainCoursesList = new ArrayList<>();
    private static List<Sides> sidesList = new ArrayList<>();
    private static List<Drinks> drinksList = new ArrayList<>();
    private static List<PromotionalSet> promotionalSetList = new ArrayList<>();
    public static void main(String[] args) {
	// write your code here
        Staff s = new Staff();
        enterStaffInfo(s);
        printOption(s);
        initializeFoodMenu();
        boolean quit = false;
        while(!quit)
        {
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1: printFoodMenu();break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7: break;
                case 8: break;
                case 9: break;
                case 10:break;
                case 11:break;
                default: quit = true;
            }
        }


    }
    public static void enterStaffInfo(Staff s)
    {
        System.out.println("=======WELCOME TO APPLE STEAK HOUSE STAFF MENU=======");
        System.out.print("Enter staff name:");
        String name = sc.nextLine().toUpperCase();
        System.out.print("Enter staff ID:");
        int id = sc.nextInt();
        s.setID(id);
        s.setName(name);
    }

    private static void printOption(Staff s)
    {
        System.out.println("=======WELCOME TO APPLE STEAK HOUSE STAFF MENU=======");
        System.out.println("StaffNo#" + s.getID() + " Welcome " + s.getName()+ "!");
        System.out.println("1 -> Print restaurant menu item");
        System.out.println("2 -> Create/Update/Remove menu item");
        System.out.println("3 -> Create/Update/Remove promotion");
        System.out.println("4 -> Create order");
        System.out.println("5 -> View order");
        System.out.println("6 -> Add/Remove order item/s to/from order ");
        System.out.println("7 -> Create reservation booking");
        System.out.println("8 -> Check/Remove reservation booking");
        System.out.println("9 -> Check table availability");
        System.out.println("10 -> Print order invoice");
        System.out.println("11 -> Print sale revenue report by period");
        System.out.println("12 -> Quit");

    }
    private static void initializeFoodMenu()
    {
        mainCoursesList.add(new MainCourse("Apple House's Steak",19.90,"Classical Tenderloin top with mushroom apple cinder and mushroom sauce"));
        mainCoursesList.add(new MainCourse("T-Bone Steak",23.5,"Hearty bone-in steak that’s sure to fill your needs"));
        mainCoursesList.add(new MainCourse("Striploin Steak",22.5,"A steakhouse classic, succulent and flavoursome"));
        mainCoursesList.add(new MainCourse("Classic Fish & Chips",15.5,"Classical fish and chips that everyone is hook into!"));
        sidesList.add(new Sides("Mushroom Soup",3.5,"Your non average mushroom soup"));
        sidesList.add(new Sides("Garlic Bun",5,"Slice of loaf topped with cheesy garlic sauce"));
        sidesList.add(new Sides("Squid Head",10,"Tender and juicy squid head"));
        sidesList.add(new Sides("Cheese Platter",20,"Gourmet cheeses include award-winning truffle gouda, smoked cheese, soft & creamy brie"));
        drinksList.add(new Drinks("Latte",5,"Made with arabic beans"));
        drinksList.add(new Drinks("English Tea",3.8,"Special tea leaves imported from england"));
        drinksList.add(new Drinks("Lime Juice",3.8,"Fresh squeezed lime juice"));
        drinksList.add(new Drinks("Coke",3,"Your typical coke on canned"));
        promotionalSetList.add(new PromotionalSet("A",mainCoursesList.get(0),sidesList.get(0),drinksList.get(2)));
        promotionalSetList.add(new PromotionalSet("B",mainCoursesList.get(1),sidesList.get(0),drinksList.get(1)));
        promotionalSetList.add(new PromotionalSet("C",mainCoursesList.get(3),sidesList.get(1),drinksList.get(0)));

    }
    private static void printFoodMenu()
    {
        int i =0;
        System.out.println("APPLE STEAK HOUSE's MENU");
        System.out.println("=======Promotional Set=======");
        for(PromotionalSet p:promotionalSetList)
            System.out.println(++i + ") Set " + p.getName() + ": $" + p.getPrice() + "\n" + p.getDesription());
        System.out.println("=======Main Course=======");
        for(MainCourse m:mainCoursesList)
            System.out.println(++i + ") " + m.getName() + ": $" + m.getPrice() + "\n" + m.getDescription());
        for(Sides s:sidesList)
            System.out.println(++i + ") " + s.getName() + ": $" + s.getPrice() + "\n" + s.getDescription());
        for(Drinks d:drinksList)
            System.out.println(++i + ") " + d.getName() + ": $" + d.getPrice() + "\n" + d.getDescription());

    }

}
