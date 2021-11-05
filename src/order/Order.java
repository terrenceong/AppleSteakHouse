package order;

import java.util.ArrayList;
import java.util.List;

import RRPSS.Staff;
/**
 * Represent a Order created in this restaurant.
 *
 * @author Terrence
 * @version 1.0
 * @since 2021-05-11
 */
public class Order {
	/**
	 * Order creation date in dd/MM/yyyy format.
	 */
    private String orderDate;
    /**
     * Order creation time in HH:mm format.
     */
    private String orderTime;
    /**
     * Order ID of this order.
     */
    private String orderid;
    /**
     * A list of items that this order contain.
     */
    private List<OrderItems> itemList;
    /**
     * Total cost of this order.
     */
    private double totalCost;
    /**
     * Reference of staff object that created this order.
     */
    private Staff s;
    /**
     * Table number for this order.
     */
    private int table;
    /**
     * True if customer is a member.
     * False if is non member.
     */
    private boolean isMembership;
   /**
    * Create order with staff object,date,time,orderID and table no.
    * @param s staff that created this order.
    * @param isMembership true if customer is a member.
    * @param date current date.
    * @param time current time.
    * @param ID auto generated ID
    * @param t table assigned to this order.
    */
    public Order(Staff s,boolean isMembership,String date,String time,String ID,int t) {
        itemList = new ArrayList<>();
        this.orderid = ID;
        this.orderDate  = date;
        this.isMembership = isMembership;
        this.table = t;
        this.s =s;
        this.orderTime =time;
    }
    
    public Order(Staff s,boolean isMembership,String date,String time,String ID,int t, List<OrderItems> itemList) {
        this.itemList = itemList;
        this.orderid = ID;
        this.orderDate  = date;
        this.isMembership = isMembership;
        this.table = t;
        this.s =s;
        this.orderTime =time;
    }
    /**
     * Gets reference of the staff object that created this order.
     * @return reference of staff object.
     */
    public Staff getS() {
		return s;
	}

    /**
     * If this is a membership order, discount of 5% will be applied.
     * @return membership details of this order.
     */
	public boolean isMembership() {
		return isMembership;
	}
	/**
	 * During order creation, this is use to set the membership.
	 * @param isMembership true/false
	 */
	public void setMembership(boolean isMembership) {
		this.isMembership = isMembership;
	}
	/**
	 * Update total cost of the order when needed.
	 * @param totalCost total cost of this order.
	 */
	public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
	/**
	 * Gets total cost of this order.
	 * @return total cost of this order.
	 */
    public double getTotalCost() {
        return totalCost;
    }
    /**
     * Gets order id of this order.
     * @return order id of this order.
     */
    public String getOrderid() {
        return orderid;
    }
    /**
     * Gets array list reference of order items that belongs to this order.
     * @return reference of order item list
     */
    public List<OrderItems> getItemList() {
        return itemList;
    }
    /**
     * Get order creation date.
     * @return order creation date.
     */
    public String getOrderDate() {
        return orderDate;
    }
    /**
     * Get table associate with this order(Dine in Orders).
     * @return table number of this order.
     */
    public int getTable() {
        return table;
    }
    /**
     * Get order creation time.
     * @return order creation time.
     */
    public String getOrderTime() {
        return orderTime;
    }
}
