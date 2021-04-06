package com.abc.api.repository;

import java.util.List;

import com.abc.api.entity.DeptDAO;
import com.abc.api.entity.SklDAO;
import com.abc.api.model.EmployeeDetails;

public interface CompanyRepository {
	

	void saveDepartmentDetails(List<DeptDAO> deptDAO);

	void saveSkillDetails(List<SklDAO> sklDAO);
	
	List<EmployeeDetails> getEmployeeDetail(long id) ;
	
	List<EmployeeDetails> getAllEmployeeDetails();

	void updateSkillDetails(List<SklDAO> sklDAOList);

	void updateDepartmentDetails(List<DeptDAO> deptDAOList);

	String deleteEmployeeDetails(long empId);
}
