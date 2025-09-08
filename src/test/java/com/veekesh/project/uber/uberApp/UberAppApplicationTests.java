package com.veekesh.project.uber.uberApp;

import com.veekesh.project.uber.uberApp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {

	}

	@Test
	void testEmailSend(){
		emailSenderService.sendEmail("dehavo5450@cspaus.com",
				"This is a test subject",
				"Body of my mail");
	}

	@Test
	void testSendEmailMultiple(){
		String[] emails = {
				"hahahaha@yopmail.com",
				"dehavo5450@cspaus.com"
		};

		emailSenderService.sendEmail(emails,
				"This is multiple mail",
				"Hey! How are you doing?");
	}

}
