package productioncode;

public class CarParkingMain {


	public static void main(String []args) {

		CarParkingView carView = new CarParkingView();
		CarParking carModel = new CarParking(carView);
		CarParkingController carController = new CarParkingController(carModel, carView);
	}
}
