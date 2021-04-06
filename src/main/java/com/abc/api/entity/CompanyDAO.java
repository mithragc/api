package com.abc.api.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cmpny")
public class CompanyDAO {
	
	@Id
	@Column(name = "cmp_id")
	private int cmpId;
	
	@Column(name = "cmp_nm")
	private String CompanyName;
	
	@OneToMany(mappedBy = "cmpny")
	private List<DeptDAO> deptId;
	
	@OneToMany(mappedBy = "cmpny")
	private List<EmpDAO> empId;

	public int getCmpId() {
		return cmpId;
	}

	public void setCmpId(int cmpId) {
		this.cmpId = cmpId;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public List<DeptDAO> getDeptId() {
		return deptId;
	}

	public void setDeptId(List<DeptDAO> deptId) {
		this.deptId = deptId;
	}

	public List<EmpDAO> getEmpId() {
		return empId;
	}

	public void setEmpId(List<EmpDAO> empId) {
		this.empId = empId;
	}


	
}
