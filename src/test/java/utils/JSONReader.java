package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JSONReader {
	
	public static JsonNode getTestData(String testName)  {
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			

            JsonNode rootNode = mapper.readTree(
                    new File("src/test/resources/testdata/bookingData.json"));
			
			
			return rootNode.get(testName);
			
		}
		
	
		
		catch(Exception e) {
			
			 throw new RuntimeException("Unable to read test data");
		}

}
	
}
