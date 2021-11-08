package invoice;

import java.util.List;

import RRPSS.Staff;
import order.OrderItems;
/**
 * Represents an Invoice created in this restaurant.
 * 
 * @author Russell
 * @version 1.0
 * @since 2021-08-11
 *
 */
public class Invoice {
	/**
	 * Invoice id for this invoice.
	 */
    private String orderid;
	/**
	 * Order creation date in dd/MM/yyyy format.
	 */
    private String orderDate;
	/**
	 * Order creation time in HH:mm format.
	 */
    private String orderTime;
    /**
     * Reference of staff object that created the order.
     */
    private Staff s;
    /**
     * A list of items that this invoice contains.
     */
    private List<OrderItems> itemList;
	/**
	 * Invoice cost before additional charges or discounts.
	 */
    private double subTotalCost;
	/**
	 * Invoice service charge.
	 */
    private double serviceCharge;
	/**
	 * Invoice GST charge.
	 */
    private double gst;
	/**
	 * Invoice discount.
	 */
    private double discount;
	/**
	 * Invoice total end cost.
	 */
    private double totalCost;

    /**
     * Create Invoice with orderid,orderDate,orderTime,staff object,item list,subTotalCost,serviceCharge,gst,discount,totalCost
     * @param orderid is the invoice id for this invoice.
     * @param orderDate is the order creation date in dd/MM/yyyy format.
     * @param orderTime is the order creation time in HH:mm format.
     * @param s is the reference of staff object that created the order.
     * @param itemList is the list of items that this invoice contains.
     * @param subTotalCost is the invoice cost before additional charges or discounts.
     * @param serviceCharge is the invoice service charge.
     * @param gst is the invoice GST charge.
     * @param discount is the invoice discount.
     * @param totalCost is the invoice total end cost.
     */
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
	
	/**
     * Gets orderid of the invoice
     * @return orderid
	 */
	public String getOrderid() {
		return orderid;
	}

	/**
     * Gets orderDate of the invoice
     * @return orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
     * Gets orderTime of the invoice
     * @return orderTime of the invoice
	 */
	public String getOrderTime() {
		return orderTime;
	}

    /**
     * Gets reference of the staff object that created the order.
     * @return reference of staff object.
     */
	public Staff getS() {
		return s;
	}

    /**
     * Gets array list reference of order items that belongs to this order.
     * @return reference of order item list
     */
	public List<OrderItems> getItemList() {
		return itemList;
	}

    /**
     * Gets service charge of the invoice
     * @return service charge of the invoice
     */
	public double getServiceCharge() {
		return serviceCharge;
	}

    /**
     * Gets GST of the invoice
     * @return GST of the invoice
     */
	public double getGst() {
		return gst;
	}

    /**
     * Gets sub total of the invoice
     * @return sub total of the invoice
     */
	public double getSubTotalCost() {
		return subTotalCost;
	}

    /**
     * Gets discount of the invoice
     * @return discount of the invoice
     */
	public double getDiscount() {
		return discount;
	}

    /**
     * Gets total cost of the invoice
     * @return total cost of the invoice
     */
	public double getTotalCost() {
		return totalCost;
	}    
}
