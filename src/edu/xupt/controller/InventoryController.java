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
import edu.xupt.entity.Inventory;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	InventoryDao inventory = new InventoryDao();
	
	@RequestMapping("/delete2.do")
	public void inventoryDelete2(String id,HttpServletResponse response){
		InventoryDao user = new InventoryDao();
		int r = user.deleteById(id);
		
		response.setCharacterEncoding("utf-8");
		Writer writer = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			writer = response.getWriter();
			if(r>0){
				System.out.println("delete success!");
				map.put("success", true);
			}else{
				map.put("success", false);
				System.out.println("delete fail!");
			}
			writer.append(JSON.toJSONString(map));
			writer.flush();
		} catch (IOException e) {
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
	
	@RequestMapping("/add.do")
	public String userAdd(String name,int sum){
		InventoryDao inventory = new InventoryDao();
		inventory.insert(name,sum);
		System.out.println(name);
		return "success";
	}
	@RequestMapping("/add2.do")
	public void userAdd(String name,int sum,HttpServletResponse response){
		InventoryDao inventory = new InventoryDao();
		int r =inventory.insert(name,sum);
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
	
	@RequestMapping("/updateUser.do")
	public void updateUser(String id, String name,int sum,HttpServletResponse response){
		InventoryDao inventory = new InventoryDao();
		Inventory u = new Inventory();
		u.setId(id);
		u.setProductName(name);
		u.setSum(sum);
		int r = inventory.updateInventory(u);
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
	
	
	@RequestMapping("/getAllInventory.do")
	public void getInventoryAll(HttpServletResponse response){
		InventoryDao inventory = new InventoryDao();
		List<Inventory> inventorys = inventory.selectAllInventory();
		System.out.println("get inventory info!");
		response.setCharacterEncoding("utf-8");
		Writer writer;
		try {
			writer = response.getWriter();
			String jsonString = JSON.toJSONString(inventorys);
			System.out.println(jsonString);
			writer.append(JSON.toJSONString(inventorys));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
