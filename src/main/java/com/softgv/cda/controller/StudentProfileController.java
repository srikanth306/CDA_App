package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.softgv.cda.service.StudentProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//@CrossOrigin
@RestController
@RequestMapping(value = "/studentprofiles")
public class StudentProfileController {
	@Autowired
	StudentProfileService studentProfileService;

	
	@Operation(summary = "To Create The Student", description = "This API Will Accept The Request body of Student Entity and Persists To The Database table")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Student Saved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save Student...") })

	@PostMapping
	public ResponseEntity<?> saveStudentProfile(@RequestParam int uid, @RequestParam MultipartFile file) {
		return studentProfileService.saveStudentProfile(uid, file);
	}
	
	@Operation(summary = "To Get The Student By Id", description = "This API Will Get The  Student Entity By Id From The Database table")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Student Found Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Found Student...") })

	@GetMapping("/{id}")
	public ResponseEntity<?> findStudentProfileById(@PathVariable int id){
		return studentProfileService.findStudentProfileById(id);
	}
	
	@Operation(summary = "To Get The All Students ", description = "This API Will Get The All Students Entity From The Database table")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Students Found Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Found Students...") })

	@GetMapping
	public ResponseEntity<?> findAllStudentProfiles(){
		return studentProfileService.findAllStudentProfiles();
	}
	
	@Operation(summary = "To Assign Department To StudentProfile", description = "This API Will Assign Department To StudentProfile  Entity By UserId and Department Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Student Details Changed Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To change  Student Details...") })
	
	@PatchMapping(value = "/{uid}/{did}")
	public ResponseEntity<?> assignDepartmentToStudentProfile(@PathVariable(name = "uid") int uid,@PathVariable(name = "did") int did){
		return studentProfileService.assignDepartmentToStudentProfile(uid,did);
	}

	
	@Operation(summary = "To Assign Year Of Passed Out To StudentProfile By Id", description = "This API Will Assign Year Of Passed Out To StudentProfile  Entity By Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Student Details Changed Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To change  Student Details...") })
	@PatchMapping(value = "/year/{uid}/{year}")
	public ResponseEntity<?> setYearToStudentProfile(@PathVariable(name = "uid") int id, @PathVariable(name = "year") String year){
		return studentProfileService.setYearToStudentProfile(id,year); 
	}
	
	
	@Operation(summary = "To Set Active Status to  StudentProfile By Id and Otp", description = "This API Will Set Active Status to  StudentProfile By Id and Otp")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Student Details Changed Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Invalid Otp...") })
	@PatchMapping("/validateOtp/{id}/{otp}")
	public ResponseEntity<?> setStudentToActiveStatus(@PathVariable int id,@PathVariable int otp)
	{
		return studentProfileService.setStudentToActiveStatus(id,otp);
	}
	
	
}
