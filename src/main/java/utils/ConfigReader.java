package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static void main(String[] args) {
		System.out.println(get("baseUrl"));
	}

	private static Properties prop;
	/*
	 * Using try-with-resources avoids resource leaks and makes code concise. In
	 * a static block, this ensures configuration files are loaded once at class
	 * initialization, with proper exception handling.
	 */

	static {
		try (FileInputStream fis = new FileInputStream(
				"src/test/resources/config.properties")) {
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return prop.getProperty(key);
	}

	public static void set(String key, String value) {
		prop = new Properties();
		prop.setProperty(key, value);
		try (FileOutputStream fos = new FileOutputStream(
				"src/test/resources/config.properties")) {
			prop.store(fos, "Updated key-value pair");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
