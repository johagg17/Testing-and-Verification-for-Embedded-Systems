package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.Before;

import productioncode.CarParking;
import productioncode.CarParkingController;
import productioncode.CarParkingView;

public class CarParkingViewTest {
	
	CarParkingView view;
	CarParkingController controller;
	CarParking model;
	
	@Before
	public void SetUp() {
		view = new CarParkingView();
		model = new CarParking();
		controller = new CarParkingController(model, view);
		
	}
	
	@Test
	public void test() {
		view = new CarParkingView();
		model = new CarParking();
		controller = new CarParkingController(model, view);
		
	}
	
	

}

