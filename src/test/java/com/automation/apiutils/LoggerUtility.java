package com.automation.apiutils;

public class LoggerUtility {
	public static void log(String message) {
		System.out.println("[INFO] " + message);
	}

	// Simple wrapper for logging errors
	public static void error(String message) {
		System.err.println("[ERROR] " + message);
	}
}
