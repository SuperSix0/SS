import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import javax.swing.JOptionPane;



class account
{
	int id = 0;
	String password;
	String name;
	int headimage;
	int sex;
	String birthday;
	String email;
	static Statement st;
	static ResultSet rs;

	account()
	{
		
	}
	account(int id)
	{
		init();
		try
		{
			rs = st.executeQuery("select * from account where id=" + id);
			if(rs.next())
				setData();
			else
				JOptionPane.showMessageDialog(null, "’ ∫≈≤ª¥Ê‘⁄", "Error", JOptionPane.ERROR_MESSAGE);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		close();
	}
	account(String cond)
	{
		init();
		try
		{
			rs = st.executeQuery("select * from account where " + cond);
			if(rs.next())
				setData();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		close();
	}
	int insert()
	{
		init();
		try
		{
			st.executeUpdate("insert into account values("
				+ id + ","
				+ "'" + password + "',"
				+ "'" + name + "',"
				+ headimage + ","
				+ sex + ","
				+ "'" + birthday + "',"
				+ "'" + email + "'"
				+ ")");
			st.close();
			return 1;
		}catch(Exception e)
		{
			return 0;
		}
	}
	void update()
	{
		init();
		try
		{
			st.executeUpdate("update account set "
				+ "password='" + password + "',"
				+ "name='" + name + "',"
				+ "headimage=" + headimage + ","
				+ "sex=" + sex + ","
				+ "birthday='" + birthday + "',"
				+ "email='" + email + "'"
				+ " where id=" + id);
			st.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void init()
	{
		try
		{
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String sourceURL = "jdbc:sqlserver://" + connect.ip + ":" + connect.port + ";databaseName=SS;";
			Class.forName(driverName);
			Connection conn = DriverManager.getConnection(sourceURL,"admin","admin");
			st = conn.createStatement();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	void setData()
	{
		try
		{
			id = rs.getInt(1);
			password = rs.getString(2);
			name = rs.getString(3);
			headimage = rs.getInt(4);
			sex = rs.getInt(5);
			birthday = rs.getString(6);
			email = rs.getString(7);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void close()
	{
		try
		{
			rs.close();
			st.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
