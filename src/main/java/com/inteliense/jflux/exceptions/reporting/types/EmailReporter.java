package com.inteliense.jflux.exceptions.reporting.types;

import org.extendedweb.aloft.utils.exceptions.types.ReportedException;

public class EmailReporter {

    private static String apiKey = null;
    private static String telnyxPhoneNumber = null;

    public static boolean hasConfiguration() {
        return (apiKey != null && telnyxPhoneNumber != null);
    }

    public static void setConfiguration(String telnyxApiKey, String telnyxPhone) {
        if(apiKey == null) apiKey = telnyxApiKey;
        if(telnyxPhoneNumber == null) telnyxPhoneNumber = telnyxPhone;
    }

    public static void report(ReportedException e) {

    }

}
