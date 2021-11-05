package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import RRPSS.*;
import order.*;
import reservation.*;
class TestOrder {

	private static List<Order> testdineinOrder;
	//private static List<Order> testtakeawayOrder;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testdineinOrder = RRPSS.RRPSSApp.dineInOrderList;
		//testtakeawayOrder = RRPSS.RRPSSApp.takeAwayOrderList;
	}

	@Test
	void test() {
		System.out.println("Test Dine In Order");
		//create Order
		testdineinOrder.add(new Order(new Staff(),true,"31/10/2021","15:21","D1235",5));
		testdineinOrder.get(0).getItemList().add(new OrderItems(2,15.5,"fish & chip"));
		testdineinOrder.get(0).getItemList().add(new OrderItems(2,5,"coke"));
		testdineinOrder.get(0).getItemList().add(new OrderItems(1,20,"cheese platter"));
		reservation.Table table = new Table(5,6);
		table.setAvailability("UNAVAILABLE");
		assertEquals(31,testdineinOrder.get(0).getItemList().get(0).getPrice());
		assertEquals(10,testdineinOrder.get(0).getItemList().get(1).getPrice());
		assertEquals(20,testdineinOrder.get(0).getItemList().get(2).getPrice());
		assertEquals("UNAVAILABLE",table.getAvailability());





	}

}