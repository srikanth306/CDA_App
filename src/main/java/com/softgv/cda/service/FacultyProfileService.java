package com.softgv.cda.service;

import java.time.LocalTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FacultyProfileService {

	ResponseEntity<?> saveFacultyProfile(int uid);

	ResponseEntity<?> findAllFacultyProfile();

	ResponseEntity<?> findFacultyProfileById(int id);

//	ResponseEntity<?> updatePhoto(int id, MultipartFile file);

	ResponseEntity<?> updateInfo(int id, String email, String phone, String officeHours);

	ResponseEntity<?> assignDepartmentToFacultyProfile(int uid, int did);

//	ResponseEntity<?> setfacultyToActiveStatus(int id);

	ResponseEntity<?> setFacultyToActiveStatus(int id, int otp);
}
