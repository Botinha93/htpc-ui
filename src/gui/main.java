package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import settings.config;
import settings.config.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JSlider;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;

public class main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(0, 0, screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		settings.config.sizeContentX =(int) (((frame.getWidth()*0.9)-15)/6);
		settings.config.sizeContenty =(int) ((frame.getHeight()*0.5)-70);
		
		frame.setContentPane(new ImagePanel(config.BackgroundFrame));
		frame.getContentPane().setLayout(null);

		
		
		try {
			config.InitContent(frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLabel Deco_Top = new JLabel(){
			@Override
			  public void paint(Graphics g) {
			    int width = getWidth();
			    int height = getHeight();

			    // Create the gradient paint
			    GradientPaint paint = new GradientPaint(0, 0, config.DomColor, width, height, new Color(106, 57, 22, 1),
			        true);

			    // we need to cast to Graphics2D for this operation
			    Graphics2D g2d = (Graphics2D) g;

			    // save the old paint
			    Paint oldPaint = g2d.getPaint();

			    // set the paint to use for this operation
			    g2d.setPaint(paint);

			    // fill the background using the paint
			    g2d.fillRect(0, 0, width, height);

			    // restore the original paint
			    g2d.setPaint(oldPaint);

			    super.paint(g);
			  }
			}
			;
		Deco_Top.setBounds((int)(frame.getWidth()*0.05), (int) (((frame.getHeight()*0.5)-(settings.config.sizeContenty/2)))-73, (int)(frame.getWidth()*0.9), 5);
		frame.getContentPane().add(Deco_Top);
		
		JPanel Top = new JPanel();
		Top.setBounds((int)(((config.catNumberCount)*120)+(5*(config.catNumberCount))+(frame.getWidth()*0.05)), (int) (((frame.getHeight()*0.5)-(settings.config.sizeContenty/2)))-61, (int)((frame.getWidth()*0.9)-((config.catNumberCount)*120)-(5*(config.catNumberCount))), 45);
		Top.setOpaque(true);
		Top.setBackground(new Color(0.1f , 0.1f, 0.1f, 0.4f));
		Top.setLayout(null);
		frame.getContentPane().add(Top);
		
		JPanel down = new JPanel();
		down.setBounds((int)(frame.getWidth()*0.05), (int) (((frame.getHeight()*0.5)+(settings.config.sizeContenty/2)))+16, (int)(frame.getWidth()*0.9), 45);
		down.setBackground(new Color(0.1f , 0.1f, 0.1f, 0.4f));
		down.setLayout(null);
		frame.getContentPane().add(down);
		
		
		

		bottomBar.ConfigVol(3);
		JPanel Volume = new JPanel();
		bottomBar.Config(down,Volume);
		Volume.setVisible(false);
		Volume.setLayout(null);
		Volume.setBounds((int)(frame.getWidth()*0.95)-150, (int) (((frame.getHeight()*0.5)+(settings.config.sizeContenty/2)))+70, 40, 130);
		JSlider Vol = new JSlider(JSlider.VERTICAL,0, 100, 100);
		JLabel P100 = new JLabel("100%");
		P100.setBounds(5, 7, 40, 12);
		P100.setFont(config.font_Menu.deriveFont(Font.PLAIN,12));
		P100.setForeground(Color.WHITE);
		Volume.add(P100);
		Vol.setBounds(6, 25, 29, 100);
		Vol.setOpaque(false);
		Vol.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				frame.repaint();
				double value = 65535/100;
				if (Vol.getValue()>60) bottomBar.ConfigVol(3);
				else if (Vol.getValue()>30) bottomBar.ConfigVol(2);
				else if (Vol.getValue()==0) bottomBar.ConfigVol(0);
				else bottomBar.ConfigVol(1);
				try
				{ 
		        // Running the above command 
					Runtime run  = Runtime.getRuntime(); 
					Process proc = run.exec("utils/nircmd.exe setsysvolume "+(int)(Vol.getValue()*value)); 
				} 

				catch (IOException x) 
				{ 
					x.printStackTrace(); 
				} 
			}
		      });
		Volume.setBackground(new Color(0.1f , 0.1f, 0.1f, 0.4f));
		Volume.add(Vol);
		frame.getContentPane().add(Volume);

	}
	
}
