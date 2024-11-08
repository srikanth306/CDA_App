package com.softgv.cda.serviceimpl;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.softgv.cda.dao.AdministratorProfileDao;
import com.softgv.cda.dao.FacultyProfileDao;
import com.softgv.cda.dao.StudentProfileDao;
import com.softgv.cda.dao.UserDao;
import com.softgv.cda.entity.AdministratorProfile;
import com.softgv.cda.entity.FacultyProfile;
import com.softgv.cda.entity.StudentProfile;
import com.softgv.cda.entity.User;
import com.softgv.cda.exceptionclasses.UserNotFoundException;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.UserService;
import com.softgv.cda.util.AuthUser;
import com.softgv.cda.util.Helper;
import com.softgv.cda.util.Role;
import com.softgv.cda.util.UserStatus;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private Helper helper;

	@Autowired
	private UserDao userDao;

	@Autowired
	private StudentProfileDao studentProfileDao;

	@Autowired
	private FacultyProfileDao facultyProfileDao;

	@Autowired
	private AdministratorProfileDao administratorProfileDao;

	public ResponseEntity<?> findByUsernameAndPassword(AuthUser authUser) {
		Optional<User> optional = userDao.findByUsernameAndPassword(authUser.getUsername(), authUser.getPassword());
		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid Credentials... Invalid Username or Password...")
					.build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Verified Successfully...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> saveUser(User user) {
		int otp = helper.generateOTP();
		boolean flag = helper.sendFirstMail(user.getEmail(), otp);
		if (!flag)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ResponseStructure.builder().status(HttpStatus.BAD_REQUEST.value())
							.message("Enter Valid Email").body("Invaid Email Id : " + user.getEmail()).build());
		String photo = "C:\\Users\\gagan\\Documents\\My-React\\cda-react-app\\public\\images\\userprofile.jpg";
		user.setStatus(UserStatus.IN_ACTIVE);
		user.setOtp(otp);
		user = userDao.saveUser(user);
		if (user.getRole() == Role.ADMINISTRATOR)
			administratorProfileDao.saveAdministratorProfile(
					AdministratorProfile.builder().id(user.getId()).photo(photo).user(user).build());
		else if (user.getRole() == Role.FACULTY)
			facultyProfileDao.saveFacultyProfile(FacultyProfile.builder().id(user.getId()).user(user)
					.officeHours(null).build());
		else
			studentProfileDao
					.saveStudentProfile(StudentProfile.builder().id(user.getId()).photo(photo).user(user).build());
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Saved Successfully...").body(user).build());
	}

	@Override
	public ResponseEntity<?> findAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("All Users Found Successfully...").body(userDao.findAllUsers()).build());
	}

	@Override
	public ResponseEntity<?> findUserById(int id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty())
			throw UserNotFoundException.builder().message("Invalid User Id : " + id).build();
		User user = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User Found Successfully...").body(user).build());
	}

}
