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

import edu.xupt.dao.InventoryDao;
import edu.xupt.dao.UserOrder;
import edu.xupt.entity.Uorder;

@Controller
@RequestMapping("/userorder")
public class OrderController {
	UserOrder order = new UserOrder();

	@RequestMapping("/delete2.do")
	public void orderDelete2(String id, HttpServletResponse response) {
		int r = order.deleteById(id);
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

	@RequestMapping("/add.do")
	public String orderAdd(String productName, int sum, String username) {
		order.insertOrder(productName, sum, username);
		System.out.println(productName);
		return "success";
	}

	@RequestMapping("/add2.do")
	public void userAdd(String productName,int sum,String username,HttpServletResponse response){
		System.out.println("+===");
		int r =order.insertOrder(productName, sum, username);
		response.setCharacterEncoding("utf-8");
		Writer writer = null;
		try {
			writer = response.getWriter();
			if(r>0){
				writer.append("OK");
			}else{
				writer.append("NO");
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(writer != null){
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@RequestMapping("/updateOrder.do")
	public void updateOrder(String id, String productName, int sum,
			String username, HttpServletResponse response) {

		Uorder u = new Uorder();
		u.setId(id);
		u.setProductName(productName);
		u.setSum(sum);
		u.setUsername(username);
		int r = order.updateOrder(u);
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

	@RequestMapping("/getAllOrder.do")
	public void getOrderAll(HttpServletResponse response) {
		List<Uorder> orders = order.selectOrderAll();
		System.out.println("get inventory info!");
		response.setCharacterEncoding("utf-8");
		Writer writer;
		try {
			writer = response.getWriter();
			String jsonString = JSON.toJSONString(orders);
			System.out.println(jsonString);
			writer.append(JSON.toJSONString(orders));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
