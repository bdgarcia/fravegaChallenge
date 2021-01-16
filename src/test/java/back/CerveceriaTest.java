package back;

import static io.restassured.RestAssured.given;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

public class CerveceriaTest {

    @Test
    public void makeSureThatGoogleIsUp() {
        String response = given().queryParam("query", "lagunitas").when().get("https://api.openbrewerydb.org/breweries/autocomplete").getBody().asString();
        JSONArray res = JsonPath.read(response, "$[?(@.name=='Lagunitas Brewing Co')]");
        res.forEach((node) -> {
            String id = JsonPath.read(node, "$.id");
            String currentCerveceria = given().when().get("https://api.openbrewerydb.org/breweries/"+id).getBody().asString();
            if (JsonPath.read(currentCerveceria, "$.state").equals("California")) {
                Assert.assertEquals(JsonPath.read(currentCerveceria, "$.id").toString(), "761");
                Assert.assertEquals(JsonPath.read(currentCerveceria, "$.name").toString(), "Lagunitas Brewing Co");
                Assert.assertEquals(JsonPath.read(currentCerveceria, "$.street").toString(), "1280 N McDowell Blvd");
                Assert.assertEquals(JsonPath.read(currentCerveceria, "$.phone").toString(), "7077694495");
            }
        });
    }

}