package com.inteliense.jflux.runtime.lib;

import java.util.jar.JarInputStream;

public class JarFileObjectInputStream extends JarInputStream {

    public JarFileObjectInputStream(JarFileInputStream is) throws Exception {
        super(is);
    }

}
