package RRPSS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import invoice.Invoice;
import invoice.InvoiceMgr;
import menu.Drinks;
import menu.MainCourse;
import menu.PromotionalSet;
import menu.Sides;
import menu.MenuMgr;
import order.Order;
import order.OrderItems;
import order.OrderMgr;
import reservation.Reservation;
import reservation.Table;
import reservation.ReservationMgr;



public class RRPSSApp {
    private static Scanner sc = new Scanner(System.in);
    public static List<MainCourse> mainCoursesList = new ArrayList<>();
    public static List<Sides> sidesList = new ArrayList<>();
    public static List<Drinks> drinksList = new ArrayList<>();
    public static List<PromotionalSet> promotionalSetList = new ArrayList<>();
    public static List<Order> dineInOrderList = new ArrayList<>();
    public static List<Order> takeAwayOrderList = new ArrayList<>();
    public static List<Order> completedOrderList = new ArrayList<>();
    public static List<Table> tableList = new ArrayList<>();
    public static List<Reservation> reservationList = new ArrayList<>();
    public static List<Invoice> invoiceList = new ArrayList<>();
    
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
	                case 1: MenuMgr.showMenuOptions();break;
	                case 2: OrderMgr.showOrderOptions(s);;break;
	                case 3: ReservationMgr.showReservationOptions();;break;
	                case 4: InvoiceMgr.showInvoiceOptions();;break;
                    default: quit = true;
                }
            }
    	}
    	catch (Exception e) {
    		System.out.println(e);
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

    private static void initializeTestValues() {
    	Staff s = new Staff();
        s.setID("12");
        s.setName("RUSSELL");
        List<OrderItems> itemList = new ArrayList<>();
        List<OrderItems> itemList2 = new ArrayList<>();
        itemList.add(new OrderItems(2, 10.0, "Something"));
        itemList2.add(new OrderItems(2, 10.0, "Something"));
        itemList.add(new OrderItems(1, 20.0, "Something2"));
        itemList.add(new OrderItems(4, 20.0, "Something2"));
        itemList2.add(new OrderItems(1, 15.0, "Something3"));
    	completedOrderList.add(new Order(s, false, "13/10/2021", "00:00", 
        		"D12345C", 4, itemList));
    	completedOrderList.add(new Order(s, false, "16/10/2021", "00:00", 
        		"D12346C", 4, itemList2));
    }
}