import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

import javax.swing.JComponent;

public class JBubble extends JComponent
{
	Image icon;
	int width;
	int height;
	Vector<String> str;
	int flag;
	JBubble(int width,int height,Vector<String> str,int flag,Image icon)
	{
		this.width = width;
		this.height = height;
		this.str = str;
		this.flag = flag;
		this.icon = icon;
	}
	//icon = Toolkit.getDefaultToolkit().getImage("image/" +  + ".jpg");
	public void paintComponent(Graphics g)
	{
		g.drawImage(icon, 10 + 490 * flag,10, 40, 40, this);
		g.setColor(new Color(162,212,242));
		int[] xPoint = new int[3];
		int[] yPoint = new int[3];
		if(flag == 0)
		{
			xPoint[0] = 50;
			xPoint[1] = 50 + height / 4;
			xPoint[2] = 50 + height / 4;
			yPoint[0] = 10 + height / 2;
			yPoint[1] = 10 + height / 4;
			yPoint[2] = 10 + height / 4 * 3;
		}
		else
		{
			xPoint[0] = 500;
			xPoint[1] = 500 - height / 4;
			xPoint[2] = 500 - height / 4;
			yPoint[0] = 10 + height / 2;
			yPoint[1] = 10 + height / 4;
			yPoint[2] = 10 + height / 4 * 3;
		}
		g.fillPolygon(xPoint, yPoint, 3);
		int x = xPoint[1] - (width + 20) * flag;
		int y = 10;
		g.fillRoundRect(x, y, width + 20, (height + 10) * str.size() + 10,10,10);
		g.setColor(Color.black);
		for(int i = 0;i < str.size();i++)
		{
			g.drawString(str.elementAt(i), x + 10, y + (height + 10) * i + 20);
		}
	}
}