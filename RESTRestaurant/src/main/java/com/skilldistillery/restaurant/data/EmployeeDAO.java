package com.skilldistillery.restaurant.data;

import java.util.List;

import com.skilldistillery.restaurant.entities.Employee;

public interface EmployeeDAO {

	Employee createEmployee(Employee employee);
	Employee updateEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	Employee getEmployeeById(int employeeId);
	List<Employee> getAllEmployees();
	
}
