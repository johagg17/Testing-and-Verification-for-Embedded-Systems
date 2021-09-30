package productioncode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import interfaces.Sensor;


public class MockCarSensor implements Sensor{
	
	
	ArrayList<Integer> sensorvalues = new ArrayList<Integer>();
	
	public MockCarSensor() {
		init();
		
	}
	
	
	/**
	 * This method will initilize the MockCarSensor class by reading sensor values from a specific file. 
	 * @param none
	 * @precondition the path to the file exists. 
	 * @postcondition ArrayList.size() > 0, sc.hasNextLine() = false
	 * @return void
	 * 
	 * @Test: 
 	 */
	public void init() {
		String path = "data\\Sensor_data";
		File file = new File(path);
		Scanner sc;
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String [] sensors = sc.nextLine().split(" ");
				int sensor1 = Integer.parseInt(sensors[0]);
				int sensor2 = Integer.parseInt(sensors[1]);
				sensorvalues.add(sensor1); 
				sensorvalues.add(sensor2);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
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
		int sensor1 = (int) sensorvalues.get(y_position);
		int sensor2 = (int) sensorvalues.get(y_position + 1); 
		
		int [] sensors = {sensor1, sensor2};
		return sensors;
		
	}
}
