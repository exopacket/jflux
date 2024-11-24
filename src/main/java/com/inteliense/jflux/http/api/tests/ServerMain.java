package com.inteliense.jflux.http.api.tests;

import com.inteliense.jflux.http.api.base.endpoints.InboundRequest;
import com.inteliense.jflux.http.api.base.prereqs.ApiService;
import com.inteliense.jflux.http.api.server.*;
import com.inteliense.jflux.http.api.server.config.APIServerConfig;
import com.inteliense.jflux.http.api.server.containers.*;
import com.inteliense.jflux.http.api.server.encryption.APIKeyPair;
import com.inteliense.jflux.http.api.server.exceptions.APIException;
import com.inteliense.jflux.http.api.server.resources.APIResource;
import com.inteliense.jflux.http.api.server.types.APIServerType;
import com.inteliense.jflux.http.api.server.types.CORSPolicy;
import com.inteliense.jflux.http.api.server.types.ContentType;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class ServerMain {

    public static void main(String[] args) throws APIException {

        APIServerConfig config = new APIServerConfig("127.0.0.1", 8181, "/api");
        config.setApiServerKeyPassword("password");
        config.setApiServerKeystorePath("/home/ryan/testkey.jks");
        config.setServerType(APIServerType.ZERO_TRUST_SYNC);
        config.setCorsPolicy(new CORSPolicy(true));
        config.setRateLimit(25);
        config.useDynamicApiKey(true);
        config.setSessionResourcePaths(
                "session/init",
                "session/keys",
                "session/close");

        APIKeyPair testKeyPair = APIKeyPair.generateNewPair();
        final String API_KEY = "apikey_JnM8qqLoh2CjEM773mMmEUQS3in5cq9VHRiw5iNnXcNeEbuPhqpDZG0OJR9hbtb1Hi8QBkaE";
        final String API_SECRET = "secret_cYM64CuorsmEkjXGNvmmdhtoSWMlrGq7zCD8Eo0O0jeKrfJ02GsAIwXr";
        System.out.println("API KEY = " + API_KEY);
        System.out.println("SECRET = " + API_SECRET);

        API api = new API(config, null) {

            @Override
            public boolean inTimeout(ClientSession clientSession, int perMinute) {
                return false;
            }

            @Override
            public boolean inBlacklist(ClientSession clientSession) {
                return false;
            }

            @Override
            public APIKeyPair lookupApiKey(String apiKey) {


                    return new APIKeyPair(API_KEY, API_SECRET);

                //return null;

            }

            @Override
            public HashMap<String, String> getParameters(String body, ContentType contentType) {
                return null;
            }

            @Override
            public void addToBlacklist(ClientSession clientSession, ApiService.BlacklistEntryType entryType) {

            }

            @Override
            public void removeFromBlacklist(ClientSession clientSession) {

            }

        };

        api.start();
//
//        api.addResource("query/new", "POST", new String[]{"sql", "parameters"}, new APIResource() {
//            @Override
//            public APIResponse execute(InboundRequest request) {
//                JSONObject obj = new JSONObject();
//                obj.put("response", "RESPONSE");
//                return new APIResponse(clientSession, obj, ResponseCode.SUCCESSFUL);
//            }
//
//        });

    }

}
