package gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import settings.config;
import settings.config.Actition;

public class bottomBar {
	static JLabel vLabel= new JLabel();
	static void Config (JPanel bot, JPanel volume) {
		timerLabel timeLabel = new timerLabel();
		timeLabel.setOpaque(true);
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setBackground(new Color(0.1f , 0.1f, 0.1f, 0.0f));
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		Rectangle r = bot.getBounds();
		r.x=r.width-100;
		r.y=0;
		r.width=70;
		timeLabel.setBounds(r);
		bot.add(timeLabel);
        Timer timer = new Timer(1000, timeLabel);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
        
        vLabel.setBackground(new Color(0.1f , 0.1f, 0.1f, 0.0f));
        Rectangle vl = bot.getBounds();
        vl.x=vl.width-157;
        vl.y=0;
        vl.width=60;
		vLabel.setBounds(vl);
		vLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent X) {
				if (volume.isVisible()) volume.setVisible(false);
				else if (!volume.isVisible()) volume.setVisible(true);
				config.frame_Main.repaint();
					
		}});
		bot.add(vLabel);
		bot.add(ShutDown());
		bot.add(explorer());
		bot.add(Settings());
        
	}
	public static void ConfigVol(int n) {
		vLabel.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/volume/"+n+".png"));
	}
	public static JLabel ShutDown() {
		JLabel label= new JLabel();
        label.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/power.png"));
        label.setBounds(0,0,46,46);
        label.setVisible(true);
        label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent X) {
				String[] options = new String[] {"Desligar", "Reiniciar", "Cancelar"};
			    int response = JOptionPane.showOptionDialog(null, "O que você deseja que eu faça?", "Desligar HTPC",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
			        null, options, options[0]);
			    System.out.println(response);
			    if (response==0) Runners.Run.Runners(Actition.EXE, "shutdown /p");
			    if (response==1) Runners.Run.Runners(Actition.EXE, "shutdown /r");	    
		}
			@Override
			public void mouseEntered(MouseEvent X) {
				label.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/powerhover.png"));
				label.getParent().getParent().repaint();
			}
			public void mouseExited(MouseEvent X) {
				label.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/power.png"));
				label.getParent().getParent().repaint();
			}
			});
        return label;
	}
	public static JLabel explorer() {
		JLabel label= new JLabel();
        label.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/folder.png"));
        label.setBounds(45,0,46,46);
        label.setVisible(true);
        label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent X) {
				Runners.Run.Runners(Actition.EXE, "explorer.exe");
			}
			@Override
			public void mouseEntered(MouseEvent X) {
				label.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/folderhover.png"));
				label.getParent().getParent().repaint();
			}
			public void mouseExited(MouseEvent X) {
				label.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/folder.png"));
				label.getParent().getParent().repaint();
			}
		});
        return label;
	}
	public static JLabel Settings() {
		JLabel label= new JLabel();
        label.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/setting.png"));
        label.setBounds(90,0,46,46);
        label.setVisible(true);
        label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent X) {
				Runners.Run.Runners(Actition.EXE, "control");
		}@Override
			public void mouseEntered(MouseEvent X) {
			label.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/settinghover.png"));
			label.getParent().getParent().repaint();
		}
		public void mouseExited(MouseEvent X) {
			label.setIcon(new ImageIcon("gui/Cattegories/Images/_Assets/setting.png"));
			label.getParent().getParent().repaint();
		}});
        return label;
	}
	
}
class timerLabel extends JLabel implements ActionListener{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		String date_anterior ="";
	@Override
	public void actionPerformed(ActionEvent e) {
		final DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
        String time = timeFormat.format(date);
        this.setFont(config.font_Menu);
        this.setText(time); 
        if (!date_anterior.contains(time))config.frame_Main.repaint();
        date_anterior=time;
    };

	
}
