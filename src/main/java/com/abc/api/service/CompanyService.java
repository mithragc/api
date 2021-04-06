package com.abc.api.service;

import java.util.List;

import com.abc.api.model.Company;
import com.abc.api.model.EmployeeDetails;

public interface CompanyService {
	

	void saveEmployeeDetails(Company cmp);
	
	List<EmployeeDetails> getEmployeeDetail(long id);
	
	List<EmployeeDetails> getAllEmployeeDetails();

	void updateEmployeeDetails(Company cmp);

	String deleteEmployeeDetails(long id);

}
