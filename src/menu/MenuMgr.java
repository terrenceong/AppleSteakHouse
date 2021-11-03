package menu;

import java.util.List;
import java.util.Scanner;

import RRPSS.RRPSSApp;
import reservation.ReservationMgr;

public class MenuMgr {
    private static Scanner sc = new Scanner(System.in);
    static List<MainCourse> mainCoursesList = RRPSSApp.mainCoursesList;
    static List<Sides> sidesList = RRPSSApp.sidesList;
    static List<Drinks> drinksList = RRPSSApp.drinksList;
    static List<PromotionalSet> promotionalSetList = RRPSSApp.promotionalSetList;

    public static void showMenuOptions() {
        boolean quit = false;
        do
        {
            System.out.println("======Menu Options======");
            System.out.println("1 -> Print restaurant menu item");
            System.out.println("2 -> Create/Update/Remove menu item");
            System.out.println("3 -> Create/Update/Remove promotion");
            System.out.println("4 -> Quit");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            ReservationMgr.checkExpiredReservations();
            switch(choice)
            {
                case 1: printFoodMenu();break;
                case 2: editFoodMenu();break;
                case 3: editPromotionalSet();break;
                default: quit = true;
            }
        } while(!quit);
    }
    
    
    public static void initializeFoodMenu() {
    	
        mainCoursesList.add(new MainCourse("Apple House's Steak", 19.90, "Classical Tenderloin top with mushroom apple cinder and mushroom sauce"));
        mainCoursesList.add(new MainCourse("T-Bone Steak", 23.5, "Hearty bone-in steak that’s sure to fill your needs"));
        mainCoursesList.add(new MainCourse("Striploin Steak", 22.5, "A steakhouse classic, succulent and flavoursome"));
        mainCoursesList.add(new MainCourse("Classic Fish & Chips", 15.5, "Classical fish and chips that everyone is hook into!"));
        sidesList.add(new Sides("Mushroom Soup", 3.5, "Your non average mushroom soup"));
        sidesList.add(new Sides("Garlic Bun", 5, "Slice of loaf topped with cheesy garlic sauce"));
        sidesList.add(new Sides("Squid Head", 10, "Tender and juicy squid head"));
        sidesList.add(new Sides("Cheese Platter", 20, "Gourmet cheeses include award-winning truffle gouda, smoked cheese, soft & creamy brie"));
        drinksList.add(new Drinks("Latte", 5, "Made with arabic beans"));
        drinksList.add(new Drinks("English Tea", 3.8, "Special tea leaves imported from England"));
        drinksList.add(new Drinks("Lime Juice", 3.8, "Fresh squeezed lime juice"));
        drinksList.add(new Drinks("Coke", 3, "Your typical coke on canned"));
        promotionalSetList.add(new PromotionalSet("House's special", mainCoursesList.get(0), sidesList.get(0), drinksList.get(2)));
        promotionalSetList.add(new PromotionalSet("T-bone's special", mainCoursesList.get(1), sidesList.get(0), drinksList.get(1)));
        promotionalSetList.add(new PromotionalSet("Fishy meal", mainCoursesList.get(3), sidesList.get(1), drinksList.get(0)));
    }
    
    private static void printFoodMenu()
    {
        int i =0;
        System.out.println("APPLE STEAK HOUSE's MENU");
        System.out.println("=======Promotional Set=======");
        for (PromotionalSet p : promotionalSetList)
            System.out.println(++i + ") Set " + p.getName() + ": $" + p.getPrice() + "\n" + p.getDesription());
        System.out.println("=======Main Course=======");
        for (MainCourse m : mainCoursesList)
            System.out.println(++i + ") " + m.getName() + ": $" + m.getPrice() + "\n" + m.getDescription());
        System.out.println("=======Sides=======");
        for (Sides s : sidesList)
            System.out.println(++i + ") " + s.getName() + ": $" + s.getPrice() + "\n" + s.getDescription());
        System.out.println("=======Drinks=======");
        for (Drinks d : drinksList)
            System.out.println(++i + ") " + d.getName() + ": $" + d.getPrice() + "\n" + d.getDescription());
    }


    private static void editFoodMenu() {
        int i = 0;
        sc.nextLine();
        System.out.println("Add(A), Update(U), Remove(R) from menu");
        System.out.print("Enter A/U/R:");
        char action = sc.nextLine().toLowerCase().charAt(0);
        while(action != 'a' && action !='u' && action!='r')
        {
            System.out.println("Enter A/U/R only!");
            System.out.print("Enter A/U/R:");
             action = sc.nextLine().toLowerCase().charAt(0);
        }
        System.out.println("Select:MainCourse(M), Sides(S), Drinks(D)");
        System.out.print("Enter M/S/D:");
        char choice = sc.nextLine().toLowerCase().charAt(0);
        while(choice != 'm' && choice !='s' && choice!='d')
        {
            System.out.println("Enter M/S/D only!");
            System.out.print("Enter M/S/D:");
            choice = sc.nextLine().toLowerCase().charAt(0);
        }
        switch (action) {
            case 'a':
                System.out.print("Enter name of item:");
                String name = sc.nextLine();
                System.out.print("Enter price:");
                double price = sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter description:");
                String description = sc.nextLine();
                System.out.println("Item added!");
                switch (choice) {
                    case 'm':
                        mainCoursesList.add(new MainCourse(name, price, description));
                        break;
                    case 's':
                        sidesList.add(new Sides(name, price, description));
                        break;
                    case 'd':
                        drinksList.add(new Drinks(name, price, description));
                        break;
                }
                break;
            case 'r':
                switch (choice) {
                    case 'm':
                        for (MainCourse m : mainCoursesList)
                            System.out.println(++i + ") " + m.getName());
                        System.out.print("Enter item to be removed:");
                        mainCoursesList.remove(sc.nextInt() - 1);
                        break;
                    case 's':
                        for (Sides s : sidesList)
                            System.out.println(++i + ") " + s.getName());
                        System.out.print("Enter item to be removed:");
                        sidesList.remove(sc.nextInt() - 1);
                        break;
                    case 'd':
                        for (Drinks d : drinksList)
                            System.out.println(++i + ") " + d.getName());
                        System.out.print("Enter item to be removed:");
                        drinksList.remove(sc.nextInt() - 1);
                        break;

                }
                System.out.println("Successfully removed");
                break;
            case 'u':
                switch (choice) {
                    case 'm':
                        for (MainCourse m : mainCoursesList)
                            System.out.println(++i + ") " + m.getName());
                        System.out.print("Enter item to be updated:");
                        int item = sc.nextInt() - 1;
                        System.out.println("1 - > Name");
                        System.out.println("2 - > Price");
                        System.out.println("3 - > Description");
                        System.out.print("Select details to be updated:");
                        switch (sc.nextInt()) {
                            case 1:
                                System.out.print("Enter new name:");
                                sc.nextLine();
                                mainCoursesList.get(item).setName(sc.nextLine());
                                break;
                            case 2:
                                System.out.print("Enter new price in SGD:");
                                mainCoursesList.get(item).setPrice(sc.nextDouble());
                                break;
                            case 3:
                                System.out.print("Enter new description:");
                                sc.nextLine();
                                mainCoursesList.get(item).setDescription(sc.nextLine());
                                break;
                        }
                        break;
                    case 's':
                        for (Sides s : sidesList)
                            System.out.println(++i + ") " + s.getName());
                        System.out.print("Enter item to be updated:");
                        item = sc.nextInt() - 1;
                        System.out.println("1 - > Name");
                        System.out.println("2 - > Price");
                        System.out.println("3 - > Description");
                        System.out.print("Select details to be updated:");
                        switch (sc.nextInt()) {
                            case 1:
                                System.out.print("Enter new name:");
                                sc.nextLine();
                                sidesList.get(item).setName(sc.nextLine());
                                break;
                            case 2:
                                System.out.print("Enter new price in SGD:");
                                sidesList.get(item).setPrice(sc.nextDouble());
                                break;
                            case 3:
                                System.out.print("Enter new description:");
                                sc.nextLine();
                                sidesList.get(item).setDescription(sc.nextLine());
                                break;
                        }
                        break;
                    case 'd':
                        for (Drinks d : drinksList)
                            System.out.println(++i + ") " + d.getName());
                        System.out.print("Enter item to be updated:");
                        item = sc.nextInt() - 1;
                        System.out.println("1 - > Name");
                        System.out.println("2 - > Price");
                        System.out.println("3 - > Description");
                        System.out.print("Select details to be updated:");
                        switch (sc.nextInt()) {
                            case 1:
                                System.out.print("Enter new name:");
                                sc.nextLine();
                                drinksList.get(item).setName(sc.nextLine());
                                break;
                            case 2:
                                System.out.print("Enter new price in SGD:");
                                drinksList.get(item).setPrice(sc.nextDouble());
                                break;
                            case 3:
                                System.out.print("Enter new description:");
                                sc.nextLine();
                                drinksList.get(item).setDescription(sc.nextLine());
                                break;
                        }
                        break;
                }
                System.out.println("Successfully updated!");
        }
    }

    private static void editPromotionalSet() {
        int i = 0;
        sc.nextLine();
        System.out.println("Add(A), Update(U), Remove(R) promotional set");
        System.out.print("Enter A/U/R:");
        char action = sc.nextLine().toLowerCase().charAt(0);
        while(action != 'a' && action !='u' && action!='r')
        {
            System.out.println("Enter A/U/R only!");
            System.out.print("Enter A/U/R:");
            action = sc.nextLine().toLowerCase().charAt(0);
        }
        switch (action) {
            case 'a':
                System.out.println("Enter name of promotion set:");
                String name = sc.nextLine();
                for (MainCourse m : mainCoursesList)
                    System.out.println(++i + ") " + m.getName());
                System.out.print("Select your MainCourse:");
                int main = sc.nextInt() - 1;
                i = 0;
                for (Sides s : sidesList)
                    System.out.println(++i + ") " + s.getName());
                System.out.print("Select your Side:");
                int side = sc.nextInt() - 1;
                i = 0;
                for (Drinks d : drinksList)
                    System.out.println(++i + ") " + d.getName());
                System.out.print("Select your Drink:");
                int drink = sc.nextInt() - 1;
                i = 0;
                promotionalSetList.add(new PromotionalSet(name, mainCoursesList.get(main), sidesList.get(side), drinksList.get(drink)));
                System.out.println("Promotional set successfully added!");
                break;
            case 'r':
                for (PromotionalSet p : promotionalSetList)
                    System.out.println(++i + ") Set " + p.getName() + ": $" + p.getPrice() + "\n" + p.getDesription());
                System.out.print("Select promotion item to be removed:");
                int item = sc.nextInt() - 1;
                promotionalSetList.remove(item);
                System.out.println("Promotional set successfully removed!");
                break;
            case 'u':
                for (PromotionalSet p : promotionalSetList)
                    System.out.println(++i + ") Set " + p.getName() + ": $" + p.getPrice() + "\n" + p.getDesription());
                System.out.print("Select promotion item to be updated:");
                item = sc.nextInt() - 1;
                sc.nextLine();
                System.out.println("Select: MainCourse(M), Sides(S), Drinks(D)");
                System.out.print("Select details to be updated:");
                char donkey = sc.nextLine().toLowerCase().charAt(0);
                while(donkey != 'm' && donkey !='s' && donkey!='d')
                {
                    System.out.println("Enter M/S/D only!");
                    System.out.print("Enter M/S/D:");
                    donkey = sc.nextLine().toLowerCase().charAt(0);
                }
                i = 0;
                switch (donkey) {
                    case 'm':
                        for (MainCourse m : mainCoursesList)
                            System.out.println(++i + ") " + m.getName());
                        System.out.print("Select your new MainCourse:");
                        main = sc.nextInt() - 1;
                        promotionalSetList.get(item).setMain(mainCoursesList.get(main));
                        promotionalSetList.get(item).setDesription();
                        promotionalSetList.get(item).setPrice();
                        break;
                    case 's':
                        for (Sides s : sidesList)
                            System.out.println(++i + ") " + s.getName());
                        System.out.print("Select your Side:");
                        side = sc.nextInt() - 1;
                        promotionalSetList.get(item).setSide(sidesList.get(side));
                        promotionalSetList.get(item).setDesription();
                        promotionalSetList.get(item).setPrice();
                        break;
                    case 'd':
                        for (Drinks d : drinksList)
                            System.out.println(++i + ") " + d.getName());
                        System.out.print("Select your Drink:");
                        drink = sc.nextInt() - 1;
                        promotionalSetList.get(item).setDrink(drinksList.get(drink));
                        promotionalSetList.get(item).setDesription();
                        promotionalSetList.get(item).setPrice();
                }
                System.out.println("Successfully updated!");
        }
    }
    
}
