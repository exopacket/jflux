package com.inteliense.jflux.http.api.utils;

import com.inteliense.jflux.exceptions.types.UndefinedException;
import jdk.jshell.spi.ExecutionControl;

public class CURL {

    public static String getUrl(String url) throws Exception {

        throw new ExecutionControl.NotImplementedException("FIX CURL");

//        String[] arr = Exec.withOut("curl " + url);
//        String retVal = "";
//
//        for(int i=0; i<arr.length; i++) {
//            if(i > 0) retVal += "\n";
//            retVal += arr[i];
//        }
//
//        return retVal;

    }

}
