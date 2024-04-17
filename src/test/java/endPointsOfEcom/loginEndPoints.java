package endPointsOfEcom;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.ResourceBundle;


import payLoadOfEcom.Login;

public class loginEndPoints {

	public static ResourceBundle routes;
	
	public static Response login(Login payload) {
		// To get URL from .properties files
		routes = ResourceBundle.getBundle("routes");
		Response response = given().contentType(ContentType.JSON).body(payload).when()
				.post(routes.getString("post_url_login"));
		return response;

	}
}
