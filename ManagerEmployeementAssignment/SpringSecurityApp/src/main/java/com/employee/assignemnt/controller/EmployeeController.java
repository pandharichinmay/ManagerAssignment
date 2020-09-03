package com.employee.assignemnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.assignemnt.model.ApiResponse;
import com.employee.assignemnt.model.EmployeeDto;
import com.employee.assignemnt.service.EmployeeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	 @Autowired
	    private EmployeeService employeeService;

	    @PostMapping
	    public ApiResponse saveEmployee(@RequestBody EmployeeDto employee){
	        return new ApiResponse(HttpStatus.OK.value(), "Employee saved successfully.",employeeService.save(employee));
	    }

	    @GetMapping
	    public ApiResponse listEmployee(){
	        return new ApiResponse(HttpStatus.OK.value(), "Employee list fetched successfully.",employeeService.findAll());
	    }

	    @GetMapping("/{id}")
	    public ApiResponse getOne(@PathVariable int id){
	        return new ApiResponse(HttpStatus.OK.value(), "Employee fetched successfully.",employeeService.findById(id));
	    }

	    @PutMapping("/{id}")
	    public ApiResponse update(@RequestBody EmployeeDto employeeDto) {
	        return new ApiResponse(HttpStatus.OK.value(), "Employee updated successfully.",employeeService.update(employeeDto));
	    }

	    @DeleteMapping("/{id}")
	    public ApiResponse delete(@PathVariable int id) {
	    	employeeService.delete(id);
	        return new ApiResponse(HttpStatus.OK.value(), "Employee deleted successfully.", null);
	    }

}
