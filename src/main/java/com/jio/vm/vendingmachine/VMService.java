package com.jio.vm.vendingmachine;

import java.util.ArrayList;

import com.jio.models.ResponseModel;
import com.jio.models.VMReport;

public interface VMService {

	ArrayList<VendingMachine> get();
	
	VendingMachine getById(int vmid);

	ResponseModel add(VendingMachine vm);

	ResponseModel update(int vmid,VendingMachine vm);

	ResponseModel delete(int vmid);

	VMReport fetchReportofVM();

	ResponseModel refillOnly(int vmid, VendingMachine refil);	

}