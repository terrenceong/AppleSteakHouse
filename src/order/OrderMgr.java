package order;

import java.text.SimpleDateFormat;
import java.util.*;

import RRPSS.RRPSSApp;
import RRPSS.Staff;
import menu.*;
import reservation.ReservationMgr;
import reservation.Table;

import static order.OrderUI.sc;

/**
 * Control class of order options.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class OrderMgr {

    
    /**
     * Storing all current dining in order.
     */
    static List<Order> dineInOrderList = new ArrayList<>();
    /**
     * Storing all pending take away orders.
     */
    static List<Order> takeAwayOrderList = new ArrayList<>();
    /**
     * Log completed orders.
     */
    static List<Order> completedOrderList = new ArrayList<>();

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
    /**
     * Logic and flow to create order.
     * @param s reference of staff object that is creating this order.
     */
     static void createOrder(Staff s) {
        sc.nextLine();
        Random rdm = new Random();
        Date d = new Date();
        String orderID;
        int table = Integer.MAX_VALUE;
        System.out.print("Take away (Y/N):");
        char t = sc.nextLine().toLowerCase().charAt(0);
        if (t == 'n') {
            System.out.print("Enter table no:");
            table = sc.nextInt();
            sc.nextLine();
        }
        System.out.print("Membership Order(Y/N):");
        char a = sc.nextLine().toLowerCase().charAt(0);
        boolean mem = false;
        if (a == 'y')
            mem = true;
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        String dateTime = d.toString();
        String date = formatDate.format(d);
        String time = formatTime.format(d);
        if (t == 'n') {
            orderID = "D" + (rdm.nextInt(9999) + 10000 + rdm.nextInt(5));
            dineInOrderList.add(new Order(s, mem, date, time, orderID, table));
            ReservationMgr.getTableList().get(table-1).setAvailability("UNAVAILABLE");
        } else {
            orderID = "T" + (rdm.nextInt(9999) + 10000 + rdm.nextInt(5));
            takeAwayOrderList.add(new Order(s, mem, date, time, orderID, table));
        }
        System.out.println("Order ID #" + orderID + " created on " + dateTime + " by staffNo#" + s.getID());
    }
    /**
     * Display order information that currently is in the system.
     */
     static void viewOrder() {
        if (dineInOrderList.size() == 0 && takeAwayOrderList.size() == 0) {
            System.out.println("There are no orders right now");
            return;
        }
        if (dineInOrderList.size() == 1 && takeAwayOrderList.size() == 0) {
            Order o = dineInOrderList.get(0);
            System.out.println("==============Table " + o.getTable() + "==============");
            for (OrderItems item : o.getItemList()) {
                System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                System.out.println();
            }

        }
        else if (dineInOrderList.size() == 0 && takeAwayOrderList.size() == 1) {
            Order o = takeAwayOrderList.get(0);
            System.out.println("=========Take away Order " + o.getOrderid() + "=========");
            for (OrderItems item : o.getItemList()) {
                System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                System.out.println();
            }

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
                System.out.print("Enter Order ID to view order:");
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
                    System.out.println("=========Take away Order " + o.getOrderid() + "=========");
                    for (OrderItems item : o.getItemList()) {
                        System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                        System.out.println();
                    }
                    //System.out.println(o.getTotalCost());
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
                    System.out.println("==============Table " + o.getTable() + "==============");
                    for (OrderItems item : o.getItemList()) {
                        System.out.printf(item.getQty() + " %-25s %.2f", item.getItemName(), item.getPrice());
                        System.out.println();
                    }
                }
            }
        }
    }
    /**
     * Staff can customize order by adding new items, updating existing item
     * and remove item from order.
     */
     static void editOrders()
    {
        int index = -1;
        int x = 0;
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
        if (dineInOrderList.size() == 0 && takeAwayOrderList.size() == 0) {
            System.out.println("There are no orders right now");
            return;
        }
        sc.nextLine();
        while (index == -1) {
            System.out.print("Enter Order ID to add/remove items:");
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
                System.out.print("Select Add(A)/Remove(R) items for order #" + takeAwayOrderList.get(index).getOrderid() + ":");
                char c = sc.nextLine().toLowerCase().charAt(0);
                do {
                    x = 0;
                    if (c == 'a') {
                        System.out.print("Select: Promotional set(P), MainCourse(M), Sides(S), Drinks(D):");
                        char l = sc.nextLine().toLowerCase().charAt(0);
                        switch (l) {

                            case 'm':
                                for (MainCourse m : MenuMgr.getMainCoursesList())
                                    System.out.println(++x + ") " + m.getName() + " $" + m.getPrice());
                                System.out.print("Select your MainCourse:");
                                int m = sc.nextInt() - 1;
                                System.out.print("Enter qty:");
                                int q = sc.nextInt();
                                Order o = takeAwayOrderList.get(index);
                                o.setTotalCost(o.getTotalCost() + (MenuMgr.getMainCoursesList().get(m).getPrice() * q));
                                o.getItemList().add(new OrderItems(q, MenuMgr.getMainCoursesList().get(m).getPrice(),
                                        MenuMgr.getMainCoursesList().get(m).getName()));
                                break;
                            case 's':
                                for (Sides s : MenuMgr.getSidesList())
                                    System.out.println(++x + ") " + s.getName() + " $" + s.getPrice());
                                System.out.print("Select your Side:");
                                int s = sc.nextInt() - 1;
                                System.out.print("Enter qty:");
                                q = sc.nextInt();
                                o = takeAwayOrderList.get(index);
                                o.setTotalCost(o.getTotalCost() + (MenuMgr.getSidesList().get(s).getPrice() * q));
                                o.getItemList().add(new OrderItems(q, MenuMgr.getSidesList().get(s).getPrice(),
                                        MenuMgr.getSidesList().get(s).getName()));
                                break;
                            case 'd':
                                for (Drinks d : MenuMgr.getDrinksList())
                                    System.out.println(++x + ") " + d.getName() + " $" + d.getPrice());
                                System.out.print("Select your drink:");
                                int d = sc.nextInt() - 1;
                                System.out.print("Enter qty:");
                                q = sc.nextInt();
                                o = takeAwayOrderList.get(index);
                                o.setTotalCost(o.getTotalCost() + (MenuMgr.getDrinksList().get(d).getPrice() * q));
                                o.getItemList().add(new OrderItems(q, MenuMgr.getDrinksList().get(d).getPrice(),
                                        MenuMgr.getDrinksList().get(d).getName()));
                                break;
                            case 'p':
                                for (PromotionalSet p : MenuMgr.getPromotionalSetList()) {
                                    System.out.println(++x + ") " + p.getName() + " $" + p.getPrice());
                                    System.out.println(p.getDescription());
                                }
                                System.out.print("Select your promotional set:");
                                int p = sc.nextInt() - 1;
                                System.out.print("Enter qty:");
                                q = sc.nextInt();
                                o = takeAwayOrderList.get(index);
                                o.setTotalCost(o.getTotalCost() + (MenuMgr.getPromotionalSetList().get(p).getPrice() * q));
                                o.getItemList().add(new OrderItems(q, MenuMgr.getPromotionalSetList().get(p).getPrice(),
                                        MenuMgr.getPromotionalSetList().get(p).getName()));
                                break;

                        }
                        System.out.println("Successfully added");
                        sc.nextLine();
                    }
                    else if(c=='r')
                    {

                        int count =0;
                        int qtyRemove =1;
                        Order o = takeAwayOrderList.get(index);
                        if(o.getItemList().size()==0)
                        {
                            System.out.println("Order ID# " + o.getOrderid() + " is empty!");
                        }
                        else
                        {
                            for (OrderItems item : o.getItemList()) {
                                System.out.printf(++count + ") %s Qty: %d",item.getItemName(),item.getQty());
                                System.out.println();
                            }
                            System.out.print("Select item to be removed:");
                            int n = sc.nextInt()-1;
                            if(o.getItemList().get(n).getQty() > 1)
                            {
                                System.out.print("Enter number of qty to remove:");
                                 qtyRemove = sc.nextInt();
                                 while(qtyRemove > o.getItemList().get(n).getQty())
                                 {
                                     System.out.println("qty cannot be greater than " + o.getItemList().get(n).getQty());
                                     System.out.print("Enter number of qty to remove:");
                                     qtyRemove = sc.nextInt();
                                 }

                            }
                            if(qtyRemove==o.getItemList().get(n).getQty())
                            {
                                o.setTotalCost(o.getTotalCost()-o.getItemList().get(n).getPrice());
                                o.getItemList().remove(n);
                                System.out.println("Successfully removed");
                                sc.nextLine();
                            }
                            else
                            {
                                int getqty = o.getItemList().get(n).getQty();
                                double totalp = o.getItemList().get(n).getPrice();
                                o.getItemList().get(n).setQty(getqty-qtyRemove);
                                o.getItemList().get(n).setPrice(totalp-qtyRemove*(totalp/getqty));
                                o.setTotalCost(o.getTotalCost() - qtyRemove*(totalp/getqty));
                                System.out.println("Successfully removed");
                                sc.nextLine();
                            }

                        }


                    }
                    System.out.print("Select Add(A)/Remove(R)/Quit(Q) items for order #" + takeAwayOrderList.get(index).getOrderid() + ":");
                    c = sc.nextLine().toLowerCase().charAt(0);
                } while (c != 'q');
            } else {
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
                System.out.print("Select Add(A)/Remove(R) items for order #" + dineInOrderList.get(index).getOrderid() + ":");
                char c = sc.nextLine().toLowerCase().charAt(0);
                do {
                    x = 0;
                    if (c == 'a') {
                        System.out.print("Select: Promotional set(P), MainCourse(M), Sides(S), Drinks(D):");
                        char l = sc.nextLine().toLowerCase().charAt(0);
                        switch (l) {

                            case 'm':
                                for (MainCourse m : MenuMgr.getMainCoursesList())
                                    System.out.println(++x + ") " + m.getName() + " $" + m.getPrice());
                                System.out.print("Select your MainCourse:");
                                int m = sc.nextInt() - 1;
                                System.out.print("Enter qty:");
                                int q = sc.nextInt();
                                Order o = dineInOrderList.get(index);
                                o.setTotalCost(o.getTotalCost() + (MenuMgr.getMainCoursesList().get(m).getPrice() * q));
                                o.getItemList().add(new OrderItems(q, MenuMgr.getMainCoursesList().get(m).getPrice(),
                                        MenuMgr.getMainCoursesList().get(m).getName()));
                                break;
                            case 's':
                                for (Sides s : MenuMgr.getSidesList())
                                    System.out.println(++x + ") " + s.getName() + " $" + s.getPrice());
                                System.out.print("Select your Side:");
                                int s = sc.nextInt() - 1;
                                System.out.print("Enter qty:");
                                q = sc.nextInt();
                                o = dineInOrderList.get(index);
                                o.setTotalCost(o.getTotalCost() + (MenuMgr.getSidesList().get(s).getPrice() * q));
                                o.getItemList().add(new OrderItems(q, MenuMgr.getSidesList().get(s).getPrice(),
                                        MenuMgr.getSidesList().get(s).getName()));
                                break;
                            case 'd':
                                for (Drinks d : MenuMgr.getDrinksList())
                                    System.out.println(++x + ") " + d.getName() + " $" + d.getPrice());
                                System.out.print("Select your drink:");
                                int d = sc.nextInt() - 1;
                                System.out.print("Enter qty:");
                                q = sc.nextInt();
                                o = dineInOrderList.get(index);
                                o.setTotalCost(o.getTotalCost() + (MenuMgr.getDrinksList().get(d).getPrice() * q));
                                o.getItemList().add(new OrderItems(q, MenuMgr.getDrinksList().get(d).getPrice(),
                                        MenuMgr.getDrinksList().get(d).getName()));
                                break;
                            case 'p':
                                for (PromotionalSet p : MenuMgr.getPromotionalSetList()) {
                                    System.out.println(++x + ") " + p.getName() + " $" + p.getPrice());
                                    System.out.println(p.getDescription());
                                }
                                System.out.print("Select your promotional set:");
                                int p = sc.nextInt() - 1;
                                System.out.print("Enter qty:");
                                q = sc.nextInt();
                                o = dineInOrderList.get(index);
                                o.setTotalCost(o.getTotalCost() + (MenuMgr.getPromotionalSetList().get(p).getPrice() * q));
                                o.getItemList().add(new OrderItems(q, MenuMgr.getPromotionalSetList().get(p).getPrice(),
                                        MenuMgr.getPromotionalSetList().get(p).getName()));
                                break;

                        }
                        System.out.println("Successfully added!");
                        sc.nextLine();


                    }
                    else if(c=='r')
                    {
                        int count =0;
                        int qtyRemove =1;
                        Order o = dineInOrderList.get(index);
                        if(o.getItemList().size()==0)
                        {
                            System.out.println("Order ID# " + o.getOrderid() + " is empty!");
                        }
                        else
                        {
                            for (OrderItems item : o.getItemList()) {
                                System.out.printf(++count + ") %s Qty: %d",item.getItemName(),item.getQty());
                                System.out.println();
                            }
                            System.out.print("Select item to be removed:");
                            int n = sc.nextInt()-1;
                            if(o.getItemList().get(n).getQty() > 1)
                            {
                                System.out.print("Enter number of qty to remove:");
                                qtyRemove = sc.nextInt();
                                while(qtyRemove > o.getItemList().get(n).getQty())
                                {
                                    System.out.println("qty cannot be greater than " + o.getItemList().get(n).getQty());
                                    System.out.print("Enter number of qty to remove:");
                                    qtyRemove = sc.nextInt();
                                }

                            }
                            if(qtyRemove==o.getItemList().get(n).getQty())
                            {
                                o.setTotalCost(o.getTotalCost()-o.getItemList().get(n).getPrice());
                                o.getItemList().remove(n);
                                System.out.println("Successfully removed");
                                sc.nextLine();
                            }
                            else
                            {
                                int getqty = o.getItemList().get(n).getQty();
                                double totalp = o.getItemList().get(n).getPrice();
                                o.getItemList().get(n).setQty(getqty-qtyRemove);
                                o.getItemList().get(n).setPrice(totalp-qtyRemove*(totalp/getqty));
                                o.setTotalCost(o.getTotalCost() - qtyRemove*(totalp/getqty));
                                System.out.println("Successfully removed");
                                sc.nextLine();
                            }

                        }
                       // System.out.println("Successfully removed");
                        //sc.nextLine();
                    }

                    System.out.print("Select Add(A)/Remove(R)/Quit(Q) items for order #" + dineInOrderList.get(index).getOrderid() + ":");
                    c = sc.nextLine().toLowerCase().charAt(0);
                } while (c != 'q');

            }
        }
    }
     /**
      * Get a list of dine in orders created.
      * @return a list of dine in orders.
      */
    public static List<Order> getDineInOrderList() {
        return dineInOrderList;
    }
    /**
     * Get a list of take away orders created.
     * @return a list of take away orders.
     */
    public static List<Order> getTakeAwayOrderList() {
        return takeAwayOrderList;
    }
    /**
     * Get a list of completed orders
     * @return a list of completed orders.
     */
    public static List<Order> getCompletedOrderList() {
        return completedOrderList;
    }
}
