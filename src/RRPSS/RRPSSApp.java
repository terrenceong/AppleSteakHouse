package RRPSS;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RRPSSApp {
    private static Scanner sc = new Scanner(System.in);
    private static List<MainCourse> mainCoursesList = new ArrayList<>();
    private static List<Sides> sidesList = new ArrayList<>();
    private static List<Drinks> drinksList = new ArrayList<>();
    private static List<PromotionalSet> promotionalSetList = new ArrayList<>();
    private static List<Order> dineInOrderList = new ArrayList<>();
    private static List<Order> takeAwayOrderList = new ArrayList<>();
    private static List<Order> completedOrderList = new ArrayList<>();
    private static List<Table> tableList = new ArrayList<>();
    private static List<Reservation> reservationList = new ArrayList<>();
    public static void main(String[] args) {
        // write your code here
        Staff s = new Staff();
        enterStaffInfo(s);
        printOption(s);
        initializeFoodMenu();
        initializeTables();
        boolean quit = false;
        while(!quit)
        {
        	checkExpiredReservations();
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
                case 7: createReservation();break;
                case 8: checkReservations();break;
                case 9: printTables();break;
                case 10: setTableAvailability();break;
                case 11: break;
                default: quit = true;
            }
        }

    }

    public static void enterStaffInfo(Staff s) {
        System.out.println("=======WELCOME TO APPLE STEAK HOUSE STAFF MENU=======");
        System.out.print("Enter staff name:");
        String name = sc.nextLine().toUpperCase();
        System.out.print("Enter staff ID:");
        String id = sc.nextLine();
        s.setID(id);
        s.setName(name);
    }

    private static void printOption(Staff s) {
        System.out.println("=======WELCOME TO APPLE STEAK HOUSE STAFF MENU=======");
        System.out.println("StaffNo#" + s.getID() + " Welcome " + s.getName() + "!");
        System.out.println("1 -> Print restaurant menu item");
        System.out.println("2 -> Create/Update/Remove menu item");
        System.out.println("3 -> Create/Update/Remove promotion");
        System.out.println("4 -> Create order");
        System.out.println("5 -> View order");
        System.out.println("6 -> Add/Remove order item/s to/from order ");
        System.out.println("7 -> Create reservation booking");
        System.out.println("8 -> Check/Remove reservation booking");
        System.out.println("9 -> Check table availability");
        System.out.println("10 -> Set table availability");
        System.out.println("11 -> Print order invoice");
        System.out.println("12 -> Print sale revenue report by period");
        System.out.println("13 -> Quit");

    }

    private static void initializeFoodMenu() {
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

    private static void initializeTables()
    {
        tableList.add(new Table(1,2));
        tableList.add(new Table(2,2));
        tableList.add(new Table(3,2));
        tableList.add(new Table(4,2));
        tableList.add(new Table(5,4));
        tableList.add(new Table(6,4));
        tableList.add(new Table(7,4));
        tableList.add(new Table(8,6));
        tableList.add(new Table(9,6));
        tableList.add(new Table(10,10));
    }
    private static void printTables()
    {
	   	int i =1;
	   	System.out.println("=======Available Tables=======");
	    for(Table t:tableList) {
	       	System.out.print("Table "+ i++ +": ");
	   		System.out.println(t.getAvailability());
	    }
   }
    private static void setTableAvailability()
    {
    	int tableId;
    	String availability;
		System.out.print("Enter the table number:");
		tableId = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter the table status:");
		availability = sc.nextLine().toUpperCase();
		if(availability.equals("AVAILABLE") || availability.equals("UNAVAILABLE") || availability.equals("RESERVED")) {
			for(Table t:tableList) {
				if(t.getTableId()==tableId)
					t.setAvailability(availability);
				
			}
			System.out.println("Table status updated");
		}
		else {
			System.out.println("Invalid status");
		}
   }
    private static void createReservation(){
    	try {
    		String reservationDate=null, name;
        	int pax, contact;
        	Table table = null;
        	LocalDateTime reservationDateFormatted,creationDate;
        	
        	System.out.print("Enter the number of persons:");
        	pax = sc.nextInt();
        	for(Table t:tableList) {
        		if(t.getAvailability()=="AVAILABLE" && t.getNumOfSeats()>=pax) {
        			if(t.getNumOfSeats()==10 && pax<=4) {
        				System.out.println("No tables available!");
        				return;
        			}
        			t.setAvailability("RESERVED");
        			table=t;
        			break;
        		}
        	}
        	if(table==null) {
        		System.out.println("No tables available!");
        		return;
        	}
        	sc.nextLine();
        	do {
        		if(reservationDate!=null)
        			System.out.println("Invalid date");
        		System.out.print("Enter the reservation date in dd-MM-yyyy HH:mm:");
    	    	reservationDate = sc.nextLine();
    	    	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); 
    	    	reservationDateFormatted = LocalDateTime.parse(reservationDate, format);
    	    	creationDate = LocalDateTime.now();
        	}
        	while(reservationDateFormatted.isBefore(creationDate));
        	

        	System.out.print("Enter your name for the reservation:");
        	name = sc.nextLine().toUpperCase();
        	System.out.print("Enter your contact for the reservation:");
        	contact = sc.nextInt();
        	
        	reservationList.add(new Reservation(reservationDateFormatted,creationDate, pax, name, contact, table));
        	System.out.println("Reservation created!");
    	}
    	catch (Exception e) {
    		System.out.print("Invalid input");
    	}
    	
	}
    private static void checkExpiredReservations() {
    	try {
    		int i=0;
        	LocalDateTime currentDate = LocalDateTime.now();
        	for(Reservation r:reservationList) {
        		if(r.getDateReserved().isBefore(currentDate)) {
        			r.getTable().setAvailability("AVAILABLE");
        			reservationList.remove(i);
        			checkExpiredReservations();
        			return;
        		}
        			
        		i++;
        	}
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    	
    }
    private static void checkReservations() {
    	if(reservationList.isEmpty()) {
			System.out.println("No current Reservations");
			return;
		}
    	char action;
    	String name;
    	int contact,i=0;
    	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("Check(C) or Remove(R) Reservations");
        System.out.print("Enter C/R:");
        sc.nextLine();
        action = sc.nextLine().toLowerCase().charAt(0);
        switch(action) {
	    	case 'c':
	    		for(Reservation r:reservationList) {
	        		System.out.println("Customer Name:"+r.getName()+" Contact:"+r.getContact()+"; For "+r.getPax()+"pax at Table "
	    		+r.getTable().getTableId()+" for "+r.getDateReserved().format(myFormatObj)+"| Created at:"+r.getDateCreated().format(myFormatObj));
	        	}
	    		break;
	    	case 'r':
	    		System.out.print("Enter Name of Reservation to remove:");
	    		name = sc.nextLine().toUpperCase();
	    		System.out.print("Enter Contact of Reservation to remove:");
	    		contact = sc.nextInt();
	    		for(Reservation r:reservationList) {
	        		if(r.getName().equals(name) && r.getContact()==contact) {
	        			r.getTable().setAvailability("AVAILABLE");
	        			reservationList.remove(i);
		        		return;
	        		}
	    			i++;
	        	}
	    		System.out.println("Reservation not found");
        }
    	
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
    private static void createOrder(Staff s) {
        sc.nextLine();
        Random rdm = new Random();
        Date d = new Date();
        String orderID;
        int table = Integer.MAX_VALUE;
        System.out.print("Take away (Y/N):");
        char t = sc.nextLine().toLowerCase().charAt(0);
        if (t == 'n') {
            System.out.print("Enter table no:");
            table = sc.nextInt();
            sc.nextLine();
        }
        System.out.print("Membership Order(Y/N):");
        char a = sc.nextLine().toLowerCase().charAt(0);
        boolean mem = false;
        if (a == 'y')
            mem = true;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        String dateTime = d.toString();
        String date = formatDate.format(d);
        String time = formatTime.format(d);
        if (t == 'n') {
            orderID = "D" + (rdm.nextInt(9999) + 10000 + rdm.nextInt(5));
            dineInOrderList.add(new Order(s, mem, date, time, orderID, table));
        } else {
            orderID = "T" + (rdm.nextInt(9999) + 10000 + rdm.nextInt(5));
            takeAwayOrderList.add(new Order(s, mem, date, time, orderID, table));
        }
        System.out.println("Order ID #" + orderID + " created on " + dateTime + " by staffNo#" + s.getID());
    }

    private static void viewOrder() {
        if (dineInOrderList.size() == 0 && takeAwayOrderList.size() == 0) {
            System.out.println("There are no orders right now");
            return;
        }
        if (dineInOrderList.size() == 1 && takeAwayOrderList.size() == 0) {
            Order o = dineInOrderList.get(0);
            System.out.println("==============Table " + o.getTable() + "==============");
            for (OrderItems item : o.getItemList()) {
                System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                System.out.println();
            }

        }
        else if (dineInOrderList.size() == 0 && takeAwayOrderList.size() == 1) {
            Order o = takeAwayOrderList.get(0);
            System.out.println("=========Take away Order " + o.getOrderid() + "=========");
            for (OrderItems item : o.getItemList()) {
                System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                System.out.println();
            }

        }
        else {
            if (takeAwayOrderList.size() != 0) {
                System.out.println("========Takeaway Orders========");
                for (Order o : takeAwayOrderList)
                    System.out.println("#" + o.getOrderid() + " " + o.getOrderTime());
            }
            if (dineInOrderList.size() != 0) {
                System.out.println("========Dine in Orders=========");
                for (Order o : dineInOrderList)
                    System.out.println("#" + o.getOrderid() + " Table " + o.getTable() + " " + o.getOrderTime());
            }
            int index = -1;
            sc.nextLine();
            while (index == -1) {
                System.out.print("Enter Order ID to view order:");
                String choice = sc.nextLine().toUpperCase();
                if (choice.charAt(0) == 'T') {
                    for (int i = 0; i < takeAwayOrderList.size(); i++) {
                        if (takeAwayOrderList.get(i).getOrderid().equals(choice)) {
                            index = i;
                            break;
                        }
                    }
                    if (index == -1) {
                        System.out.println("Invalid Order ID please try again");
                        continue;
                    }
                    Order o = takeAwayOrderList.get(index);
                    System.out.println("=========Take away Order " + o.getOrderid() + "=========");
                    for (OrderItems item : o.getItemList()) {
                        System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                        System.out.println();
                    }
                    //System.out.println(o.getTotalCost());
                }
                else {
                    for (int i = 0; i < dineInOrderList.size(); i++) {
                        if (dineInOrderList.get(i).getOrderid().equals(choice)) {
                            index = i;
                            break;
                        }
                    }
                    if (index == -1) {
                        System.out.println("Invalid Order ID please try again");
                        continue;
                    }
                    Order o = dineInOrderList.get(index);
                    System.out.println("==============Table " + o.getTable() + "==============");
                    for (OrderItems item : o.getItemList()) {
                        System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                        System.out.println();
                    }
                }
            }
        }
    }
        private static void editOrders()
        {
            int index = -1;
            int x = 0;
            if (takeAwayOrderList.size() != 0) {
                System.out.println("========Takeaway Orders========");
                for (Order o : takeAwayOrderList)
                    System.out.println("#" + o.getOrderid() + " " + o.getOrderTime());
            }
            if (dineInOrderList.size() != 0) {
                System.out.println("========Dine in Orders=========");
                for (Order o : dineInOrderList)
                    System.out.println("#" + o.getOrderid() + " Table " + o.getTable() + " " + o.getOrderTime());
            }
            if (dineInOrderList.size() == 0 && takeAwayOrderList.size() == 0) {
                System.out.println("There are no orders right now");
                return;
            }
            sc.nextLine();
            while (index == -1) {
                System.out.print("Enter Order ID to add/remove items:");
                String choice = sc.nextLine().toUpperCase();
                if (choice.charAt(0) == 'T') {
                    for (int i = 0; i < takeAwayOrderList.size(); i++) {
                        if (takeAwayOrderList.get(i).getOrderid().equals(choice)) {
                            index = i;
                            break;
                        }
                    }
                    if (index == -1) {
                        System.out.println("Invalid Order ID please try again");
                        continue;
                    }
                    System.out.print("Select Add(A)/Remove(R) items for order #" + takeAwayOrderList.get(index).getOrderid() + ":");
                    char c = sc.nextLine().toLowerCase().charAt(0);
                    do {
                        x = 0;
                        if (c == 'a') {
                            System.out.print("Select: Promotional set(P), MainCourse(M), Sides(S), Drinks(D):");
                            char l = sc.nextLine().toLowerCase().charAt(0);
                            switch (l) {

                                case 'm':
                                    for (MainCourse m : mainCoursesList)
                                        System.out.println(++x + ") " + m.getName() + " $" + m.getPrice());
                                    System.out.print("Select your MainCourse:");
                                    int m = sc.nextInt() - 1;
                                    System.out.print("Enter qty:");
                                    int q = sc.nextInt();
                                    Order o = takeAwayOrderList.get(index);
                                    o.setTotalCost(o.getTotalCost() + (mainCoursesList.get(m).getPrice() * q));
                                    o.getItemList().add(new OrderItems(q, mainCoursesList.get(m).getPrice(),
                                            mainCoursesList.get(m).getName()));
                                    break;
                                case 's':
                                    for (Sides s : sidesList)
                                        System.out.println(++x + ") " + s.getName() + " $" + s.getPrice());
                                    System.out.print("Select your Side:");
                                    int s = sc.nextInt() - 1;
                                    System.out.print("Enter qty:");
                                    q = sc.nextInt();
                                    o = takeAwayOrderList.get(index);
                                    o.setTotalCost(o.getTotalCost() + (sidesList.get(s).getPrice() * q));
                                    o.getItemList().add(new OrderItems(q, sidesList.get(s).getPrice(),
                                            sidesList.get(s).getName()));
                                    break;
                                case 'd':
                                    for (Drinks d : drinksList)
                                        System.out.println(++x + ") " + d.getName() + " $" + d.getPrice());
                                    System.out.print("Select your drink:");
                                    int d = sc.nextInt() - 1;
                                    System.out.print("Enter qty:");
                                    q = sc.nextInt();
                                    o = takeAwayOrderList.get(index);
                                    o.setTotalCost(o.getTotalCost() + (drinksList.get(d).getPrice() * q));
                                    o.getItemList().add(new OrderItems(q, drinksList.get(d).getPrice(),
                                            drinksList.get(d).getName()));
                                    break;
                                case 'p':
                                    for (PromotionalSet p : promotionalSetList) {
                                        System.out.println(++x + ") " + p.getName() + " $" + p.getPrice());
                                        System.out.println(p.getDesription());
                                    }
                                    System.out.print("Select your promotional set:");
                                    int p = sc.nextInt() - 1;
                                    System.out.print("Enter qty:");
                                    q = sc.nextInt();
                                    o = takeAwayOrderList.get(index);
                                    o.setTotalCost(o.getTotalCost() + (promotionalSetList.get(p).getPrice() * q));
                                    o.getItemList().add(new OrderItems(q, promotionalSetList.get(p).getPrice(),
                                            promotionalSetList.get(p).getName()));
                                    break;

                            }
                            System.out.println("Successfully added");
                            sc.nextLine();
                        }
                        else if(c=='r')
                        {

                            int count =0;
                            int qtyRemove =1;
                            Order o = takeAwayOrderList.get(index);
                            if(o.getItemList().size()==0)
                            {
                                System.out.println("Order ID# " + o.getOrderid() + " is empty!");
                            }
                            else
                            {
                                for (OrderItems item : o.getItemList()) {
                                    System.out.printf(++count + ") %s Qty: %d",item.getItemName(),item.getQty());
                                    System.out.println();
                                }
                                System.out.print("Select item to be removed:");
                                int n = sc.nextInt()-1;
                                if(o.getItemList().get(n).getQty() > 1)
                                {
                                    System.out.print("Enter number of qty to remove:");
                                     qtyRemove = sc.nextInt();
                                     while(qtyRemove > o.getItemList().get(n).getQty())
                                     {
                                         System.out.println("qty cannot be greater than " + o.getItemList().get(n).getQty());
                                         System.out.print("Enter number of qty to remove:");
                                         qtyRemove = sc.nextInt();
                                     }

                                }
                                if(qtyRemove==o.getItemList().get(n).getQty())
                                {
                                    o.setTotalCost(o.getTotalCost()-o.getItemList().get(n).getPrice());
                                    o.getItemList().remove(n);
                                }
                                else
                                {
                                    int getqty = o.getItemList().get(n).getQty();
                                    double totalp = o.getItemList().get(n).getPrice();
                                    o.getItemList().get(n).setQty(getqty-qtyRemove);
                                    o.getItemList().get(n).setPrice(totalp-qtyRemove*(totalp/getqty));
                                    o.setTotalCost(o.getTotalCost() - qtyRemove*(totalp/getqty));
                                }

                            }
                            System.out.println("Successfully removed");
                            sc.nextLine();

                        }
                        System.out.print("Select Add(A)/Remove(R)/Quit(Q) items for order #" + takeAwayOrderList.get(index).getOrderid() + ":");
                        c = sc.nextLine().toLowerCase().charAt(0);
                    } while (c != 'q');
                } else {
                    for (int i = 0; i < dineInOrderList.size(); i++) {
                        if (dineInOrderList.get(i).getOrderid().equals(choice)) {
                            index = i;
                            break;
                        }
                    }
                    if (index == -1) {
                        System.out.println("Invalid Order ID please try again");
                        continue;
                    }
                    System.out.print("Select Add(A)/Remove(R) items for order #" + dineInOrderList.get(index).getOrderid() + ":");
                    char c = sc.nextLine().toLowerCase().charAt(0);
                    do {
                        x = 0;
                        if (c == 'a') {
                            System.out.print("Select: Promotional set(P), MainCourse(M), Sides(S), Drinks(D):");
                            char l = sc.nextLine().toLowerCase().charAt(0);
                            switch (l) {

                                case 'm':
                                    for (MainCourse m : mainCoursesList)
                                        System.out.println(++x + ") " + m.getName() + " $" + m.getPrice());
                                    System.out.print("Select your MainCourse:");
                                    int m = sc.nextInt() - 1;
                                    System.out.print("Enter qty:");
                                    int q = sc.nextInt();
                                    Order o = dineInOrderList.get(index);
                                    o.setTotalCost(o.getTotalCost() + (mainCoursesList.get(m).getPrice() * q));
                                    o.getItemList().add(new OrderItems(q, mainCoursesList.get(m).getPrice(),
                                            mainCoursesList.get(m).getName()));
                                    break;
                                case 's':
                                    for (Sides s : sidesList)
                                        System.out.println(++x + ") " + s.getName() + " $" + s.getPrice());
                                    System.out.print("Select your Side:");
                                    int s = sc.nextInt() - 1;
                                    System.out.print("Enter qty:");
                                    q = sc.nextInt();
                                    o = dineInOrderList.get(index);
                                    o.setTotalCost(o.getTotalCost() + (sidesList.get(s).getPrice() * q));
                                    o.getItemList().add(new OrderItems(q, sidesList.get(s).getPrice(),
                                            sidesList.get(s).getName()));
                                    break;
                                case 'd':
                                    for (Drinks d : drinksList)
                                        System.out.println(++x + ") " + d.getName() + " $" + d.getPrice());
                                    System.out.print("Select your drink:");
                                    int d = sc.nextInt() - 1;
                                    System.out.print("Enter qty:");
                                    q = sc.nextInt();
                                    o = dineInOrderList.get(index);
                                    o.setTotalCost(o.getTotalCost() + (drinksList.get(d).getPrice() * q));
                                    o.getItemList().add(new OrderItems(q, drinksList.get(d).getPrice(),
                                            drinksList.get(d).getName()));
                                    break;
                                case 'p':
                                    for (PromotionalSet p : promotionalSetList) {
                                        System.out.println(++x + ") " + p.getName() + " $" + p.getPrice());
                                        System.out.println(p.getDesription());
                                    }
                                    System.out.print("Select your promotional set:");
                                    int p = sc.nextInt() - 1;
                                    System.out.print("Enter qty:");
                                    q = sc.nextInt();
                                    o = dineInOrderList.get(index);
                                    o.setTotalCost(o.getTotalCost() + (promotionalSetList.get(p).getPrice() * q));
                                    o.getItemList().add(new OrderItems(q, promotionalSetList.get(p).getPrice(),
                                            promotionalSetList.get(p).getName()));
                                    break;

                            }
                            System.out.println("Successfully added!");


                        }
                        else if(c=='r')
                        {
                            int count =0;
                            int qtyRemove =1;
                            Order o = dineInOrderList.get(index);
                            if(o.getItemList().size()==0)
                            {
                                System.out.println("Order ID# " + o.getOrderid() + " is empty!");
                            }
                            else
                            {
                                for (OrderItems item : o.getItemList()) {
                                    System.out.printf(++count + ") %s Qty: %d",item.getItemName(),item.getQty());
                                    System.out.println();
                                }
                                System.out.print("Select item to be removed:");
                                int n = sc.nextInt()-1;
                                if(o.getItemList().get(n).getQty() > 1)
                                {
                                    System.out.print("Enter number of qty to remove:");
                                    qtyRemove = sc.nextInt();
                                    while(qtyRemove > o.getItemList().get(n).getQty())
                                    {
                                        System.out.println("qty cannot be greater than " + o.getItemList().get(n).getQty());
                                        System.out.print("Enter number of qty to remove:");
                                        qtyRemove = sc.nextInt();
                                    }

                                }
                                if(qtyRemove==o.getItemList().get(n).getQty())
                                {
                                    o.setTotalCost(o.getTotalCost()-o.getItemList().get(n).getPrice());
                                    o.getItemList().remove(n);
                                }
                                else
                                {
                                    int getqty = o.getItemList().get(n).getQty();
                                    double totalp = o.getItemList().get(n).getPrice();
                                    o.getItemList().get(n).setQty(getqty-qtyRemove);
                                    o.getItemList().get(n).setPrice(totalp-qtyRemove*(totalp/getqty));
                                    o.setTotalCost(o.getTotalCost() - qtyRemove*(totalp/getqty));
                                }

                            }
                            System.out.println("Successfully removed");
                            //sc.nextLine();
                        }
                        sc.nextLine();
                        System.out.print("Select Add(A)/Remove(R)/Quit(Q) items for order #" + dineInOrderList.get(index).getOrderid() + ":");
                        c = sc.nextLine().toLowerCase().charAt(0);
                    } while (c != 'q');

                }
            }
        }
}

