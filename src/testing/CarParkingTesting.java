package testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import productioncode.CarParking;
import productioncode.CarParkingView;
import productioncode.MockCarActuator;
import productioncode.MockCarSensor;

public class CarParkingTesting {
	
	/* 
	 * All the tests below is for testing the methods within the CarParking.java file. 
	 * 
	 * */
	CarParkingView view;
	MockCarActuator act;
	MockCarSensor sens;
	
	@Before
	public void setUp() {
		view = new CarParkingView(); 
		act = mock(MockCarActuator.class);
		sens = mock(MockCarSensor.class);
		
	}
	@Test
	public void testCarParkingWhereIs() { // test for WhereIs
		CarParking car = new CarParking(sens, act, view); 
		int [] position = car.WhereIs();
		assertTrue(0 == position[0]); 
		assertTrue(0 == position[1]); 
		
		reset(sens, act);
	}

	@Test
	public void testCarParkingWhereIsAfterMoving() throws Exception { // test for WhereIs
		// Init mock returns
		when(sens.getValues(1)).thenReturn(new int[] {100, 100});
		
		
		CarParking car = new CarParking(sens, act, view); 
		int[] new_pos = car.MoveForward();
		int [] position = car.WhereIs();

		assertTrue(new_pos[0] == position[0]); 
		assertTrue(new_pos[1] == position[1]); 
		assertTrue(new_pos[2] == position[2]); 
		
		reset(sens, act);
	}

	@Test
	public void testCarParkingIsEmpty1() { // test for IsEmpty
		
		// Init mocks
		when(sens.getValues(0)).thenReturn(new int[] {50, 50}); 
		
		CarParking car = new CarParking(sens, act, view);
		boolean free_slot = car.isEmpty();
		assertTrue(free_slot);
		
		reset(sens, act);

	}
	@Test
	public void testCarParkingIsNotEmptyTest2() { // test for IsEmpty
		
		// Init mocks
		
		when(sens.getValues(0)).thenReturn(new int[] {200, 100});
		
		CarParking car = new CarParking(sens, act, view);
		boolean free_slot = car.isEmpty(); 
		assertFalse(free_slot);
		
		
		reset(sens, act);
	}
	@Test 
	public void testCarParkingIsNotEmptyTest3() { // test for IsEmpty
		
		// Init mocks
		when(sens.getValues(0)).thenReturn(new int[] {100, 200});
		
		CarParking car = new CarParking(sens, act, view);
		boolean free_slot = car.isEmpty(); 
		assertFalse(free_slot);
		
		
		reset(sens, act);
		
	}
	@Test 
	public void testCarParkingIsNotEmptyTest4() { // test for IsEmpty
		// Init mocks
		when(sens.getValues(0)).thenReturn(new int[] {500, 500});
		
		CarParking car = new CarParking(sens, act, view);
		boolean free_slot = car.isEmpty(); 
		assertFalse(free_slot);	
		
		
		reset(sens, act);
	}
	@Test 
	public void testCarParkingIsEmptySensor1brokenSensor2WorksFine() { // Test for IsEmpty
		
		// Init mocks
		when(sens.getValues(0)).thenReturn(new int[] {-1, 150});
		CarParking car = new CarParking(sens, act, view);
		boolean empty = car.isEmpty();
		
		assertTrue(empty);
		
		reset(sens, act);
		
	}
	@Test
	public void testCarParkingIsEmptySensor1brokenSensor2GivesNonEmptySlot() { // Test for IsEmpty
		
		// Init mocks
		when(sens.getValues(0)).thenReturn(new int[] {-1, 160});
		CarParking car = new CarParking(sens, act, view);
		boolean empty = car.isEmpty();
				
		assertFalse(empty);
		reset(sens, act);
		
	}
	@Test 
	public void testCarParkingIsEmptySensor2brokenSensor1WorksFine () { // Test for IsEmpty
		
		// Init mocks
		when(sens.getValues(0)).thenReturn(new int[] {100, -1});
		CarParking car = new CarParking(sens, act, view);
		boolean empty = car.isEmpty();
				
		assertTrue(empty);
				
		reset(sens, act);
		
	}
	@Test
	public void testCarParkingIsEmptySensor2brokenSensor1GivesNonEmptySlot() { // Test for IsEmpty
		
		// Init mocks
		when(sens.getValues(0)).thenReturn(new int[] {160, -1});
		CarParking car = new CarParking(sens, act, view);
		boolean empty = car.isEmpty();
				
		assertFalse(empty);
		reset(sens, act);
		
	}
	
	@Test
	public void testCarParkingMoveForward() throws Exception { // test for MoveForward
		// Init mock
		when(sens.getValues(1)).thenReturn(new int[] {100, 100});
		
		CarParking car = new CarParking(sens, act, view);
		int[] pos = car.WhereIs();
		int [] result = car.MoveForward(); 
		assertTrue(pos[0] == result[0]);
		assertTrue(pos[1] == result[1] - 1);
		assertTrue(result[2] == 1); 
		
		
		reset(sens, act);

	}
	
	@Test
	public void testCarParkingMoveForwardAndDetectOccupiedSlot() throws Exception { // test for MoveForward
		
		// Init mocks for sensor service.
		
		when(sens.getValues(1)).thenReturn(new int[] {100, 100});
		when(sens.getValues(2)).thenReturn(new int[] {100, 100});
		when(sens.getValues(3)).thenReturn(new int[] {100, 100});
		when(sens.getValues(4)).thenReturn(new int[] {200, 200});
		
		
		CarParking car = new CarParking(sens, act, view);
		car.MoveForward(); car.MoveForward(); car.MoveForward(); 
		car.MoveForward(); 
		int [] pos = car.MoveBackward();
		assertTrue(pos[2] == 3);
		
		reset(sens, act);
	}
	@Test
	public void testCarParkingMoveForwardFiveTimes() throws Exception { // test for MoveForward
		
		for(int i=1; i<=5; i++) when(sens.getValues(i)).thenReturn(new int[] {100, 100});
		CarParking car = new CarParking(sens, act, view);
		int[] pos = car.WhereIs();
		int [] result = new int[3];
		for (int i=0; i<5; i++) {result = car.MoveForward();}
		assertTrue(result[1] == pos[1] + 5);
		assertTrue(result[0] == pos[0]);
		assertTrue(result[2] == pos[2] + 5);
		
		
		reset(sens, act);

	}
	@Test(expected = Exception.class) 
	public void testCarParkingMoveForwardWhenParked() throws Exception { // test for MoveForward
		CarParking car = new CarParking(sens, act, view);
		for(int i=0; i<5; i++) car.MoveForward();
		boolean park = car.Park();
		assertTrue(park);
		car.MoveForward();
	}
	@Test(expected = Exception.class)
	public void testCarParkingMoveForwardOutOfBounds() throws Exception{ // test for MoveForward
		
		for(int i=1; i<=500; i++) when(sens.getValues(i)).thenReturn(new int[] {200, 200});
		CarParking car = new CarParking(sens, act, view); 
		for (int i=0; i<=500; i++) {car.MoveForward();} 
	} 
	
	@Test
	public void testCarParkingUnParkWhenNotParked() { // test for UnPark
		CarParking car = new CarParking(sens, act, view);
		int y = car.WhereIs()[1];
		boolean ableToUnPark = car.UnPark();
		assertFalse(ableToUnPark); 
		assertTrue(y == car.WhereIs()[1]);
		
		
		reset(sens, act);
		
	}

	@Test
	public void testCarParkingUnParkWhenParked() throws Exception { // test for UnPark
		
		// Init mock
		for(int i=1; i<30; i++) {
			if (i >= 20 && i <= 25) {
				when(sens.getValues(i)).thenReturn(new int[] {100, 100});
			}
			else {
				when(sens.getValues(i)).thenReturn(new int[] {200, 200});
				
			}
		}
		
		CarParking car = new CarParking(sens, act, view); 
		boolean parked = car.Park();
		int y = car.WhereIs()[1];
		boolean unparked = car.UnPark();

		assertTrue(parked);
		assertTrue(unparked);
		assertTrue(y + 1 == car.WhereIs()[1]); 
		
		
		reset(sens, act);

	}

	@Test
	public void testCarParkingParkWhenAlreadyParked() throws Exception { // test for Park
		// Init mocks
		for(int i=1; i<=5; i++) {
			when(sens.getValues(i)).thenReturn(new int[] {100, 100});
		}
		
		CarParking car = new CarParking(sens, act, view);
		
		
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();
		car.MoveForward();

		car.Park();
		boolean parkAgain = car.Park();
		assertFalse(parkAgain);
		
		reset(sens, act);

	}
	@Test
	public void testCarParkingParkWhenEmptySlotsAreAvailable() { // test for Park
		// Init mocks
		
		when(sens.getValues(1)).thenReturn(new int[] {100, 100}); 
		when(sens.getValues(2)).thenReturn(new int[] {100, 100});
		when(sens.getValues(3)).thenReturn(new int[] {100, 100});
		when(sens.getValues(4)).thenReturn(new int[] {100, 100});
		when(sens.getValues(5)).thenReturn(new int[] {100, 100});
		
		CarParking car = new CarParking(sens, act, view);
		boolean parked = car.Park();
		assertTrue(parked);
		
		
		reset(sens, act); 
		
	} 
	@Test
	public void testCarParkingParkWhenEmptySlotsAreNotAvailable() { // test for Park
		for(int i=1; i<=500; i++) when(sens.getValues(i)).thenReturn(new int[] {200, 200});
		CarParking car = new CarParking(sens, act, view);
		boolean parked = car.Park();
		assertFalse(parked);
		int [] status = car.WhereIs();
		assertTrue(status[0] == 0);
		assertTrue(status[1] == 500);	 
		
		 
		reset(sens, act);
	}
	

	@Test(expected = Exception.class)
	public void testCarParkingMoveBackWardAtStart() throws Exception { // test for MoveBackward
		CarParking car = new CarParking(sens, act, view);
		car.MoveBackward();
		
		
		reset(sens, act);
	}

	@Test 
	public void testCarParkingMoveBackwardOnce() throws Exception { // test for MoveBackward
		
		// Init mocks
		for(int i=1; i<=2; i++) when(sens.getValues(i)).thenReturn(new int[] {100, 100});
		
		CarParking car = new CarParking(sens, act, view);
		car.MoveForward();
		car.MoveForward();
		int [] prevPos = car.WhereIs();
		int[] currentPos = car.MoveBackward();
		assertTrue(prevPos[0] == currentPos[0]);
		assertTrue(prevPos[1] == currentPos[1] + 1);
		
		
		reset(sens, act);

	}

	@Test(expected = Exception.class)
	public void testCarParkingMoveBackwardWhenParked() throws Exception { // test for MoveBackward
		// Init mock
		when(sens.getValues(1)).thenReturn(new int[] {100, 100});
		when(sens.getValues(2)).thenReturn(new int[] {100, 100});
		when(sens.getValues(3)).thenReturn(new int[] {100, 100});
		when(sens.getValues(4)).thenReturn(new int[] {100, 100});
		when(sens.getValues(5)).thenReturn(new int[] {100, 100});
		
		CarParking car = new CarParking(sens, act, view);
		assertTrue(car.Park());
		car.MoveBackward();
		
		
		reset(sens, act);
	}
	
	@Test
	public void testCarParkinggetAvailableSlots() { // Test for getAvailableSlots
		
		CarParking car = new CarParking(sens, act, view); 
		int [] slots = car.getAvailableSlots();
		assertTrue(slots.length > 0);
		
	}
	@Test
	public void testCarParkinggetAvailableSlotsNull() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException { // Test for getAvailableSlots
		CarParking car = new CarParking(sens, act, view);
		Field privateStringField = CarParking.class.getDeclaredField("slots");
		privateStringField.setAccessible(true);
		
		privateStringField.set(car, null);
		
		int [] a = car.getAvailableSlots();
		assertNull(a);
	}




}
