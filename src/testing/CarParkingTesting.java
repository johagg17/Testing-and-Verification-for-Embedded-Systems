package testing;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import productioncode.CarParking;

public class CarParkingTesting {

	@Test
	public void testCarParkingWhereIs() {
		CarParking car = new CarParking(); // Create new car object
		int [] position = car.WhereIs();
		assertTrue(0 == position[0]); // test x-pos
		assertTrue(0 == position[1]); // test y-pos
	}

	@Test
	public void testCarParkingWhereIsAfterMoving() throws Exception {
		CarParking car = new CarParking(); // Create new car object
		int[] new_pos = car.MoveForward();
		int [] position = car.WhereIs();

		assertTrue(new_pos[0] == position[0]); // test x-pos
		assertTrue(new_pos[1] == position[1]); // test y-pos
		assertTrue(new_pos[2] == position[2]); // test freespace
	}

	@Test
	public void testCarParkingIsEmpty() { // sensor1 and sensor2 both gives true
		CarParking car = new CarParking();
		boolean free_slot = car.isEmpty(); // both sensors indicates on free slot
		assertTrue(free_slot);

	}
	@Test
	public void testCarParkingIsNotEmptyTest1() { // sensor1 = false, sensor2 = true
		CarParking car = new CarParking();
		car.sensor1 = 200;
		boolean free_slot = car.isEmpty(); // the first sensors tells that there is not empty slot in current y-pos
		assertFalse(free_slot);
	}
	@Test 
	public void testCarParkingIsNotEmptyTest2() { // sensor1 = true, sensor2 = false
		CarParking car = new CarParking();
		car.sensor2 = 200;
		boolean free_slot = car.isEmpty(); 
		assertFalse(free_slot);
		
	}
	@Test 
	public void testCarParkingIsNotEmptyTest3() { // sensor1 = false, sensor2 = false
		CarParking car = new CarParking();
		car.sensor1 = 200;
		car.sensor2 = 200;
		boolean free_slot = car.isEmpty(); 
		assertFalse(free_slot);	
	}
	
	@Test
	public void testCarParkingMoveForward() throws Exception {
		CarParking car = new CarParking();
		int[] pos = car.WhereIs();
		int [] result = car.MoveForward(); 
		assertTrue(pos[0] == result[0]);
		assertTrue(pos[1] == result[1] - 1);
		assertTrue(result[2] == 1); // Since we have only moved forward ones this should return 1

	}
	
	@Test
	public void testCarParkingMoveForwardAndDetectOccupiedSlot() throws Exception {
		CarParking car = new CarParking();
		car.MoveForward(); car.MoveForward(); car.MoveForward(); // 3 empty slots
		car.sensor1 = 200; // set sensor value to no empty slot
		car.MoveForward(); // this.freespace = 0
		car.sensor1 = 80;
		int [] pos = car.MoveBackward(); // this.freespace = 3
		assertTrue(pos[2] == 3);
		
		
	}
	@Test
	public void testCarParkingMoveForwardFiveTimes() throws Exception { // Testing to moveForward 5 times,
		CarParking car = new CarParking();
		int[] pos = car.WhereIs();
		int [] result = new int[3];
		for (int i=0; i<5; i++) {result = car.MoveForward();}
		assertTrue(result[1] == pos[1] + 5);
		assertTrue(result[0] == pos[0]);
		assertTrue(result[2] == pos[2] + 5);

	}
	@Test(expected = Exception.class)
	public void testCarParkingMoveForwardOutOfBounds() throws Exception{
		CarParking car = new CarParking();
		for (int i=0; i<=500; i++) {car.MoveForward();}
	}
	
	@Test
	public void testCatParkingUnParkWhenNotParked() {
		CarParking car = new CarParking();
		int y = car.WhereIs()[1];
		boolean ableToUnPark = car.UnPark();
		assertFalse(ableToUnPark); 
		assertTrue(y == car.WhereIs()[1]);
		
	}

	@Test
	public void testCarParkingUnPark() throws Exception {
		CarParking car = new CarParking();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		int y = car.WhereIs()[1];
		boolean parked = car.Park();
		boolean unparked = car.UnPark();

		assertTrue(parked);
		assertTrue(unparked);
		assertTrue(y + 1 == car.WhereIs()[1]); 

	}

	@Test
	public void testCarParkingTryParkWhenThereIsNoSpaceAvailable () {
		CarParking car = new CarParking();
		boolean p = car.Park();
		assertFalse(p);
		

	}
	@Test
	public void testCarParkingParkWhenAlreadyParked() throws Exception {
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
	public void testCarParkingPark() throws Exception {
		CarParking car = new CarParking();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();

		boolean parking = car.Park();
		assertTrue(parking);
	}

	@Test(expected = Exception.class)
	public void testCarParkingMoveBackWardAtStart() throws Exception {
		CarParking car = new CarParking();
		int [] prev_pos = car.WhereIs();
		car.MoveBackward();
	}

	@Test //not looking if the amount of free spaces is correct only that we actually backed up 1 space
	public void testCarParkingMoveBackward() throws Exception {
		CarParking car = new CarParking();
		car.MoveForward();
		car.MoveForward();
		int [] prevPos = car.WhereIs();
		int[] currentPos = car.MoveBackward();
		assertTrue(prevPos[0] == currentPos[0]);
		assertTrue(prevPos[1] == currentPos[1] + 1);

	}

	@Test(expected = Exception.class)
	public void testCarParkingMoveBackwardWhenParked() throws Exception {
		CarParking car = new CarParking();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();

		assertTrue(car.Park());
		car.MoveBackward();
	}




}
