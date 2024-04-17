package endPointsOfEcom;

import java.util.ResourceBundle;

import org.testng.ITestContext;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class deleteOrderEndPoints {

	
	public static ResourceBundle routes;
	public static String tokenId;
	public static String productId;

	
	public static Response deleteOrder(ITestContext context) {
		
		
		tokenId = (String) context.getSuite().getAttribute("generatedTokenId");
		productId=(String) context.getSuite().getAttribute("productID");
		routes = ResourceBundle.getBundle("routes");
		RequestSpecification deleteOrderBaseReq=new RequestSpecBuilder().setBaseUri(routes.getString("base_URI"))
				.setContentType(ContentType.JSON)
				.addHeader("authorization", tokenId)
				.build();
		RequestSpecification deleteOrderRequest=given().log().all().spec(deleteOrderBaseReq).pathParam("productId", productId);
		Response deleteProductResponse=deleteOrderRequest.when().delete(routes.getString("deleteOrder")+"{productId}");
		  
		return deleteProductResponse;
		     
	}
}
