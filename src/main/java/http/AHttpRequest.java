package http;

import jdk.nashorn.api.scripting.JSObject;
import java.net.URL;
import java.util.HashMap;

public abstract class AHttpRequest implements IHttpRequest{
    protected URL url = null;
    protected String method = null;
    protected String contentType;
    protected boolean isIdempotent;
    protected HashMap<String, String > headers;
    protected HttpResponse response;
    protected String responseString = null;
    protected JSObject data = null;
    protected String dataRaw = null;

    /**
     * Sets the method
     * and decides if data is needed
     * for the request.
     * @param method type of method
     */
    @Override
    public AHttpRequest setMethod(String method) throws Exception {
//        if (this.url == null)
//            throw new Exception("Set URL first!");

        this.method = method;
        // id GET is idempotent (changes state)
        this.isIdempotent = method.equals("GET");
        return this;
    }
}
