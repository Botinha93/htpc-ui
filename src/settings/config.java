package settings;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import gui.factory.Category;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Random;

public class config {
	
	public enum Type {
		  VIDEO(new ImageIcon("gui/Cattegories/Images/Icons/Navbar/hexagons.png")),
		  PROG(new ImageIcon("gui/Cattegories/Images/Icons/Navbar/Arc.png")),
		  GAME(new ImageIcon("gui/Cattegories/Images/Icons/Navbar/gamepad.png"));
		  public static Type FromString(String name) {
			if (name.contentEquals("Video"))
				return VIDEO;
			if (name.contentEquals("Programs"))
				return PROG;
			if (name.contentEquals("Game"))
				return GAME;
			return null;
		   }
			public final Icon label;
		    private Type(Icon label) {
		        this.label = label;
		    }	  
		    public Icon Get() {
		    	return label;
		    }
		}
	public enum Actition {
		  EXE,
		  BROW;
		  public static Actition FromString(String name) {
			if (name.contentEquals("EXE"))
				return EXE;
			if (name.contentEquals("Brow"))
				return BROW;
			return null;
			  
		  }
		}
	public static JFrame frame_Main;
	public static int sizeContentX ;
	public static int sizeContenty ;
	public static int  rHeight;
	public static int catNumberCount = 0;
	public static int LastClicked = 0;
	public static Color DomColor;
	static InputStream stream;
	public static Font font_item;
	public static Font font_Menu;
	public static Image BDef =new ImageIcon("gui/Cattegories/Images/_Assets/empty.png").getImage();
	static Random rn = new Random();
	public static Image BackgroundFrame =new ImageIcon("gui/Cattegories/Images/bg "+(rn.nextInt(10) + 1)+".png").getImage();
	
	public static void InitContent(JFrame frame) {
			 frame_Main = frame;
			 initFont();
			 try {
				createCat();
			 } catch (Exception e) {
				 System.out.println("Config File not found"); 
			 }
			 DomColor = gui.factory.ImageDominantColor.getHexColor(config.BackgroundFrame);
			 setVol();
	         Category.Registry.get(0).Show();
	}
	
	private static void createCat() throws Exception {
		File inputFile = new File("gui/Cattegories/Cat.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
		 doc.getDocumentElement().normalize();
         NodeList cats = doc.getElementsByTagName("cat");
         for (int temp2 = 0; temp2 < cats.getLength(); temp2++) {
	            Node catsNode = cats.item(temp2);
	            if (catsNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element catsElement = (Element) catsNode;
	               Category cat = new Category (frame_Main,Type.FromString(catsElement.getAttribute("type")));
	               Category.Register(cat,frame_Main);
	               NodeList nList = catsElement.getElementsByTagName("item");
	               	for (int temp = 0; temp < nList.getLength(); temp++) {
	               		Node nNode = nList.item(temp);
	               		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               			Element eElement = (Element) nNode;
	               			Category.Registry.get(Category.Registry.size()-1).AddItens( new ImageIcon(eElement.getElementsByTagName("arteHover").item(0).getTextContent()).getImage(), new ImageIcon(eElement.getElementsByTagName("arte").item(0).getTextContent()), Actition.FromString(eElement.getElementsByTagName("action").item(0).getTextContent()), eElement.getElementsByTagName("uri").item(0).getTextContent(),eElement.getAttribute("name"));
	               		}
	               	}
	               	
	            }
         }
	}
	private static void setVol() {
		try
		{ 
        // Running the above command 
			Runtime run  = Runtime.getRuntime(); 
			Process proc = run.exec("utils/nircmd.exe setsysvolume 65535"); 
		} 

		catch (IOException x) 
		{ 
			System.out.println("Volume not Set, check nircmd");; 
		} 
	}
	private static void initFont() {

		try {
		     stream = new BufferedInputStream(new FileInputStream("gui/Cattegories/Fonts/Nexa Bold.ttf"));
		     Font font = Font.createFont(Font.TRUETYPE_FONT, stream);
			 font_item = font.deriveFont(Font.PLAIN, 20);
			 stream = new BufferedInputStream(new FileInputStream("gui/Cattegories/Fonts/Baksheesh-Bold.ttf"));
		     Font fontt = Font.createFont(Font.TRUETYPE_FONT, stream);
		     font_Menu = fontt.deriveFont(Font.PLAIN, 25);
		} catch (FontFormatException ee ) {
			System.out.println("File not a valide font");;
		} catch (IOException e) {
			System.out.println("No fount found");
		}
	}
}
