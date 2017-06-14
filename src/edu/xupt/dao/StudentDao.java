package edu.xupt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.xupt.util.JdbcUtil;

public class StudentDao {
	private JdbcUtil jdbc = new JdbcUtil();
	
	public void insert(){
		Connection conn = jdbc.getConn();
		String sql = "insert into students(name,password) values ('aaa','123321')";
//		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert2(String username, String password){
		Connection conn = jdbc.getConn();
		String sql = "insert into students(name,password) values ('"+username+"','"+password+"')";
//		System.out.println(sql);
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		String sql = "delete from students where id='3'";
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
		String sql = "delete from students where id='"+id+"'";
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
	
	public String selectByUserName(String username){
		Connection conn = jdbc.getConn();
		String password = "";
		String sql = "select * from students where name='"+username+"'";
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
	
	public void update(){
		//sql = "insert into student (id,name,password) values('";
		Connection conn = jdbc.getConn();
		String sql = "update students set name='bbb' where id='4'";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void select(){
		Connection conn = jdbc.getConn();
		String sql = "select * from students where name='bbb'";
		System.out.println(sql);
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				System.out.print(rs.getString(1)+", ");
				System.out.print(rs.getString(2)+", ");
				System.out.print(rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		StudentDao stu  = new StudentDao();
//		stu.insert();
//		stu.delete();
//		stu.update();
//		stu.select();
		stu.insert2("c", "123");
	}
}
