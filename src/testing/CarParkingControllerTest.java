package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import productioncode.*;

class CarParkingControllerTest {
	CarParking car;
	CarParkingView view;
	@Before
	public void SetUp() {
		car = new CarParking();
		view = new CarParkingView();
	}
	@Test
	public void PressUpArrowAndConfirmNewYposition() {
		CarParkingController controller = new CarParkingController(car, view);
	}

}
