package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import productioncode.CarParking;
import productioncode.CarParkingView;

public class CarParkingTesting {
	
	/* 
	 * All the tests below is for testing the methods within the CarParking.java file. 
	 * 
	 * */
	CarParkingView view;
	@Before
	public void setUp() {
		view = new CarParkingView();
	}
	@Test
	public void testCarParkingWhereIs() { // test for WhereIs
		CarParking car = new CarParking(view); 
		int [] position = car.WhereIs();
		assertTrue(0 == position[0]); 
		assertTrue(0 == position[1]); 
	}

	@Test
	public void testCarParkingWhereIsAfterMoving() throws Exception { // test for WhereIs
		CarParking car = new CarParking(view); 
		int[] new_pos = car.MoveForward();
		int [] position = car.WhereIs();

		assertTrue(new_pos[0] == position[0]); 
		assertTrue(new_pos[1] == position[1]); 
		assertTrue(new_pos[2] == position[2]); 
	}

	@Test
	public void testCarParkingIsEmpty1() { // test for IsEmpty
		CarParking car = new CarParking(view);
		boolean free_slot = car.isEmpty();
		assertTrue(free_slot);

	}
	@Test
	public void testCarParkingIsNotEmptyTest2() { // test for IsEmpty
		CarParking car = new CarParking(view);
		car.sensor1 = 200;
		boolean free_slot = car.isEmpty(); 
		assertFalse(free_slot);
	}
	@Test 
	public void testCarParkingIsNotEmptyTest3() { // test for IsEmpty
		CarParking car = new CarParking(view);
		car.sensor2 = 200;
		boolean free_slot = car.isEmpty(); 
		assertFalse(free_slot);
		
	}
	@Test 
	public void testCarParkingIsNotEmptyTest4() { // test for IsEmpty
		CarParking car = new CarParking(view);
		car.sensor1 = 200;
		car.sensor2 = 200;
		boolean free_slot = car.isEmpty(); 
		assertFalse(free_slot);	
	}
	
	@Test
	public void testCarParkingMoveForward() throws Exception { // test for MoveForward
		CarParking car = new CarParking(view);
		int[] pos = car.WhereIs();
		int [] result = car.MoveForward(); 
		assertTrue(pos[0] == result[0]);
		assertTrue(pos[1] == result[1] - 1);
		assertTrue(result[2] == 1); 

	}
	
	@Test
	public void testCarParkingMoveForwardAndDetectOccupiedSlot() throws Exception { // test for MoveForward
		CarParking car = new CarParking(view);
		car.MoveForward(); car.MoveForward(); car.MoveForward(); 
		car.sensor1 = 200; 
		car.MoveForward(); 
		car.sensor1 = 80;
		int [] pos = car.MoveBackward();
		assertTrue(pos[2] == 3);
		
		
	}
	@Test
	public void testCarParkingMoveForwardFiveTimes() throws Exception { // test for MoveForward
		CarParking car = new CarParking(view);
		int[] pos = car.WhereIs();
		int [] result = new int[3];
		for (int i=0; i<5; i++) {result = car.MoveForward();}
		assertTrue(result[1] == pos[1] + 5);
		assertTrue(result[0] == pos[0]);
		assertTrue(result[2] == pos[2] + 5);

	}
	@Test(expected = Exception.class) 
	public void testCarParkingMoveForwardWhenParked() throws Exception { // test for MoveForward
		CarParking car = new CarParking(view);
		for(int i=0; i<5; i++) car.MoveForward();
		boolean park = car.Park();
		assertTrue(park);
		car.MoveForward();
	}
	@Test(expected = Exception.class)
	public void testCarParkingMoveForwardOutOfBounds() throws Exception{ // test for MoveForward
		CarParking car = new CarParking(view);
		for (int i=0; i<=500; i++) {car.MoveForward();}
	}
	
	@Test
	public void testCarParkingUnParkWhenNotParked() { // test for UnPark
		CarParking car = new CarParking(view);
		int y = car.WhereIs()[1];
		boolean ableToUnPark = car.UnPark();
		assertFalse(ableToUnPark); 
		assertTrue(y == car.WhereIs()[1]);
		
	}

	@Test
	public void testCarParkingUnParkWhenParked() throws Exception { // test for UnPark
		CarParking car = new CarParking(view);
		boolean parked = car.Park();
		int y = car.WhereIs()[1];
		boolean unparked = car.UnPark();

		assertTrue(parked);
		assertTrue(unparked);
		assertTrue(y + 1 == car.WhereIs()[1]); 

	}

	@Test
	public void testCarParkingParkWhenAlreadyParked() throws Exception { // test for Park
		CarParking car = new CarParking(view);
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();

		car.Park();
		boolean parkAgain = car.Park();
		assertFalse(parkAgain);

	}
	@Test
	public void testCarParkingParkWhenEmptySlotsAreAvailable() { // test for Park
		CarParking car = new CarParking(view);
		boolean parked = car.Park();
		assertTrue(parked);
		
	}
	@Test
	public void testCarParkingParkWhenEmptySlotsAreNotAvailable() { // test for Park
		CarParking car = new CarParking(view);
		car.sensor1 = 200;
		boolean parked = car.Park();
		assertFalse(parked);
		int [] status = car.WhereIs();
		assertTrue(status[0] == 0);
		assertTrue(status[1] == 500);	
	}
	

	@Test(expected = Exception.class)
	public void testCarParkingMoveBackWardAtStart() throws Exception { // test for MoveBackward
		CarParking car = new CarParking(view);
		car.MoveBackward();
	}

	@Test 
	public void testCarParkingMoveBackwardOnce() throws Exception { // test for MoveBackward
		CarParking car = new CarParking(view);
		car.MoveForward();
		car.MoveForward();
		int [] prevPos = car.WhereIs();
		int[] currentPos = car.MoveBackward();
		assertTrue(prevPos[0] == currentPos[0]);
		assertTrue(prevPos[1] == currentPos[1] + 1);

	}

	@Test(expected = Exception.class)
	public void testCarParkingMoveBackwardWhenParked() throws Exception { // test for MoveBackward
		CarParking car = new CarParking(view);
		assertTrue(car.Park());
		car.MoveBackward();
	}




}
