package com.jio.models;

import java.util.ArrayList;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.jio.vm.vendingmachine.VendingMachine;

public class VMReport {

	private int total_vms=0;
	private int total_refillings=0;
	private double total_money=0;
	private double average_money_per_vm=0;
	private ArrayList<VendingMachine> vmlist;
	
	public int getTotal_vms() {
		return total_vms;
	}
	public void setTotal_vms(int total_vms) {
		this.total_vms = total_vms;
	}
	public int getTotal_refillings() {
		return total_refillings;
	}
	public void setTotal_refillings(int total_refillings) {
		this.total_refillings = total_refillings;
	}
	public double getTotal_money() {
		return total_money;
	}
	public void setTotal_money(double total_money) {
		this.total_money = total_money;
	}
	public double getAverage_money_per_vm() {
		return average_money_per_vm;
	}
	public void setAverage_money_per_vm(double average_money_per_vm) {
		this.average_money_per_vm = average_money_per_vm;
	}
	public ArrayList<VendingMachine> getVmlist() {
		return vmlist;
	}
	public void setVmlist(ArrayList<VendingMachine> vmlist) {
		this.vmlist = vmlist;
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
