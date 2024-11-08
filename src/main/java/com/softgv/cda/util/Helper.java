package com.softgv.cda.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class Helper {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendFirstMail(String email, int otp) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(email);
			helper.setSubject("Account Created");
			String htmlContent = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "  <meta charset=\"UTF-8\">\r\n"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "  <title>Login Form</title>\r\n"
					+ "</head>\r\n"
					+ "<body style=\"display: flex; justify-content: center; align-items: center; height: 100vh; font-family: Arial, sans-serif;\">\r\n"
					+ "\r\n"
					+ "    <div class=\"mb-3 row\">\r\n"
					+ "    <label for=\"staticEmail\" class=\"col-sm-2 col-form-label\">Email :-</label>\r\n"
					+ "    <div class=\"col-sm-10\">\r\n"
					+ "      <input type=\"text\" readonly class=\"form-control-plaintext\" id=\"staticEmail\" value=\""+email+"\">\r\n"
					+ "    </div>\r\n"
					+ "  </div>\r\n <br>"
					+ "    <div class=\"mb-3 row\">\r\n"
					+ "    <label for=\"staticEmail\" class=\"col-sm-2 col-form-label\">OTP :-</label>\r\n"
					+ "    <div class=\"col-sm-10\">\r\n"
					+ "      <input type=\"text\" readonly class=\"form-control-plaintext\" id=\"staticEmail\" value=\""+otp+"\">\r\n"
					+ "    </div>\r\n"
					+ "  </div>\r\n <br>"
					+"<br>"
					+"<div class=\"d-grid gap-2 col-6 mx-auto\">\r\n"
					+ "<br>  <button class=\"btn btn-primary\" type=\"button\">Verify</button>\r\n"
					+ "</div>"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "";
			helper.setText(htmlContent, true);
		} catch (MessagingException e) {
			System.out.println("Invalid Email Id");
			return false;
		}
		try {
			javaMailSender.send(mimeMessage);
		} catch (MailException e) {
			System.out.println("Invalid Email Id");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int generateOTP() {
		double otp = 0;
		while (otp < 1000) {
			otp = Math.random() * 10000;
		}
		return (int) otp;
	}
	
	
}
