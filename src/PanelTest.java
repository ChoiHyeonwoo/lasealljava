
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelTest extends JPanel {

	Toolkit tk = Toolkit.getDefaultToolkit();
	BufferedImage img_buffer = null;
	Graphics2D buffer = null; // Graphics
	final static BasicStroke stroke = new BasicStroke(4.0f);
	ColorTest colT = ColorTest.getInstance();

	static String drawString = ""; 
	static boolean stringmode = false; 
	
	static boolean figuremode = false; 

	static String mode = "redpointer"; 
	static int figureVal;

	int roundDraw = 10; 

	public int getRoundDraw() {
		return roundDraw;
	}

	public void setRoundDraw(int roundDraw) {
		this.roundDraw = roundDraw;
	}

	Dimension dimScreen = Toolkit.getDefaultToolkit().getScreenSize();
	int t = (int) (dimScreen.getWidth());
	int q = (int) (dimScreen.getHeight());
	int mid_x = t / 2;
	int mid_y = q / 2;
	int x = t / 2;
	int y = q / 2;

	public void init() {
		img_buffer = new BufferedImage(t, q, BufferedImage.TYPE_INT_ARGB);
		buffer = (Graphics2D) img_buffer.getGraphics();

		buffer.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));

		buffer.fillRect(0, 0, t, q);
		buffer.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

	}

	public PanelTest() {
		setVisible(true);
		setBackground(new Color(255, 255, 255, 0));
	}

	public void paint(Graphics g) {
		init();

		if (ArrayTest.arrDraw.size() != 0) {
			for (int i = 0; i < ArrayTest.arrDraw.size(); i++) {
				if (i!=0)
				{
					if(ArrayTest.arrDraw.get(i-1).getDrawCnt()==ArrayTest.arrDraw.get(i).getDrawCnt()){
						
						buffer.setColor(ArrayTest.arrDraw.get(i).getPosC());
						int beforeX=ArrayTest.arrDraw.get(i-1).getPosX();
						int beforeY=ArrayTest.arrDraw.get(i-1).getPosY();		
						buffer.setStroke(stroke);
						buffer.drawLine(beforeX, beforeY, ArrayTest.arrDraw.get(i).getPosX(), ArrayTest.arrDraw.get(i).getPosY());
					}
				}
			}
		}

		if (ArrayTest.arrString.size() != 0) {
			for (int i = 0; i < ArrayTest.arrString.size(); i++) {
				buffer.setColor(ArrayTest.arrString.get(i).getPosC());
				
				buffer.setFont(new Font("돋움", Font.BOLD, 40));
				
				buffer.drawString(ArrayTest.arrString.get(i).getStr(), ArrayTest.arrString.get(i).getPosX(),
						ArrayTest.arrString.get(i).getPosY());
			}
		}

		if (ArrayTest.arrFigure.size() != 0) {
			for (int i = 0; i < ArrayTest.arrFigure.size(); i++) {
				if(ArrayTest.arrFigure.get(i).getFigureNum() == 1) //B T
				{	
					buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("bigtriangle.png")), ArrayTest.arrFigure.get(i).getPosX(),
							ArrayTest.arrFigure.get(i).getPosY(), this);
				}
				if(ArrayTest.arrFigure.get(i).getFigureNum() == 2) // B C
				{
					buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("bigcircle.png")), ArrayTest.arrFigure.get(i).getPosX(),
							ArrayTest.arrFigure.get(i).getPosY(), this);
				}
				if(ArrayTest.arrFigure.get(i).getFigureNum() == 3) // B R
				{
					buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("bigrect.png")), ArrayTest.arrFigure.get(i).getPosX(),
							ArrayTest.arrFigure.get(i).getPosY(), this);
				}
				if(ArrayTest.arrFigure.get(i).getFigureNum() == 4) // M T
				{
					buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("midtriangle.png")), ArrayTest.arrFigure.get(i).getPosX(),
							ArrayTest.arrFigure.get(i).getPosY(), this);
				}
				if(ArrayTest.arrFigure.get(i).getFigureNum() == 5) // M C
				{
					buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("midcircle.png")), ArrayTest.arrFigure.get(i).getPosX(),
							ArrayTest.arrFigure.get(i).getPosY(), this);
				}
				if(ArrayTest.arrFigure.get(i).getFigureNum() == 6) // M R
				{
					buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("midrect.png")), ArrayTest.arrFigure.get(i).getPosX(),
							ArrayTest.arrFigure.get(i).getPosY(), this);
				}
				if(ArrayTest.arrFigure.get(i).getFigureNum() == 7) // S T
				{
					buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("smtriangle.png")), ArrayTest.arrFigure.get(i).getPosX(),
							ArrayTest.arrFigure.get(i).getPosY(), this);
				}
				if(ArrayTest.arrFigure.get(i).getFigureNum() == 8) // S C
				{
					buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("smcircle.png")), ArrayTest.arrFigure.get(i).getPosX(),
							ArrayTest.arrFigure.get(i).getPosY(), this);
				}
				if(ArrayTest.arrFigure.get(i).getFigureNum() == 9) // S R
				{
					buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("smrect.png")), ArrayTest.arrFigure.get(i).getPosX(),
							ArrayTest.arrFigure.get(i).getPosY(), this);
				}
			}
		}
		switch (mode) {
		case "redpointer": {
			
			buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("ray.png")), x, y, this);
			g.drawImage(img_buffer, 0, 0, this);

			break;
		}
		case "realdraw": {

			buffer.drawOval(x, y, 10, 10);
			buffer.fillOval(x, y, 10, 10);	
			ArrayTest.arrDraw.add(new DrawPoint(x, y, TCPServer.drawCount, colT.getDrawColor()));

			g.drawImage(img_buffer, 0, 0, this);
			break;
		}
		case "drawpointer": {
			buffer.setColor(colT.getDrawColor());
			buffer.drawOval(x, y, 10, 10);
			buffer.fillOval(x, y, 10, 10);
			g.drawImage(img_buffer, 0, 0, this);
			break;
		}
		case "inputstring": {
			buffer.setColor(Color.BLACK);
			if (stringmode == true) {
				int a = x;
				int b = y;
				ArrayTest.arrString.add(new StringPoint(a, b, buffer.getColor(), drawString));
				stringmode = false;
			}

			buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("red.png")), x, y, this);
			g.drawImage(img_buffer, 0, 0, this);
			break;
		}
		case "figuremode": {

			if (figuremode == true) {
				int a = x;
				int b = y;
				ArrayTest.arrFigure.add(new FigurePoint(a, b, figureVal));
				figuremode = false;
			}

			if(figureVal==1){ // bigtriangle
				buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("bigtriangle.png")), x, y, this);
			}
			else if(figureVal==2){ //bigcircle
				buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("bigcircle.png")), x, y, this);
			}
			else if(figureVal==3){ //bigrect
				buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("bigrect.png")), x, y, this);
			}
			else if(figureVal==4){ //midtriangle
				buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("midtriangle.png")), x, y, this);
			}
			else if(figureVal==5){ //midcircle
				buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("midcircle.png")), x, y, this);
			}
			else if(figureVal==6){ //midrect
				buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("midrect.png")), x, y, this);
			}
			else if(figureVal==7){ // smtriangle
				buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("smtriangle.png")), x, y, this);
			}
			else if(figureVal==8){ // smcircle
				buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("smcircle.png")), x, y, this);
			}
			else if(figureVal==9){ // smrect
				buffer.drawImage(tk.getImage(getClass().getClassLoader().getResource("smrect.png")), x, y, this);
			}
			
			g.drawImage(img_buffer, 0, 0, this);
			break;
		}

		default:
			break;
		}

	}

	
	public void pointer(int _x, int _y) {
		int temp_x;
		int temp_y;
		temp_x = x + _x;
		temp_y = y + _y;

		if (temp_x < 0)
			temp_x = 0;
		else if (temp_x >= t)
			temp_x = t;

		if (temp_y < 0)
			temp_y = 0;
		else if (temp_y >= q)
			temp_y = q;

		if (x < temp_x && y < temp_y) {
			while (true) {
				if (x != temp_x)
					x++;
				if (y != temp_y)
					y++;
				this.setOpaque(false);
				repaint();

				if (x == temp_x && y == temp_y)
					break;
			}
		} else if (x < temp_x && y > temp_y) {
			while (true) {
				if (x != temp_x)
					x++;
				if (y != temp_y)
					y--;
				this.setOpaque(false);
				repaint();

				if (x == temp_x && y == temp_y)
					break;
			}
		} else if (x > temp_x && y < temp_y) {
			while (true) {
				if (x != temp_x)
					x--;
				if (y != temp_y)
					y++;
				this.setOpaque(false);
				repaint();

				if (x == temp_x && y == temp_y)
					break;
			}
		} else if (x > temp_x && y > temp_y) {
			while (true) {
				if (x != temp_x)
					x--;
				if (y != temp_y)
					y--;
				this.setOpaque(false);
				repaint();

				if (x == temp_x && y == temp_y)
					break;
			}
		}
	}
}
