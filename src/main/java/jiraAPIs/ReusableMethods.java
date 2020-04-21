package jiraAPIs;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import jiraAPIs.Body;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	
	public static XmlPath rawToXML(Response res) {
		
		// we get extract.response as Raw so we are converting it to string
		String responseString = res.asString();
		//now we convert that string to xml
		XmlPath xml = new XmlPath(responseString);
		
		return xml;
	}
	public static JsonPath rawToJson (Response res) {
		
		// we get extract.response as Raw so we are converting it to string
		String responseString = res.asString();
		//now we convert that string to Json
		JsonPath Json = new JsonPath(responseString);
		
		return Json;
	}

	public static String getSessionID() throws IOException {
	
	Properties prop = new Properties ();
	FileInputStream fs = new FileInputStream("D:\\restassured workspace\\Demo\\src\\files\\env.properties");
	prop.load(fs);
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
	
	return sessionID;
}
	public static String getIssueID() throws IOException {
		
		Properties prop = new Properties ();
		FileInputStream fs = new FileInputStream("D:\\restassured workspace\\Demo\\src\\files\\env.properties");
		prop.load(fs);
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
			return issueID;
}
	public static String getCommentID() throws IOException {
		
		Properties prop = new Properties ();
		FileInputStream fs = new FileInputStream("D:\\restassured workspace\\Demo\\src\\files\\env.properties");
		prop.load(fs);
		RestAssured.baseURI=prop.getProperty("HOSTJIRA");
		

		Response res = given().
				header("Content-Type","application/json").
				header("Cookie","JSESSIONID="+ReusableMethods.getSessionID()).
				body(Body.getAddCommentBody()).
			when().
				post(Resources.getAddCommentResource()).
			then().assertThat().
				statusCode(201).
				extract().response();
			
			JsonPath js = ReusableMethods.rawToJson(res);
			String commentID = js.get("id");
			return commentID;
}
	public static String getPropertiesPath() {
		String s = System.getProperty("user.dir")+"//env.properties";
		return s;
		
	}
}