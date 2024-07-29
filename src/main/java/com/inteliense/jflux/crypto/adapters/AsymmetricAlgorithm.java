package com.inteliense.jflux.crypto.adapters;

import com.inteliense.jflux.crypto.adapters.impl.AsymmetricAlgorithmImpl;
import com.inteliense.jflux.files.PathObject;
import com.inteliense.jflux.runtime.lib.JarFileInputStream;
import com.inteliense.jflux.runtime.lib.JarFileObjectInputStream;
import com.inteliense.jflux.runtime.lib.StreamClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AsymmetricAlgorithm extends AsymmetricAlgorithmImpl {

    private String identifier;
    private int keySize;

    private Method encryptMethod = null;
    private Method decryptMethod = null;
    private Method generatePairMethod = null;

    public AsymmetricAlgorithm(String identifier, int keySize) {
        this.identifier = identifier;
        this.keySize = keySize;
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
    public byte[] encrypt(byte[] input, byte[] key, byte[]... aad) {
        if(encryptMethod == null) return new byte[0];
        //TODO make params be an object of known type
        String[] params = new String[]{};
        try {
            return (byte[]) encryptMethod.invoke(null, (Object) params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] ciphertext, byte[] key, byte[]... aad) {
        if(decryptMethod == null) return new byte[0];
        //TODO make params be an object of known type
        String[] params = new String[]{};
        try {
            return (byte[]) decryptMethod.invoke(null, (Object) params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[][] generateKeyPair(byte[]... params) {
        if(generatePairMethod == null) return new byte[0][0];
        //TODO make params be an object of known type
        try {
            return (byte[][]) generatePairMethod.invoke(null, (Object) params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
