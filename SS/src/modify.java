import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class modify extends JFrame{
	int id;
	JPasswordField password;
	JTextField name;
	JComboBox<String> sex;
	JComboBox<String> head;
	JTextField birthday;
	JTextField email;
	modify(int id)
	{
		Toolkit kit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = kit.getScreenSize();  
		int screenWidth = screenSize.width;  
		int screenHeight = screenSize.height;
		this.setBounds(screenWidth / 2 - 150, screenHeight / 2-150, 415, 335);
		this.setResizable(false);
		
		this.id = id;
		account user = new account(id);
		this.setTitle("修改资料");
		password = new JPasswordField(10);
		password.setText(user.password);
		name = new JTextField(10);
		name.setText(user.name);
		head = new JComboBox<String>(new String[] {"1","2","3","4","5","6","7"});
		head.setSelectedIndex(user.headimage);
		sex = new JComboBox<String>(new String[] {"女","男","保密"});
		sex.setSelectedIndex(user.sex);
		birthday = new JTextField(10);
		birthday.setText(user.birthday);
		email = new JTextField(10);
		email.setText(user.email);
		
		JPanel pan0 = new JPanel();
		pan0.setBackground(null); 
		pan0.setOpaque(false);
		
		JPanel pan1 = new JPanel();
		pan1.add(new JLabel("密码:"));
		pan1.add(password);
		pan1.setBackground(null); 
		pan1.setOpaque(false);
		
		JPanel pan2 = new JPanel();
		pan2.add(new JLabel("昵称:"));
		pan2.add(name);
		pan2.setBackground(null); 
		pan2.setOpaque(false);
		
		JPanel pan3 = new JPanel();
		pan3.add(new JLabel("头像"));
		pan3.add(head);
		pan3.add(new JLabel("性别:"));
		pan3.add(sex);
		pan3.setBackground(null); 
		pan3.setOpaque(false);
		
		JPanel pan4 = new JPanel();
		pan4.add(new JLabel("生日:"));
		pan4.add(birthday);
		pan4.setBackground(null); 
		pan4.setOpaque(false);
		
		JPanel pan5 = new JPanel();
		pan5.add(new JLabel("邮箱:"));
		pan5.add(email);
		pan5.setBackground(null); 
		pan5.setOpaque(false);
		
		JButton update = new JButton("修改");
		JButton cancel = new JButton("取消");
		JPanel pan6 = new JPanel();
		pan6.add(update);
		pan6.add(cancel);
		pan6.setBackground(null); 
		pan6.setOpaque(false);
		
		JPanel content = (JPanel)getContentPane();
	    content.setLayout(new GridLayout(7,1));
	    content.add(pan0);
	    content.add(pan1);
	    content.add(pan2);
	    content.add(pan3);
	    content.add(pan4);
	    content.add(pan5);
	    content.add(pan6);
	    content.setOpaque(false);
	    ImageIcon img = new ImageIcon("image/background.jpg");
	    img.setImage(img.getImage().getScaledInstance(400, 300,Image.SCALE_DEFAULT ));
	    JLabel imgLabel = new JLabel(img);
	    imgLabel.setBounds(0,0,400,300);
	    getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
	    getLayeredPane().setLayout(null);
	    
		this.setVisible(true);
		
		update.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update();
				connect.s.dispose();
				connect.s = new SS(id);
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
	}
	void update()
	{
		account user = new account();
		user.id = id;
		user.password = String.valueOf(password.getPassword());
		user.name = name.getText();
		user.headimage = head.getSelectedIndex();
		user.sex = sex.getSelectedIndex();
		user.birthday = birthday.getText();
		user.email = email.getText();
		user.update();
		dispose();
	}

}
