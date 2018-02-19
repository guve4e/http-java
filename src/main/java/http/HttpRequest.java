package http;

import org.json.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest extends AHttpRequest{

    HttpURLConnection connection = null;
    private boolean test = false;

    private void sendData() throws Exception {
        if(dataRaw.isEmpty() || dataRaw == null)
            throw new Exception("Data to be sent has to be set first!");

        if (this.test != true)
        {
            // send data
            DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
            wr.writeBytes(dataRaw);
            wr.close();
        }
    }

    private void setUpConnection() throws Exception {
        if (connection == null)
            throw new Exception("Null connection!");

        connection.setUseCaches(false);
        connection.setDoOutput(true);
    }

    /**
     *
     * @param
     */
    public HttpRequest() { }

    /**
     * Testing constructor
     * for dependency injection.
     * @param connection
     */
    public HttpRequest(HttpURLConnection connection) {
        this.connection = connection;
        this.test = true;
    }

    @Override
    public AHttpRequest setUrl(String urlString) throws IOException {
        this.url = new URL(urlString);
        this.connection = (HttpURLConnection) this.url.openConnection();
        return this;
    }

    @Override
    public AHttpRequest addQueryParameter(String queryParameter) throws Exception {
        return null;
    }

    @Override
    public AHttpRequest setContentType(String contentType) {
        this.connection.setRequestProperty("Content-Type", contentType);
        return this;
    }

    @Override
    public AHttpRequest addHeader(String fieldName, String fieldValue) {
        this.connection.setRequestProperty(fieldName, fieldValue);
        return this;
    }

    @Override
    public AHttpRequest addBody(JSONObject data) {
        this.dataRaw = data.toString();
        return this;
    }

    @Override
    public void send() throws Exception {

        // set up the connection first
        this.setUpConnection();

        // send that if POST, PUT, DELETE, etc
        if (!this.isIdempotent)
            this.sendData();

        // get the response
        InputStream inputStream = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+

        // collect each line
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }

        rd.close();
        this.responseString = response.toString();
    }

    @Override
    public String getResponseRaw() {
        return this.responseString;
    }

    @Override
    public JSONObject getResponseAsJson() {
        return new JSONObject(this.responseString);
    }

    @Override
    public HttpResponse getResponseWithInfo() {
        return null;
    }

    public static void main(String[] args) throws Exception {
        HttpRequest request = new HttpRequest();
        request.setUrl("http://webapi.ddns.net/index.php/mockcontroller/1001")
        .setContentType("application/json")
        .setMethod("GET")
        .send();

        // get the response as raw string
        String s = request.getResponseRaw();
        System.out.println("Raw request-> " + s);

        // or get it as json
        JSONObject jsonObject = request.getResponseAsJson();
//
//        JSONObject obj = new JSONObject();
//
//        obj.put("name", "foo");
//        obj.put("num", new Integer(100));
//        obj.put("balance", new Double(1000.21));
//        obj.put("is_vip", new Boolean(true));
//
//
//        HttpRequest request2 = new HttpRequest();
//        request.setUrl("http://localhost/web-api/index.php/mockcontroller/1001")
//                .setContentType("application/json")
//                .setMethod("POST")
//                .addBody(obj)
//                .send();
//
//        // get the response as raw string
//        String s2 = request.getResponseRaw();
//        System.out.println("Raw request-> " + s);
//
//        // or get it as json
//        JSONObject jsonObject2 = request.getResponseAsJson();

    }
}
