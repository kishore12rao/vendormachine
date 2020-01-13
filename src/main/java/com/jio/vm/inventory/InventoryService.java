package com.jio.vm.inventory;

import com.jio.models.ResponseModel;

public interface InventoryService {

	ResponseModel addInventory(int noOfProd);
	
	ResponseModel updateInventory(int noOfProd);
}
