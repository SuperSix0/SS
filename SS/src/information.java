import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class information extends JFrame {
	information(int id)
	{
		Toolkit kit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = kit.getScreenSize();  
		int screenWidth = screenSize.width;  
		int screenHeight = screenSize.height;
		this.setBounds(screenWidth / 2 - 150, screenHeight / 2-150, 415, 335);
		this.setResizable(false);
		this.setTitle("个人资料");
		
		account user = new account(id);
		
		JLabel ID = new JLabel("帐号: " + user.id);
		
		JLabel name = new JLabel("昵称: " + user.name);
		
		ImageIcon icon = new ImageIcon("image/" + user.headimage + ".jpg");
		icon.setImage(icon.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT ));
		JLabel head = new JLabel(icon);
		
		JLabel sex = new JLabel();
		switch(user.sex)
		{
			case 0:
				sex.setText("性别: 女");
				break;
			case 1:
				sex.setText("性别: 男");
			case 2:
				sex.setText("性别: 保密");
		}
		JLabel birthday = new JLabel("生日: " + user.birthday);
		JLabel email = new JLabel("邮箱" + user.email);
		
		Border border = BorderFactory.createEmptyBorder(100, 60, 0, 30);
		JPanel pan = new JPanel();
		pan.setBorder(border);
		pan.setOpaque(false);
		pan.setBackground(null);
		pan.add(head);
		
		JPanel pan0 = new JPanel();
		pan0.setBackground(null); 
		pan0.setOpaque(false);
		
		JPanel pan1 = new JPanel();
		pan1.add(ID);
		pan1.setBackground(null); 
		pan1.setOpaque(false);
		
		JPanel pan2 = new JPanel();
		pan2.add(name);
		pan2.setBackground(null); 
		pan2.setOpaque(false);
		
		JPanel pan3 = new JPanel();
		pan3.add(sex);
		pan3.setBackground(null); 
		pan3.setOpaque(false);
		
		JPanel pan4 = new JPanel();
		pan4.add(birthday);
		pan4.setBackground(null); 
		pan4.setOpaque(false);
		
		JPanel pan5 = new JPanel();
		pan5.add(email);
		pan5.setBackground(null); 
		pan5.setOpaque(false);
		
		
		JPanel content = new JPanel();
	    content.setLayout(new GridLayout(6,1));
	    content.add(pan0);
	    content.add(pan1);
	    content.add(pan2);
	    content.add(pan3);
	    content.add(pan4);
	    content.add(pan5);
	    content.setBackground(null); 
	    content.setOpaque(false);
	    
	    JPanel top = (JPanel)getContentPane();
	    top.setLayout(new BorderLayout());
	    top.setOpaque(false);
	    top.add(pan,BorderLayout.WEST);
	    top.add(content,BorderLayout.CENTER);
	    ImageIcon img = new ImageIcon("image/background.jpg");
	    img.setImage(img.getImage().getScaledInstance(400, 300,Image.SCALE_DEFAULT ));
	    JLabel imgLabel = new JLabel(img);
	    imgLabel.setBounds(0,0,400,300);
	    getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
	    getLayeredPane().setLayout(null);
	    
		this.setVisible(true);
	}
}
