package testsOfEcom;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import endPointsOfEcom.deleteOrderEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class deleteProductTest {
	
	
	
	@Test
	
	public static void verifyDeleteProduct(ITestContext context) {
		Response deleteProduct=deleteOrderEndPoints.deleteOrder(context);
		String deleteProductResponse=deleteProduct.then().log().all().extract().response().asString();
		JsonPath js = new JsonPath(deleteProductResponse);
		Assert.assertEquals("Product Deleted Successfully",js.get("message"));

	}

}
