package testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import productioncode.MockCarSensor;

class CarIntegrationTest {
	
	Scanner sc;
	File file;
	String path;
	
	MockCarSensor sensors;
	
	
	
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
	void TestScenario1() throws FileNotFoundException {
		fail("Still the integration test is missing");
		
		String path = "data\\Sensor_data";
		File file = new File(path);
		Scanner sc;
		int index = 0;
		
		sensors = mock(MockCarSensor.class);
		sc = new Scanner(file);
		while(sc.hasNextLine()) {
			String [] sensors_ = sc.nextLine().split(" ");
			int sensor1 = Integer.parseInt(sensors_[0]);
			int sensor2 = Integer.parseInt(sensors_[1]);
			int [] s = {sensor1, sensor2};
			when(sensors.getValues(index)).thenReturn(s);
			index++;
		}
		System.out.println(sensors.getValues(1)[0] + " " + sensors.getValues(1)[1]);
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	@Test 
	void TestScenario2() {
		
		fail("Still the integration test is missing");
		
		
		
	}
	
	

}
