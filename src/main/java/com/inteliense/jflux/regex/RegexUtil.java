package com.inteliense.jflux.regex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static String[] getPathVariables(String path) {
        ArrayList<String> matches = new ArrayList<>();
        Pattern p = Pattern.compile("\\{([a-zA-Z0-0_\\-])\\}");
        Matcher m = p.matcher(path);
        while(m.find()) matches.add(m.group(1));
        String[] arr = new String[matches.size()];
        matches.toArray(arr);
        return arr;
    }
}
