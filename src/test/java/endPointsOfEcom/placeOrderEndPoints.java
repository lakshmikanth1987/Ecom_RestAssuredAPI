package endPointsOfEcom;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.testng.ITestContext;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payLoadOfEcom.OrderDetail;
import payLoadOfEcom.Orders;

import static io.restassured.RestAssured.*;

public class placeOrderEndPoints {
	public static ResourceBundle routes;
	public static String tokenId;
	public static String productId;

	public static Response placeOrder(ITestContext context) {
		tokenId = (String) context.getSuite().getAttribute("generatedTokenId");
		productId = (String) context.getSuite().getAttribute("productID");
		routes = ResourceBundle.getBundle("routes");
		RequestSpecification placeOrderBaseReq = new RequestSpecBuilder().setBaseUri(routes.getString("base_URI"))
				.setContentType(ContentType.JSON).addHeader("authorization", tokenId).build();

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry(routes.getString("country"));
		orderDetail.setProductOrderedId(productId);
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		orderDetailList.add(orderDetail);
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);

		RequestSpecification createOrderReq = given().log().all().spec(placeOrderBaseReq).body(orders);

		Response createOrderResponse = createOrderReq.when().post(routes.getString("createOrder"));

		return createOrderResponse;

	}
}
