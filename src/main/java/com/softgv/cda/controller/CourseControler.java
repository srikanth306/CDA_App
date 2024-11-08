package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softgv.cda.entity.Course;
import com.softgv.cda.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value = "/courses")
public class CourseControler {
	
	@Autowired
	private CourseService service;
	
	@Operation(summary = "To Save The  Course ", description = "This API Will Save The  Course ")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Course Saved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save Course...") })

	
	@PostMapping
	public ResponseEntity<?> saveCourse(@RequestBody Course course){
		return service.saveCourse(course);
	}
	
	
	@Operation(summary = "To Get The  Course By Id ", description = "This API Will Get The  Course By Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Course Found Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Find Course...") })

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findCourseById(@PathVariable int id){
		return service.findCourseById(id);
	}
	
	
	@Operation(summary = "To Asiign Faculty to Course By Course Id and Faculty Id ", description = "This API Will  Asiign Faculty to Course By Course Id and Faculty Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = " Sucessfully Assign Faculty To Course..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save Course...") })

	@PatchMapping(value = "/{cid}/{fid}")
	public ResponseEntity<?> assignFacultyToCourse(@PathVariable(name = "cid") int id, @PathVariable(name = "fid") int fid){
		return service.assignFacultyToCourse(id,fid);
	}
	
	@Operation(summary = "To Get The All Courses ", description = "This API Will Get The  All Courses")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Course SAved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save Course...") })

	@GetMapping
	public ResponseEntity<?> findAllCourses(){
		return service.findAllCourses();
	}
	
	@Operation(summary = "To Delete The  Course By Id ", description = "This API Will Delete The  Course By Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Course Deleted Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Delete Course...") })

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCourseById(@PathVariable int id){
		return service.deleteCourseById(id);
	}
	
}
