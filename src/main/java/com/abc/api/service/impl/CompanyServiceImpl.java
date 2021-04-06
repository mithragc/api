package com.abc.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.api.entity.CompanyDAO;
import com.abc.api.entity.DeptDAO;
import com.abc.api.entity.EmpDAO;
import com.abc.api.entity.SklDAO;
import com.abc.api.model.Company;
import com.abc.api.model.Department;
import com.abc.api.model.Employee;
import com.abc.api.model.EmployeeDetails;
import com.abc.api.model.Skill;
import com.abc.api.repository.impl.CompanyRepositoryImpl;
import com.abc.api.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepositoryImpl repo;

	@Override
	public void saveEmployeeDetails(Company cmp) {
		CompanyDAO cmpanyDAO = companyDAODetails(cmp);
		EmpDAO empDAO = new EmpDAO();
		List<DeptDAO> deptDAOList = new ArrayList<>();
		List<Employee> employeeList = cmp.getEmp();
		List<EmpDAO> empDAOList = new ArrayList<>();
		if (!employeeList.isEmpty()) {
			employeeDetails(deptDAOList, employeeList, empDAOList, cmpanyDAO, empDAO);

		}

		cmpanyDAO.setDeptId(deptDAOList);
		cmpanyDAO.setEmpId(empDAOList);
		empDAO.setCmpny(cmpanyDAO);
	}

	private void employeeDetails(List<DeptDAO> deptDAOList, List<Employee> employeeList, List<EmpDAO> empDAOList,
			CompanyDAO cmpanyDAO, EmpDAO empDAO) {
		for (Employee employee : employeeList) {
			empDAO = setEmployeeDeails(empDAOList, employee);
			List<DeptDAO> deptList = departmentDetails(deptDAOList, employee, cmpanyDAO, empDAO);
			List<SklDAO> sklDAOList = skillDetails(employee, empDAO, cmpanyDAO);
			empDAO.setDeptId(deptList);
			empDAO.setSkillId(sklDAOList);

		}
	}

	private EmpDAO setEmployeeDeails(List<EmpDAO> empDAOList, Employee employee) {
		EmpDAO empDAO = new EmpDAO();
		if (null != employee.getDesignation()) {
			empDAO.setDesignation(employee.getDesignation());
		}
		if (0 != employee.getEmpId()) {
			empDAO.setEmpId(employee.getEmpId());
		}
		if (null != employee.getEmpName()) {
			empDAO.setEmpName(employee.getEmpName());
		}
		if (0 != employee.getSalary()) {
			empDAO.setSalary(employee.getSalary());
		}
		empDAOList.add(empDAO);
		return empDAO;
	}

	private List<SklDAO> skillDetails(Employee employee, EmpDAO empDAO, CompanyDAO cmpanyDAO) {
		List<Skill> sklList = employee.getSkillList();
		List<SklDAO> sklDAOList = new ArrayList<>();
		if (!sklList.isEmpty()) {
			for (Skill skl : sklList) {
				SklDAO sklDAO = new SklDAO();
				if (0 != skl.getSkillId()) {
					sklDAO.setSkillId(skl.getSkillId());
				}
				if (null != skl.getSkillName()) {
					sklDAO.setSkillName(skl.getSkillName());
				}
				empDAO.setCmpny(cmpanyDAO);
				sklDAO.setEmp(empDAO);
				sklDAOList.add(sklDAO);

			}
			repo.saveSkillDetails(sklDAOList);
		}
		return sklDAOList;
	}

	private List<DeptDAO> departmentDetails(List<DeptDAO> deptDAOList, Employee employee, CompanyDAO cmpanyDAO,
			EmpDAO empDAO) {
		List<Department> deptList = employee.getDeptList();
		DeptDAO deptDAO = new DeptDAO();
		if (!deptList.isEmpty()) {
			for (Department department : deptList) {
				deptDAO = new DeptDAO();
				if (0 != department.getDeptId()) {
					deptDAO.setDeptId(department.getDeptId());
				}
				if (null != department.getDeptName()) {
					deptDAO.setDeptName(department.getDeptName());
				}
				deptDAO.setCmpny(cmpanyDAO);
				deptDAO.setEmp(empDAO);
				deptDAOList.add(deptDAO);

			}

			repo.saveDepartmentDetails(deptDAOList);
		}
		return deptDAOList;
	}

	private CompanyDAO companyDAODetails(Company cmp) {
		CompanyDAO cmpanyDAO = new CompanyDAO();
		if (0 != cmp.getCompanyId()) {
			cmpanyDAO.setCmpId(cmp.getCompanyId());
		}
		if (null != cmp.getCompanyName()) {
			cmpanyDAO.setCompanyName(cmp.getCompanyName());
		}

		return cmpanyDAO;
	}

	@Override
	public List<EmployeeDetails> getEmployeeDetail(long id) {
		List<EmployeeDetails> cmp = repo.getEmployeeDetail(id);
		return cmp;

	}

	@Override
	public List<EmployeeDetails> getAllEmployeeDetails() {
		List<EmployeeDetails> cmp = repo.getAllEmployeeDetails();
		return cmp;
	}

	@Override
	public void updateEmployeeDetails(Company cmp) {
		CompanyDAO cmpanyDAO = companyDAODetails(cmp);

		List<DeptDAO> deptDAOList = new ArrayList<>();
		List<Employee> employeeList = cmp.getEmp();
		List<EmpDAO> empDAOList = new ArrayList<>();
		EmpDAO empDAO = new EmpDAO();
		if (!employeeList.isEmpty()) {
			for (Employee employee : employeeList) {
				empDAO = setEmployeeDeails(empDAOList, employee);
				List<DeptDAO> deptList = updatedepartmentDetails(deptDAOList, employee, cmpanyDAO, empDAO);
				List<SklDAO> sklDAOList = updateskillDetails(employee, cmpanyDAO, empDAO);
				empDAO.setDeptId(deptList);
				empDAO.setSkillId(sklDAOList);

			}

		}

		cmpanyDAO.setDeptId(deptDAOList);
		cmpanyDAO.setEmpId(empDAOList);
		empDAO.setCmpny(cmpanyDAO);
	}

	private List<DeptDAO> updatedepartmentDetails(List<DeptDAO> deptDAOList, Employee employee, CompanyDAO cmpanyDAO,
			EmpDAO empDAO) {
		List<Department> deptList = employee.getDeptList();
		DeptDAO deptDAO = new DeptDAO();
		if (!deptList.isEmpty()) {
			for (Department department : deptList) {
				deptDAO = new DeptDAO();
				if (0 != department.getDeptId()) {
					deptDAO.setDeptId(department.getDeptId());
				}
				if (null != department.getDeptName()) {
					deptDAO.setDeptName(department.getDeptName());
				}
				deptDAO.setCmpny(cmpanyDAO);
				deptDAO.setEmp(empDAO);
				deptDAOList.add(deptDAO);

			}

			repo.updateDepartmentDetails(deptDAOList);
		}
		return deptDAOList;
	}

	private List<SklDAO> updateskillDetails(Employee employee, CompanyDAO cmpanyDAO, EmpDAO empDAO) {
		List<Skill> sklList = employee.getSkillList();
		List<SklDAO> sklDAOList = new ArrayList<>();
		if (!sklList.isEmpty()) {
			for (Skill skl : sklList) {
				SklDAO sklDAO = new SklDAO();
				if (0 != skl.getSkillId()) {
					sklDAO.setSkillId(skl.getSkillId());
				}
				if (null != skl.getSkillName()) {
					sklDAO.setSkillName(skl.getSkillName());
				}
				empDAO.setCmpny(cmpanyDAO);
				sklDAO.setEmp(empDAO);
				sklDAOList.add(sklDAO);

			}
			repo.updateSkillDetails(sklDAOList);
		}
		return sklDAOList;
	}

	@Override
	public String deleteEmployeeDetails(long id) {
		String res = repo.deleteEmployeeDetails(id);
		return res;

	}

}
