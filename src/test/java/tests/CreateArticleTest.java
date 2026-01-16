package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;

import java.util.Set;

import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.path.json.JsonPath;
import pojo.Article;
import pojo.ArticleRequest;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.EndPoints;

public class CreateArticleTest extends BaseTest {
	private static String slugId;

	public static String getSlugId() {
		return slugId;
	}

	@Test(groups = "article")
	public void createArticle() {
		Article article = new Article();
		article.setTitle("Title of the Article");
		article.setDescription("Learning Rest Assured");
		article.setBody("API CRUD operations");
		article.setTagList(Set.of("learn", "rest", "api")); // unique tags

		ArticleRequest artReq = new ArticleRequest();
		artReq.setArticle(article);

		//@formatter:off
		String response = 
			given()
			    .spec(RequestSpec.withAuth())
			    .body(artReq)
			.when()
				.post(EndPoints.ARTICLES)
			.then().spec(ResponseSpec.status201())
			
				.body("article.title", equalTo("Title of the Article"))
				.body("article.tagList", everyItem(anyOf(
				            equalToIgnoringCase("learn"),
				            equalToIgnoringCase("rest"),
				            equalToIgnoringCase("api")
				    )))
				.extract().response().asString();
		
		JsonPath jp = new JsonPath(response);
		slugId = jp.getString("article.slug");
	}
}
