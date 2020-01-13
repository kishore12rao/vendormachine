package com.jio.vm.vendingmachine;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jio.models.ResponseModel;
import com.jio.models.VMReport;

@Service
public class VMServiceImpl implements VMService{

	@Autowired
	VMDAO vmDAO;

	@Override
	public ArrayList<VendingMachine> get() {
		return vmDAO.getVM();
	}

	@Override
	public ResponseModel add(VendingMachine vm) {
		return vmDAO.addVM(vm);
	}

	@Override
	public ResponseModel update(int vmid,VendingMachine vm) {
		return vmDAO.updateVM(vmid,vm);
	}

	@Override
	public VendingMachine getById(int vmid) {
		return vmDAO.getById(vmid);
	}

	@Override
	public ResponseModel delete(int vmid) {
		return vmDAO.delete(vmid);
	}

	@Override
	public VMReport fetchReportofVM() {
		return vmDAO.fetchReportofVM();
	}

	@Override
	public ResponseModel refillOnly(int vmid, VendingMachine refil) {
		return vmDAO.vmRefill(vmid,refil);
	}


}

