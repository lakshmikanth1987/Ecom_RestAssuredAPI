package testsOfEcom;

import java.util.ResourceBundle;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import endPointsOfEcom.loginEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payLoadOfEcom.Login;

import utilitiesOfEcom.toReadProperties;

public class loginTest extends toReadProperties {
	
	Login loginPayload;
	public static String tokenId;
	public static String userId;
	
	@BeforeClass
	public void setUpData() {
		ResourceBundle routes=getProperties();
		loginPayload=new Login();
		loginPayload.setUserEmail(routes.getString("userEmail"));
		loginPayload.setUserPassword(routes.getString("userPassword"));	
	}

	@Test
	
	public void verifyLoginIsSuccess(ITestContext context) {
		
		Response loginResponse=loginEndPoints.login(loginPayload);
		String lResponse=loginResponse.then().log().all().extract().response().asString();
		JsonPath jPath=new JsonPath(lResponse);
		tokenId=jPath.getString("token");
		userId=jPath.getString("userId");
		String actualMessage=jPath.getString("message");
		Assert.assertEquals(actualMessage, "Login Successfully");
		Assert.assertNotNull(tokenId);
		Assert.assertNotNull(userId);
		context.getSuite().setAttribute("generatedTokenId", tokenId);
		context.getSuite().setAttribute("generatedUserId", userId);
		
	}
}
