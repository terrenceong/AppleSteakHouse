package reservation;

import java.util.Scanner;

import static reservation.ReservationMgr.*;

public class ReservationUI {
     static Scanner sc = new Scanner(System.in);

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
    static void printTables()
    {
        int i =1;
        System.out.println("=======Available Tables=======");
        for(Table t:tableList) {
            System.out.print("Table "+ i++ +": ");
            System.out.println(t.getAvailability());
        }
    }
}
