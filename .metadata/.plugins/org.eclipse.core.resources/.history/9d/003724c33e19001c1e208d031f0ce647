package testing;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import productioncode.CarParking;

public class CarParkingTesting {
	
	@Test
	public void testCarParkingWhereIs() {
		CarParking car = new CarParking(); // Create new car object
		int[] new_pos = car.MoveForward();
		int [] position = car.WhereIs();
		

		assertTrue(new_pos[0] == position[0]); // test x-pos
		assertTrue(new_pos[1] == position[1]); // test y-pos
		assertTrue(new_pos[2] == position[2]); // test freespace
		
	}
	
	@Test
	public void testCarParkingIsEmpty() {
		CarParking car = new CarParking();
		int sensorval = 80; 
		boolean free_slot = car.isEmpty(sensorval);
		assertTrue(free_slot);

	}
	@Test
	public void testCarParkingIsNotEmpty() {
		CarParking car = new CarParking();
		int sensorval = 200; 
		boolean free_slot = car.isEmpty(sensorval);
		assertFalse(free_slot);
	}
	
	@Test
	public void testCarParkingMoveForward() {
		CarParking car = new CarParking();
		int[] pos = car.WhereIs();
		int [] result = car.MoveForward();
		assertTrue(pos[0] == result[0]);
		assertTrue(pos[1] == result[1] - 1);
		assertTrue(result[2] == 0); // Since we have only moved forward ones this should return 0.
		
	}
	@Test
	public void testCarParkingMoveForwardFiveTimes() { // Testing to moveForward 5 times, 
		CarParking car = new CarParking();
		int[] pos = car.WhereIs();
		int [] result = new int[3];
		for (int i=0; i<5; i++) {result = car.MoveForward();}
		assertTrue(result[1] == pos[1] + 5);
		assertTrue(result[0] == pos[0]);
		assertTrue(result[2] == pos[2] + 5);
		
	}
	@Test 
	public void testCarParkingMoveForwardOutOfBounds() {
		CarParking car = new CarParking();
		for (int i=0; i<500; i++) {car.MoveForward();}
		//System.out.println("Loop is done");
		int[] newpos = car.MoveForward();
		// System.out.println(newpos[1]);
		assertTrue(newpos[1] == 500); 
		
	}
	@Test
	public void testCatParkingUnParkWhenNotParked() {
		CarParking car = new CarParking();
		int [] prev_pos = car.WhereIs();
		car.UnPark();
		int [] new_pos = car.WhereIs();
		assertTrue(prev_pos[0] == new_pos[0]); // test x
		assertTrue(prev_pos[1] == new_pos[1]); // test y 
		
		
	}
	
	@Test
	public void testCarParkingUnPark() {
		CarParking car = new CarParking();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.Park();
		int [] previousPos = car.WhereIs();
		car.UnPark();
		int [] currentPos = car.WhereIs();

		assertTrue(currentPos[0] == previousPos[0] - 1);
		assertTrue(currentPos[1] == previousPos[1] + 1);

	}
	
	@Test
	public void testCarParkingTryParkWhenThereIsNoSpaceAvailable () {
		CarParking car = new CarParking();
		int[] prev_pos = car.WhereIs(); 
		car.Park();
		int[] new_pos = car.WhereIs(); 
		assertTrue(prev_pos[0] == new_pos[0]); // x
		assertTrue(prev_pos[1] == new_pos[1]); // y
		assertTrue(prev_pos[2] == new_pos[2]); // freespace
	
	}
	@Test 
	public void testCarParkingParkWhenAlreadyParked() {
		CarParking car = new CarParking();
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
	public void testCarParkingPark() {
		CarParking car = new CarParking();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();

		boolean parking = car.Park();
		assertTrue(parking); 
	}
	
	@Test
	public void testCarParkingMoveBackWardAtStart() {
		CarParking car = new CarParking();
		int [] prev_pos = car.WhereIs();
		int[] pos = car.MoveBackward();
		assertTrue(pos[0] == prev_pos[0]); // x
		assertTrue(pos[1] == prev_pos[1]); // y
		assertTrue(pos[2] == prev_pos[2]); // freespace
		
	}

	@Test //not looking if the amount of free spaces is correct only that we actually backed up 1 space
	public void testCarParkingMoveBackward() {
		CarParking car = new CarParking();
		car.MoveForward(); 
		car.MoveForward();
		int [] prevPos = car.WhereIs();
		int[] currentPos = car.MoveBackward();
		assertTrue(prevPos[0] == currentPos[0]);
		assertTrue(prevPos[1] == currentPos[1] + 1);

	}
	
	@Test
	public void testCarParkingMoveBackwardWhenParked() {
		CarParking car = new CarParking();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();

		assertTrue(car.Park());
		int [] prevPos = car.WhereIs();
		int [] newPos = car.MoveBackward();
		
		assertTrue(prevPos[0] == newPos[0]);
		assertTrue(prevPos[1] == newPos[1]);
	}




}
