package com.employee.assignemnt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.assignemnt.model.Employee;

@Repository
public interface EmployeeDao extends CrudRepository {

	Employee findByFirstName(String firstname);

}
