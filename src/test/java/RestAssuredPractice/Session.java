package RestAssuredPractice;

import jiraAPIs.Resources;
import jiraAPIs.ReusableMethods;
import jiraAPIs.Body;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Session {
	private static Logger log= LogManager.getLogger(CreateIssue.class.getName());
	Properties prop = new Properties ();
	
	@BeforeTest
	public void loadFileEnv() throws IOException {
		FileInputStream fs = new FileInputStream(ReusableMethods.getPropertiesPath());
		prop.load(fs);

}
	@Test
	public void StartingSessionTest() {
	log.info("Host Information: " + prop.getProperty("HOSTJIRA"));
	RestAssured.baseURI=prop.getProperty("HOSTJIRA");
	

	Response res = given().
		header("Content-Type","application/json").
		body(Body.getSessionBody()).
	when().
		post(Resources.getSessionResource()).
	then().assertThat().
		statusCode(200).
	extract().response();
	
	JsonPath js = ReusableMethods.rawToJson(res);
	String sessionID = js.get("session.value");
	log.info("Session ID is: " + sessionID);
}
}
