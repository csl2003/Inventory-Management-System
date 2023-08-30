package databaseConnection;

import java.sql.*;
import java.sql.ResultSet;


public class DbConnection {
	
//	public void add(int id,String name,int qt,String category)
	public void add(Connection conn,int id,String name,int qt,String category) {
		try {
		 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO INVENTORY VALUES (?, ?, ?, ?)");
	      pstmt.setInt(1,id);
	      pstmt.setString(2, name);
	      pstmt.setInt(3, qt);
	      pstmt.setString(4, category)
	      ;
	      pstmt.execute();
	      }
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while inserting");
			e.printStackTrace();
		}
	}	
	public void delete(Connection conn,int item_id) {
		try {
	PreparedStatement pstmt = conn.prepareStatement("delete from INVENTORY where id=?");
	pstmt.setInt(1, item_id);
	pstmt.execute();}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while delete");
			e.printStackTrace();
		}

}
	public void update(Connection conn,int id,int ud) {
		try {
	PreparedStatement pstmt = conn.prepareStatement("update INVENTORY set QUANTITY=QUANTITY+? where id=?");
	pstmt.setInt(1, ud);
	pstmt.setInt(2, id);
pstmt.executeUpdate();}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while update");
			e.printStackTrace();
		}
		
}	
	public Connection connect() {
		Connection conn = null;
		DbConnection ob=new DbConnection();
		try {

			Class.forName("com.mysql.jdbc.Driver");

		}
		catch(ClassNotFoundException e) {
			System.out.println("JDBC not registered!!");
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
		System.out.println("JDBC Registered!!");
		System.out.println("Connecting to the database...");
		try {

		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop","root","1234"); 
		}
		catch(SQLException e) {

		System.out.println("MySql Database not connected!!");
		return null;
		}

		return conn;
	}
	public Object[][] display(Connection conn,String ct) {
		try {
		PreparedStatement ps = conn.prepareStatement("select count(*) from INVENTORY where CATEGORY=?");
		ps.setString(1, ct);
		ResultSet s=ps.executeQuery();
		int row=0;
		if (s.next()) {
            row= s.getInt(1);
       } 
       Object[][] ar=new Object[row][4];
		PreparedStatement pstmt = conn.prepareStatement("select * from INVENTORY where CATEGORY=?");
		pstmt.setString(1, ct);
		ResultSet rs=pstmt.executeQuery();
		int i=0;
		while(rs.next()){
            //Display values
			ar[i][0]=rs.getInt("id");
            ar[i][1]=rs.getString("NAME");
            ar[i][2]=rs.getInt("QUANTITY");
           ar[i][3]=rs.getString("CATEGORY");
            i++;
         }
		return ar;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while display");
			e.printStackTrace();
		}
		return null;
	}
	public Object[][] displayall(Connection conn) {
		try{
		Statement stmt = conn.createStatement();
		String query = "SELECT COUNT(*) FROM  INVENTORY";
        ResultSet resultSet = stmt.executeQuery(query);
       int row=0;
        if (resultSet.next()) {
             row= resultSet.getInt(1);
        } 
        Object[][] ar=new Object[row][4];
		ResultSet rs=stmt.executeQuery("select * from INVENTORY ");
		int i=0;
		while(rs.next()){
            //Display values
//			ar[i]= {rs.getInt("id"),rs.getString("NAME"),rs.getInt("QUANTITY"),rs.getString("CATEGORY")};
			ar[i][0]=rs.getInt("id");
            ar[i][1]=rs.getString("NAME");
            ar[i][2]=rs.getInt("QUANTITY");
           ar[i][3]=rs.getString("CATEGORY");
            i++;
         }
		return ar;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while display");
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbConnection ob=new DbConnection();
		Connection conn=ob.connect();
			if(conn != null) {
			System.out.println("Success!! connected to the database");
			Object[][] ar=ob.display(conn, "c1");
            ob.delete(conn, 4);
				
				
		}
	
	}
}
