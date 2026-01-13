package tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.http.ContentType;
import pojo.User;
import pojo.UserRequest;
import utils.EndPoints;

public class RegisterUserTest extends BaseTest {

	@Test
	public void testRegisterUser() {
		User user = new User();
		user.setEmail("naveen@naveen1n.com");
		user.setPassword("Test@123");
		user.setUsername("naveen_naveen1");

		UserRequest userreq = new UserRequest();
		userreq.setUser(user);

		// @formatter:off
		given()
			.contentType(ContentType.JSON)
			.body(userreq)
		.when()
			.post(EndPoints.REGISTER_USER)
		.then()
			.statusCode(201)
			.log().all();
		// @formatter:on
	}
}
