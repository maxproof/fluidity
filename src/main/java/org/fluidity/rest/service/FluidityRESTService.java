package org.fluidity.rest.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.fluidity.rest.dto.Employee;

//service
public class FluidityRESTService {

	//stor emplyees in Map rather than in DB
	private static Map<Integer, String> employeesMap = new HashMap<Integer, String>();
	
	//get employee
	@Path("/employee/{id}")
	@GET
	@Produces({"application/json"})
	@Consumes({"application/json"})
	private Response get(@PathParam("id") Integer id, @QueryParam("QueryParamId") Integer queryParam) {
		
		//if employee exists
		Employee employee = new Employee();
		String employeeName = null;
		Integer employeeId = null;
		if(employeesMap.containsKey(id)) {
			employeeId = id;
				employeeName = employeesMap.get(id);
		} else if(employeesMap.containsKey(queryParam)) {
			employeeId = queryParam;
			employeeName = employeesMap.get(queryParam);
		}
		
		//could not find employee
		if(employeeId == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Employee having Id " + id + " not found").build();
		} else {
			//populate employee
			employee.setId(employeeId);
			employee.setName(employeeName);
			return Response.status(Response.Status.OK).entity(employee).build();
		}
		
	}
	
	//create employee id in memory with id = 1
	private static Integer empId = 1;
	
	//create employee
	@Path("/employee")
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	public Employee create(Employee employee ) {
		
		String employeeName = employee.getName();
		employeesMap.put(empId, employeeName);
		
		//prepare employee to send as response
		Employee createdEmployee = new Employee();
		createdEmployee.setId(empId);
		createdEmployee.setName(employeeName);
		//increment employee id
		empId++;
		return createdEmployee;
	}
	
	//update employee
	@Path("/employee")
	@PUT
	@Produces({"application/json"})
	@Consumes({"application/json"})	
	public Employee update(Employee employee) {
		
		employeesMap.put(employee.getId(), employee.getName());
		return employee;
	}
	
	//delete employee: if not, return 404
	@Path("/employee/{id}")
	@DELETE
	@Produces({"application/json"})
	@Consumes({"application/json"})	
	public Response delete(@PathParam("id") Integer id) {
		
		if(employeesMap.containsKey(id)) {
			String employeeName = employeesMap.get(id);
			Employee employee = new Employee();
			employee.setId(id);
			employee.setName(employeeName);
			//delete employee from map
			employeesMap.remove(id);
			return Response.status(Response.Status.OK).entity(employee).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Employee having Id " + id + " not found.").build();	
		}
	}
}
