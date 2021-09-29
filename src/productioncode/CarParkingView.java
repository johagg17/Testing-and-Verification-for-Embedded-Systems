package productioncode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CarParkingView extends JFrame {

	private CarParkingController controller;

	public CarParkingView() {
		this.setSize(200, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int[] init_position = new int[3];
		int[] init_knowledge = new int[1];
		this.map(init_position, init_knowledge);
		this.pack();
		this.setVisible(true);
	}
	public void map(int[] currentsituation, int[] slots) {
		this.getContentPane().removeAll();
		Color color = new Color(0, 0, 0);
		int y = currentsituation[1];
		int parked = currentsituation[0];
		//System.out.println(parked);
		this.setSize(200, 300);
		int rows = 0;
		if (y > 24) {
			rows = 25;
		}else {rows = y+1;}
		GridLayout layout = new GridLayout(rows, 2);
		this.setLayout(layout);
		for (int row = 0; row < rows; row++){
		    for (int col = 0; col < 2; col++){
		    	if(row <5 && col == parked) {
		    		color = new Color(50, 50, 205);//car? blueihs
		    	}
		    	else if (col == 0) {
		    		color = new Color(220, 220, 220);//street? grayish
		    	}
		    	else if(slots[y-row] == 0){
		    		color = new Color(205, 50, 50);//street? redish occupied
		    	}else {
		    		color = new Color(50, 205, 50);//grass? greenish free
		    	}
		        JPanel panel = new JPanel();
		        JLabel b = new JLabel ("(" + (y-row)+","+col+")");
		        this.add(b).setLocation(row, col);
		        panel.add(b);
		        panel.setPreferredSize(new Dimension(10, 25));
		        panel.setBackground(color);
		        this.add(panel);
		    }
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}


}
