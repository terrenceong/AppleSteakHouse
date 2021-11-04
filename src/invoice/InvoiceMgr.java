package invoice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import invoice.Invoice;
import menu.Drinks;
import menu.MainCourse;
import menu.PromotionalSet;
import menu.Sides;
import order.Order;
import order.OrderItems;
import reservation.ReservationMgr;
import reservation.Table;

public class InvoiceMgr {
    private static Scanner sc = new Scanner(System.in);
    static List<MainCourse> mainCoursesList = RRPSS.RRPSSApp.mainCoursesList;
    static List<Sides> sidesList = RRPSS.RRPSSApp.sidesList;
    static List<Drinks> drinksList = RRPSS.RRPSSApp.drinksList;
    static List<PromotionalSet> promotionalSetList = RRPSS.RRPSSApp.promotionalSetList;
    static List<Order> dineInOrderList = RRPSS.RRPSSApp.dineInOrderList;
    static List<Order> takeAwayOrderList = RRPSS.RRPSSApp.takeAwayOrderList;
    static List<Order> completedOrderList = RRPSS.RRPSSApp.completedOrderList;
    static List<Table> tableList = RRPSS.RRPSSApp.tableList;
    static List<Invoice> invoiceList = RRPSS.RRPSSApp.invoiceList;

    public static void showInvoiceOptions() {
        boolean quit = false;
        do
        {
            System.out.println("======Invoice Options======");
            System.out.println("1 -> Print order invoice");
            System.out.println("2 -> View order invoices");
            System.out.println("3 -> Print sale revenue report by period");
            System.out.println("4 -> Quit");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            ReservationMgr.checkExpiredReservations();
            switch(choice)
            {
	            case 1: closeOrderInvoice();break;
	            case 2: listInvoices();break;
	            case 3: salesReport();break;
                default: quit = true;
            }
        } while(!quit);
    }

    
    private static void closeOrderInvoice() {
        if (dineInOrderList.size() == 0 && takeAwayOrderList.size() == 0) {
            System.out.println("There are no orders right now.");
            return;
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
                System.out.print("Enter Order ID to print invoice:");
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
                    double subCost = o.getTotalCost();
                    double serviceCharge = 0;
                    double gst = subCost*0.07;
                    double discount = 0;
                    if(o.isMembership())
                    	discount = subCost*0.05;
                    double totalCost = subCost+serviceCharge+gst-discount;
                    Invoice newInvoice = new Invoice(o.getOrderid()+"C", o.getOrderDate(), o.getOrderTime(), o.getS(), o.getItemList(),
                    		subCost, serviceCharge, gst, discount, totalCost);
                    invoiceList.add(newInvoice);
                    completedOrderList.add(new Order(o.getS(), o.isMembership(), o.getOrderDate(), o.getOrderTime(), 
                    		o.getOrderid()+"C", o.getTable(), o.getItemList()));
                    takeAwayOrderList.remove(index);
                    printOrderInvoice(newInvoice);
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
                    double subCost = o.getTotalCost();
                    double serviceCharge = subCost*0.1;
                    double gst = subCost*0.07;
                    double discount = 0;
                    if(o.isMembership())
                    	discount = subCost*0.05;
                    double totalCost = subCost+serviceCharge+gst-discount;
                    Invoice newInvoice = new Invoice(o.getOrderid()+"C", o.getOrderDate(), o.getOrderTime(), o.getS(), o.getItemList(),
                    		subCost, serviceCharge, gst, discount, totalCost);
                    invoiceList.add(newInvoice);
                    completedOrderList.add(new Order(o.getS(), o.isMembership(), o.getOrderDate(), o.getOrderTime(), 
                    		o.getOrderid()+"C", o.getTable(), o.getItemList()));
                    tableList.get(o.getTable()-1).setAvailability("AVAILABLE");
                    dineInOrderList.remove(index);
                    printOrderInvoice(newInvoice);
                }
            }
        }
    }
    
    private static void printOrderInvoice(Invoice i) {
    	System.out.println("========Apple Steak House========");
    	System.out.print("Staff Name: ");
    	System.out.println(i.getS().getName());
    	System.out.print("Order ID: ");
    	System.out.println(i.getOrderid());
    	System.out.print("Order Date: ");
    	System.out.println(i.getOrderDate());
    	
    	System.out.println("---------------------------------");
    	for (OrderItems item : i.getItemList()) {
            System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
            System.out.println();
        }
    	System.out.println("---------------------------------");
    	System.out.printf("%-27s %.2f","SUBTOTAL:", i.getSubTotalCost());
        System.out.println();
    	System.out.printf("%-27s %.2f","10% SERVICE CHARGE:", i.getServiceCharge());
        System.out.println();
    	System.out.printf("%-27s %.2f","7% GST:", i.getGst());
        System.out.println();
    	if(i.getDiscount()!=0) {
    	    System.out.printf("%-27s %.2f","5% MEMBER DISCOUNT:", i.getDiscount());
            System.out.println();
    	}
    	System.out.println("_________________________________");
    	System.out.printf("%-27s %.2f","TOTAL DUE:", i.getTotalCost());
        System.out.println();
    	System.out.println("---------------------------------");
    	System.out.println("-Thank you for ordering with us!-");
    	System.out.println("--------Please Come Again!-------");
    	
    }
    
    private static void listInvoices() {
        int index = -1;
        sc.nextLine();
        if(invoiceList.size()==0) {
        	System.out.println("No invoices.");
        	return;
        }
        else {
        	System.out.println("===========Invoice List==========");
            for (Invoice i : invoiceList)
                System.out.println("#" + i.getOrderid() + " " + i.getOrderTime());
        }
        while (index == -1) {
            System.out.print("Enter Invoice ID to view order:");
            String choice = sc.nextLine().toUpperCase();
            for (int i = 0; i < invoiceList.size(); i++) {
                if (invoiceList.get(i).getOrderid().equals(choice)) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                System.out.println("Invalid Invoice ID please try again");
                continue;
            }
            Invoice i = invoiceList.get(index);
            printOrderInvoice(i);
        }
    }
    
    private static void salesReport() {
    	System.out.println("Generate Report for Daily or Monthly Sales?");
        System.out.println("1. Daily");
        System.out.println("2. Monthly");
        List<OrderItems> itemList = new ArrayList<>();
    	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
    	LocalDateTime currentDate = LocalDateTime.now();
        double total=0;
        boolean entered=false;
        int choice = sc.nextInt();
        switch (choice){
        	case 1:
        		for (Order order : completedOrderList) {
        			if(currentDate.toLocalDate().isEqual(LocalDate.parse(order.getOrderDate(), format))){
        				for (OrderItems item : order.getItemList()) {
        					entered=false;
        					for (OrderItems itemCheck : itemList) {
        						if(itemCheck.getItemName().equals(item.getItemName())) {
        							itemCheck.setQty(item.getQty()+itemCheck.getQty());
        							itemCheck.setPrice(itemCheck.getPrice()+item.getPrice());
        							total+=item.getPrice();
        							entered=true;
        							break;
        						}
        					}
        					if(!entered) {
        						itemList.add(new OrderItems(item.getQty(), item.getPrice()/item.getQty(), item.getItemName()));
        						total+=item.getPrice();
        					}
	        	        }
        			}
        		}
            	System.out.println("===========CZ2002 Cafe===========");
            	System.out.println("========Gross Revenue for========");
            	System.out.println("==========="+currentDate.format(format)+"============");
            	System.out.println("---------------------------------");
            	for (OrderItems item : itemList) {
                    System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                    System.out.println();
                }
            	System.out.println("_________________________________");
            	System.out.printf("%-27s %.2f","TOTAL GROSS REVENUE:", total);
            	System.out.println();
            	break;
        	case 2:
        		for (Order order : completedOrderList) {
        			if(currentDate.toLocalDate().getMonth()==(LocalDate.parse(order.getOrderDate(), format)).getMonth()){
        				for (OrderItems item : order.getItemList()) {
        					entered=false;
        					for (OrderItems itemCheck : itemList) {
        						if(itemCheck.getItemName().equals(item.getItemName())) {
        							itemCheck.setQty(item.getQty()+itemCheck.getQty());
        							itemCheck.setPrice(itemCheck.getPrice()+item.getPrice());
        							total+=item.getPrice();
        							entered=true;
        							break;
        						}
        					}
        					if(!entered) {
        						itemList.add(new OrderItems(item.getQty(), item.getPrice()/item.getQty(), item.getItemName()));
        						total+=item.getPrice();
        					}
	        	        }
        			}
        		}
            	System.out.println("===========CZ2002 Cafe===========");
            	System.out.println("========Gross Revenue for========");
            	System.out.println("============="+currentDate.toLocalDate().getMonth()+"=============");
            	System.out.println("---------------------------------");
            	for (OrderItems item : itemList) {
                    System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                    System.out.println();
                }
            	System.out.println("_________________________________");
            	System.out.printf("%-27s %.2f","TOTAL GROSS REVENUE:", total);
            	System.out.println();
            	break;
        }
    }
}