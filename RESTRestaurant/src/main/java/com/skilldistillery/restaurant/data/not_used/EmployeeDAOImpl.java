package com.skilldistillery.restaurant.data.not_used;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.restaurant.entities.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Employee createEmployee(Employee employee) {
		em.persist(employee);
        return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return em.merge(employee);
	}

	@Override
	public void deleteEmployee(int employeeId) {
		Employee employee = em.find(Employee.class, employeeId);
        if (employee != null) {
            em.remove(employee);
        }
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		return em.find(Employee.class, employeeId);
	}

	@Override
	public List<Employee> getAllEmployees() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
	}

}
