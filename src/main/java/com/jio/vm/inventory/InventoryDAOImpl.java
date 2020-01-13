package com.jio.vm.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.jio.models.ResponseModel;
import com.jio.utils.Utils;
import com.jio.vm.security.AuthenticationDetails;
import com.jio.vm.users.User;
import com.jio.vm.users.UserDAO;

@Component
public class InventoryDAOImpl implements InventoryDAO {
	@Autowired
	private AuthenticationDetails authDetails;

	@Autowired
	UserDAO userDAO;

	ResponseModel res = new ResponseModel();
	Gson gson = new Gson();

	@Override
	public ResponseModel addInventory(int noOfProd) {
		try {
			Authentication auth = authDetails.getAuthentication();
			User user = userDAO.loadUserByUsername(auth.getName());	
			if(!user.getRole().contains("admin")) {
				res.setMessage("You dont have authorization to this service");
				res.setSuccess(false);
				return res;
			}

			boolean exists = checkInventoryExists();
			if(exists) {
				res.setMessage("Product Already exist in Inventory");
				res.setSuccess(false);
				return res;
			}
			
			Map<String, Object> values = new HashMap<String,Object>();
			values.put("products", noOfProd);

			int rs = Utils.insertwithConn("inventory", values);
			if(rs > 0) {
				res.setMessage("Product Added to Inventory Successfully");
				res.setSuccess(true);
			}else {
				res.setMessage("Faile to Add Product to Inventory");
				res.setSuccess(false);
			}

		}catch (Exception e) {
			e.printStackTrace();
			res.setMessage("Try again Later..");
			res.setSuccess(false);
		}
		return res;
	}

	@Override
	public boolean checkInventoryExists() {
		String sql = "select * from inventory";

		JSONArray rs = Utils.executeQueryWithConn(sql, null);
		if(rs.length() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseModel updateInventory(int noOfProd) {
		try {
			Authentication auth = authDetails.getAuthentication();
			User user = userDAO.loadUserByUsername(auth.getName());	
			if(!user.getRole().contains("admin") && !user.getRole().contains("service")) {
				res.setMessage("You dont have authorization to this service");
				res.setSuccess(false);
				return res;
			}

			String sql = "update inventory set products = ?";

			ArrayList<Object> arg = new ArrayList<Object>();
			arg.add(noOfProd);

			int rs = Utils.executeUpdateWithConn(sql, arg);
			if(rs > 0) {
				res.setMessage("Inventory Products updated successfully");
				res.setSuccess(true);
			}else {
				res.setMessage("Failed to Update Inventory Products");
				res.setSuccess(false);
			}

		}catch (Exception e) {
			e.printStackTrace();
			res.setMessage("Try again Later..");
			res.setSuccess(false);
		}

		return res;
	}

}
