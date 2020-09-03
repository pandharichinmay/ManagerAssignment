package com.employee.assignemnt.service;

import java.util.List;

import com.employee.assignemnt.model.Employee;
import com.employee.assignemnt.model.EmployeeDto;

public interface EmployeeService {
	Employee save(EmployeeDto employee);

	List<Employee> findAll();

	void delete(int id);

	Employee findOne(String firstname);

	Employee findById(int id);

	EmployeeDto update(EmployeeDto userDto);
}
