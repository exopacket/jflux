package com.inteliense.jflux.shell;

import com.inteliense.jflux.threading.types.DetachedThread;
import com.inteliense.jflux.todash.__;

public interface CommandImpl {

    void lineRead(String str, byte[] bytes);
    void noOut(String cmd) throws Exception;
    int runAndWait(String cmd) throws Exception;
    int execute(String cmd) throws Exception;

    int stream(String cmd, __.Console console) throws Exception;

    DetachedThread threaded(String cmd) throws Exception;

}
