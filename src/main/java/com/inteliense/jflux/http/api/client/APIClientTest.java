package com.inteliense.jflux.http.api.client;

import com.inteliense.jflux.http.api.server.exceptions.APIException;
import org.json.simple.JSONObject;

public class APIClientTest {

    public static void main(String[] args) throws Exception, APIException {

        ZETAClient client = new ZETAClient("apikey_JnM8qqLoh2CjEM773mMmEUQS3in5cq9VHRiw5iNnXcNeEbuPhqpDZG0OJR9hbtb1Hi8QBkaE", "secret_cYM64CuorsmEkjXGNvmmdhtoSWMlrGq7zCD8Eo0O0jeKrfJ02GsAIwXr", "http://127.0.0.1:8181/api", "/home/ryan/.zeta");
        client.beginSession("/session/init", "/session/keys", "/session/close");
        JSONObject testData = new JSONObject();
        testData.put("sql", "FIX ME");
        testData.put("parameters", "WITH CODE");
        JSONObject response = client.request("query/new", testData);
        System.out.println(response.get("response"));

    }

}