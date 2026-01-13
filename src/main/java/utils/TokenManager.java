package utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import pojo.User;
import pojo.UserRequest;

public class TokenManager {
	private static String token;

	public static String getToken() {
		if (token == null) {
			token = generateToken();
		}
		return token;
	}

	private static String generateToken() {
		User user = new User();
		user.setEmail(ConfigReader.get("email"));
		user.setPassword(ConfigReader.get("password"));

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
			.body("user.token", notNullValue())
			.extract()
			.response().asString();

		// @formatter:on
		JsonPath jp = new JsonPath(response);
		return jp.getString("user.token");
	}
}
