package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import base.BaseTest;
import utils.EndPoints;
import utils.TokenManager;

public class GetCurrentUserTest extends BaseTest {
	@Test
	public void getCurrentUser() {
		// @formatter:off
		given()
			.header("Authorization", "Token "+TokenManager.getToken())
		.when()
			.get(EndPoints.CURRENT_USER)
		.then()
			.statusCode(200)
			  .body("user.id", notNullValue())
		        .body("user.email", notNullValue())
			.log().all();
	}
}
