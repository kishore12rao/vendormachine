package com.jio.vm.vendingmachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.jio.models.ResponseModel;
import com.jio.models.VMReport;
import com.jio.utils.Utils;
import com.jio.vm.security.AuthenticationDetails;
import com.jio.vm.users.User;
import com.jio.vm.users.UserDAO;

@Component
public class VMDAOImpl implements VMDAO{
	@Autowired
	private AuthenticationDetails authDetails;

	@Autowired
	UserDAO userDAO;

	ResponseModel res = new ResponseModel();
	Gson gson = new Gson();

	@Override
	public ArrayList<VendingMachine> getVM() {
		Authentication auth = authDetails.getAuthentication();
		User user = userDAO.loadUserByUsername(auth.getName());

		if(!user.getRole().contains("admin")) {
			return null;
		}


		String sql = "select * from vending_machine where status = ? ";

		ArrayList<VendingMachine> vms = new ArrayList<VendingMachine>();
		ArrayList<Object> arg = new ArrayList<Object>();
		arg.add("active");


		JSONArray rs = Utils.executeQueryWithConn(sql, arg);
		if(rs.length() > 0) {
			for(int i=0;i<rs.length();i++) {
				VendingMachine vm = gson.fromJson(rs.optJSONObject(i).toString(), VendingMachine.class);
				if(vm != null) {
					vms.add(vm);
				}
			}


		}

		return vms;
	}

	@Override
	public VMReport fetchReportofVM() {
		VMReport vmReport = new VMReport();
		try {
			Authentication auth = authDetails.getAuthentication();
			User user = userDAO.loadUserByUsername(auth.getName());	
			if(!user.getRole().contains("admin")) {
				return null;
			}

			int total_vms=0;
			int total_refillings=0;
			double average_money_per_vm=0;
			double total_money=0;


			//REPORT
			String sql = "select count(id) as total_vms,sum(itemcount) as total_items,sum(itemrefilled) as total_refillings,"
					+ "sum(moneycollected) as total_money from vending_machine where status = ? ";

			ArrayList<Object> arg = new ArrayList<Object>();
			arg.add("active");

			JSONArray rs = Utils.executeQueryWithConn(sql, arg);
			if(rs.length() > 0) {
				JSONObject obj = rs.optJSONObject(0);
				total_vms = obj.optInt("total_vms");
				total_refillings = obj.optInt("total_refillings");
				total_money = obj.optInt("total_money");
				average_money_per_vm = total_money/total_vms;						
			}

			//END CALCULATIONS			
			ArrayList<VendingMachine> vms = getVM();
			vmReport.setVmlist(vms);			
			vmReport.setAverage_money_per_vm(average_money_per_vm);			
			vmReport.setTotal_money(total_money);			
			vmReport.setTotal_refillings(total_refillings);			
			vmReport.setTotal_vms(total_vms);			

		}catch (Exception e) {
			e.printStackTrace();
		}


		return vmReport;
	}


	@Override
	public ResponseModel addVM(VendingMachine vm) {

		try {
			Authentication auth = authDetails.getAuthentication();
			User user = userDAO.loadUserByUsername(auth.getName());	
			if(!user.getRole().contains("admin")) {
				res.setMessage("You dont have authorization to this service");
				res.setSuccess(false);
				return res;
			}

			if(vm == null || vm.getName().equalsIgnoreCase("")) {
				res.setMessage("Provide proper Name for Vending Machine");
				res.setSuccess(false);
				return res;
			}			
			if(checkVMExists(-1,vm)) {
				res.setMessage("Name Already Exists");
				res.setSuccess(false);
				return res;
			}

			Map<String, Object> values = new HashMap<String,Object>();
			values = gson.fromJson(vm.toJSON().toString(), Map.class);

			int rs = Utils.insertwithConn("vending_machine", values);
			if(rs > 0) {
				res.setMessage("Vending Machine Added Successfully");
				res.setSuccess(true);
			}else {
				res.setMessage("Faile to Add Vending Machine");
				res.setSuccess(false);
			}

		}catch (Exception e) {
			e.printStackTrace();
			res.setMessage("Try again Later..");
			res.setSuccess(false);
		}
		return res;

	}


	@Override
	public boolean checkVMExists(int vmid, VendingMachine vm) {
		String sql = "select * from vending_machine where status = ? and lower(name) = lower(?)";

		ArrayList<Object> arg = new ArrayList<Object>();
		arg.add("active");
		arg.add(vm.getName());
		
		if(vmid > 0) {
			sql += " and id <> ?";
			arg.add(vmid);
		}		
		JSONArray rs = Utils.executeQueryWithConn(sql, arg);

		if(rs.length() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseModel updateVM(int vmid,VendingMachine vm) {
		try {
			Authentication auth = authDetails.getAuthentication();
			User user = userDAO.loadUserByUsername(auth.getName());	
			if(!user.getRole().contains("admin")) {
				res.setMessage("You dont have authorization to this service");
				res.setSuccess(false);
				return res;
			}

			if(vm == null || vm.getName().equalsIgnoreCase("")) {
				res.setMessage("Provide proper Name for Vending Machine");
				res.setSuccess(false);
				return res;
			}			
			if(checkVMExists(vmid,vm)) {
				res.setMessage("Name Already Exists with other Vending Machines");
				res.setSuccess(false);
				return res;
			}

			VendingMachine singleoldVM = getById(vmid);
			if((singleoldVM.getName() == null || singleoldVM.getName().equalsIgnoreCase(vm.getName())) && (singleoldVM.getDescription() == null && singleoldVM.getDescription().equalsIgnoreCase(vm.getDescription()))) {
				res.setMessage("No Changes to Update");
				res.setSuccess(false);
				return res;
			}

			String sql = "update vending_machine set name = ?,description= ? where status = ? and id = ?";

			ArrayList<Object> arg = new ArrayList<Object>();
			arg.add(vm.getName());
			arg.add(vm.getDescription());
			arg.add("active");
			arg.add(vmid);

			int rs = Utils.executeUpdateWithConn(sql, arg);
			if(rs > 0) {
				res.setMessage("Vending Machine: "+vmid+" updated successfully");
				res.setSuccess(true);
			}else {
				res.setMessage("Failed to Update Vending Machine");
				res.setSuccess(false);
			}

		}catch (Exception e) {
			e.printStackTrace();
			res.setMessage("Try again Later..");
			res.setSuccess(false);
		}

		return res;
	}

	@Override
	public VendingMachine getById(int vmid) {

		VendingMachine vm = new VendingMachine();

		String sql = "select * from vending_machine where status = ? and id = ?";

		ArrayList<Object> arg = new ArrayList<Object>();
		arg.add("active");
		arg.add(vmid);


		JSONArray rs = Utils.executeQueryWithConn(sql, arg);
		if(rs.length() > 0) {
			if(vm != null) {
				vm = gson.fromJson(rs.optJSONObject(0).toString(), VendingMachine.class);
			}
		}

		return vm;
	}

	@Override
	public ResponseModel delete(int vmid) {
		try {
			Authentication auth = authDetails.getAuthentication();
			User user = userDAO.loadUserByUsername(auth.getName());	
			if(!user.getRole().contains("admin")) {
				res.setMessage("You dont have authorization to this service");
				res.setSuccess(false);
				return res;
			}

			VendingMachine singleoldVM = getById(vmid);

			if(singleoldVM.getStatus() == null || singleoldVM.getStatus().equalsIgnoreCase("deleted")) {
				res.setMessage("Vending Machine: "+vmid+" Already deleted/doesn't exist");
				res.setSuccess(false);
				return res;
			}

			String sql = "update vending_machine set status = ? where status = ? and id = ?";

			ArrayList<Object> arg = new ArrayList<Object>();
			arg.add("deleted");
			arg.add("active");
			arg.add(vmid);

			int rs = Utils.executeUpdateWithConn(sql, arg);
			if(rs > 0) {
				res.setMessage("Vending Machine: "+vmid+" deleted successfully");
				res.setSuccess(true);
			}else {
				res.setMessage("Vending Machine: "+vmid+" delete failed");
				res.setSuccess(false);
			}

		}catch (Exception e) {
			e.printStackTrace();
			res.setMessage("Try again Later..");
			res.setSuccess(false);
		}

		return res;
	}

	@Override
	public ResponseModel vmRefill(int vmid,VendingMachine refill) {
		try {
			Authentication auth = authDetails.getAuthentication();
			User user = userDAO.loadUserByUsername(auth.getName());	
			if(user.getRole().contains("admin")) {
				res.setMessage("You dont have authorization to this service, Only Service Man has access");
				res.setSuccess(false);
				return res;
			}
			if(refill == null || refill.getItemRefilled()== 0) {
				res.setMessage("Provide proper Refil Count for Vending Machine");
				res.setSuccess(false);
				return res;
			}			
			VendingMachine singleoldVM = getById(vmid);
			if(singleoldVM == null || singleoldVM.getStatus() == null || (singleoldVM.getStatus() != null && singleoldVM.getStatus().equalsIgnoreCase("deleted"))) {
				res.setMessage("Vending Machine Doesn't Exist/Don't have access");
				res.setSuccess(false);				
				return res;
			}
			String sql = "update vending_machine set itemcount= itemcount+?,itemrefilled= ?,moneycollected= ?  where status = ? and id = ?";
			ArrayList<Object> arg = new ArrayList<Object>();
			arg.add(refill.getItemRefilled());
			arg.add(refill.getItemRefilled());
			arg.add(refill.getMoneyCollected());
			arg.add("active");
			arg.add(vmid);

			int rs = Utils.executeUpdateWithConn(sql, arg);
			if(rs > 0) {
				res.setMessage("Vending Machine : "+vmid+" Refil successfully");
				res.setSuccess(true);
			}else {
				res.setMessage("Failed to Refil Vending Machine");
				res.setSuccess(false);
			}

		}catch (Exception e) {
			e.printStackTrace();
			res.setMessage("Try again Later..");
			res.setSuccess(false);
		}

		return res;
	}

}
