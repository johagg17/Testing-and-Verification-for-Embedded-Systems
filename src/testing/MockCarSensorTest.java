package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import productioncode.AddNumbers;
import productioncode.MockCarSensor;

class MockCarSensorTest {
	
	// Maybe it is a bit unnecessary to test the Mockito since we tells how it should behave for specifc inputs.
	// Though this testing file can be seen as a test where we validaties that we got Mockito to work, and that we can control the framework in our way. 

	@Test
	public void getSensorValuesBothWorksFine() {
		
		MockCarSensor sensorclass = mock(MockCarSensor.class);
		
		int y = 10;
		
		int [] s = {100, 100}; 
		when(sensorclass.getValues(y)).thenReturn(s);
		
		assertEquals("Mocking the MockCarSensorclass :", s, sensorclass.getValues(y));
		verify(sensorclass).getValues(y);
		
	}
	
	@Test
	public void getSensorValuesSensor1IsBrokenSensor2WorksFine() {
		MockCarSensor sensorclass = mock(MockCarSensor.class);
		
		int y = 10;
		
		int [] s = {-1, 100}; 
		when(sensorclass.getValues(y)).thenReturn(s);
		
		assertEquals("Mocking the MockCarSensorclass :", s, sensorclass.getValues(y));
		verify(sensorclass).getValues(y);
		
	}
	
	@Test
	public void getSensorValuesSensor2IsBrokenSensor1WorksFine() {
		MockCarSensor sensorclass = mock(MockCarSensor.class);
		
		int y = 10;
		
		int [] s = {100, -1}; 
		when(sensorclass.getValues(y)).thenReturn(s);
		
		assertEquals("Mocking the MockCarSensorclass :", s, sensorclass.getValues(y));
		verify(sensorclass).getValues(y);
		
	}

}
