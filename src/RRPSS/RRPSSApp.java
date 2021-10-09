package RRPSS;

import java.text.SimpleDateFormat;
import java.util.*;

public class RRPSSApp {
    private static Scanner sc = new Scanner(System.in);
    private static List<MainCourse> mainCoursesList = new ArrayList<>();
    private static List<Sides> sidesList = new ArrayList<>();
    private static List<Drinks> drinksList = new ArrayList<>();
    private static List<PromotionalSet> promotionalSetList = new ArrayList<>();
    private static List<Order> dineInOrderList = new ArrayList<>();
    private static List<Order> takeAwayOrderList = new ArrayList<>();
    private static List<Order> completedOrderList = new ArrayList<>();
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
                case 2: editFoodMenu();break;
                case 3: editPromotionalSet();break;
                case 4: createOrder(s);break;
                case 5: viewOrder();break;
                case 6: editOrders();break;
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
        drinksList.add(new Drinks("English Tea",3.8,"Special tea leaves imported from England"));
        drinksList.add(new Drinks("Lime Juice",3.8,"Fresh squeezed lime juice"));
        drinksList.add(new Drinks("Coke",3,"Your typical coke on canned"));
        promotionalSetList.add(new PromotionalSet("House's special",mainCoursesList.get(0),sidesList.get(0),drinksList.get(2)));
        promotionalSetList.add(new PromotionalSet("T-bone's special",mainCoursesList.get(1),sidesList.get(0),drinksList.get(1)));
        promotionalSetList.add(new PromotionalSet("Fishy meal",mainCoursesList.get(3),sidesList.get(1),drinksList.get(0)));

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
        System.out.println("=======Sides=======");
        for(Sides s:sidesList)
            System.out.println(++i + ") " + s.getName() + ": $" + s.getPrice() + "\n" + s.getDescription());
        System.out.println("=======Drinks=======");
        for(Drinks d:drinksList)
            System.out.println(++i + ") " + d.getName() + ": $" + d.getPrice() + "\n" + d.getDescription());

    }
    private static void editFoodMenu()
    {
        int i =0;
        sc.nextLine();
        System.out.println("Add(A), Update(U), Remove(R) from menu");
        System.out.print("Enter A/U/R:");
        char action = sc.nextLine().toLowerCase().charAt(0);
        System.out.println("Select:MainCourse(M), Sides(S), Drinks(D)");
        System.out.print("Enter M/S/D:");
        char choice = sc.nextLine().toLowerCase().charAt(0);
        switch(action)
        {
            case 'a':
                        System.out.print("Enter name of item:");
                        String name = sc.nextLine();
                        System.out.print("Enter price:");
                        double price = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter description:");
                        String description = sc.nextLine();
                        System.out.println("Item added!");
                        switch(choice) {
                            case 'm':  mainCoursesList.add(new MainCourse(name,price,description));break;
                            case 's':  sidesList.add(new Sides(name,price,description));break;
                            case 'd':  drinksList.add(new Drinks(name,price,description));break;
                        }break;
            case 'r':
                        switch (choice)
                        {
                            case 'm':  for(MainCourse m:mainCoursesList)
                                         System.out.println(++i  +") " + m.getName());
                                        System.out.print("Enter item to be removed:");
                                        mainCoursesList.remove(sc.nextInt()-1);break;
                            case 's': for(Sides s:sidesList)
                                         System.out.println(++i  +") " + s.getName());
                                        System.out.print("Enter item to be removed:");
                                        sidesList.remove(sc.nextInt()-1);break;
                            case 'd':  for(Drinks d:drinksList)
                                        System.out.println(++i  +") " + d.getName());
                                        System.out.print("Enter item to be removed:");
                                        drinksList.remove(sc.nextInt()-1);break;

                        }
                        System.out.println("Successfully removed");break;
            case 'u':
                        switch(choice)
                        {
                            case 'm':  for(MainCourse m:mainCoursesList)
                                System.out.println(++i  +") " + m.getName());
                                System.out.print("Enter item to be updated:");
                                int item = sc.nextInt()-1;
                                System.out.println("1 - > Name");
                                System.out.println("2 - > Price");
                                System.out.println("3 - > Description");
                                System.out.print("Select details to be updated:");
                                switch(sc.nextInt())
                                {
                                    case 1:
                                        System.out.print("Enter new name:");
                                        sc.nextLine();
                                        mainCoursesList.get(item).setName(sc.nextLine());break;
                                    case 2:
                                        System.out.print("Enter new price in SGD:");
                                        mainCoursesList.get(item).setPrice(sc.nextDouble());break;
                                    case 3:
                                        System.out.print("Enter new description:");
                                        sc.nextLine();
                                        mainCoursesList.get(item).setDescription(sc.nextLine());break;
                                }break;
                            case 's':
                                    for(Sides s:sidesList)
                                        System.out.println(++i  +") " + s.getName());
                                    System.out.print("Enter item to be updated:");
                                    item = sc.nextInt()-1;
                                    System.out.println("1 - > Name");
                                    System.out.println("2 - > Price");
                                    System.out.println("3 - > Description");
                                    System.out.print("Select details to be updated:");
                                    switch(sc.nextInt())
                                    {
                                        case 1:
                                            System.out.print("Enter new name:");
                                            sc.nextLine();
                                            sidesList.get(item).setName(sc.nextLine());break;
                                        case 2:
                                            System.out.print("Enter new price in SGD:");
                                            sidesList.get(item).setPrice(sc.nextDouble());break;
                                        case 3:
                                            System.out.print("Enter new description:");
                                            sc.nextLine();
                                            sidesList.get(item).setDescription(sc.nextLine());break;
                                    }break;
                            case 'd':
                                for(Drinks d:drinksList)
                                    System.out.println(++i  +") " + d.getName());
                                System.out.print("Enter item to be updated:");
                                item = sc.nextInt()-1;
                                System.out.println("1 - > Name");
                                System.out.println("2 - > Price");
                                System.out.println("3 - > Description");
                                System.out.print("Select details to be updated:");
                                switch(sc.nextInt())
                                {
                                    case 1:
                                        System.out.print("Enter new name:");
                                        sc.nextLine();
                                        drinksList.get(item).setName(sc.nextLine());break;
                                    case 2:
                                        System.out.print("Enter new price in SGD:");
                                        drinksList.get(item).setPrice(sc.nextDouble());break;
                                    case 3:
                                        System.out.print("Enter new description:");
                                        sc.nextLine();
                                        drinksList.get(item).setDescription(sc.nextLine());break;
                                }break;
                        }
                        System.out.println("Successfully updated!");
        }
    }
    private static void editPromotionalSet()
    {
        int i =0;
        sc.nextLine();
        System.out.println("Add(A), Update(U), Remove(R) promotional set");
        System.out.print("Enter A/U/R:");
        char action = sc.nextLine().toLowerCase().charAt(0);
        switch(action)
        {
            case 'a':
                System.out.println("Enter name of promotion set:");
                String name = sc.nextLine();
                for(MainCourse m:mainCoursesList)
                    System.out.println(++i  +") " + m.getName());
                System.out.print("Select your MainCourse:");
                int main = sc.nextInt()-1;i=0;
                for(Sides s:sidesList)
                    System.out.println(++i  +") " + s.getName());
                System.out.print("Select your Side:");
                int side = sc.nextInt()-1;i=0;
                for(Drinks d:drinksList)
                    System.out.println(++i  +") " + d.getName());
                System.out.print("Select your Drink:");
                int drink = sc.nextInt()-1;i=0;
                promotionalSetList.add(new PromotionalSet(name,mainCoursesList.get(main),sidesList.get(side),drinksList.get(drink)));
                System.out.println("Promotional set successfully added!");
                break;
            case 'r':
                    for(PromotionalSet p:promotionalSetList)
                        System.out.println(++i + ") Set " + p.getName() + ": $" + p.getPrice() + "\n" + p.getDesription());
                System.out.print("Select promotion item to be removed:");
                int item = sc.nextInt()-1;
                promotionalSetList.remove(item);
                System.out.println("Promotional set successfully removed!");break;
            case 'u':
                for(PromotionalSet p:promotionalSetList)
                    System.out.println(++i + ") Set " + p.getName() + ": $" + p.getPrice() + "\n" + p.getDesription());
                System.out.print("Select promotion item to be updated:");
                item = sc.nextInt()-1;
                sc.nextLine();
                System.out.println("Select: MainCourse(M), Sides(S), Drinks(D)");
                System.out.print("Select details to be updated:");
                char donkey = sc.nextLine().toLowerCase().charAt(0);i=0;
                switch(donkey)
                {
                    case 'm':
                        for(MainCourse m:mainCoursesList)
                            System.out.println(++i  +") " + m.getName());
                        System.out.print("Select your new MainCourse:");
                        main = sc.nextInt()-1;
                        promotionalSetList.get(item).setMain(mainCoursesList.get(main));
                        promotionalSetList.get(item).setDesription();
                        promotionalSetList.get(item).setPrice();
                        break;
                    case 's':
                        for(Sides s:sidesList)
                            System.out.println(++i  +") " + s.getName());
                        System.out.print("Select your Side:");
                        side = sc.nextInt()-1;
                        promotionalSetList.get(item).setSide(sidesList.get(side));
                        promotionalSetList.get(item).setDesription();
                        promotionalSetList.get(item).setPrice();
                        break;
                    case 'd':
                        for(Drinks d:drinksList)
                            System.out.println(++i  +") " + d.getName());
                        System.out.print("Select your Drink:");
                        drink = sc.nextInt()-1;
                        promotionalSetList.get(item).setDrink(drinksList.get(drink));
                        promotionalSetList.get(item).setDesription();
                        promotionalSetList.get(item).setPrice();
                }
                System.out.println("Successfully updated!");




        }
    }
    private static void createOrder(Staff s)
    {
         sc.nextLine();
         Random rdm = new Random();
         Date d = new Date();
         int table = Integer.MAX_VALUE;
         System.out.print("Take away (Y/N):");
         char t =sc.nextLine().toLowerCase().charAt(0);
         if(t=='n')
         {
             System.out.print("Enter table no:");
             table = sc.nextInt();
             sc.nextLine();
         }
         System.out.print("Membership Order(Y/N):");
         char a = sc.nextLine().toLowerCase().charAt(0);
         boolean mem = false;
         if(a=='y')
             mem = true;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        String dateTime = d.toString();
        String date = formatDate.format(d);
        String time = formatTime.format(d);
         String orderID = "#" + (rdm.nextInt(9999) + 10000 + rdm.nextInt(5));
         System.out.println("Order ID " + orderID + " created on " + dateTime + " by staffNo#" + s.getID());
         if(t=='n')
            dineInOrderList.add(new Order(s,mem,date,time,orderID,table));
         else
             takeAwayOrderList.add(new Order(s,mem,date,time,orderID,table));
    }
    private static void viewOrder()
    {
        if(dineInOrderList.size()==1) {
            Order o = dineInOrderList.get(0);
            System.out.println("=====Table " + o.getTable() + "=====");
            for (OrderItems item : o.getItemList())
                System.out.println(item.getQty() + "   " + item.getItemName() + "              " + item.getPrice());
        }
    }
    private static void editOrders()
    {
        if(takeAwayOrderList.size()!=0)
        {
            System.out.println("========Takeaway Orders========");
            for(Order o:takeAwayOrderList)
                System.out.println(o.getOrderid() + " " + o.getOrderTime());
        }
        
    }





}
