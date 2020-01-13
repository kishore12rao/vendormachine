package com.jio.vm.vendingmachine;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jio.models.ResponseModel;
import com.jio.models.VMReport;
  

@RestController
@RequestMapping("/vendingmachine")
public class VMCtrl {
 
    @Autowired
    private VMService  vmservice;
    
        
    @RequestMapping(value="/*")
    @ResponseBody
    public String welcome() {
		return "Welcome to VM, Provide valid Request";    	

    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<VendingMachine> get() {
    	return vmservice.get();
    }
    
    @RequestMapping(value="/report",method = RequestMethod.GET)
    @ResponseBody
    public VMReport fetchReport() {
    	return vmservice.fetchReportofVM();
    }
    
    @RequestMapping(value="/{vmid}",method = RequestMethod.GET)
    @ResponseBody
    public VendingMachine getById(@PathVariable(value="vmid") final int vmid) {
        return vmservice.getById(vmid);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel add(@RequestBody VendingMachine vm) {
    	return vmservice.add(vm);
    }
    
    @RequestMapping(value="/{vmid}",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseModel update(@PathVariable(value="vmid") final int vmid,@RequestBody VendingMachine vm) {
        return vmservice.update(vmid,vm);
    }
    
    @RequestMapping(value="/{vmid}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseModel delete(@PathVariable(value="vmid") final int vmid) {
		return vmservice.delete(vmid);
    }
}
