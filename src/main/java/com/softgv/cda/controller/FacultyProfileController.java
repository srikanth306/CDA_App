package com.softgv.cda.controller;

import java.time.LocalTime;

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

import com.softgv.cda.service.FacultyProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value = "/facultyprofiles")
public class FacultyProfileController {

	@Autowired
	private FacultyProfileService facultyProfileService;

	

	@Operation(summary = "To Create The Faculty By Id", description = "This API Will Accept User Id To Craete Faculty Entity By Id Into The Database table")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Faculty Saved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save Faculty...") })

	@PostMapping
	public ResponseEntity<?> saveFacultyProfile(@RequestParam int uid) {
		return facultyProfileService.saveFacultyProfile(uid);
	}

	
	@Operation(summary = "To Update The Faculty Details By Id, email,phone,Office Hours", description = "This API Will change the Faculty Details Based On Id,email,phone,Office Hours ")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Faculty Saved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save Faculty...") })

	@PatchMapping("/update")
	public ResponseEntity<?> updateInfo(@RequestParam int id, @RequestParam String  email, @RequestParam String phone,
			@RequestParam String officeHours) {
		return facultyProfileService.updateInfo(id, email, phone, officeHours);
	}

	@Operation(summary = "To Assign Department to Faculty Based On User Id and Department Id", description = "This API Will Assign Department to Faculty Based On User Id and Department Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Faculty Saved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save Faculty...") })

	@PatchMapping(value = "/{uid}/{did}")
	public ResponseEntity<?> assignDepartmentToFacultyProfile(@PathVariable(name = "uid") int uid,
			@PathVariable(name = "did") int did) {
		return facultyProfileService.assignDepartmentToFacultyProfile(uid, did);
	}

	
	
//	@PatchMapping
//	public ResponseEntity<?> updatePhoto(@RequestParam int id, @RequestParam MultipartFile file) {
//		return facultyProfileService.updatePhoto(id, file);
//	}
	
	@Operation(summary = "To Get The Faculty Profile By Id", description = "This API Will To Get The Faculty Profile By Id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Faculty Found Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Find Faculty...") })

	@GetMapping("/{id}")
	public ResponseEntity<?> findFacultyProfileById(@PathVariable int id) {
		return facultyProfileService.findFacultyProfileById(id);
	}
  
	

	@Operation(summary = "To Get The All Faculties", description = "This API Will Get The All  Faculty Entities From The Database table")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = " Sucessfully Found Faculty Entities..."),
			@ApiResponse(responseCode = "400", description = "Unable To Found Faculty Entities...") })

	@GetMapping
	public ResponseEntity<?> findAllFacultyProfile() {
		return facultyProfileService.findAllFacultyProfile();
	}
	
	
//	@PatchMapping("/ActiveStatus/{id}")
//	public ResponseEntity<?> setfacultyToActiveStatus(@PathVariable int id)
//	{
//		return facultyProfileService.setfacultyToActiveStatus(id);
//	}
	
	@Operation(summary = "To Set Active Status to  Faculty Profile By Id and Otp", description = "This API Will Set Active Status to  Faculty Profile By Id and Otp")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Faculty Details Changed Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Invalid Otp...") })
	@PatchMapping("/validateOtp/{id}/{otp}")
	public ResponseEntity<?> setFacultyToActiveStatus(@PathVariable int id,@PathVariable int otp)
	{
		return facultyProfileService.setFacultyToActiveStatus(id,otp);
	}
	

}
