package com.abc.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "skill")
public class SklDAO {
	
	@Id
	@Column(name = "skill_id" )
	private int skillId;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "emp_id")
	private EmpDAO emp;
	
	@Column(name ="skill_nm")
	private String skillName;

	
	
	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	
	public EmpDAO getEmp() {
		return emp;
	}

	public void setEmp(EmpDAO emp) {
		this.emp = emp;
	}
	
	
	
}
