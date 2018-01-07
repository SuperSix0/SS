import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class friendRequest extends JFrame {
	int friendID;
	friendRequest(int friend)
	{
		friendID = friend;
		this.setTitle("好友请求");
		Toolkit kit = Toolkit.getDefaultToolkit();  
	    Dimension screenSize = kit.getScreenSize();  
	    int screenWidth = screenSize.width;  
	    int screenHeight = screenSize.height;
		this.setBounds(screenWidth / 2 - 100, screenHeight / 2 - 75, 200, 150);
		this.setResizable(false);
		this.setVisible(true);
		
		JButton image = new JButton();
		account f = new account(friend);
		ImageIcon icon = new ImageIcon("image/" + f.headimage + ".jpg");
		icon.setImage(icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
		image.setIcon(icon);
		image.setPreferredSize(new Dimension(50,50));
		
		JPanel pan1 = new JPanel();
		pan1.add(image);
		pan1.add(new JLabel(f.name + "请求添加你为好友"));
		
		JButton agree = new JButton("同意");
		JButton refuse = new JButton("拒绝");
		
		JPanel pan2 = new JPanel();
		pan2.add(agree);
		pan2.add(refuse);
		
		getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(pan1, BorderLayout.NORTH);
	    getContentPane().add(pan2, BorderLayout.SOUTH);
	    
	    image.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new information(friendID);
			}
		});
	    agree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int id = connect.s.id;
				relation r = new relation("id1=" + id + " and id2=" + friendID);
				if(r.relation == -1)
				{
					r.id1 = id;
					r.id2 = friendID;
					r.relation = 1;
					r.insert();
				}
				else
				{
					r.relation = 1;
					r.update();
				}
				r.id1 = friendID;
				r.id2 = id;
				r.relation = 1;
				r.update();
				dispose();
				connect.s.dispose();
				connect.s = new SS(id);
			}
		});
	    refuse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int id = connect.s.id;
				relation r = new relation();
				r.id1 = friendID;
				r.id2 = id;
				r.delete();
				dispose();
				connect.s.dispose();
				connect.s = new SS(id);
			}
		});
	}
}
