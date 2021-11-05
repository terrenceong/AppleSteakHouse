package reservation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import RRPSS.RRPSSApp;
import order.Order;
import reservation.Reservation;
import reservation.Table;

public class ReservationMgr {
    private static Scanner sc = new Scanner(System.in);
    static List<Order> dineInOrderList = RRPSSApp.dineInOrderList;
    static List<Order> takeAwayOrderList =RRPSSApp.takeAwayOrderList;
    static List<Order> completedOrderList = RRPSSApp.completedOrderList;
    static List<Table> tableList = RRPSSApp.tableList;
    static List<Reservation> reservationList = RRPSSApp.reservationList;

    public static void showReservationOptions() {
        boolean quit = false;
        do
        {
            System.out.println("======Reservation Options======");
            System.out.println("1 -> Create reservation booking");
            System.out.println("2 -> Check/Remove reservation booking");
            System.out.println("3 -> Check table availability");
            System.out.println("4 -> Set table availability");
            System.out.println("5 -> Return to main");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            ReservationMgr.checkExpiredReservations();
            switch(choice)
            {
	            case 1: createReservation();break;
	            case 2: checkReservations();break;
	            case 3: printTables();break;
	            case 4: setTableAvailability();break;
                default: quit = true;
            }
        } while(!quit);
    }

    public static void initializeTables()
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
        		System.out.print("Enter the reservation date in dd/MM/yyyy HH:mm:");
    	    	reservationDate = sc.nextLine();
    	    	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
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
    		System.out.println("Invalid input");
    	}
    	
	}
    
    public static void checkExpiredReservations() {
    	try {
    		int i=0;
        	LocalDateTime currentDate = LocalDateTime.now();
        	for(Reservation r:reservationList) {
        		if(r.getDateReserved().plusMinutes(10).isBefore(currentDate)) {
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
    	DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM HH:mm");
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
    
}