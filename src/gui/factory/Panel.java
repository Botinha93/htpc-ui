package gui.factory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import settings.config;
import settings.config.Actition;

public class Panel extends JPanel implements ActionListener {

	public Image Backg = config.BDef;

	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(Backg, 0, 0, this);
	};
	
	public static Panel PanelCreator (Rectangle r, JFrame frame,Image background, ImageIcon icom,Actition Act, String url,String Name) {

			int widht=(int)(((float) (config.sizeContenty)/background.getHeight(null))*(float) background.getWidth(null));
			Image tempBackgroud = background.getScaledInstance(widht, config.sizeContenty, Image.SCALE_SMOOTH);
			Panel Content = new Panel();
			JLabel Arte = new JLabel();
			JLabel Deco = new JLabel();
			JLabel BotName = new JLabel();
			Content.setBounds(r);	
			Content.setVisible(false);
			Content.setLayout(null);
			Content.setOpaque(false);
			Content.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					Deco.setBackground(new Color(1.0f , 1.0f, 1.0f, 0.9f));
					Content.Backg=tempBackgroud;
					Content.repaint();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Deco.setBackground(new Color(1.0f , 1.0f, 1.0f, 0.5f));
					Content.Backg = config.BDef;
					Content.repaint();
				}
				@Override
				public void mouseClicked(MouseEvent X) {
					Runners.Run.Runners(Act, url);
				}
			});
			frame.getContentPane().add(Content);
			
			Deco.setBounds(0,0,r.width,5);
			Deco.setOpaque(true);
			Deco.setBackground(new Color(1.0f , 1.0f, 1.0f, 0.5f));
			Content.add(Deco);
			
			Arte.setBounds((int) ((r.width*0.5)-(icom.getImage().getWidth(null)/2)),(int) ((r.height/2)-(icom.getImage().getWidth(null)/2)),icom.getImage().getWidth(null),icom.getImage().getHeight(null));
			Arte.setIcon(icom);
			Content.add(Arte);
			
			
			BotName.setBounds(0,r.height-50,r.width,20);
			BotName.setText(Name);
			BotName.setFont(config.font_item);
			BotName.setForeground(Color.WHITE);
			BotName.setHorizontalAlignment(SwingConstants.CENTER);
			Content.add(BotName);
			
		return Content;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.getBounds().height<config.rHeight) {
			Rectangle r=this.getBounds();
			r.height=this.getBounds().height+10;
			this.setBounds(r);
		}else {
			Rectangle r=this.getBounds();
			r.height=config.rHeight;
			this.setBounds(r);
			((Timer)e.getSource()).stop();
		}
		
	}
}
