package com.jio.vm.vendingmachine;

import java.util.ArrayList;

import com.jio.models.ResponseModel;
import com.jio.models.VMReport;
import com.jio.vm.inventory.VMRefilOnly;

public interface VMDAO {
	
	ArrayList<VendingMachine> getVM();

	ResponseModel addVM(VendingMachine vm);

	boolean checkVMExists(int vmid,VendingMachine vm);

	ResponseModel updateVM(int vmid,VendingMachine vm);

	VendingMachine getById(int vmid);

	ResponseModel delete(int vmid);

	VMReport fetchReportofVM();

	ResponseModel vmRefill(int vmid,VMRefilOnly refil);	

}
