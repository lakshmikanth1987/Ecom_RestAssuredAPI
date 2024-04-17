package endPointsOfEcom;

import java.io.File;
import java.util.ResourceBundle;

import org.testng.ITestContext;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class createProductEndPoints {

	public static ResourceBundle routes;
	public static String tokenId;
	public static String userId;

	public static Response addProduct(ITestContext context) {
		tokenId = (String) context.getSuite().getAttribute("generatedTokenId");
		userId = (String) context.getSuite().getAttribute("generatedUserId");
		routes = ResourceBundle.getBundle("routes");
		RequestSpecification addBaseReq = new RequestSpecBuilder().setBaseUri(routes.getString("base_URI"))
				.addHeader("authorization", tokenId).build();
		RequestSpecification addProductReq = given().log().all().spec(addBaseReq).param("productName", "qwerty")
				.param("productAddedBy", userId).param("productCategory", "fashion")
				.param("productSubCategory", "shirts").param("productPrice", "11500")
				.param("productDescription", "Lenova").param("productFor", "men")
				.multiPart("productImage", new File(".\\Images\\LaptopImage.jpg"));
		Response addProductResponse = addProductReq.when().post(routes.getString("addproduct"));

		return addProductResponse;
	}
}
