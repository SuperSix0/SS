import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class connect extends JFrame{
	
	JTextField ipr;
    JTextField prt;
    JTextField username;
    JPasswordField password;
    static String ip;
    static String port;
    static SS s;
    String usr;
    String psw;
	public connect()
	{
		this.setTitle("SS");
		Toolkit kit = Toolkit.getDefaultToolkit();  
	    Dimension screenSize = kit.getScreenSize();  
	    int screenWidth = screenSize.width;  
	    int screenHeight = screenSize.height;
		this.setBounds(screenWidth / 2 - 225, screenHeight / 2-150, 465, 335);
		this.setResizable(false);
		
		ipr = new JTextField(9);
		ipr.setText("localhost");
		prt = new JTextField(3);
		prt.setText("1433");
		username = new JTextField(20);
		password = new JPasswordField(20);
		password.setEchoChar('*');
		
		JPanel pan1 = new JPanel();
		pan1.add(new JLabel("ÕÊºÅ:"));
		pan1.add(username);
		pan1.setBackground(null); 
		pan1.setOpaque(false);
		
		JPanel pan2 = new JPanel();
		pan2.add(new JLabel("ÃÜÂë:"));
		pan2.add(password);
		pan2.setBackground(null); 
		pan2.setOpaque(false);
		
		JPanel pan3 = new JPanel();
	    pan3.add(new JLabel("·þÎñÆ÷µØÖ·£º"));
	    pan3.add(ipr);
	    pan3.add(new JLabel("¶Ë¿Ú£º"));
	    pan3.add(prt);
	    pan3.setBackground(null); 
		pan3.setOpaque(false);
	    
	    JPanel pan4 = new JPanel();
	    JButton register = new JButton("×¢²á");
	    JButton signin = new JButton("µÇÂ¼");
	    pan4.add(register);
	    pan4.add(signin);
	    pan4.setBackground(null); 
		pan4.setOpaque(false);
	    
		JPanel pan0 = new JPanel();
		pan0.setBackground(null); 
		pan0.setOpaque(false);
		
	    JPanel content = (JPanel)getContentPane();
	    content.setLayout(new GridLayout(5,1));
	    content.add(pan0);
	    content.add(pan1);
	    content.add(pan2);
	    content.add(pan3);
	    content.add(pan4);
	    content.setOpaque(false);
	    ImageIcon img = new ImageIcon("image/background.jpg");
	    img.setImage(img.getImage().getScaledInstance(450, 300,Image.SCALE_DEFAULT ));
	    JLabel imgLabel = new JLabel(img);
	    imgLabel.setBounds(0,0,450,300);
	    getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
	    getLayeredPane().setLayout(null);
		this.setVisible(true);
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				register();
			}
		});
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				signin();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	void setData()
	{
		ip = ipr.getText();
		port = prt.getText();
		usr = username.getText();
		psw = String.valueOf(password.getPassword());
	}
	public void register()
	{
		setData();
		if(usr.length() == 0 || psw.length() == 0)
			JOptionPane.showMessageDialog(null, "ÕÊºÅ/ÃÜÂë²»¿ÉÎª¿Õ", "Error", JOptionPane.ERROR_MESSAGE);
		else
		{
			account user = new account();
			user.id = Integer.parseInt(usr);
			user.password = psw;
			user.name = usr;
			user.sex = 2;
			user.birthday = "1997-01-01";
			user.email = "null";
			if(user.insert() == 1)
				{
					JOptionPane.showMessageDialog(null, "×¢²á³É¹¦", "Information", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new SS(user.id);
				}
			else
				JOptionPane.showMessageDialog(null, "×¢²áÊ§°Ü", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void signin()
	{
		setData();
		if(usr.length() == 0 || psw.length() == 0)
			JOptionPane.showMessageDialog(null, "ÕÊºÅ/ÃÜÂë²»¿ÉÎª¿Õ", "Error", JOptionPane.ERROR_MESSAGE);
		else
		{
			account user = new account(Integer.parseInt(usr));
			if(user.id != 0 && user.password.equals(psw))
			{
				JOptionPane.showMessageDialog(null, "µÇÂ½³É¹¦", "Information", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				s = new SS(user.id);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "ÕÊºÅ/ÃÜÂë´íÎó", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public static void main(String args[])
	{
		new connect();
	}
}
