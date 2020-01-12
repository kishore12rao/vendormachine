package com.jio.vm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class ExceptionController{
 
    @RequestMapping("/*") 
    @ResponseBody
    public String welcome() {
		return "Welcome to Jio-Vending Machine, Please provide Valid API";    	

    }
}
