import java.util.*;
import java.sql.*;

class relation
{
	int id1;
	int id2;
	int relation = -1;
	static Statement st;
	static ResultSet rs;

	relation()
	{
		
	}
	relation(String cond)
	{
		init();
		try
		{
			rs = st.executeQuery("select * from relation where " + cond);
			if(rs.next())
				setData();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		close();
	}
	Vector<relation> select(String sql)
	{
		init();
		Vector<relation> vt = new Vector<relation>();
		try
		{
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				relation r=new relation();
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
			st.executeUpdate("insert into relation values("
				+ id1 + ","
				+ id2 + ","
				+ relation + ""
				+ ")");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		close();
	}
	void update()
	{
		init();
		try
		{
			st.executeUpdate("update relation set "
				+ "relation=" + relation + ""
				+ " where id1=" + id1 + " and id2=" + id2);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		close();
	}
	void delete()
	{
		init();
		try
		{
			st.executeUpdate("delete from relation where id1=" + id1 + " and id2=" + id2);
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
			id1 = rs.getInt(1);
			id2 = rs.getInt(2);
			relation = rs.getInt(3);
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
