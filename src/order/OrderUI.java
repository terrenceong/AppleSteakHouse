package order;

import RRPSS.Staff;
import reservation.ReservationMgr;
import java.util.Scanner;

import static order.OrderMgr.*;
/**
 * Order user interface.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class OrderUI {
    static Scanner sc = new Scanner(System.in);
    /**
     * Display order options.
     * This method involve directing users to the right option within the class.
     * @param s reference of staff that is using this application
     */
    public static void showOrderOptions(Staff s) {
        boolean quit = false;
        do
        {
            System.out.println("======Order Options======");
            System.out.println("1 -> Create order");
            System.out.println("2 -> View order");
            System.out.println("3 -> Add/Remove order item/s to/from order ");
            System.out.println("4 -> Return to main");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            ReservationMgr.checkExpiredReservations();
            switch(choice)
            {
                case 1: createOrder(s);break;
                case 2: viewOrder();break;
                case 3: editOrders();break;
                default: quit = true;
            }
        } while(!quit);
    }
}
