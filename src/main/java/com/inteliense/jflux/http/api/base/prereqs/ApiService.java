package com.inteliense.jflux.http.api.base.prereqs;

import com.inteliense.jflux.crypto.Rand;
import com.inteliense.jflux.crypto.builtin.SHA;
import com.inteliense.jflux.db.connectors.MysqlConnection;
import com.inteliense.jflux.encoding.BaseX;
import com.inteliense.jflux.encoding.Hex;
import com.inteliense.jflux.http.api.base.endpoints.permissions.ApiEndpointPermissions;
import com.inteliense.jflux.http.api.base.models.AppModelCollection;
import com.inteliense.jflux.http.api.base.users.ApiUserPermissions;
import com.inteliense.jflux.http.api.server.containers.ClientSession;
import com.inteliense.jflux.http.api.server.containers.RemoteClient;

import java.util.ArrayList;

public class ApiService {

    private AppModelCollection appModels;
    private ApiUserPermissions userPermissions;
    private ApiEndpointPermissions endpointPermissions;
    private ArrayList<BlacklistEntry> blacklist = new ArrayList<BlacklistEntry>();
    private ArrayList<RemoteClient> rateLimitedClients = new ArrayList<RemoteClient>();
    private String appKey;
    private MysqlConnection mysql;

    public ApiService(String appName, MysqlConnection mysql) {
        byte[] random = Rand.secure(64, appName);
        String hash = SHA.get512(random);
        this.appKey = BaseX.encode64(Hex.fromHex(hash));
        this.mysql = mysql;
    }

    public void migrateDatabase() {

    }

    public String getAppKey() {
        return appKey;
    }

    public String getSession() {
        return "";
    }

    public String createSession() {
        return "";
    }

    private class BlacklistEntry {

        private BlacklistEntryType entryType;
        private String value;

        public BlacklistEntry(BlacklistEntryType entryType, String value) {
            this.entryType = entryType;
            this.value = value;
        }

        public BlacklistEntry(BlacklistEntryType entryType, ClientSession clientSession) {
            this.entryType = entryType;
            switch(entryType) {
                case CLIENT_ID:
                    this.value = clientSession.getSession().getClientId();
                    break;
                case USER_ID:
                    this.value = clientSession.getSession().getUserId();
                    break;
                case API_KEY:
                    this.value = clientSession.getClient().getApiKey();
                    break;
                case IP_ADDRESS:
                    this.value = clientSession.getClient().getClientInfo().getRemoteIp();
                    break;
            }
        }

        public boolean equals(ClientSession clientSession) {
            switch(entryType) {
                case CLIENT_ID:
                    return this.value.equals(clientSession.getSession().getClientId());
                case USER_ID:
                    return this.value.equals(clientSession.getSession().getUserId());
                case API_KEY:
                    return this.value.equals(clientSession.getClient().getApiKey());
                case IP_ADDRESS:
                    return this.value.equals(clientSession.getClient().getClientInfo().getRemoteIp());
            }
            return false;
        }

        public String getValue() {
            return value;
        }

        public BlacklistEntryType getEntryType() {
            return entryType;
        }

    }

    public boolean inTimeout(ClientSession clientSession, int perMinute) {

        if(clientSession.getSession().getRecentRequests() >= perMinute) {
            return true;
        }

        return false;

    }

    public boolean inBlacklist(ClientSession clientSession) {

        for(int i=0; i<blacklist.size(); i++) {

            BlacklistEntry entry = blacklist.get(i);
            BlacklistEntryType type = entry.getEntryType();
            String value = entry.getValue();

            boolean found = false;

            switch(type) {
                case API_KEY:
                    found = value.equals(clientSession.getClient().getApiKey());
                    break;
                case USER_ID:
                    found = value.equals(clientSession.getSession().getUserId());
                    break;
                case CLIENT_ID:
                    found = value.equals(clientSession.getSession().getClientId());
                    break;
                case IP_ADDRESS:
                    found = value.equals(clientSession.getClient().getClientInfo().getRemoteIp());
                    break;
            }

            if(found)
                return true;

        }

        return false;

    }

    public void addToBlacklist(ClientSession clientSession, BlacklistEntryType entryType) {
        blacklist.add(0, new BlacklistEntry(entryType, clientSession));
    }

    public void removeFromBlacklist(ClientSession clientSession) {
        for(int i= blacklist.size() - 1; i>=0; i--) {
            if(blacklist.get(i).equals(clientSession)) {
                blacklist.remove(i);
            }
        }
    }

    public enum BlacklistEntryType {
        IP_ADDRESS,
        USER_ID,
        CLIENT_ID,
        API_KEY
    }

}
