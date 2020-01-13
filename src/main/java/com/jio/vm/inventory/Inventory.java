package com.jio.vm.inventory;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inventory {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("products")
	@Expose
	private Integer products;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProducts() {
		return products;
	}

	public void setProducts(Integer products) {
		this.products = products;
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
