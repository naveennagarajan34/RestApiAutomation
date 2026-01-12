package base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import utils.ConfigReader;

public class BaseTest {
	@BeforeClass
	public void setup() {
		/*
		 * Static methods belong to the class, not to any particular object instance.
		 * Best practice is to call them with the class name for clarity and to avoid
		 * confusion. ConfigReader.get("baseURL");
		 */

		RestAssured.baseURI = ConfigReader.get("baseURL");
	}
}
