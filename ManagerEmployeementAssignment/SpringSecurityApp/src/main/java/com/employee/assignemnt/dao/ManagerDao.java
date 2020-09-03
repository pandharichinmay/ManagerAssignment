package com.employee.assignemnt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.assignemnt.model.Manager;

@Repository
public interface ManagerDao extends CrudRepository {

	Manager findAllByEmail(String email);

	
}
