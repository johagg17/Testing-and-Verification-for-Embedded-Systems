package testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import productioncode.CarParking;
import productioncode.CarParkingController;
import productioncode.CarParkingView;
import productioncode.MockCarActuator;
import productioncode.MockCarSensor;

class CarIntegrationTest {
		
	MockCarSensor sensors;
	MockCarActuator act;
	CarParking car;
	CarParkingView view;
	CarParkingController cr;
	
	
	// This testing class is used to test the total 
	
	
	/*
	 * The first scenario will be as following: 
	 * 1. The car starts at the beginning of the street, 
	 * 2. Moves along the street and scan for available parking places.
	 * 3. Move backwards, until it finds the most efficient parking place
	 * 4. Parks the car. 
	 * 5. Unparks the car and drive to the end of the street. 
	 * 
	 * */
	@Test
	void TestScenario1() throws Exception{
		sensors = mock(MockCarSensor.class);
		act = mock(MockCarActuator.class);
		view = new CarParkingView();
		// init mocks
		for (int i=1; i<=10; i++) when(sensors.getValues(i)).thenReturn(new int[] {200, 100}); // The first 100 meters will not contain any empty slots.
		for (int i=10; i<=100; i++) when(sensors.getValues(i)).thenReturn(new int[] {200, 200}); // The first 100 meters will not contain any empty slots.
		for (int i=101; i<=111; i++) when(sensors.getValues(i)).thenReturn(new int[] {100, 100}); // Between 101 and 111 should be empty slots.
		for (int i=112; i<=250; i++) when(sensors.getValues(i)).thenReturn(new int[] {200, 200}); // Between 112 and 250 there should be non-empty slots. 
		for (int i=251; i<400; i++) when(sensors.getValues(i)).thenReturn(new int[] {-1, 200}); // Between 251 and 399 there should be non-empty slots and one sensor should be broken. 
		for (int i=400; i<405; i++) when(sensors.getValues(i)).thenReturn(new int[] {-1, 100}); // One sensor broken but there should be one empty slot
		for (int i=405; i<=500; i++) when(sensors.getValues(i)).thenReturn(new int[] {400, 200}); // From 406 to 500 there should be one sensor broken and non-empty slots. 
		
		
		car = new CarParking(sensors, act, view); 
		
		// MoveForward until the end of the street
		for(int i=0; i<500; i++) {
			car.MoveForward();
			Thread.sleep(10);
		}
		// find the most efficient parking-slot
		int[] availableSlots = car.getAvailableSlots();
		int value;
		int previous = 0;
		int size = 0;
		int parking_slot = 0;
		int parking_size = 500;
		for(int i=1; i<availableSlots.length; i++) {
			value = availableSlots[i];
			if (value == 1) {
				// räknar storleken
				size++;
			}
			if(value == 0 && previous == 1) {
				//slutar en parkeringsplats
				if (size >= 5 && size < parking_size) {
					parking_size = size;
					parking_slot = i;
					size = 0;
				}
				
			}
			previous = value;
		}
		
		for(int i=500; parking_slot <= i; i--) {
			car.MoveBackward();
			Thread.sleep(10);
		}
		car.Park();
		Thread.sleep(3000);
		car.UnPark();
		for(int i = car.WhereIs()[1]; i<500; i++) car.MoveForward();
		
		 
		
	}
	/*
	 * The main purpose of this test scenario is to test as many execptions as possible
	 * This is done by first try to move backward at the beginning of the street, and unpark a unparked car.
	 * Secondly we park the car, and tries to park a already parked car and tries to move the car while 
	 * it is parked. After unparking we and the car is still in the begiining of the street because we allowed
	 * it to park as early as possible we move backward to test our "freespace" variable because in both 
	 * move backward and forward we have two free space scenarios one where we check the 5 most recent meters
	 * to make sure they are free if we want to park, but the second one is if we are in the first 4 meters
	 * we cant check previous 5 because there are not 5 meters behind us so we just force it to look at the 
	 * first 5. Then we move the car to the end of the street and tries to move forward again. 
	 * */
	@Test
	void TestScenario2(){
		
		sensors = mock(MockCarSensor.class);
		act = mock(MockCarActuator.class);
		view = new CarParkingView();
		
		// init mocks
		for (int i=1; i<=5; i++) when(sensors.getValues(i)).thenReturn(new int[] {100, 100});
		for (int i=6; i<=10; i++) when(sensors.getValues(i)).thenReturn(new int[] {100, 200});
		for (int i=10; i<=100; i++) when(sensors.getValues(i)).thenReturn(new int[] {200, 200}); // The first 100 meters will not contain any empty slots.
		for (int i=101; i<=111; i++) when(sensors.getValues(i)).thenReturn(new int[] {100, 100}); // Between 101 and 111 should be empty slots.
		for (int i=112; i<=250; i++) when(sensors.getValues(i)).thenReturn(new int[] {200, 200}); // Between 112 and 250 there should be non-empty slots. 
		for (int i=251; i<400; i++) when(sensors.getValues(i)).thenReturn(new int[] {200, -1}); // Between 251 and 399 there should be non-empty slots and one sensor should be broken. 
		for (int i=400; i<405; i++) when(sensors.getValues(i)).thenReturn(new int[] {100, -1}); // One sensor broken but there should be one empty slot
		for (int i=405; i<=500; i++) when(sensors.getValues(i)).thenReturn(new int[] {200, 400}); // From 406 to 500 there should be one sensor broken and non-empty slots. 
		
		CarParking car = new CarParking(sensors, act, view);
		
		try {
			car.MoveBackward();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			}
		
		car.UnPark();
		car.Park();
		car.Park();
		try {
			car.MoveForward();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			car.MoveBackward();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		car.UnPark();
		try {
			car.MoveBackward();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=car.WhereIs()[1]; i<505; i++) {
			try {
				car.MoveForward();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			//Thread.sleep(10);
		}

	} 
	/*
	 * Here we test the execption that happens when we call the park method but there 
	 * exist no free spaces in the entire road, so it drives to the end and returns a execption.
	 * */
	@Test
	public void TestScenario3() {
		sensors = mock(MockCarSensor.class);
		act = mock(MockCarActuator.class);
		view = new CarParkingView();
		
		for (int i=0; i<=500; i++) when(sensors.getValues(i)).thenReturn(new int[] {200, 200});
		
		CarParking car = new CarParking(sensors, act, view);
		car.Park();
	}
	/*
	 * Here we try to park at the very end of the street and gets an execption because we can not
	 * unparked after we parked at the end of the road since the unpark method drives the car 1 meter
	 * forward and out from the slot.
	 * */
	@Test
	public void TestScenario4() {
		
		sensors = mock(MockCarSensor.class);
		act = mock(MockCarActuator.class);
		view = new CarParkingView();
		
		for (int i=1; i<496; i++) when(sensors.getValues(i)).thenReturn(new int[] {200, 200});
		for (int i=496; i<=500; i++) when(sensors.getValues(i)).thenReturn(new int[] {100, 100});
		
		CarParking car = new CarParking(sensors, act, view);
		
		car.Park();
		car.UnPark();
	}

	
	
}
