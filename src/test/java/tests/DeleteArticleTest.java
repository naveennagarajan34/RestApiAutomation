package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import utils.EndPoints;
import utils.TokenManager;

public class DeleteArticleTest {
	@Test(dependsOnGroups = "article")
	public void deleteArticle() {
		assertThat(CreateArticleTest.getSlugId(), notNullValue());
		//@formatter:off
		given()
			.header("Authorization", "Token " + TokenManager.getToken())
		.when().
			delete(EndPoints.ARTICLES + "/" + CreateArticleTest.getSlugId())
		.then()
			.statusCode(204);
	}
}
