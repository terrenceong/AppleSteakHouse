package RRPSS;


import java.util.Scanner;


import invoice.InvoiceUI;
import menu.*;
import order.*;
import reservation.*;
import reservation.*;

/**
 * Boundary Class of the restaurant reservation Application.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */

public class RRPSSApp {
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
    	try {
            // write your code here 
            Staff s = new Staff();
            enterStaffInfo(s);
            System.out.println("=======WELCOME TO APPLE STEAK HOUSE STAFF MENU=======");
            System.out.println("StaffNo#" + s.getID() + " Welcome " + s.getName() + "!");
            //initializeTestValues();
            MenuMgr.initializeFoodMenu();
            ReservationMgr.initializeTables();
            boolean quit = false;
            while(!quit)
            {
                System.out.println("======RRPSS Options======");
                System.out.println("1 -> Menu Options");
                System.out.println("2 -> Order Options");
                System.out.println("3 -> Reservation Options");
                System.out.println("4 -> Invoice Options");
                System.out.println("5 -> Quit");
                System.out.print("Enter your choice:");
                int choice = sc.nextInt();
                ReservationMgr.checkExpiredReservations();
                switch(choice)
                {
	                case 1: MenuUI.showMenuOptions();break;
	                case 2: OrderUI.showOrderOptions(s);break;
	                case 3: ReservationUI.showReservationOptions();break;
	                case 4: InvoiceUI.showInvoiceOptions();break;
                    default: quit = true;
                }
            }
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
    }
    /**
     * Enter staff information before proceeding on the restaurant application.
     * @param s Staff object.
     */
    public static void enterStaffInfo(Staff s) {
        System.out.println("=======WELCOME TO APPLE STEAK HOUSE STAFF MENU=======");
        System.out.print("Enter staff name:");
        String name = sc.nextLine().toUpperCase();
        System.out.print("Enter staff ID:");
        String id = sc.nextLine();
        s.setID(id);
        s.setName(name);
    }
}