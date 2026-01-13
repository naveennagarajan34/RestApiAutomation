package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import pojo.User;
import pojo.UserRequest;
import utils.EndPoints;

public class LoginTest extends BaseTest {
	@Test
	public void testLoginUser() {
		User user = new User();
		user.setEmail("naveennagarajan@gmail.com");
		user.setPassword("Test@123");

		UserRequest userReq = new UserRequest();
		userReq.setUser(user);

		// @formatter:off
		String response =
		given()
			.contentType(ContentType.JSON)
			.body(userReq)
		.when()
			.post(EndPoints.LOGIN_USER)
		.then()
			.statusCode(200)
			.log().all()
			.body("user.email", equalTo("naveennagarajan@gmail.com"))
			.body("user.token", notNullValue())
			.body("user.image", startsWith("http"))
			.body("user.username", not(emptyOrNullString()))
			.body("user.bio", anyOf(nullValue(), not(emptyOrNullString())))
			.extract()
			.response().asString();

		// @formatter:on
		JsonPath jp = new JsonPath(response);
		System.out.println("Token: " + jp.getString("user.token"));

	}
}
