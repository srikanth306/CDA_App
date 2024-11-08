package com.softgv.cda.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softgv.cda.dao.DepartmentDao;
import com.softgv.cda.dao.FacultyProfileDao;
import com.softgv.cda.dao.UserDao;
import com.softgv.cda.entity.AdministratorProfile;
import com.softgv.cda.entity.Department;
import com.softgv.cda.entity.FacultyProfile;
import com.softgv.cda.entity.StudentProfile;
import com.softgv.cda.entity.User;
import com.softgv.cda.exceptionclasses.UserNotFoundException;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.FacultyProfileService;
import com.softgv.cda.util.Role;
import com.softgv.cda.util.UserStatus;

@Service
public class FacultyProfileServiceImpl implements FacultyProfileService {

	private static final String FOLDER_PATH = "C:\\Users\\gagan\\Documents\\My-React\\cda-react-app\\public\\images\\facultiesS\\";

	@Autowired
	private UserDao userDao;

	@Autowired
	private FacultyProfileDao facultyProfileDao;

	@Autowired
	private DepartmentDao departmentDao;
	
	
//	@Override
//	public ResponseEntity<?> saveFacultyProfile(@RequestParam int uid, @RequestParam MultipartFile file) {
//		Optional<User> optional = userDao.findUserById(uid);
//		if (optional.isEmpty())
//			throw UserNotFoundException.builder().message("Inavlid User Id : " + uid).build();
//		User user = optional.get();
//		user.setRole(Role.FACULTY);
//		String photo = FOLDER_PATH + UUID.randomUUID() + file.getOriginalFilename();
//		try {
//			file.transferTo(new File(photo));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		FacultyProfile facultyProfile = FacultyProfile.builder().photo(photo).id(uid).user(user).build();
//
//		facultyProfile = facultyProfileDao.saveFacultyProfile(facultyProfile);
//
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
//				.message("Faculty Profile Saved Successfully...").body(facultyProfile).build());
//	}
	@Override
	public ResponseEntity<?> saveFacultyProfile(@RequestParam("uid") int uid) {
	    Optional<User> optional = userDao.findUserById(uid);
	    
	    // Check if user exists
	    if (optional.isEmpty()) {
	        throw UserNotFoundException.builder()
	            .message("Invalid User Id: " + uid)
	            .build();
	    }
	    
	    User user = optional.get();
	    user.setRole(Role.FACULTY);  // Setting role to FACULTY
	    
	    // Ensure file is not empty
//	    if (file.isEmpty()) {
//	        return ResponseEntity.badRequest()
//	            .body("File is empty. Please upload a valid file.");
//	    }
	    
	    // Generate unique file name
//	    String photoPath = FOLDER_PATH + UUID.randomUUID() + "_" + file.getOriginalFilename();
	    
	    // Attempt to save file
//	    try {
//	        file.transferTo(new File(photoPath));
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	            .body("Failed to save file. Please try again.");
//	    }

	    // Save faculty profile
	    FacultyProfile facultyProfile = FacultyProfile.builder()
	            
	            .id(uid)
	            .user(user)
	            .build();

	    facultyProfile = facultyProfileDao.saveFacultyProfile(facultyProfile);

	    // Build success response
	    return ResponseEntity.status(HttpStatus.OK)
	        .body(ResponseStructure.builder()
	                .status(HttpStatus.OK.value())
	                .message("Faculty Profile Saved Successfully.")
	                .body(facultyProfile)
	                .build()
	        );
	}


	@Override
	public ResponseEntity<?> findAllFacultyProfile() {
		List<FacultyProfile> facultyProfiles = facultyProfileDao.findAllFacultyProfile();
		if (facultyProfiles.isEmpty())
			throw new RuntimeException("No Faculty Profiles Present in Databse Table...");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Faculty Profiles Found Succesfully...").body(facultyProfiles).build());
	}

	@Override
	public ResponseEntity<?> findFacultyProfileById(int id) {
		Optional<FacultyProfile> optional = facultyProfileDao.findFacultyProfileById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + id);
		FacultyProfile facultyProfile = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Found Successfully...").body(facultyProfile).build());
	}

//	@Override
//	public ResponseEntity<?> updatePhoto(int id, MultipartFile file) {
//		Optional<FacultyProfile> optional = facultyProfileDao.findFacultyProfileById(id);
//		if (optional.isEmpty())
//			throw new RuntimeException("Invalid Faculty Profile Id : " + id);
//		FacultyProfile facultyProfile = optional.get();
//		String photo = FOLDER_PATH + UUID.randomUUID() + file.getOriginalFilename();
//		try {
//			file.transferTo(new File(photo));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
////		facultyProfile.setPhoto(photo);
//		facultyProfile = facultyProfileDao.saveFacultyProfile(facultyProfile);
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
//				.message("Photo Uploaded Successfully...").body(facultyProfile).build());
//	}

	@Override
	public ResponseEntity<?> updateInfo(int id, String email, String phone, String officeHours) {
		Optional<FacultyProfile> optional = facultyProfileDao.findFacultyProfileById(id);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + id);
		Optional<User> optional2 = userDao.findUserById(id);
		if (optional2.isEmpty())
			throw new RuntimeException("Invalid User Id : " + id);
		User user = optional2.get();
		FacultyProfile facultyProfile = optional.get();
		facultyProfile.setOfficeHours(officeHours);
		facultyProfile = facultyProfileDao.saveFacultyProfile(facultyProfile);
		user.setEmail(email);
		user.setPhone(phone);
		user = userDao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Information Updated Successfully...").body(facultyProfile).build());
	}

	@Override
	public ResponseEntity<?> assignDepartmentToFacultyProfile(int uid, int did) {
		Optional<Department> optional1 = departmentDao.findDepartmentById(did);
		if (optional1.isEmpty())
			throw new RuntimeException("Invalid Department Id : " + did);
		Optional<FacultyProfile> optional2 = facultyProfileDao.findFacultyProfileById(uid);
		if (optional2.isEmpty())
			throw new RuntimeException("Invalid Faculty Profile Id : " + uid);
		Department department = optional1.get();
		FacultyProfile  facultyProfile = optional2.get();
		facultyProfile.setDepartment(department);
		facultyProfile=facultyProfileDao.saveFacultyProfile(facultyProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Department Assigned Successfully To Faculty Profile").body(facultyProfile).build());
	}


//	@Override
//	public ResponseEntity<?> setfacultyToActiveStatus(int id) 
//	{
//		Optional<FacultyProfile> optional=facultyProfileDao.findFacultyProfileById(id);
//		if(optional.isEmpty())
//			throw new RuntimeException();
//		FacultyProfile facultyProfile=optional.get();
//		facultyProfile.getUser().setStatus(UserStatus.ACTIVE);
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Succesfully Changed to Active Status ").body(facultyProfile).build());
//	}


	@Override
	public ResponseEntity<?> setFacultyToActiveStatus(int id, int otp)
	{
		Optional<FacultyProfile> optional=facultyProfileDao.findFacultyProfileById(id);
		if(optional.isEmpty())
			throw new RuntimeException();
		FacultyProfile profile=optional.get();
		profile.getUser().setStatus(UserStatus.ACTIVE);
		profile=facultyProfileDao.saveFacultyProfile(profile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Succesfully Changed to Active Status ").body(profile).build());
	}

}
