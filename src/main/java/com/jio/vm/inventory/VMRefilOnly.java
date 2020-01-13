package com.jio.vm.inventory;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VMRefilOnly {

	@SerializedName("item_count")
	@Expose
	private Integer item_count;
	@SerializedName("item_refilled")
	@Expose
	private Integer item_refilled;
	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("money_collected")
	@Expose
	private Integer money_collected;
	
	public Integer getItemCount() {
		return getItemCount();
	}
	public void setItemCount(Integer itemCount) {
		this.item_count = itemCount;
	}
	public Integer getItemRefilled() {
		return item_refilled;
	}
	public void setItemRefilled(Integer itemRefilled) {
		this.item_refilled = itemRefilled;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMoneyCollected() {
		return money_collected;
	}
	public void setMoneyCollected(Integer moneyCollected) {
		this.money_collected = moneyCollected;
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
