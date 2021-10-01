package productioncode;

import interfaces.Actuator;

public class MockCarActuator implements Actuator{
	
	private int y = 0;
	
	@Override
	public int MoveForward() {
		// TODO Auto-generated method stub
		if (y < 500) y++;
		else System.out.println("Out of bounds"); 
		return y;
	}

	@Override
	public int MoveBackward() {
		// TODO Auto-generated method stub
		if (y > 0) y--;
		else System.out.println("Out of bounds");
		return y;
	}
	@Override
	public void Park() {
		
	}
}
