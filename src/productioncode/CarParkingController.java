package productioncode;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CarParkingController implements KeyListener{
	
	private CarParking carModel;
	private CarParkingView view;
	private char keyPressed;
	private KeyListener key;
	
	public CarParkingController(CarParking carModel, CarParkingView view) {
		this.carModel = carModel;
		this.view = view;
		view.addKeyListener(this);
		
	}

	@Override
	public void keyTyped(KeyEvent e){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		try {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("MoveForward");
			carModel.MoveForward();
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("MoveBackward");
			carModel.MoveBackward();
			
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_P) {
			System.out.println("Park");
			carModel.Park();
		}
		else if (e.getKeyCode() == KeyEvent.VK_U) {
			System.out.println("UnPark");
			carModel.UnPark();
			
		}
		
		} catch (Exception er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		} 
		int [] pos = carModel.WhereIs();
		System.out.println("Y: " + pos[1]);
		System.out.println("Parked: " + pos[0]);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}


