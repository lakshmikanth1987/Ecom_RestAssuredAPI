package testsOfEcom;


import org.testng.ITestContext;
import org.testng.annotations.Test;
import endPointsOfEcom.createProductEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class createProductTest {
	
	public static String generatedProductId;
	public static String generatedMessage;
	
	
	@Test
	public void verifyCreateProduct(ITestContext context) {
		
		Response addProduct=createProductEndPoints.addProduct(context);
		String addproductResponse=addProduct.then().log().all().extract().response().asString();
		JsonPath jPath=new JsonPath(addproductResponse);
		generatedProductId=jPath.getString("productId");
		generatedMessage=jPath.getString("message");
		context.getSuite().setAttribute("productID", generatedProductId);
		context.getSuite().setAttribute("message", generatedMessage);
	}

	
}
