package com.inteliense.jflux.crypto.adapters;

import com.inteliense.jflux.crypto.adapters.impl.HmacAlgorithmImpl;
import com.inteliense.jflux.files.PathObject;
import com.inteliense.jflux.runtime.lib.JarFileInputStream;
import com.inteliense.jflux.runtime.lib.JarFileObjectInputStream;
import com.inteliense.jflux.runtime.lib.StreamClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HmacAlgorithm extends HmacAlgorithmImpl {

    private String identifier;
    private int keySize;
    private int outSize;

    private Method hashMethod = null;

    public HmacAlgorithm(String identifier, int keySize, int outSize) {
        this.identifier = identifier;
        this.keySize = keySize;
        this.outSize = outSize;
    }

    public void register(PathObject jarFile, String className) throws Exception {
        JarFileInputStream jarIn = new JarFileInputStream(jarFile.getString());
        JarFileObjectInputStream jarObj = new JarFileObjectInputStream(jarIn);
        StreamClassLoader scl = new StreamClassLoader(jarObj);
        jarObj.close();
        jarIn.close();
        String[] classes = scl.getAllClassNames();
        for(String cl : classes) {
            if(!cl.replace(".class", "").equals(className)) continue;
            Class c = scl.loadClass(cl);
            //TODO set private method varaibles using c.getMethod(...)
        }
    }

    @Override
    public byte[] hash(byte[] input, byte[] key) {
        if(hashMethod == null) return new byte[0];
        //TODO make params be an object of known type
        try {
            return (byte[]) hashMethod.invoke(null, (Object) input, (Object) key);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
