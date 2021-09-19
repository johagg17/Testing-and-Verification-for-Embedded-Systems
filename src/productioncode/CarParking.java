package productioncode;
import java.lang.Math;
import java.util.ArrayList;
public class CarParking {
	//hello is this doing stuff
	int x = 0;
	int y = 0;
	int freespace = 0;
	int[] slots = new int[501]; // The size is 501 since we do not query index 0 at the start.  
	public int sensor1 = 80;
	public int sensor2 = 80; 
	
	/**
	 * Method that will return the current position and the situation of the car, (x, y). X represents if it is parked or not
	 * x = 1 indicates on parked and x = 0 indicates on not parked. 
	 * @param none 
	 * @Test-cases: 1. MoveForward and confirm the new pos, 2. MoveForward and park, confirm that the car is parked.
	 * @return int [] of current position and situation
	 */
	public int [] WhereIs() {
		// TODO Auto-generated method stub
		int[] position = {x,y, freespace};
		return position;
		
	}
	/**
	 * Method that will query the two sensors at least 5 times,
	 * and return the distance to the nearest parking-spot
	 * @param None
	 * @precondition 0 <= y <= 500, x=0
	 * @postcondition 0 <= y <= 500, x=0,  0 <= sensor values <= 200
	 * @Test-cases: 1. When a spot is empty, IsEmpty should return true, 2. When a spot is not empty, IsEmpty should return false
	 * @return boolean emptySpot  
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
	 * @precondition 0 <= y < 500, x = 0
	 * @postcondition x = 0, 0 <= y <= 500
	 * @Test-cases: 1. MoveForward once and confirm the new position and the situation, 
	 * 				2. MoveForward 5 times, confirm that 5 empty slots were detected and this.freespace changes to 5. 
	 * 				3. MoveForward on non-empty slots, confirm the non-empty slots by reading the value from this.freespace.
	 * 				4. Try to MoveForward when y=500, this should result in not moving. 
	 * 				5. MoveForward when x=1 (park mode)
	 * 
	 * @return int [] of current situation
	 */
	public int[] MoveForward() throws Exception {
		// TODO Auto-generated method stub
		if (y == 500 || x == 1) { throw new Exception("Not able to move"); } // throw exception when this is true // Added this line to pass the test testCarParkingMoveForwardOutOfBounds. 
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
		int [] currentSituation = {x, y, freespace};

		return currentSituation;

	}

	/**
	 * The method will move the car forward 1 meter and move it out from the parking lot to the street (Only if the car is already parked, otherwise it does
	 * nothing).
	 * @param none
	 * @precondition x = 1
	 * @postcondition x = 0, 0 < y <= 500
	 * @Test-cases: 1. Test Unpark when x==0, 2. Test Unpark when x==1, 
	 * @return boolean if the parking succeeded or not. 
	 */
	public boolean UnPark() {
		// TODO Auto-generated method stub
		boolean didUnpark = false;
		if (x != 0) { // Added this line to pass the testCatParkingUnParkWhenNotParked, precondition
			x = 0;
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
	 * The method will move the car 5 meters forward from the parking lot, and then do a
	 * pre-programmed parallel parking maneuver.
	 *
	 * @param None
	 * @precondition x=0, freespace >= 5
	 * @postcondition x=1,
	 * @Test-cases 1. Test Park when unparked but 5 free-slots are not available, 2. Test Park when already parked (x=1), 3. Test Park when there are 5 free-slots
	 * @return boolean			   
	 *
	 */
	public boolean Park() {
		// TODO Auto-generated method stub
		if(x == 0 && freespace == 5) {
			x = 1;//pre parallel implementation
			freespace = 0; 
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
	 * 				4. MoveBackward and remember previous states. 
	 * 				 
	 * @return int [] of current position and situation
	 *
	 */
	public int[] MoveBackward() throws Exception {
		// TODO Auto-generated method stub
		if (y == 0 || x == 1) {throw new Exception("Not able to move");}
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
		int [] currentSituation = {x, y, freespace};

		return currentSituation; 

		}


}