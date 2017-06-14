package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.Inventory;
import edu.xupt.entity.User;
import edu.xupt.util.JdbcUtil;
public class InventoryDao {
	private JdbcUtil jdbc = new JdbcUtil();
	private ProductDao product = new ProductDao();
	public int deleteById(String id){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		String sql = "delete from inventory where id='"+id+"'";
		System.out.println(sql);
		int result = 0;
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int insert(String name,int sum){
		Connection conn = jdbc.getConn();
		String sql = "insert into inventory(ProductName,sum) values ('"+name+"','"+sum+"')";
		int result = 0;
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int updateInventory(Inventory u){
		Connection conn = jdbc.getConn();
		int result = 0;
		String sql = "update inventory set productName=?,sum=? where id=?";
		System.out.println(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getProductName());
			ps.setInt(2, u.getSum());
			int id = Integer.parseInt(u.getId());
			ps.setInt(3, id);
			System.out.println(u.getProductName());
			System.out.println(u.getSum());
			System.out.println(u.getId());
			System.out.println(ps.toString());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	public List<Inventory> selectAllInventory(){
		Connection conn = jdbc.getConn();
		String sql = "select * from inventory";
		List<Inventory> inventorys = new ArrayList<Inventory>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Inventory inventory = new Inventory();
				inventory.setId(rs.getString(1));
				inventory.setProductName(rs.getString(2));
				inventory.setSum(rs.getInt(3));	
				inventorys.add(inventory);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inventorys;
	}
	
	
	
	public int getProductNum(String name) {
		Connection conn = jdbc.getConn();
		String sql = "select * from inventory where productName='" + name + "'";
		int num = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				num = rs.getInt(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return num;
	}

	public void insertRecord(String name, int num) {
		Connection conn = jdbc.getConn();
		String sql = "insert into inventory (productName,sum) values(?,?)";
		PreparedStatement premt;
		try {
			premt = conn.prepareStatement(sql);
			premt.setString(1, name);
			premt.setInt(2, num);
			premt.executeUpdate();
			premt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateInventory(String name, int sum) {
		Connection conn = jdbc.getConn();
		String sql = "update inventory set sum='" + sum
				+ "' where productName='" + name + "'";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		InventoryDao invent = new InventoryDao();
//		invent.insertRecord("apple", 5);
		invent.updateInventory("apple", 44);
		System.out.println(invent.getProductNum("apple"));
		System.out.println();
	}
}
