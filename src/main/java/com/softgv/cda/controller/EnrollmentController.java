package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.EnrollmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value = "/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService service;

	
	@Operation(summary = "To Save  The  EnrollMent Based On User Id and Course Id", description = "This API Will Save  The  EnrollMent Based On User Id and Course Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Enrollment Saved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save Enrollment...") })

	@PostMapping("/{uid}/{cid}")
	public ResponseEntity<?> saveEnrollment(@PathVariable int uid, @PathVariable int cid) {
		return service.saveEnrollment(uid, cid);
	}
   
	@Operation(summary = "To Get The  EnrollMent Based On User Id", description = "This API Will Get The  EnrollMent Based On User Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Enrollment FOund Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Found Enrollment...") })

	@GetMapping(value = "/user/{uid}")
	public ResponseEntity<?> findEnrollmemtByUserId(@PathVariable(name = "uid") int uid) {
		return service.findEnrollmentByUserId(uid);
	}

	@Operation(summary = "To Get The  EnrollMent Based On Faculty Id", description = "This API Will Get The  EnrollMent Based On Faculty Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Enrollment FOund Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Found Enrollment...") })

	@GetMapping(value = "/faculty/{fid}")
	public ResponseEntity<?> findAllEnrollmentsByFacultyProfileId(@PathVariable(name = "fid") int fid){
		return service.findAllEnrollmentsByFacultyProfileId(fid);
	}
	
	
}
