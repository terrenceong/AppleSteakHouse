package Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import menu.*;
import RRPSS.RRPSSApp;
class TestMenu {

	private static List<MainCourse> testmain;
	private static List<Drinks> testdrink;
	private static List<Sides> testsides;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testmain = RRPSSApp.mainCoursesList;
		testdrink =RRPSSApp.drinksList;
		testsides =RRPSSApp.sidesList;
	}

	@Test
	void test() {
		
		System.out.println("Test case 1: MainCourse");
		testmain.add(new MainCourse("test1",10.0,"test1description"));
		String name = testmain.get(testmain.size()-1).getName();
		Double price = testmain.get(testmain.size()-1).getPrice();
		String description = testmain.get(testmain.size()-1).getDescription();
		assertEquals("test1", name);
		assertEquals(10.0, price);
		assertEquals("test1description",description);
		
		System.out.println("Test case 2: Drinks");
		testdrink.add(new Drinks("test2",10.0,"test2description"));
	     name = testdrink.get(testdrink.size()-1).getName();
		 price = testdrink.get(testdrink.size()-1).getPrice();
		 description = testdrink.get(testdrink.size()-1).getDescription();
		assertEquals("test2", name);
		assertEquals(10.0, price);
		assertEquals("test2description",description);
		
		System.out.println("Test case 3: Sides");
		testsides.add(new Sides("test3",10.0,"test3description"));
	     name = testsides.get(testsides.size()-1).getName();
		 price = testsides.get(testsides.size()-1).getPrice();
		 description = testsides.get(testsides.size()-1).getDescription();
		assertEquals("test3", name);
		assertEquals(10.0, price);
		assertEquals("test3description",description);
		
	}

}
