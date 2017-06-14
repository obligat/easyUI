package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.Product;
import edu.xupt.entity.Uorder;
import edu.xupt.util.JdbcUtil;

public class UserOrder {
	private JdbcUtil jdbc = new JdbcUtil();

	public int insertOrder(String productName, int sum,String username) {
		Connection conn = jdbc.getConn();
		String sql = "insert into userorder (productName,sum,username) values(?,?,?)";
		PreparedStatement premt;
		int result = 0;
		try {
			premt = conn.prepareStatement(sql);
			premt.setString(1, productName);
			premt.setInt(2, sum);
			premt.setString(3, username);
			result = premt.executeUpdate();
			premt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteById(String id) {
		Connection conn = jdbc.getConn();
		String sql = "delete from userorder where id='" + id + "'";
		System.out.println("*******************");
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
	
	public int updateOrder(Uorder u){
		Connection conn = jdbc.getConn();
		int result = 0;
		String sql = "update userorder set productName=?,sum=?,username=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getProductName());
			ps.setInt(2, u.getSum());
			ps.setString(3, u.getUsername());
			System.out.println(u.getId());
			int id = Integer.parseInt(u.getId());
			ps.setInt(4, id);
			System.out.println(ps.toString());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}


	public List<Uorder> selectOrderAll() {
		Connection conn = jdbc.getConn();
		String sql = "select * from userorder";
		ArrayList<Uorder> list = new ArrayList<Uorder>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Uorder prod = new Uorder();
				prod.setProductName(rs.getString("productName"));
				prod.setSum(rs.getInt("sum"));
				prod.setUsername(rs.getString("username"));
				prod.setId(String.valueOf(rs.getInt(1)));
				System.out.println(rs.getString("productName"));
				System.out.println(rs.getString("sum"));
				System.out.println(rs.getString("username"));
				System.out.println(String.valueOf(rs.getInt(1)));
				list.add(prod);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.size());
//			System.out.println(list.get(i).getProductName());
//		}
		return list;
	}

	public void selectByProductName(String name) {
		Connection conn = jdbc.getConn();
		String sql = "select * from userorder where productName='" + name + "'";
		System.out.println(sql);
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.print(rs.getString(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.print(rs.getString(3));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserOrder order = new UserOrder();
		// product.insertProduct("apple";, "fruit", "delicious");
		// product.selectByProductName("apple");
//		product.deleteById("3");
		order.insertOrder("pear", 22, "user");
	}

}



