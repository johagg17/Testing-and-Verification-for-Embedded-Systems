package productioncode;
import java.lang.Math;
import java.util.ArrayList;
public class CarParking {
	//hello is this doing stuff
	int x = 0;
	int y = 0;
	int freespace = 0;
	int[] slots = new int[501];
	/**
	 * Method that will return the current position and the situation of the car, (x, y).
	 * @param none
	 * @precondition 0 <= y <= 500, 0 <= x <= 1
	 * @postcondition 0 <= y <= 500, 0 <= x <= 1
	 * @Test-cases: 1. MoveForward and confirm the new pos, 2. MoveForward and park, confirm that the car is parked.
	 * @return int [] of current position
	 * 
	 *
	 */
	public int [] WhereIs() {
		// TODO Auto-generated method stub
		int[] position = {this.x,this.y, this.freespace};
		return position;
		
	}
	/**
	 * Method that will query the two sensors at least 5 times,
	 * and return the distance to the nearest parking-spot
	 * @param None
	 * @precondition 0 <= y <= 500, x=0
	 * @postcondition 0 <= y <= 500, x=0,  0 <= sensor values <= 200
	 * @Test-cases: 1. When a spot is empty, IsEmpty should return true, 2. When a spot is not empty, IsEmpty should return false
	 * 
	 * @return int y, distance to nearest parking-spot.
	 */
	public boolean isEmpty(int fixed_sensor_val) {
		// TODO Auto-generated method stub
		int sensor1 = 0;
		int sensor2 = 0;
		sensor1 = (int) (Math.random() * 200);
		sensor2 = (int) (Math.random() * 200);
		int sensor_val = (sensor1+sensor2)/2;
		sensor_val = fixed_sensor_val;
		if(sensor_val > 100){
			this.slots[this.y] = 0;
			return false;
		}
		else {
			this.slots[this.y] = 1;
			return true;
		}
	}
	/**
	 *
	 * @param None
	 * @precondition y < 500, x = 0, freespace < 5 ?? 
	 * @postcondition y = previous_pos + 1, x = 0, y <= 500
	 * @Test-cases: 1. MoveForward once and confirm the new position and the situation, 
	 * 				2. MoveForward 5 times, confirm that 5 empty slots were detected and this.freespace changes to 5. 
	 * 				3. MoveForward on non-empty slots, confirm the non-empty slots by reading the value from this.freespace.  // We will wait with this test
	 * 				4. Try to MoveForward when y=500, this should result in not moving. 
	 * 				5. MoveForward when x=1 (park mode)
	 * @return a data structure containing the new position and the situation (parked / unparked)
	 */
	public int[] MoveForward() {
		// TODO Auto-generated method stub
		int [] situation = {this.x, this.y, this.freespace};
		//System.out.println(this.y);
		if (this.y == 500 || x == 1) {return situation;} // Added this line to pass the test testCarParkingMoveForwardOutOfBounds. 
		this.y += 1;
		boolean noise = this.isEmpty(80);
		if(noise) {
			if (y >= 5) {
				this.freespace = slots[y] + slots[y - 1] + slots[y - 2] + slots[y - 3] + slots[y - 4];
			}
		}
		else {
			// this.freespace = 0;
		}
		int [] currentSituation = {x, y, this.freespace};

		return currentSituation;

	}

	/**
	 * The method will move the car forward 1 meter and move it out from the parking lot to the street.
	 * @param none
	 * @precondition x = 1
	 * @postcondition x = 0, 0 < y <= 500
	 * @Test-cases: 1. Test Unpark when x==0, 2. Test Unpark when x==1, 
	 * @return None
	 */
	public void UnPark() {
		// TODO Auto-generated method stub
		if (this.x != 0) { // Added this line to pass the testCatParkingUnParkWhenNotParked, precondition
			this.x = 0;
			this.MoveForward(); 
		}
	}

	/**
	 * The method will move the car 5 meters forward from the parking lot, and then do a
	 * pre-programmed parallel parking maneuver.
	 *
	 * @param None
	 * @precondition x=0, freespace >= 5
	 * @postcondition x=1,
	 * @Test-cases 1. Test Park when unparked but 5 free-slots are not available, 2. Test Park when already parked (x=1), 3. Test Park when there are 5 free-slots
	 * 			   
	 *
	 */
	public boolean Park() {
		// TODO Auto-generated method stub
		if(this.x == 0 && this.freespace == 5) {
			this.x = 1;//pre parallel implementation
			this.freespace = 0;
			return true;
		}
		return false;

	}
	/**
	 * This method will move the car backwards 1 meter, and query the sensors through isEmpty.
	 * @param None
	 * @precondition x = 0, 0 < y <= 500
	 * @postcondition x = 0, 0 <= y < 500
	 * @Test-cases: 1. Try to MoveBackward when y=0, 2. MoveBackward and confirm that new y-value = prev y - 1. 3. Try to MoveBackward when parked
	 * 				 
	 * @return int [] of current position
	 *
	 */
	public int[] MoveBackward() {
		// TODO Auto-generated method stub
		int [] situation = {this.x, this.y, this.freespace};
		if (this.y == 0 || x == 1) {return situation;} // Added this line to pass the test testCarParkingMoveBackWardAtStart. 
		this.y -= 1;
		boolean noise = this.isEmpty(80);
		if(noise) {
			if (this.y >= 5) {
				this.freespace = slots[this.y] + slots[this.y - 1] + slots[this.y - 2] + slots[this.y - 3] + slots[this.y - 4];
			}
		}
		else {
			// this.freespace = 0;
		}
		int [] currentSituation = {this.x, this.y, this.freespace};

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
