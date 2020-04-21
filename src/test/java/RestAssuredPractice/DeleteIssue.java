package RestAssuredPractice;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import jiraAPIs.Body;
import jiraAPIs.Resources;
import jiraAPIs.ReusableMethods;

public class DeleteIssue {
	
		private static Logger log=LogManager.getLogger(DeleteIssue.class.getName());
		Properties prop = new Properties ();
		
		@BeforeTest
		public void loadFileEnv() throws IOException {
			FileInputStream fs = new FileInputStream(ReusableMethods.getPropertiesPath());
			prop.load(fs);

	}
		@Test
		public void DeletingIssueTest() throws IOException {
		log.info("Host Information: " + prop.getProperty("HOSTJIRA"));
		RestAssured.baseURI=prop.getProperty("HOSTJIRA");
		
		given().
			header("Content-Type","application/json").
			header("Cookie","JSESSIONID="+ReusableMethods.getSessionID()).
			body(Body.getCreateIssueBody()).
		when().
			delete(Resources.getDeleteIssueResource()).
		then().assertThat().
			statusCode(204);
			
}
}

