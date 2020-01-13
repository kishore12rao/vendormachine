package com.jio.vm.vendingmachine;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendingMachine {


	@SerializedName("item_count")
	@Expose
	private Integer itemCount;
	@SerializedName("item_refilled")
	@Expose
	private Integer itemRefilled;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("money_collected")
	@Expose
	private Integer moneyCollected;
	@SerializedName("createdts")
	@Expose
	private String createdts;
	@SerializedName("status")
	@Expose
	private String status;

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Integer getItemRefilled() {
		return itemRefilled;
	}

	public void setItemRefilled(Integer itemRefilled) {
		this.itemRefilled = itemRefilled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMoneyCollected() {
		return moneyCollected;
	}

	public void setMoneyCollected(Integer moneyCollected) {
		this.moneyCollected = moneyCollected;
	}

	public String getCreatedts() {
		return createdts;
	}

	public void setCreatedts(String createdts) {
		this.createdts = createdts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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