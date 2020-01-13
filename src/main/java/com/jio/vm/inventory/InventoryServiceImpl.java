package com.jio.vm.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jio.models.ResponseModel;

@Service
public class InventoryServiceImpl  implements InventoryService {

	@Autowired
	InventoryDAO inventoryDAO;
	
	@Override
	public ResponseModel addInventory(int noOfProd) {
		return inventoryDAO.addInventory(noOfProd);
	}

	@Override
	public ResponseModel updateInventory(int noOfProd) {
		return inventoryDAO.updateInventory(noOfProd);
	}

}
