package com.bytestree.restful.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytestree.restful.model.Employee;
import com.bytestree.restful.repository.EmployeeRepository;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
@Service
public class DefaultEmployeeService implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee save(Employee entity) {
		return employeeRepository.save(entity);
	}

	@Override
	public Employee getById(Serializable id) {
		return employeeRepository.findOne((Long) id);
	}

	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void delete(Serializable id) {
		employeeRepository.delete((Long) id);
	}

}
