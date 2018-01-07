import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class FriendCellRender extends JLabel implements ListCellRenderer {
	Vector<ImageIcon> icons;
	private Border lineBorder = BorderFactory.createLineBorder(Color.black, 1);
	private Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
	FriendCellRender()
	{
		
	}
	FriendCellRender(Vector<ImageIcon> icons)
	{
		this.icons = icons;
	}
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		String str = value.toString();
		setText(str);
		setIcon(icons.elementAt(index));
		setBorder(cellHasFocus?lineBorder:emptyBorder);
		if (isSelected)
		{
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else
		{
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		return this;
	}

}
