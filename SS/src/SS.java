import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class SS extends JFrame{
	int id;
	private int friendNum;
	private int requestNum;
	private JTextField add;
	SS(int i)
	{
		id = i;
		account user = new account(id);
		this.setTitle(user.name);
		Toolkit kit = Toolkit.getDefaultToolkit();  
	    Dimension screenSize = kit.getScreenSize();  
	    int screenWidth = screenSize.width;  
	    int screenHeight = screenSize.height;
		this.setBounds(screenWidth * 2 / 3, 0, 225, screenHeight * 2 / 3);
		this.setResizable(false);
		this.setVisible(true);
		
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JButton update = new JButton("修改资料");
		JTextField add = new JTextField(7);
		JButton addfriend = new JButton("添加好友");
		
		ImageIcon icon = new ImageIcon("image/" + user.headimage + ".jpg");
		icon.setImage(icon.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT ));
		JLabel head = new JLabel(icon);
		
		pan1.add(head);
		pan1.add(update);
		pan1.setBackground(new Color(255,255,255));
		
		icon = new ImageIcon("image/refresh.jpg");
		icon.setImage(icon.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
		JButton refresh = new JButton(icon);
		refresh.setPreferredSize(new Dimension(20,20));
		
		pan2.add(add);
		pan2.add(addfriend);
		pan2.add(refresh);
		pan2.setBackground(new Color(255,255,255));
		
		Vector<ImageIcon> icons = new Vector<ImageIcon>();
		Vector<Integer> friendID = new Vector<Integer>();
		DefaultListModel listModel = new DefaultListModel();
		Vector<relation> fre = new relation().select("select * from relation where id1=" + id + " and " + "relation=1");
		friendNum = fre.size();
		for(int k = 0;k < friendNum;k++)
		{
			account fr= new account(fre.elementAt(k).id2);
			friendID.add(fr.id);
			icon = new ImageIcon("image/" + fr.headimage + ".jpg");
			icon.setImage(icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
			icons.add(icon);
			listModel.add(k, fr.name);
		}
		fre = new relation().select("select * from relation where id2=" + id + " and " + "relation=0");
		requestNum = fre.size();
		for(int k = 0;k < requestNum;k++)
		{
			account fr= new account(fre.elementAt(k).id1);
			friendID.add(fr.id);
			icon = new ImageIcon("image/" + fr.headimage + ".jpg");
			icon.setImage(icon.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
			icons.add(icon);
			listModel.add(k + friendNum, fr.name + " 请求添加你为好友");
		}
		
		JList friend = new JList(listModel);
		friend.setCellRenderer(new FriendCellRender(icons));
		friend.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane listScroller = new JScrollPane(friend);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pan1, BorderLayout.NORTH);
		getContentPane().add(listScroller, BorderLayout.CENTER);
		getContentPane().add(pan2, BorderLayout.SOUTH);
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new modify(id);
			}
		});
		addfriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				addFriend();
			}
		});
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				connect.s = new SS(id);
			}
		});
		friend.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)
			{
				if(e.getClickCount() == 2)
				{
					int index = friend.getSelectedIndex();
					if(index >= 0 && index < friendNum)
					{
						new chatWindow(id,friendID.elementAt(index));
					}
					else if(index >= friendNum && index < friendNum + requestNum)
					{
						new friendRequest(friendID.elementAt(index));
					}
				}
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	void addFriend()
	{
		int id = Integer.parseInt(add.getText());
		relation r = new relation("id1=" + this.id + " and id2=" + id);
		if(r.relation == -1)
		{
			r.id1 = this.id;
			r.id2 = id;
			r.relation = 0;
			r.insert();
		}
	}
}
