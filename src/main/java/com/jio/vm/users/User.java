package com.jio.vm.users;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("role")
	@Expose
	private String role;
	@SerializedName("id")
	@Expose
	private Long id;
	@SerializedName("createdts")
	@Expose
	private String createdts;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("status")
	@Expose
	private String status;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedts() {
		return createdts;
	}
	public void setCreatedts(String createdts) {
		this.createdts = createdts;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public JSONObject toJSON() {
		try {
			Gson gson = new Gson(); 

			return new JSONObject(gson.toJson(this));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}