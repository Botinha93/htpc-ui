package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension.*;

import javax.swing.JComponent;

public class ImagePanel  extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	System.out.println(((float) (screenSize.height)/image.getHeight(null))*4000+"");
    	Image tmp = image.getScaledInstance((int)(((float) (screenSize.height)/image.getHeight(null))*image.getWidth(null)), screenSize.height, Image.SCALE_SMOOTH);
        this.image = tmp;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

