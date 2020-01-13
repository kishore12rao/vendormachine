package com.jio.models;

import org.json.JSONObject;

import com.google.gson.Gson;

public class ResponseModel {

	private boolean success=true;
	private String message="";
	//private JSONObject data=new JSONObject();

	/*
	 * public JSONObject getData() { return data; } public void setData(JSONObject
	 * data) { this.data = data; }
	 */
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
