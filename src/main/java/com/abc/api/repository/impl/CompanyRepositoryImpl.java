package com.abc.api.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abc.api.entity.DeptDAO;
import com.abc.api.entity.EmpDAO;
import com.abc.api.entity.SklDAO;
import com.abc.api.model.EmployeeDetails;
import com.abc.api.repository.CompanyRepository;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void saveDepartmentDetails(List<DeptDAO> deptDAOList) {
		for (DeptDAO deptDAO2 : deptDAOList) {
			em.persist(deptDAO2);
		}
	}

	@Override
	@Transactional
	public void saveSkillDetails(List<SklDAO> sklDAOList) {
		for (SklDAO sklDAO : sklDAOList) {
			em.persist(sklDAO);
		}
	}

	public List<EmployeeDetails> getEmployeeDetail(long id) {
		final String getEmployeeQuery = "Select c.cmp_id,c.cmp_nm,d.dept_id,d.dept_nm,e.emp_id,e.designation,e.emp_nm,e.slry,s.skill_id,s.skill_nm from cmpny c join dept d on c.cmp_id = d.cmp_id join employee e on e.cmp_id = d.cmp_id join skill s on s.emp_id = e.emp_id where e.emp_id =? order by e.slry";
		Query query = em.createNativeQuery(getEmployeeQuery);
		query.setParameter(1, id);
		List<Object[]> cmpnyList1 = query.getResultList();
		List<EmployeeDetails> empList = new ArrayList<>();
		for (Object[] result : cmpnyList1) {
			setEmployeeDetails(empList, result);

		}

		return empList;

	}

	private void setEmployeeDetails(List<EmployeeDetails> empList, Object[] result) {
		EmployeeDetails empDetails = new EmployeeDetails();
		empDetails.setCompanyId((int) result[0]);
		empDetails.setCompanyName((String) result[1]);
		empDetails.setDesignation((String) result[5]);
		BigInteger empId = (BigInteger) result[4];
		BigInteger salary = (BigInteger) result[7];
		empDetails.setEmpId(empId.longValue());
		empDetails.setSalary(salary.longValue());
		empDetails.setEmpName((String) result[6]);
		empDetails.setDeptId((int) result[2]);
		empDetails.setDeptName((String) result[3]);
		empDetails.setSkillId((int) result[8]);
		empDetails.setSkillName((String) result[9]);
		empList.add(empDetails);
	}

	@Override
	public List<EmployeeDetails> getAllEmployeeDetails() {
		final String getAllEmployeesQuery = "Select c.cmp_id,c.cmp_nm,d.dept_id,d.dept_nm,e.emp_id,e.designation,e.emp_nm,e.slry,s.skill_id,s.skill_nm from cmpny c join dept d on c.cmp_id = d.cmp_id join employee e on e.cmp_id = d.cmp_id join skill s on s.emp_id = e.emp_id order by  e.slry";
		Query query = em.createNativeQuery(getAllEmployeesQuery);
		List<Object[]> cmpnyList1 = query.getResultList();
		List<EmployeeDetails> empList = new ArrayList<>();
		for (Object[] result : cmpnyList1) {
			setEmployeeDetails(empList, result);

		}

		return empList;
	}

	@Override
	@Transactional
	public void updateDepartmentDetails(List<DeptDAO> deptDAOList) {
		for (DeptDAO deptDAO : deptDAOList) {
			DeptDAO dpt = (DeptDAO) em.find(DeptDAO.class, deptDAO.getDeptId());
			EmpDAO emp = (EmpDAO) em.find(EmpDAO.class, deptDAO.getEmp().getEmpId());

			if (null == dpt && null == emp) {
				em.persist(deptDAO);
			} else if (null != dpt) {
				em.merge(deptDAO);
			}
		}
	}

	@Override
	@Transactional
	public void updateSkillDetails(List<SklDAO> sklDAOList) {

		for (SklDAO sklDAO : sklDAOList) {
			SklDAO skl = (SklDAO) em.find(SklDAO.class, sklDAO.getSkillId());
			EmpDAO emp = (EmpDAO) em.find(EmpDAO.class, sklDAO.getEmp().getEmpId());
			if (null == skl && null == emp) {
				em.persist(sklDAO);
			} else if (null != skl) {
				em.merge(sklDAO);
			}
		}
	}

	@Override
	@Transactional
	public String deleteEmployeeDetails(long empId) {
		String result = "";
		final String deleteQuery = "delete from table where emp_id = ?";
		String[] tableArray = new String[] { "dept", "skill", "employee" };
		EmpDAO emp = (EmpDAO) em.find(EmpDAO.class, empId);
		if (null != emp) {
			for (String table : tableArray) {
				em.createNativeQuery(deleteQuery.replace("table", table)).setParameter(1, empId).executeUpdate();
			}
			result = "Successfully deleted the employee details";
		} else {
			result = "employee is not available";
		}
		return result;
	}

}
