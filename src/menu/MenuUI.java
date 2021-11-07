package menu;

import reservation.ReservationMgr;

import java.util.Scanner;
import menu.MenuMgr.*;

import static menu.MenuMgr.*;
/**
 * Menu user interface.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class MenuUI {
     static Scanner sc = new Scanner(System.in);
    /**
     * Display menu options.
     * This method involve directing users to the right option within the class.
     */
    public static void showMenuOptions() {
        boolean quit = false;
        do
        {
            System.out.println("======Menu Options======");
            System.out.println("1 -> Print restaurant menu item");
            System.out.println("2 -> Create/Update/Remove menu item");
            System.out.println("3 -> Create/Update/Remove promotion");
            System.out.println("4 -> Return to main");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            ReservationMgr.checkExpiredReservations();
            switch(choice)
            {
                case 1: printFoodMenu();break;
                case 2: editFoodMenu();break;
                case 3: editPromotionalSet();break;
                default: quit = true;
            }
        } while(!quit);
    }
    /**
     * Prints the entire restaurant menu.
     */
    static void printFoodMenu()
    {
        int i =0;
        System.out.println("APPLE STEAK HOUSE's MENU");
        System.out.println("=======Promotional Set=======");
        for (PromotionalSet p : promotionalSetList)
            System.out.println(++i + ") Set " + p.getName() + ": $" + p.getPrice() + "\n" + p.getDescription());
        System.out.println("=======Main Course=======");
        for (MainCourse m : mainCoursesList)
            System.out.println(++i + ") " + m.getName() + ": $" + m.getPrice() + "\n" + m.getDescription());
        System.out.println("=======Sides=======");
        for (Sides s : sidesList)
            System.out.println(++i + ") " + s.getName() + ": $" + s.getPrice() + "\n" + s.getDescription());
        System.out.println("=======Drinks=======");
        for (Drinks d : drinksList)
            System.out.println(++i + ") " + d.getName() + ": $" + d.getPrice() + "\n" + d.getDescription());
        sc.nextLine();
    }

}
