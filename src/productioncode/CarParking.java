package productioncode;

public class CarParking {
	
	public static void main(String[] args) {
		System.out.println("Hello");
		boolean b = true;
		System.out.println(b == true);
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


