package RRPSS;

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
    private static List<Table> tableList = new ArrayList<>();
    private static List<Reservation> reservationList = new ArrayList<>();
    public static void main(String[] args) {
	// write your code here
        Staff s = new Staff();
        //enterStaffInfo(s);
        //printOption(s);
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
                case 4: break;
                case 5: break;
                case 6: System.out.println("6 -> Add/Remove order item/s to/from order ");
                	break;
                case 7: createReservation();break;
                case 8: checkReservations();break;
                case 9: printTables();break;
                case 10: setTableAvailability();break;
                case 11: break;
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
        System.out.println("10 -> Set table availability");
        System.out.println("11 -> Print order invoice");
        System.out.println("12 -> Print sale revenue report by period");
        System.out.println("13 -> Quit");

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
        promotionalSetList.add(new PromotionalSet("A",mainCoursesList.get(0),sidesList.get(0),drinksList.get(2)));
        promotionalSetList.add(new PromotionalSet("B",mainCoursesList.get(1),sidesList.get(0),drinksList.get(1)));
        promotionalSetList.add(new PromotionalSet("C",mainCoursesList.get(3),sidesList.get(1),drinksList.get(0)));

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
        System.out.println("Add(A), Update(U), Remove(R)");
        System.out.print("Enter A/U/R:");
        char action = sc.nextLine().toLowerCase().charAt(0);
        System.out.println("MainCourse(M), Sides(S), Drinks(D)");
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
        System.out.println("Add(A), Update(U), Remove(R)");
        System.out.print("Enter A/U/R:");
        char action = sc.nextLine().toLowerCase().charAt(0);
        switch(action)
        {
            case 'a':
        }
    }



}
