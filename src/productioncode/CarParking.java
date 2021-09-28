package productioncode;
import java.lang.Math;
import java.util.ArrayList;
public class CarParking {
	//hello is this doing stuff
	int parked = 0; // either 0, 1
	int y = 0; // ranges from 0 to 500
	int freespace = 0; // ranges from 0 to 5
	int[] slots = new int[501]; // The size is 501 since we do not query index 0 at the start.  
	public int sensor1 = 80; // ranges from 0 to 200
	public int sensor2 = 80; // ranges from 0 to 200
	
	/**
	 * Method that will return the current position and the situation of the car, (parked, y, freespace). 
	 * @param none 
	 * @Test-cases: 1. (testname=testCarParkingWhereIsAfterMoving) action: MoveForward one step, expected output: {parked=0, y=1, freespace=1}. 
	 * 				2. (testname=testCarParkingWhereIs) Action: Confirm the initial state of the car, expected output: {parked=0, y=0, freespace=0}.
	 * @return int [] of current position and situation
	 */
	public int [] WhereIs() {
		// TODO Auto-generated method stub
		int[] position = {parked,y, freespace};
		return position;
		
	}
	/**
	 * Method that will query the two sensors at least 5 times,
	 * and return if the slot is empty or not. 
	 * @param None
	 * @precondition 0 <= y <= 500, parked=0
	 * @postcondition 0 <= y <= 500, parked=0,  0 <= sensor values <= 200
	 * @Test-cases: 1. (testname = testCarParkingIsEmpty1) Both sensor1 and sensor2 gives true on empty slot, expected output is true. 
	 * 				2. (testname = testCarParkingIsEmpty2) Sensor1 tells false and sensor2 tells true, expected output is false.
	 * 				3. (testname = testCarParkingIsEmpty3) Sensor1 tells true and sensor2 tells false, expected output is false.
	 * 				4. (testname = testCarParkingIsEmpty4) Both sensor1 and sensor2 tells false, expected output is false.
	 * 
	 * @return boolean   
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		int sensor_val1 = sensor1;
		int sensor_val2 = sensor2;
		if(sensor_val1 > 100 || sensor_val2 > 100 ){
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 *
	 * @param None
	 * @precondition 0 <= y < 500, parked = 0
	 * @postcondition parked = 0, 0 <= y <= 500
	 * @Test-cases: 1. (testname = testCarParkingMoveForward) Action: MoveForward once, expected output: {parked=0, y=1, freespace=1}.
	 * 				2. (testname = testCarParkingMoveForwardFiveTimes) Action:MoveForward 5 times, expected output: {parked=0, y=5, freespace=5}. 
	 * 				3. (testname = testCarParkingMoveForwardAndDetectOccupiedSlot) Action: MoveForward on non-empty slots, expected output: {parked=0, y=5, freespace=0}.
	 * 				4. (testname = testCarParkingMoveForwardAndDetectOccupiedSlot) Action: Try to MoveForward when y=500, expected output: Error. 
	 * 				5. (testname = testCarParkingMoveForwardWhenParked) Action: MoveForward when parked=1 (park mode), expected output: Error.
	 * 
	 * @return int []
	 */
	public int[] MoveForward() throws Exception {
		// TODO Auto-generated method stub
		if (y == 500 || parked == 1) { throw new Exception("Not able to move"); } // throw exception when this is true // Added this line to pass the test testCarParkingMoveForwardOutOfBounds. 
		y += 1;
		boolean noise = isEmpty();
		if(noise) {
			slots[y] = 1; 
			if (y > 5) {
				freespace = slots[y] + slots[y - 1] + slots[y - 2] + slots[y - 3] + slots[y - 4];
			}
			else {
				freespace = slots[1] + slots[2] + slots[3] + slots[4] + slots[5];
			}
		}
		else {
			slots[y] = 0;  
			freespace = 0;
		}
		int [] currentSituation = {parked, y, freespace};

		return currentSituation;

	}

	/**
	 * The method will move the car forward 1 meter and move it out from the parking lot to the street (Only if the car is already parked, otherwise it does
	 * nothing).
	 * @param none
	 * @precondition parked = 1
	 * @postcondition parked = 0, 0 < y <= 500
	 * @Test-cases: 1. (testname = testCarParkingUnParkWhenNotParked) Action: Unpark when parked=0, expected output: false. 
	 * 				2. (testname = testCarParkingUnParkWhenParked) Action: Test Unpark when parked=1, expected output: true
	 * @return boolean 
	 */
	public boolean UnPark() {
		// TODO Auto-generated method stub
		boolean didUnpark = false;
		if (parked != 0) { // Added this line to pass the testCatParkingUnParkWhenNotParked, precondition
			parked = 0;
			try {
				MoveForward();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			didUnpark = true;
		}
		return didUnpark;
	}

	/**
	 * The method will try to park the car. If the car is already parked, it will return false. If it is not parked, it will move forward until it finds an empty parking slot or reaches the 
	 * end of the street. 
	 *
	 * @param None
	 * @precondition parked=0
	 * @postcondition parked=1,
	 * @Test-cases 1. (testname = testCarParkingParkWhenAlreadyParked) Action: Test Park when already parked, expected output: Error 
	 * 			   2. (testname = testCarParkingParkWhenEmptySlotsAreAvailable) Action: When there are empty slots, park the car, expected output: true. 
	 * 			   3. (testname = ) Action: Try park when none of the slots are empty, expected output: false
	 * @return boolean			   
	 *
	 */
	public boolean Park() {
		// TODO Auto-generated method stub
		if(parked == 0) {
			while(freespace < 5 && y<=500) {
				try {
					MoveForward();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					return false;
				}
			}
			parked = 1;//pre parallel implementation
			freespace = 0; 
			return true;
		}
		return false;

	}
	/**
	 * This method will move the car backwards 1 meter, and query the sensors through isEmpty.
	 * @param None
	 * @precondition parked = 0, 0 < y <= 500
	 * @postcondition parked = 0, 0 <= y < 500
	 * @Test-cases: 1. (testname = testCarParkingMoveBackWardAtStart) Action: Try to MoveBackward when y=0, expected output: Error
	 * 				2. (testname = testCarParkingMoveBackwardOnce) Action: MoveBackward after two MoveForward, expected output: {parked=0, y=1, this.freespace=2} 
	 * 				3. (testname = testCarParkingMoveBackwardWhenParked) Action: Try to MoveBackward when parked, expected output: Error
	 * 				 
	 * @return int [] of current position and situation
	 *
	 */
	public int[] MoveBackward() throws Exception {
		// TODO Auto-generated method stub
		if (y == 0 || parked == 1) {throw new Exception("Not able to move");}
		y -= 1;
		boolean noise = isEmpty();
		if(noise) {
			slots[y] = 1;
			if (y > 5) { // If y is less than 5, there is no spaces to park within. 
				freespace = slots[y] + slots[y - 1] + slots[y - 2] + slots[y - 3] + slots[y - 4];
			}
			else {
				freespace =  slots[1] + slots[2] + slots[3] + slots[4] + slots[5];
			}
		}
		else {
			slots[y] = 0;
			freespace = 0;
		}
		int [] currentSituation = {parked, y, freespace};

		return currentSituation; 

		}


}