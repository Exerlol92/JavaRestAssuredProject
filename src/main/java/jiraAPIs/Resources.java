package jiraAPIs;

import java.io.IOException;

public class Resources {

	public static String getSessionResource() {
		String resource = "/rest/auth/1/session";
		return resource;
	}
	public static String getCreateIssueResource() {
		String resource = "/rest/api/2/issue";
		return resource;
	}
	public static String getAddCommentResource() throws IOException {
		String resource = "/rest/api/2/issue/"+ReusableMethods.getIssueID()+"/comment";
		return resource;
	}
	public static String getUpdateCommentResource() throws IOException {
		String resource = "/rest/api/2/issue/10021/comment/10008";
		return resource;
	}
	public static String getDeleteIssueResource() {
		String resource = "/rest/api/2/issue/10214";
		return resource;
	}
	

}
