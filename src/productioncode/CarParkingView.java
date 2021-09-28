package productioncode;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CarParkingView extends JFrame {
	
	private CarParkingController c;
	
	public CarParkingView() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(200, 300);  
		this.setLocationRelativeTo(null);
		
		JPanel p = new JPanel();
		p.setLayout(null);
		JLabel label = new JLabel("s");
		//label.setPreferredSize(new Dimension(1, 1));
		label.setSize(10, 10);
		label.setLocation(199, 299);
		label.setBackground(new Color(255, 255, 255));

		p.add(label);
		this.add(p);
		
		
	}

}
