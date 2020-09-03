package com.employee.assignemnt.service;

import java.util.List;

import com.employee.assignemnt.model.Manager;
import com.employee.assignemnt.model.ManagerDto;

public interface ManagerService {
	Manager save(ManagerDto manager);

	List<Manager> findAll();

	Manager findOne(String email);

}
