package base;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;



public class BaseTest {
	
	
	protected static String token;
	
	@BeforeClass
	public static RequestSpecification  getRequestSpec() {
		
		return new RequestSpecBuilder()

                .setBaseUri(
                        ConfigReader.getBaseUrl())

                .addHeader(
                        "Content-Type",
                        "application/json")

                .build();

	
	}
	

}
