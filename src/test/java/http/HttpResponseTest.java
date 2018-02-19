package http;

import junit.framework.TestCase;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

@RunWith( MockitoJUnitRunner.class )
public class HttpResponseTest {

    @Mock
    private HttpResponse mockResponse;

    @Before
    public void setUp() throws Exception {
        // check if class is wrapped with Runner
        assertNotNull(mockResponse);

    }

    JSONObject createTestJsonObject() {

        JSONObject obj = new JSONObject();
        JSONObject data = new JSONObject();

        data.put("controller", "Test");
        data.put("method", "GET");
        data.put("id", Integer.valueOf(1001));

        obj.put("data",data);
        obj.put("key", "value");

        return obj;
    }

    @Test
    public void testWhenConstructedWithResponseString() {
        // Arrange
        String responseString ="b'HTTP/1.1 200 OK\\r\\nDate: Sat, 17 Feb 2018 15:27:19 GMT\\r\\nServer: Apache/2.4.18 (Ubuntu)\\r\\nVary: Accept-Encoding\\r\\nContent-Length: 119\\r\\nConnection: close\\r\\nContent-Type: text/html; charset=UTF-8\\r\\n\\r\\n{\\n    \"data\": {\\n        \"controller\": \"Test\",\\n        \"method\": \"GET\",\\n        \"id\": \"1001\"\\n    },\\n    \"key\": \"value\"\\n}'";
        JSONObject expectedJson = createTestJsonObject();

        // Act
        JSONObject actualJson = null;
        try {
            HttpResponse response = new HttpResponse(responseString);
           // actualJson = response.getBodyAsJson();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Assert
        //TestCase.assertEquals(expectedJson, actualJson);
        fail("test");
    }
}