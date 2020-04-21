package jiraAPIs;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Body {

	//this is function to convert xml data to string data
	public static String GenerateStringFromResource (String path) throws IOException {
		
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	public static String getSessionBody() {

		String body = "{\"username\":\"exerlol\",\"password\":\"exerlol1991\"}";

		return body;
	}
	public static String getCreateIssueBody() {

		String body = "{\"fields\":{\"project\":{\"key\":\"RA\"},\"summary\":\"Creditcard\",\"description\":\"Creating my second bug in JIRA\",\"issuetype\":{\"name\":\"Bug\"}}}";
		return body;
	}
	public static String getAddCommentBody() {

		String body = "{"+
		"\"body\":\"Comment for testing\""
				+"}";

		return body;
	}
	public static String getUpdateCommentBody() {

		String body = "{\"body\":\"UPDATED\"}";

		return body;
	}
}
