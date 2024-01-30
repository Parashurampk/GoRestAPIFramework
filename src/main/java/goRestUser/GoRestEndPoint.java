package goRestUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestEndPoint {
	
	public static Response sendRequest(String endpoint, String httpMethod, Object payload, String id)
			throws IOException {

		Path path = Paths.get("./src/main/java/goRestUser/token.txt");
		String token = Files.readString(path);
		
		Response response = null;
		switch (httpMethod.toLowerCase()) {
		case "get":
			response = RestAssured.given().when().get(endpoint).then().extract().response();
			break;
		case "post":
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).contentType(ContentType.JSON).accept(ContentType.JSON)
					.body(payload).when().post(endpoint).then().extract().response();
			break;
		case "patch":
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).pathParam("id", id).contentType(ContentType.JSON)
					.body(payload).when().patch(endpoint).then().extract().response();
			break;
		case "delete":
			response = RestAssured.given().header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + token).pathParam("id", id).when().delete(endpoint).then()
					.extract().response();
			break;
		default:
			System.out.println("Unsupported HTTP method");
			break;
		}
		return response;
	}
}

/**
	The method is declared as public and static, making it accessible without the need to create an instance of the class.
	It's named sendRequest and accepts four parameters:
	endpoint: The URL or API endpoint to send the request to.
	httpMethod: The HTTP method for the request (GET, POST, PATCH, DELETE).
	payload: The payload (request body) to be sent with the request. It's of type Object, so it can accommodate different types of payloads.
	id: An identifier, likely used for PATCH and DELETE requests.
	
    It reads the authentication token from a file named token.txt located in the 
    ./src/main/java/goRestUser/ directory.
   
	It uses a switch statement to determine the type of HTTP request to be made (GET, POST, PATCH, or DELETE).
	For each case, it utilizes RestAssured to build and execute the corresponding HTTP request.
    The response is extracted and stored in the response variable.
		
*/
