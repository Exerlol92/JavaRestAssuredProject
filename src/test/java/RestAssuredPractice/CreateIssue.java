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

public class CreateIssue {
	private static Logger log= LogManager.getLogger(CreateIssue.class.getName());
	Properties prop = new Properties ();
	
	@BeforeTest
	public void loadFileEnv() throws IOException {
		FileInputStream fs = new FileInputStream(ReusableMethods.getPropertiesPath());
		prop.load(fs);

}
	@Test
	public void CreatingIssueTest() throws IOException {
	log.info("Host Information: " + prop.getProperty("HOSTJIRA"));
	RestAssured.baseURI=prop.getProperty("HOSTJIRA");
	
	Response res = given().
		header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+ReusableMethods.getSessionID()).
		body(Body.getCreateIssueBody()).
	when().
		post(Resources.getCreateIssueResource()).
	then().assertThat().
		statusCode(201).
		extract().response();
	
	JsonPath js = ReusableMethods.rawToJson(res);
	String issueID = js.get("id");
	log.info("Issue ID is: " + issueID);
	
	}
}