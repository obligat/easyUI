package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.Product;
import edu.xupt.util.JdbcUtil;



public class ProductDao {

	/**
	 * @param args
	 */
	private JdbcUtil jdbc = new JdbcUtil();

	public int insertProduct(String name, String type, String description) {
		Connection conn = jdbc.getConn();
		String sql = "insert into product (productName,type,description) values(?,?,?)";
		PreparedStatement premt;
		int result = 0;
		try {
			premt = conn.prepareStatement(sql);
			premt.setString(1, name);
			premt.setString(2, type);
			premt.setString(3, description);
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
		String sql = "delete from product where id='" + id + "'";
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
	
	public int updateProduct(Product u){
		Connection conn = jdbc.getConn();
		int result = 0;
		String sql = "update product set productName=?,type=?,description=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getProductName());
			ps.setString(2, u.getType());
			ps.setString(3, u.getDescription());
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


	public List<Product> selectProductAll() {
		Connection conn = jdbc.getConn();
		String sql = "select * from product";
		ArrayList<Product> list = new ArrayList<Product>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Product prod = new Product();
				prod.setProductName(rs.getString("productName"));
				prod.setType(rs.getString("type"));
				prod.setDescription(rs.getString("description"));
				prod.setId(String.valueOf(rs.getInt(1)));
				System.out.println(rs.getString("productName"));
				System.out.println(rs.getString("type"));
				System.out.println(rs.getString("description"));
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
		String sql = "select * from product where productName='" + name + "'";
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
		ProductDao product = new ProductDao();
		// product.insertProduct("apple", "fruit", "delicious");
		// product.selectByProductName("apple");
		product.deleteById("3");
	}

}
