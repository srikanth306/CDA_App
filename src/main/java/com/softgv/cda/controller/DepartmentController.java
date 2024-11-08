package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softgv.cda.entity.Department;
import com.softgv.cda.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@CrossOrigin
@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@Operation(summary = "To Save The  DepartMent ", description = "This API Will Save The  Department ")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "DepartMent SAved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save Department...") })

	@PostMapping
	public ResponseEntity<?> saveDepartment(@RequestBody Department department){
		return service.saveDepartment(department);
	}
	
	@Operation(summary = "To Get The  Department Based On User Id", description = "This API Will Get The  Department Based On Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Enrollment FOund Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Found Department...") })

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findDepartmentById(@PathVariable int id){
		return service.findDepartmentById(id);
	}
	
	
	@Operation(summary = "To Get The All  Departments ", description = "This API Will Get The  To Get The All  Departments")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Departments FOund Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Found Departments...") })

	@GetMapping
	public ResponseEntity<?> findAllDepartments(){
		return service.findAllDepartments();
	}
	
	@Operation(summary = "To Delete The   Department By Id ", description = "This API Will To Delete The   Department By Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Department FOund Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Found Department...") })

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteDepartmentById(@PathVariable int id){
		return service.deleteDepartmentById(id);
	}
}
