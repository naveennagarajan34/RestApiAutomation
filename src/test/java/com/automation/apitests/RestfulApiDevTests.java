package com.automation.apitests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.apiutils.LoggerUtility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestfulApiDevTests {
	public static String productId;

	@BeforeClass
	public void setupApi() {
		RestAssured.baseURI = "https://api.restful-api.dev";
	}

	@Test
	public static void addProduct() {
		// 1. Create the inner 'data' map
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("year", 2019);
		dataMap.put("price", 1849.99);
		dataMap.put("CPU model", "Intel Core i9");
		dataMap.put("Hard disk size", "1 TB");

		// 2. Create the outer 'payload' map
		Map<String, Object> payloadMap = new HashMap<>();
		payloadMap.put("name", "HP EliteBook Pro 16");

		// 3. Nest the 'dataMap' inside the 'payloadMap'
		payloadMap.put("data", dataMap);
		Response response = null;
		response = given().contentType(ContentType.JSON).body(payloadMap).when().post("/objects");
		response.then().statusCode(200).body("id", is(notNullValue()));
		productId = response.jsonPath().getString("id");
		LoggerUtility.log("✅ POST SUCCESSFUL. Created product ID: " + productId);
	}
}
