package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.TokenManager;

public class RequestSpec {

	public static RequestSpecification withoutAuth() {
		//@formatter:off
		return new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.build();
		//@formatter:on
	}

	public static RequestSpecification withAuth() {
		//@formatter:off
		return 
			new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Token " + TokenManager.getToken())
				.build();
		//@formatter:on
	}
}
