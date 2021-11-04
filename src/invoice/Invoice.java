package invoice;

import java.util.List;

import RRPSS.Staff;
import order.OrderItems;

public class Invoice {
    private String orderid;
    private String orderDate;
    private String orderTime;
    private Staff s;
    private List<OrderItems> itemList;
    private double subTotalCost;
    private double serviceCharge;
    private double gst;
    private double discount;
    private double totalCost;

	public Invoice(String orderid, String orderDate, String orderTime, Staff s, List<OrderItems> itemList,
			double subTotalCost, double serviceCharge, double gst, double discount, double totalCost) {
		super();
		this.orderid = orderid;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.s = s;
		this.itemList = itemList;
		this.serviceCharge = serviceCharge;
		this.gst = gst;
		this.subTotalCost = subTotalCost;
		this.discount = discount;
		this.totalCost = totalCost;
	}

	public String getOrderid() {
		return orderid;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public Staff getS() {
		return s;
	}

	public List<OrderItems> getItemList() {
		return itemList;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public double getGst() {
		return gst;
	}

	public double getSubTotalCost() {
		return subTotalCost;
	}

	public double getDiscount() {
		return discount;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public void setS(Staff s) {
		this.s = s;
	}

	public void setItemList(List<OrderItems> itemList) {
		this.itemList = itemList;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public void setSubTotalCost(double subTotalCost) {
		this.subTotalCost = subTotalCost;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
    
}
