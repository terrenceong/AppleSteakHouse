package RRPSS;

import java.util.ArrayList;
import java.util.List;


public class Order {
    private String orderDate;
    private String orderTime;
    private String orderid;
    private List<OrderItems> itemList;
    private double totalCost;
    private Staff s;
    private int table;
    private boolean isMembership;

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

    public Staff getS() {
		return s;
	}

	public void setS(Staff s) {
		this.s = s;
	}

	public boolean isMembership() {
		return isMembership;
	}

	public void setMembership(boolean isMembership) {
		this.isMembership = isMembership;
	}

	public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getOrderid() {
        return orderid;
    }

    public List<OrderItems> getItemList() {
        return itemList;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public int getTable() {
        return table;
    }

    public String getOrderTime() {
        return orderTime;
    }
}
