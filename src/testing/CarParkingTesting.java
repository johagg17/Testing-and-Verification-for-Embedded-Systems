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

		assertTrue(position != null); // We need to fail in order to touch the production code
	}
	@Test
	public void testCarParkingIsEmpty() {
		CarParking car = new CarParking();
		boolean free_slot = car.isEmpty();
		//assertEquals("Nothing is implemented in isEmpty, so make this fail", 1, distance); // added this line to fail in order to touch the production code
		assertTrue(free_slot);

		//System.out.println("Distance to nearest parking-spot " + distance);
	}
	@Test
	public void testCarParkingMoveForward() {
		CarParking car = new CarParking();
		int[] pos = car.WhereIs();
		int [] result = car.MoveForward();
		assertTrue(pos[0] == result[0]);
		assertTrue(pos[1] == result[1] - 1);
		assertTrue(1 == result[2]);
		//assertTrue(car.isEmpty() == result[2]); This will fail because of the generation of the sensor-values.

		//assertTrue(true); // We stopped here, the implementation of the MoveForward methods is missing.
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
	public void testCarParkingPark() {
		CarParking car = new CarParking();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();

		boolean parking = car.Park();
		assertTrue(parking); // Stopped here, the implementation for Park is missing.
	}

	@Test //not looking if the amount of free spaces is correct only that we actually backed up 1 space
	public void testCarParkingMoveBackward() {
		CarParking car = new CarParking();
		car.MoveForward(); car.MoveForward();
		int [] prevPos = car.WhereIs();
		int[] currentPos = car.MoveBackward();
		assertTrue(prevPos[0] == currentPos[0]);
		assertTrue(prevPos[1] == currentPos[1] + 1);
		// assertTrue(currentPos[2] == car.isEmpty()); Will fail since isEmpty is random values.

	}




}
