package com.bytestree.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 * 
 */
@Entity
@Table(name = "employee")
public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = 4910225916550731446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "firstname", length = 50)
	private String firstname;

	@Column(name = "lastname", length = 50)
	private String lastname;

	@Column(name = "designation", length = 20)
	private String designation;

	@Column(name = "salary")
	private Integer salary;

	public Employee() {
	}

	public Employee(Long id) {
		this.id = id;
	}

	public Employee(Long id, String firstname, String lastname, String designation, Integer salary) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.designation = designation;
		this.salary = salary;
	}

	public Employee(String firstname, String lastname, String designation, Integer salary) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.designation = designation;
		this.salary = salary;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getSalary() {
		return this.salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Id: ").append(this.id).append(", firstName: ").append(this.firstname).append(", lastName: ")
				.append(this.lastname).append(", Designation: ").append(this.designation).append(", Salary: ")
				.append(this.salary);
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (id == null || obj == null || getClass() != obj.getClass())
			return false;
		Employee toCompare = (Employee) obj;
		return id.equals(toCompare.id);
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

}
