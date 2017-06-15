package edu.xupt.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

import edu.xupt.dao.UserDao;
import edu.xupt.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	UserDao user = new UserDao();

	// @RequestMapping("/add.do")
	// public String addUser(String username,String password){
	// userdao.insert2(username, password);
	// System.out.println(username);
	// System.out.println(password);
	// return "success";
	// }
//
//	@RequestMapping("/login.do")
//	// xxxx/user/login.do
//	public String login(String username, String password) {
//		String queryPass = user.selectByUsername(username);
//		System.out.println(queryPass);
//		if (queryPass.equals(password)) {
//			return "index";
//		} else {
//			return "error";
//		}
//
//	}

	// http://localhost:8080/user/add.do
	@RequestMapping("/add.do")
	public String userAdd(String username, String password) {

		user.insert(username, password);
		System.out.println(username);
		return "success";
	}

	@RequestMapping("/add2.do")
	public void userAdd(String name, String password, String firstname,
			String lastname, String email, String phone,
			HttpServletResponse response) {
		UserDao user = new UserDao();
		name = firstname + lastname;
		int r = user.insert2(name, password, firstname, lastname, email, phone);
		System.out.println(name);

		response.setCharacterEncoding("utf-8");
		Writer writer = null;
		try {
			writer = response.getWriter();
			if (r > 0) {
				writer.append("OK");
			} else {
				writer.append("NO");
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@RequestMapping("/updateUser.do")
	public void updateUser(String id, String name, String password,
			String firstname, String lastname, String email, String phone,
			HttpServletResponse response) {
		System.out.println("*****************update user!************");
		System.out
				.println("id=" + id + " name=" + name + " password=" + password
						+ " firstname" + firstname + " lastname=" + lastname);
		UserDao userDao = new UserDao();
		User u = new User();
		name = firstname + lastname;
		u.setEmail(email);
		u.setFirstname(firstname);
		u.setId(id);
		u.setLastname(lastname);
		u.setName(name);
		u.setPassword(password);
		u.setPhone(phone);
		int r = userDao.updateUser(u);

		response.setCharacterEncoding("utf-8");
		Writer writer = null;
		try {
			writer = response.getWriter();
			if (r > 0) {
				writer.append("OK");
			} else {
				writer.append("NO");
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping("/delete2.do")
	public void userDelete2(String id, HttpServletResponse response) {
		UserDao user = new UserDao();
		int r = user.deleteById(id);

		response.setCharacterEncoding("utf-8");
		Writer writer = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			writer = response.getWriter();
			if (r > 0) {
				System.out.println("delete success!");
				map.put("success", true);
			} else {
				map.put("success", false);
				System.out.println("delete fail!");
			}
			writer.append(JSON.toJSONString(map));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping("/getAll2.do")
	public void getUserAll(HttpServletResponse response) {
		UserDao user = new UserDao();
		List<User> users = user.selectAll2();
		System.out.println("get user info!");
		response.setCharacterEncoding("utf-8");
		Writer writer;
		try {
			writer = response.getWriter();
			String jsonString = JSON.toJSONString(users);
			System.out.println(jsonString);
			writer.append(JSON.toJSONString(users));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
