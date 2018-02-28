package com.bytestree.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytestree.restful.model.Employee;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
