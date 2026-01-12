package utils;

public class EndPoints {
	/*
	 * Static → because endpoints are common and don’t depend on object state. Final
	 * → because they’re constants and should never change. Not private → because
	 * they need to be reused across the project.
	 */

	// User related endpoints
	public static final String REGISTER_USER = "/users";
	public static final String LOGIN_USER = "/users/login";
	public static final String CURRENT_USER = "/user";

	// Article related endpoints
	public static final String ARTICLES = "/articles";
}
