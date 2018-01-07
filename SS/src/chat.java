import java.util.*;
import java.sql.*;

class chat
{
	int sendid;
	int receiveid;
	String time;
	String record;
	static Statement st;
	static ResultSet rs;

	chat()
	{
		
	}
	chat(String cond)
	{
		init();
		try
		{
			rs = st.executeQuery("select * from chat where " + cond);
			if(rs.next())
				setData();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		close();
	}
	Vector<chat> select(String sql)
	{
		init();
		Vector<chat> vt = new Vector<chat>();
		try
		{
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				chat r=new chat();
				r.setData();
				vt.add(r);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		close();
		return vt;
	}
	void insert()
	{
		init();
		try
		{
			st.executeUpdate("insert into chat values("
				+ sendid + ","
				+ receiveid + ","
				+ "GETDATE(),"
				+ "'" + record + "'"
				+ ")");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		close();
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
	void setData()
	{
		try
		{
			sendid = rs.getInt(1);
			receiveid = rs.getInt(2);
		time = rs.getString(3);
		record = rs.getString(4);
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
