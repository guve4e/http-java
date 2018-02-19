package http;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Map;


public class HttpResponse {

    private String httpVersion;
    private Integer code;
    private String message;
    private String[] headersRaw;
    private Map<String, String> headers;
    private String bodyString;
    private JSONObject bodyJson;
    private String responseInfoRaw;

    /**
     * Constructor
     * @param responseRaw
     * @throws Exception
     */
    public HttpResponse(String responseRaw) throws Exception {

        // break response
        this.responseInfoRaw = this.breakResponseString(responseRaw);

        // extract http version, code and message
       String responseLine = this.retrieveResponseLineAndHeaders();

        // parse the response line
        this.parseResponseLine(responseLine);

        // extract headers
        this.retrieveRawHeaders();

    }

    private void retrieveRawHeaders() {

    }

    /**
     *
     * @param responseLine
     * @throws Exception
     */
    private void parseResponseLine(String responseLine) throws Exception {

        String[] respLine = responseLine.split(" ");

        // check for meaningful response line
        if (respLine.length > 3)
            throw new Exception("Wrong response line!");

        this.httpVersion = respLine[0];
        this.code = Integer.parseInt(respLine[1]);
        this.message = respLine[2];
    }

    /**
     * Extracts the response line
     * Ret
     * @return
     * @throws Exception
     */
    private String retrieveResponseLineAndHeaders() throws Exception {

        String[] response = this.responseInfoRaw.split("\r\n");
        int responseLine = 0;
        int endIndex = response.length;

        // response info must contain at leas one line
        if (endIndex < 1)
            throw new Exception("Wrong response info!");

        // headers should be the second line till the end
        this.headersRaw = Arrays.copyOfRange(response, 1, endIndex);

        // return the response line
        return response[responseLine];
    }

    /**
     * Breaks response string into two parts:
     * 1. The response info (http version, code, message and headers)
     * 2. The response body
     *
     * @param responseRaw String: the whole http response
     * @return String: the response info
     * @throws Exception
     */
    private String breakResponseString(String responseRaw) throws Exception {

        String[] response = responseRaw.split("\\s*\\r?\\n\\r?\\n\\s*");

        // check for a reasonable response string
        if (response.length > 2 )
            throw new Exception("Wrong Response String!");

        int responseInfo = 0;
        int responseBody = 1;

        // extract the body first
        this.bodyString = response[responseBody];

        // return rest response info
        return response[responseInfo];
    }

}
