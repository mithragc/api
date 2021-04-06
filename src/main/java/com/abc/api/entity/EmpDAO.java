package com.abc.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class EmpDAO {
	@Id
	@Column(name = "emp_id" )
	private long empId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name ="cmp_id")
	private CompanyDAO cmpny;
	
	@OneToMany(mappedBy = "emp")
	private List<SklDAO> skillId;

	@Column(name ="designation")
	private String designation;
	
	
	@Column(name = "emp_nm")
	private String empName;
	
	@Column(name = "slry")
	private long salary;
	
	@OneToMany(mappedBy = "emp")
	private List<DeptDAO> deptId;

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public CompanyDAO getCmpny() {
		return cmpny;
	}

	public void setCmpny(CompanyDAO cmpny) {
		this.cmpny = cmpny;
	}

	public List<SklDAO> getSkillId() {
		return skillId;
	}

	public void setSkillId(List<SklDAO> skillId) {
		this.skillId = skillId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public List<DeptDAO> getDeptId() {
		return deptId;
	}

	public void setDeptId(List<DeptDAO> deptId) {
		this.deptId = deptId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	
	
}
