package gui.animations;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import gui.factory.Panel;

public class showAN {
	static Timer timer;


	  static public void showAN(Panel panel) {
	    timer = new Timer(20, panel);
	    Rectangle r=panel.getBounds();
		r.height=(int) (panel.getBounds().height*0.05);
		panel.setBounds(r);
	    timer.setInitialDelay(60);
	    timer.start();
	  }


}
