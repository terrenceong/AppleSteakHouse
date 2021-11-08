package invoice;

import order.OrderItems;
import reservation.ReservationMgr;

import java.util.Scanner;

import static invoice.InvoiceMgr.*;
/**
 * Invoice user interface.
 * 
 * @author Russell
 * @version 1.0
 * @since 2021-08-11
 *
 */
public class InvoiceUI {
     static Scanner sc = new Scanner(System.in);
     /**
      * Display Invoice options.
      * This method involve directing users to the right option within the class.
      */
    public static void showInvoiceOptions() {
        boolean quit = false;
        do
        {
            System.out.println("======Invoice Options======");
            System.out.println("1 -> Print order invoice");
            System.out.println("2 -> View order invoices");
            System.out.println("3 -> Print sale revenue report by period");
            System.out.println("4 -> Return to main");
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

}
