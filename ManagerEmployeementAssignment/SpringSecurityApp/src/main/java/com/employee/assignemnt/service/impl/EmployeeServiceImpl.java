package com.employee.assignemnt.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.assignemnt.dao.EmployeeDao;
import com.employee.assignemnt.dao.ManagerDao;
import com.employee.assignemnt.model.Employee;
import com.employee.assignemnt.model.EmployeeDto;
import com.employee.assignemnt.model.Manager;
import com.employee.assignemnt.model.ManagerDto;
import com.employee.assignemnt.service.EmployeeService;

@Transactional
@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeDetailsService, EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ManagerDao managerDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public ManagerDetails loadManagerByEmail(String email) throws UsernameNotFoundException {
		Manager manager = managerDao.findAllByEmail(email);
		if (manager == null) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}
		return new org.springframework.security.core.userdetails.User(manager.getEmail(), manager.getPassword(),
				getAuthority());
	}

	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List findAll() {
		List list = new ArrayList<>();
		employeeDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		employeeDao.deleteById(id);
	}

	@Override
	public EmployeeDao findOne(String email) {
		return employeeDao.findByUsername(email);
	}

	@Override
	public EmployeeDao findById(int id) {
		Optional optionalUser = employeeDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

	public Manager update(ManagerDto userDto) {
		ManagerDao user = findById(userDto.getIdManager());
		if (user != null) {
			BeanUtils.copyProperties(managerDao, user, "password", "email");
			managerDao.save(user);
		}
		return userDto;
	}

	@Override
	public Employee save(EmployeeDto employee) {
		// TODO Auto-generated method stub
		Employee newUser = new Employee();
		newUser.setIdManager(newUser.getIdManager());
		newUser.setFirstname(newUser.getFirstname());
		newUser.setLastname(newUser.getLastname());
		newUser.setMobile(newUser.getMobile());
		newUser.setDob(newUser.getDob());
		newUser.setAddress(newUser.getAddress());
		newUser.setCity(newUser.getCity());
		return employeeDao.save(newUser);
	}

	@Override
	public Employee findOne(String firstname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDto update(EmployeeDto userDto) {
		// TODO Auto-generated method stub
		EmployeeDao user = findById(userDto.getEmpId());
		if (user != null) {
			BeanUtils.copyProperties(employeeDao, user);
			employeeDao.save(user);
		}
		return userDto;
	}
}