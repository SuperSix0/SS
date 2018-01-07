import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class chatWindow extends JFrame {
	Image friendIcon;
	Image selfIcon;
	int count = 0;
	JPanel history;
	JTextArea txt;
	chatWindow(int selfID, int friendID)
	{
		this.setTitle("chat");
		Toolkit kit = Toolkit.getDefaultToolkit();  
	    Dimension screenSize = kit.getScreenSize();  
	    int screenWidth = screenSize.width;  
	    int screenHeight = screenSize.height;
	    this.setBounds(screenWidth / 2 - 250, screenHeight / 2 - 250, 585, 535);
		this.setResizable(false);
		this.setVisible(true);
		
		account self = new account(selfID);
		account friend = new account(friendID);
		selfIcon = Toolkit.getDefaultToolkit().getImage("image/" + self.headimage + ".jpg");
		friendIcon = Toolkit.getDefaultToolkit().getImage("image/" + friend.headimage + ".jpg");
		
		history = new JPanel();
		BoxLayout boxLayout = new BoxLayout(history, BoxLayout.Y_AXIS);
		history.setLayout(boxLayout);
		history.setBackground(Color.white);
		history.setOpaque(true);
		txt = new JTextArea(5,20);
		txt.setLineWrap(true);
		
		Vector<chat> c = new chat().select("select * from chat where "
			+ "sendid=" + selfID
			+ " and receiveid=" + friendID
			+ " or sendid=" + friendID
			+ " and receiveid=" + selfID + " order by time");
		String time = "1997/05/31 00:00:00", tmp, tmp1, tmp2;
		int id,flag;
		for(int i = 0;i < c.size();i++)
		{
			id = c.elementAt(i).sendid;
			tmp = c.elementAt(i).time;
			tmp = tmp.substring(0, tmp.lastIndexOf("."));
			tmp1 = time.substring(0,time.lastIndexOf(":"));
			tmp2 = tmp.substring(0,tmp.lastIndexOf(":"));
			if(i == 0 || !tmp1.equals(tmp2))
			{
				time = tmp;
				SimpleAttributeSet attr=new SimpleAttributeSet(); 
				StyleConstants.setAlignment(attr, StyleConstants.ALIGN_CENTER);
				history.add(new JLabel("                                                              " + time));
			}
			if(id == selfID)
				flag = 1;
			else
				flag = 0;
			addTextMessage(c.elementAt(i).record, flag);
		}
		JLabel jlabel = new JLabel(friend.name);
		ImageIcon icon = new ImageIcon("image/" + friend.headimage + ".jpg");
		icon.setImage(icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
		JButton image = new JButton();
		image.setIcon(icon);
		image.setPreferredSize(new Dimension(50,50));
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		pan.add(image);
		pan.add(jlabel);
		pan.setBackground(null); 
		pan.setOpaque(false);
		JPanel Pan = new JPanel();
		Pan.setLayout(new BorderLayout());
		Pan.add(pan, BorderLayout.WEST);
		Pan.setBackground(new Color(137, 190, 178));
		
		JButton shutdown, send, refresh;
		shutdown = new JButton("¹Ø±Õ");
		send = new JButton("·¢ËÍ");
		icon = new ImageIcon("image/refresh.jpg");
		icon.setImage(icon.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		refresh = new JButton(icon);
		refresh.setPreferredSize(new Dimension(20,20));
		
		JPanel pan0 = new JPanel();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		pan2.add(shutdown);
		pan2.add(send);
		pan2.add(refresh);
		pan1.setLayout(new BorderLayout());
		pan1.add(pan2, BorderLayout.EAST);
		pan0.setLayout(new BorderLayout());
		pan0.add(new JScrollPane(txt), BorderLayout.NORTH);
		pan0.add(pan1, BorderLayout.SOUTH);
		
		JScrollPane jScrollPane = new JScrollPane(history);
		jScrollPane.doLayout();
		JScrollBar sBar = jScrollPane.getVerticalScrollBar();
		sBar.setValue(sBar.getMaximum()); 
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(Pan, BorderLayout.NORTH);
		getContentPane().add(jScrollPane, BorderLayout.CENTER);
		getContentPane().add(pan0, BorderLayout.SOUTH);
		
		image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new information(friendID);
			}
		});
		shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String str = txt.getText();
				if(str.length() > 0)
				{
					chat Chat = new chat();
					Chat.sendid = selfID;
					Chat.receiveid = friendID;
					Chat.record = str;
					Chat.insert();
					dispose();
					new chatWindow(selfID, friendID);
				}
			}
		});
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				new chatWindow(selfID, friendID);
			}
		});
	}
	public void addTextMessage(String messages, int flag)
	{
		FontMetrics fm = getGraphics().getFontMetrics();
		int sHeight =fm.getHeight();
		int sWidth = fm.stringWidth(messages);
		Vector<String> str = new Vector<String>();
		if (sWidth > 300)
		{
			int beginIndex=0;
			int endIndex=1;
			while(endIndex < messages.length())
			{
				String s=messages.substring(beginIndex,endIndex);
				if(fm.stringWidth(s)>(300) || endIndex == messages.length() - 1)
				{
					str.add(messages.substring(beginIndex,endIndex-1));
					beginIndex = endIndex-1;			
				}
				endIndex++;
			}	
		}
		else
			str.add(messages);
		Image icon;
		if(flag == 0)
			icon = friendIcon;
		else
			icon = selfIcon;
		JBubble jbubble = new JBubble(fm.stringWidth(str.elementAt(0)), sHeight, str, flag, icon);
		jbubble.setPreferredSize(new Dimension(500, (sHeight + 20) * str.size() + 30));
		history.add(jbubble);
	}
}
