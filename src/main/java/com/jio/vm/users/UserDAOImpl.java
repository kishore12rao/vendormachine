package com.jio.vm.users;

import java.util.ArrayList;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.jio.utils.Utils;

@Component
public class UserDAOImpl implements UserDAO{


	@Override
	public User loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		User usr = new User();
		String sql = "select * from users where status = ? and username = ?";
		
		ArrayList<Object> arg = new ArrayList<Object>();
		arg.add("active");
		arg.add(username);
		
		JSONArray user = Utils.executeQueryWithConn(sql, arg);
		Gson gson = new Gson();
		if(user.length() > 0) {
			usr = gson.fromJson(user.optJSONObject(0).toString(), User.class);	
		}
		 
		return usr;
		
	}


}
