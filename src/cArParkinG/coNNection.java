package cArParkinG;

import java.sql.Connection;
import java.sql.DriverManager;

public class coNNection 
{
	static Connection con;
    public static Connection docoNNect()
    {
    	try
    	{
    		Class.forName("com.mysql.jdbc.Driver");
    		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/carparking","root","bce");
    		return con;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return null;
    }
}
