package org.fluidity.rest.client;

import java.util.Collections;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.fluidity.rest.dto.Employee;



//Apache CXF to invoke service
public class RestServiceClient {
	
	//service base endpoint url
	private static final String serviceURL = "http://localhost:8081/fluidity/services";

	//create service client basic auth; Jackson provides consume service
	private static WebClient getBasicClient() {
		
		//user auth
		String userName = "max";
		String password = "proof";
		//if null
		String configLocation = null;
		WebClient webClient = WebClient.create(serviceURL, Collections.singletonList(new JacksonJsonProvider()), userName, password, configLocation);
		return webClient;
	}
	
	//CRUD methods
	
	//get employee
	private static void getEmployee(Integer employeeId) {
		//basic auth
		WebClient client = getBasicClient();
		//path to GET
		client.path("employee").path(employeeId).query("QueryParamId", employeeId);
		//service accepts json
		client.accept(MediaType.APPLICATION_JSON_TYPE);
		//response is employee object
		//GET
		try {
			Employee employee = client.get(Employee.class);
			System.out.println("Employee found: " + employee);
		} catch (NotFoundException e) {
			System.out.println("Employee could not be found: " + e.getMessage());
		}
	}
	
	//create employee and return id
	private static Integer createEmployee() {
		
		WebClient client = getBasicClient();
		//path to POST
		client.path("employee");
		//accepting json type
		client.accept(MediaType.APPLICATION_JSON_TYPE);
		//POST
		Employee employee = new Employee();
		employee.setName("max proof");
		//return created employee
		Employee createdEmployee = client.type(MediaType.APPLICATION_JSON_TYPE).post(employee, Employee.class);
		System.out.println("Employee" + createdEmployee);
		return createdEmployee.getId();
	}
	
	//update created employee
	private static Integer updateEmployee(Integer employeeId) {
		
		WebClient client = getBasicClient();
		client.path("employee");
		client.accept(MediaType.APPLICATION_JSON_TYPE);
		Employee employee = new Employee();
		employee.setId(employeeId);
		employee.setName("max proof updated");
		Employee updatedEmployee = client.type(MediaType.APPLICATION_JSON_TYPE).post(employee, Employee.class);
		System.out.println("Employee updated: " + updatedEmployee);
		return updatedEmployee.getId();
	}
	
	//delete employee if exists (based on id)
	private static void deleteEmployee(Integer employeeId) {
		WebClient client = getBasicClient();
		client.path("employee").path(employeeId);
		client.accept(MediaType.APPLICATION_JSON_TYPE);
		try {
			Response employee = client.delete();
			System.out.println("Employee deleted: " + employee.readEntity(Employee.class));
		} catch (NotFoundException e) {
			System.out.println("Employee could not be found to delete: " + e.getMessage());
		}
		
	}
	
	//invoke methods
	public static void main(String[] args) {
		
		Integer employeeId = 1000;
		getEmployee(employeeId);//no employee created with id = 1000
		
		employeeId = createEmployee(); //create employee with id = 1
		
		//get created employee
		getEmployee(employeeId);
		
		//update created employee
		employeeId = updateEmployee(employeeId);
		
		//get updated employee
		getEmployee(employeeId);
		
		//delete updated employee
		deleteEmployee(employeeId);
		
		//try to get deleted employee
		getEmployee(employeeId);
		
	}
	
}
