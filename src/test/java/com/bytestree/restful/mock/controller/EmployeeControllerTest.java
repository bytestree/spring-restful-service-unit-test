package com.bytestree.restful.mock.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bytestree.restful.TestUtils;
import com.bytestree.restful.controller.EmployeeController;
import com.bytestree.restful.model.Employee;
import com.bytestree.restful.service.EmployeeService;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author bytesTree
 * @see <a href="http://www.bytestree.com/">BytesTree</a>
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	EmployeeService empService;

	private final String URL = "/employee/";

	@Test
	public void testAddEmployee() throws Exception {

		// prepare data and mock's behaviour
		Employee empStub = new Employee(1l, "bytes", "tree", "developer", 12000);
		when(empService.save(any(Employee.class))).thenReturn(empStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(empStub))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);

		// verify that service method was called once
		verify(empService).save(any(Employee.class));

		Employee resultEmployee = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Employee.class);
		assertNotNull(resultEmployee);
		assertEquals(1l, resultEmployee.getId().longValue());

	}

	@Test
	public void testGetEmployee() throws Exception {

		// prepare data and mock's behaviour
		Employee empStub = new Employee(1l, "bytes", "tree", "developer", 12000);
		when(empService.getById(any(Long.class))).thenReturn(empStub);

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(empService).getById(any(Long.class));

		Employee resultEmployee = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Employee.class);
		assertNotNull(resultEmployee);
		assertEquals(1l, resultEmployee.getId().longValue());
	}

	@Test
	public void testGetEmployeeNotExist() throws Exception {

		// prepare data and mock's behaviour
		// Not Required as employee Not Exist scenario

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);

		// verify that service method was called once
		verify(empService).getById(any(Long.class));

		Employee resultEmployee = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Employee.class);
		assertNull(resultEmployee);
	}

	@Test
	public void testGetAllEmployee() throws Exception {

		// prepare data and mock's behaviour
		List<Employee> empList = buildEmployees();
		when(empService.getAll()).thenReturn(empList);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(empService).getAll();

		// get the List<Employee> from the Json response
		TypeToken<List<Employee>> token = new TypeToken<List<Employee>>() {
		};
		@SuppressWarnings("unchecked")
		List<Employee> empListResult = TestUtils.jsonToList(result.getResponse().getContentAsString(), token);

		assertNotNull("Employees not found", empListResult);
		assertEquals("Incorrect Employee List", empList.size(), empListResult.size());

	}

	@Test
	public void testDeleteEmployee() throws Exception {
		// prepare data and mock's behaviour
		Employee empStub = new Employee(1l);
		when(empService.getById(any(Long.class))).thenReturn(empStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(URL + "{id}", new Long(1))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);

		// verify that service method was called once
		verify(empService).delete(any(Long.class));

	}

	@Test
	public void testUpdateEmployee() throws Exception {
		// prepare data and mock's behaviour
		// here the stub is the updated employee object with ID equal to ID of
		// employee need to be updated
		Employee empStub = new Employee(1l, "bytes", "tree", "developer", 12000);
		when(empService.getById(any(Long.class))).thenReturn(empStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(empStub))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(empService).save(any(Employee.class));

	}

	private List<Employee> buildEmployees() {
		Employee e1 = new Employee(1l, "bytes", "tree", "developer", 12000);
		Employee e2 = new Employee(2l, "bytes2", "tree2", "Senior developer", 16000);
		List<Employee> empList = Arrays.asList(e1, e2);
		return empList;
	}

}
