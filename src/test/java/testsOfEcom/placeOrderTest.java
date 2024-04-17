package testsOfEcom;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import endPointsOfEcom.placeOrderEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class placeOrderTest {
	
	
	
	@Test
	
	public void verifyPlaceOrder(ITestContext context) {
		
		Response createOrder=placeOrderEndPoints.placeOrder(context);
		String createOrderResponse=createOrder.then().log().all().extract().response().asString();
		JsonPath jPath=new JsonPath(createOrderResponse.toString());
		String orderId=jPath.getString("orders[0]"); 
		context.getSuite().setAttribute("generatedOrderId", orderId);
		String productOrderId=jPath.getString("productOrderId[0]");
		context.getSuite().setAttribute(orderId, productOrderId); 
		Assert.assertEquals(jPath.get("message"), "Order Placed Successfully");
		
	}
	
	
	
	
	
	

}
