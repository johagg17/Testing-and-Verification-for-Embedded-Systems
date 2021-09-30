package productioncode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import interfaces.Sensor;


public class MockCarSensor implements Sensor{
	
	private int sensor1;
	private int sensor2;
	
	public MockCarSensor(int sensor1, int sensor2) {
		this.sensor1 = sensor1;
		this.sensor2 = sensor2;
		
	}
	/**
	 * 
	 * getValues will return the sensor values for a specified y_position. If sensor value is between 0-200 then it is considered to work fine. 
	 * If the sensor value is -1, then the sensor is broken and can not be used. 
	 * 
	 * @param y_position
	 * @precondition ??
	 * @postcondition ?? 
	 * @return int []
	 *  
	 * @Test: 1. (testname = getSensorValuesBothWorksFine) Action: input a position, verify that the method got called correct. Expected output: Both sensors works fine
	 * 		  2. (testname = getSensorValuesSensor1IsBrokenSensor2WorksFine) Action: input a position, but for that position only sensor1 works, verify that the method got called correct. Expected output: sensor1 is broken, sensor2 works fine.
	 * 		  3. (testname = getSensorValuesSensor2IsBrokenSensor1WorksFine) Action: input a position, but for that position only sensor2 works, verify that the method got called correct. Expected output: sensor2 is broken, sensor1 works fine.
	 */
	
	public int[] getValues(int y_position) {
		int [] sensors = {sensor1, sensor2};
		return sensors;
		
	}
}
