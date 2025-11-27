package com.automation.apitests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.apiutils.LoggerUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserApiTests {

	private static String createdUserId;

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "/api";
	}

	@Test(priority = 1)
	public void test_1_createUser() {
		Map<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", "Naveen Nagarajan");
		requestBody.put("job", "Software Developer");

		Response response = null;

		try {
			response = given().header("x-api-key", "reqres-free-v1")

					.contentType(ContentType.JSON).body(requestBody).when().post("/users");
			response.then().statusCode(201).body("name", equalTo("Naveen Nagarajan")).body("id", is(notNullValue()));
			createdUserId = response.jsonPath().getString("id");
			LoggerUtility.log("✅ POST SUCCESSFUL. Created User ID: " + createdUserId);
		} catch (AssertionError e) {
			LoggerUtility.error("Assertion Failed in test_1_createUser: " + e.getMessage());
			response.prettyPrint(); // Print the failure response body for debugging
			Assert.fail("API Test Failed due to assertion error: " + e.getMessage());

		} catch (Exception e) {
			LoggerUtility.error("Runtime Exception in test_1_createUser: " + e.getMessage());
			Assert.fail("API Test Failed due to runtime error: " + e.getMessage());
		}
	}

	@Test(priority = 2)
	public void test_2_getUser() {
		if (createdUserId == null)
			throw new RuntimeException("Id is null");
		createdUserId = "2"; // Using a known existing user ID for retrieval
		Response response = given().header("x-api-key", "reqres-free-v1").pathParam("id", createdUserId).when()
				.get("/users/{id}");
		LoggerUtility.log("✅ GET SUCCESSFUL. User Data Retrieved:");
		response.then().statusCode(200).body("data.first_name", equalTo("Janet")).body("data.email",
				containsString("@reqres.in"));
	}

	@Test(priority = 3)
	public void test3_UpdateUserJob() {
		if (createdUserId == null)
			throw new RuntimeException("ID is null.");

		// GIVEN: Prepare the updated request body
		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("name", "Naveen");
		requestBody.put("job", "Software Developer"); // Change the job

		// WHEN: Send the PUT request
		Response response = RestAssured.given().header("x-api-key", "reqres-free-v1").contentType(ContentType.JSON)
				.body(requestBody).pathParam("id", createdUserId).when().put("/users/{id}");

		// THEN: Validate the update was successful
		response.then().statusCode(200) // 200 is used for successful updates
				.body("job", equalTo("Software Developer")); // Verify the new job title

		LoggerUtility.log("✅ PUT SUCCESSFUL. User job updated." + createdUserId);
	}

	@Test(priority = 4)
	public void test4_DeleteUser() {
		if (createdUserId == null)
			throw new RuntimeException("ID is null.");
		createdUserId = "2";
		// WHEN: Send the DELETE request
		Response response = RestAssured.given().header("x-api-key", "reqres-free-v1").pathParam("id", createdUserId)
				.when().delete("/users/{id}");
		// THEN: Validate deletion
		response.then().log().ifValidationFails().statusCode(204); // 204 (No Content) confirms successful deletion
		LoggerUtility.log("✅ DELETE SUCCESSFUL. User ID " + createdUserId + " deleted.");
	}

	@AfterClass
	public void teardown() {
		// 1. Reset Base Configurations to default values
		RestAssured.baseURI = null;
		RestAssured.basePath = null;
		// 2. Clear the custom RequestSpecification
//		RestAssured.requestSpecification = null;
		LoggerUtility.log("Test execution teardown complete for UserApiTests. Configurations reset.");
	}

}
