package edu.xupt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.xupt.entity.User;
import edu.xupt.entity.User2;
import edu.xupt.util.JdbcUtil;

public class UserDao {
	private JdbcUtil jdbc = new JdbcUtil();
	
	public void insert(){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		String sql = "insert into user(name,password) values ('aaa','123321')";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(String name,String password){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		//String sql = "insert into user(name,password) values ('aaa','123321')";
		String sql = "insert into user(name,password) values ('"+name+"','"+password+"')";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert2(String name,String password,String firstname,String lastname,String email,String phone){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		//String sql = "insert into user(name,password) values ('aaa','123321')";
		String sql = "insert into user(name,password,firstname,lastname,email,phone) values ('"+name+"','"+password+"','"+firstname+"','"+lastname+"','"+email+"','"+phone+"')";
		int result = 0;
		try {
			Statement st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;
	}
	
	public void insert2(String username,String password){
		Connection conn = jdbc.getConn();
		String sql = "insert into user(name,password) values ('"+username+"','"+password+"')";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void delete(){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		String sql = "delete from user where id='1'";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int deleteById(String id){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		String sql = "delete from user where id='"+id+"'";
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
	
	public void update(){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		String sql = "update user set name='bbb' where id='2'";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int updateUser(User u){
		Connection conn = jdbc.getConn();
		int result = 0;
		String sql = "update user set name=?,firstname=?,lastname=?,password=?,phone=?,email=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getFirstname());
			ps.setString(3, u.getLastname());
			ps.setString(4, u.getPassword());
			ps.setString(5, u.getPhone());
			ps.setString(6, u.getEmail());
			System.out.println(u.getId());
			int id = Integer.parseInt(u.getId());
			ps.setInt(7, id);
			System.out.println(ps.toString());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<User2> selectAll(){
		Connection conn = jdbc.getConn();
		String sql = "select * from user";
		
		List<User2> users = new ArrayList<User2>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				User2 user = new User2();
				user.setId(rs.getString(2));
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(3));
				
				System.out.print(rs.getString(1));
				System.out.print("-");
				System.out.print(rs.getString(2));
				System.out.print("-");
				System.out.print(rs.getString(3));
				System.out.println();
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public List<User> selectAll2(){
		Connection conn = jdbc.getConn();
		String sql = "select * from user";
		List<User> users = new ArrayList<User>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				User user = new User();
				user.setName(rs.getString(2));
				user.setId(rs.getString(1));
				user.setPassword(rs.getString(3));
				user.setFirstname(rs.getString(4));
				user.setLastname(rs.getString(5));
				user.setEmail(rs.getString(7));
				user.setPhone(rs.getString(6));
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public String selectByUserName(String username){
		Connection conn = jdbc.getConn();
		String password = "";
		String sql = "select * from user where name='"+username+"'";
		System.out.println(sql);
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				password = rs.getString(3);
				//System.out.print(rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	public static void main(String[] args) {
		UserDao user = new UserDao();
		user.insert();
		//user.delete();
		//user.update();
		//String pass = user.selectByUserName("aaa");
		//System.out.print(pass);
		//user.insert("张三","test");
		//user.insert("aaabbb","test");
		
//		List<User2> users = user.selectAll();
//		int count=users.size();
//		for(int j=0;j<count;j++){
//			System.out.println(users.get(j));
//		}
	}
}
