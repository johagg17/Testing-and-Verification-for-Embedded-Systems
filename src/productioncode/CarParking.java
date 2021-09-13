package productioncode;
import java.lang.Math;

public class CarParking {
	
	int x = 0;
	int y = 0;
	/**
	 * Method that will return the current position and the situation of the car, (x, y).  
	 * @param none
	 * @precondition 0 <= y <= 500, 0 <= x <= 1
	 * @postcondition 0 <= y <= 500, 0 <= x <= 1
	 * @return int [] of current position
	 * 
	 */
	public int [] WhereIs() {
		// TODO Auto-generated method stub
		int[] position = {x,y};
		return position;	
	}
	/**
	 * Method that will query the two sensors at least 5 times, 
	 * and return the distance to the nearest parking-spot
	 * @param None
	 * @precondition 0 <= y <= 500, x=0
	 * @postcondition 0 <= y <= 500, x=0,  0 <= sensor values <= 200
	 * @return int y, distance to nearest parking-spot. 
	 */
	public int isEmpty() {
		// TODO Auto-generated method stub
		int sensor1 = 0;
		int sensor2 = 0;
		int n = 5;
		for (int i=0; i < n; i++ ) {
			sensor1 += (int) (Math.random() * 200);
			sensor2 += (int) (Math.random() * 200);
		}
		// To check if a sensor is very noise, we could use standard deviation
		sensor1 = sensor1 / n;
		sensor2 = sensor2 / n;
		return Math.min(sensor1, sensor2);
	}
	/**
	 * 
	 * @param None
	 * @return 
	 * @precondition y < 500, x = 0
	 * @postcondition y = previous_pos + 1, x = 0, y <= 500
	 * @return a data structure containing the new position and the situation (parked / unparked)
	 */
	public int[] MoveForward() {
		// TODO Auto-generated method stub
		this.y += 1;
		int distance = this.isEmpty();
		int [] currentSituation = {x, y, distance};
		
		return currentSituation;
		
	}
	
	/**
	 * The method will move the car forward 1 meter and move it out from the parking lot to the street.
	 * @param none
	 * @precondition x = 1
	 * @postcondition x = 0, 0 < y <= 500
	 * @return None
	 */
	public void UnPark() {
		// TODO Auto-generated method stub
		this.x = 0;
		this.y += 1;
	}
	
	/**
	 * The method will move the car 5 meters forward from the parking lot, and then do a 
	 * pre-programmed parallel parking maneuver. 
	 * 
	 * @param None
	 * @precondition x=0, isEmpty() = 0 ?? 
	 * @postcondition x=1, 
	 * 
	 */
	public void Park() {
		// TODO Auto-generated method stub
		
		
	}
	/**
	 * This method will move the car backwards 1 meter, and query the sensors through isEmpty. 
	 * @param None
	 * @precondition x = 0, 0 < y <= 500
	 * @postcondition x = 0, 0 <= y < 500
	 * @return int [] of current position
	 * 
	 */
	public int[] MoveBackward() {
		// TODO Auto-generated method stub
		this.y -= 1;
		int distance = this.isEmpty();
		int [] currentSituation = {x, y, distance};
		
		return currentSituation;
		
	}
		
}


/*
 Description:
 
 	Assumptions:
 		The car car moving along a perfectly straight street which is 500 meters long and registers the available parking places on its right-hand side
 		Forward movement using two ultrasound sensors.
 		The measurements are combined and filtered to reliably find a free parking stretch of 5 meters.
 		The car then moves to the end of the 5 meter stretch and runs a standard parallel reverse parking maneuver.
 		
 		
 	Design:
 		Implement functions MoveForward, isEmpty, MoveBackward, Park, UnPark and WhereIs. 
 		
 		MoveForward - move the car 1 meter forward. 
 		
 		isEmpty - Queries the sensors at least 5 times and filters the noise in their results and returns the distance to 
 				  the nearest object in the right hand side. If one sensor is detected to continuously return very noisy output, 
 				  it should be completely disregarded.  You can use averaging or any other statistical method to filter the noise 
 				  from the signals received from the ultrasound sensors. 
 		
 		MoveBackward - The same as above; only it moves the car 1 meter backwards. The car cannot be moved behind if it is already at the beginning of the street.
 		
 		UnPark - It moves the car forward (and to left) to front of the parking place, if it is parked. 
 		
 		WhereIs - returns current position of the car, as well as the situation (park or not parked).
 	
 	
 	
 	
 	Part1:
 		Start my thinking of the data structure, and what the different methods should do. 
 		For each method, write a specification in the following form:
 
 			 Description (What is the method going to do)
 			 Pre-condition (What can/should be checked before, how does the situation look like?)
 			 Post-conditon (After, the execution of the method, what should the result look like?)
 			 Test-cases: For each method, how should the test-cases look like?
 			 
	Part2:
		For the second part, we should start implement. Each method should be implemented using the TDD approach (Test-Driven development). TDD means that
		the implementation starts with writing test-cases and you as a developer does not have the permisson to implement the production-code until some test
		fails. 
		
	Part3:
		Write a short document describing the development of each phase.
 		
 		
*/


