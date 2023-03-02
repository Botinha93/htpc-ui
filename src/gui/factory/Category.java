package gui.factory;

import java.awt.Color;
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

import gui.animations.showAN;
import settings.config;
import settings.config.Actition;
import settings.config.Type;

public class Category  {
	
	int catNumber;
	Type Tipo;
	static int labelSize= 120;
	Rectangle r;
	public static ArrayList <Category> Registry = new ArrayList <Category>();
	ArrayList <Panel> RegContent = new ArrayList <Panel>();
	JFrame frame;
	
	public Category(JFrame frame,Type Tipo) {
		this.frame=frame;
		this.Tipo=Tipo;
		catNumber = config.catNumberCount;
		config.catNumberCount++;
		r =new Rectangle((int) (frame.getWidth()*0.05), (int) ((frame.getHeight()*0.5)-(settings.config.sizeContenty/2)), settings.config.sizeContentX, settings.config.sizeContenty);
		config.rHeight=(int) r.getHeight();
	}
	public void AddItens(Image background,ImageIcon icom,Actition Act, String url,String name) {
		if (!(RegContent.size()==6)) {
			RegContent.add(Panel.PanelCreator(r , frame, background, icom,Act,url,name));
			r.x=r.x+settings.config.sizeContentX+3;
		}
			
	}
	public void Show() {
		for (Panel p : RegContent) {
			p.setVisible(true);
			showAN.showAN(p);
		}
	}
	public void Hide() {
		for (Panel p : RegContent) {
			p.setVisible(false);
		}
	}
	public static void Register(Category cat, JFrame Menu) {
		cat.AddMenu(Menu);
		Registry.add(cat);
	}
	void AddMenu(JFrame Menu) {
		
		JLabel Cat = new JLabel("0"+config.catNumberCount+" ");
		Cat.setBounds( (int) (((config.catNumberCount-1)*labelSize)+(5*(config.catNumberCount-1))+(frame.getWidth()*0.05)), (int) (((frame.getHeight()*0.5)-(settings.config.sizeContenty/2)))-61, labelSize, 45);
		Cat.setFont(config.font_Menu);
		Cat.setBackground(new Color(0.1f , 0.1f, 0.1f, 0.4f));
		Cat.setForeground(Color.WHITE);
		Cat.setIcon(Tipo.Get());
		Cat.setIconTextGap(30);
		Cat.setOpaque(true);
		Cat.setHorizontalAlignment(SwingConstants.RIGHT);
		Cat.setVerticalAlignment(SwingConstants.CENTER);
		Cat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent X) {
				Cat.setBackground(new Color(0.1f , 0.1f, 0.1f, 0.6f));
				frame.repaint();
			}
			@Override
			public void mouseExited(MouseEvent X) {
				Cat.setBackground(new Color(0.1f , 0.1f, 0.1f, 0.4f));

				frame.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent X) {
				Registry.get(config.LastClicked).Hide();
				Registry.get(Integer.parseInt(Cat.getText().toCharArray()[1]+"")-1).Show();
				config.LastClicked=Integer.parseInt(Cat.getText().toCharArray()[1]+"")-1;
				
			}
		});
		Menu.add(Cat);
		
	}
}
