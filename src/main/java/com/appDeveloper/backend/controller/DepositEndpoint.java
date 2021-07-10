package com.appDeveloper.backend.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appDeveloper.backend.model.CustomerAccountDetails;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.Getter;

@RestController
@RequestMapping("/endpoint") //localhost:8080/users/
public class DepositEndpoint {
	
private BigDecimal BigDecimal;
private BigDecimal amountDeposited;


//	request for lists of users per page using pagenation implementation
	@GetMapping //user respond to http get request
	public CustomerAccountDetails getUsers(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue="50") int sort) {
		
		DepositEndpoint de = new DepositEndpoint();
		CustomerAccountDetails returnValue  = de.feedDetails();
		return returnValue;
	}
	
	
	@GetMapping(path="/{userId}") //user response to http get request
	public CustomerAccountDetails getCustomerDetails(@PathVariable String userId, BigDecimal BigDecimal) {

		DepositEndpoint de = new DepositEndpoint();
		CustomerAccountDetails returnValue  = de.feedDetails();
		
		return returnValue;
	}

	
//	handle http get request
	
//	@PostMapping( value="/post")
	
	@RequestMapping( method = RequestMethod.POST)
	public String postCustomerDetails() throws JsonParseException, JsonMappingException, IOException {
//		CustomerAccountDetails returnValue = new CustomerAccountDetails();
		Gson gson =new Gson();
		BigDecimal output;
		BigDecimal input;
		BigDecimal result = null;
		
//		return percentage of value of amount deposited
		Reader reader;
//		Map<CustomerAccountDetails, String> map;
		
			reader = Files.newBufferedReader(Paths.get("waya.json"));
			CustomerAccountDetails cusDetails = gson.fromJson(reader,  CustomerAccountDetails.class);
			input = cusDetails.getAmount();
			result = input.multiply(new BigDecimal(0.7665));
			System.out.println(result);
			
			//read to map - jackson mapper
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<CustomerAccountDetails, String> map = mapper.readValue(Files.newBufferedReader(Paths.get("waya.json")), Map.class);
			
			String res = map.get(cusDetails.getCustomerName());
		
		
//		return gson.toJson(map);
			return res;
	}
	

	
	//update user
	@PutMapping
	public String updateUser() {
		return "update user was called";
	}
	
	//deleter user
	@DeleteMapping
	public String deleteUser() {
		return "delete use was called";
	}
	
	private  CustomerAccountDetails feedDetails() {
		CustomerAccountDetails returnValue = new CustomerAccountDetails();
		String str = "500.23";
		returnValue.setCustomerName("Freddie Jensen");
		returnValue.setAccountNumber("1234567890123456");
		returnValue.setAmount(BigDecimal  = new BigDecimal(str));
		returnValue.setCurrency("GH");
		returnValue.setTransferType("Deposit");
		
		//save to CustomerAccountDEtails to json file
		try {
			Writer writer = new FileWriter("waya.json");
			new Gson().toJson(returnValue, writer);
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return returnValue;
	}
	
	
	private void readfeedDetails() {
		Gson gson =new Gson();
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get("waya.json"));
			CustomerAccountDetails cusDetails = gson.fromJson(reader,  CustomerAccountDetails.class);
			System.out.println(cusDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	read json to map
	
   
	
}
