package com.abc.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abc.api.model.Company;
import com.abc.api.model.EmployeeDetails;
import com.abc.api.service.impl.CompanyServiceImpl;

@Controller
@RequestMapping(value = "/api")
public class CompanyController {

	@Autowired
	CompanyServiceImpl srvc;

	@PostMapping(value = "/saveemployeedetails")
	public ResponseEntity<String> saveEmployeeDetails(@RequestBody Company cmp) {
		srvc.saveEmployeeDetails(cmp);
		return new ResponseEntity<String>("Successfully saved employee details", HttpStatus.OK);

	}

	@GetMapping(value = { "/getemployeedetails/{id}", "/getemployeedetails" })
	public ResponseEntity<List<EmployeeDetails>> getEmployeeDetails(@PathVariable(required = false) Long id) {
		if (null != id) {
			List<EmployeeDetails> cmp = srvc.getEmployeeDetail(id);
			return new ResponseEntity<List<EmployeeDetails>>(cmp, HttpStatus.OK);
		} else {
			List<EmployeeDetails> cmp = srvc.getAllEmployeeDetails();
			return new ResponseEntity<List<EmployeeDetails>>(cmp, HttpStatus.OK);
		}

	}

	@PutMapping(value = "/updateemployeedetails")
	public ResponseEntity<String> updateEmployeeDetails(@RequestBody Company cmp) {

		srvc.updateEmployeeDetails(cmp);
		return new ResponseEntity<String>("Successfully updated the employee details", HttpStatus.OK);

	}

	@DeleteMapping(value = "/deleteEmployee/{id}")
	public ResponseEntity<String> deleteEmployeeDetails(@PathVariable long id) {
		String res = srvc.deleteEmployeeDetails(id);
		return new ResponseEntity<String>(res, HttpStatus.OK);

	}

}
