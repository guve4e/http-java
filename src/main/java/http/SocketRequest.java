package http;

import org.json.JSONObject;

public class SocketRequest extends AHttpRequest{

    @Override
    public AHttpRequest setUrl(String url) {

        return null;
    }

    @Override
    public AHttpRequest addQueryParameter(String queryParameter) throws Exception {
        return null;
    }

    @Override
    public AHttpRequest setContentType(String contentType) {

        return null;
    }

    @Override
    public AHttpRequest addHeader(String fieldName, String fieldValue) {

        return null;
    }

    @Override
    public AHttpRequest addBody(JSONObject data) {
        return null;
    }

    @Override
    public void send() {

    }

    @Override
    public String getResponseRaw() {
        return null;
    }

    @Override
    public JSONObject getResponseAsJson() {
        return null;
    }

    @Override
    public HttpResponse getResponseWithInfo() {
        return null;
    }
}
