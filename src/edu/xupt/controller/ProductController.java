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

import edu.xupt.dao.ProductDao;
import edu.xupt.entity.Product;


@Controller
@RequestMapping("/product")
public class ProductController {
	ProductDao product = new ProductDao();
	
	@RequestMapping("/delete2.do")
	public void inventoryDelete2(String id,HttpServletResponse response){
		int r = product.deleteById(id);
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
	public String userAdd(String name,String type,String description){
		product.insertProduct(name,type,description);
		System.out.println(name);
		return "success";
	}
	@RequestMapping("/add2.do")
	public void userAdd(String name,String type,String description,HttpServletResponse response){
		int r =	product.insertProduct(name,type,description);
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
	
	@RequestMapping("/updateProduct.do")
	public void updateUser(String id, String name,String type,String description,HttpServletResponse response){
		ProductDao product = new ProductDao();
		Product u = new Product();
		u.setId(id);
		u.setProductName(name);
		u.setType(type);
		u.setDescription(description);
		int r = product.updateProduct(u);
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
	
	
	@RequestMapping("/getAllProduct.do")
	public void getInventoryAll(HttpServletResponse response){
		List<Product> products = product.selectProductAll();
		System.out.println("get inventory info!");
		response.setCharacterEncoding("utf-8");
		Writer writer;
		try {
			writer = response.getWriter();
			String jsonString = JSON.toJSONString(products);
			System.out.println(jsonString);
			writer.append(JSON.toJSONString(products));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
