package com.abc.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "dept")
public class DeptDAO {
	
	
@Id
	@Column(name = "dept_id")
	private int deptId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="emp_id")
	private EmpDAO emp;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "cmp_id")
	private CompanyDAO cmpny;
	
	@Column(name = "dept_nm")
	private String deptName;
	
	
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public EmpDAO getEmp() {
		return emp;
	}
	public void setEmp(EmpDAO emp) {
		this.emp = emp;
	}
	public CompanyDAO getCmpny() {
		return cmpny;
	}
	public void setCmpny(CompanyDAO cmpny) {
		this.cmpny = cmpny;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	

}
