package RRPSS;

import java.util.Scanner;

public class RRPSSApp {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
	// write your code here
        Staff s = new Staff();
        enterStaffInfo(s);
        printOption(s);
        boolean quit = false;
        while(!quit)
        {
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7: break;
                case 8: break;
                case 9: break;
                case 10:break;
                case 11:break;
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
        System.out.println("10 -> Print order invoice");
        System.out.println("11 -> Print sale revenue report by period");
        System.out.println("12 -> Quit");

    }
}
