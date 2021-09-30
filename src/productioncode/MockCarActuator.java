package productioncode;

import interfaces.Actuator;

public class MockCarActuator implements Actuator{
	
	private int y = 0;
	private int parked = 0;

	@Override
	public int[] MoveForward() {
		// TODO Auto-generated method stub
		
		
		if (y < 500) y++;
		int [] currentpos = {parked, y};
	
		return currentpos;
	}

	@Override
	public int[] MoveBackward() {
		// TODO Auto-generated method stub
		if (y > 0) y--;
		int [] currentpos = {parked, y};
		
		return currentpos;
	}
}
