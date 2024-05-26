package com.inteliense.jflux.crypto.adapters;

import com.inteliense.jflux.crypto.adapters.impl.RandomGeneratorImpl;
import com.inteliense.jflux.files.PathObject;
import com.inteliense.jflux.runtime.lib.JarFileInputStream;
import com.inteliense.jflux.runtime.lib.JarFileObjectInputStream;
import com.inteliense.jflux.runtime.lib.StreamClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RandomGenerator extends RandomGeneratorImpl {

    private String identifier;

    private Method generateMethod = null;

    public RandomGenerator(String identifier) { this.identifier = identifier; }

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
    public byte[] generate(int size, byte[]... params) {
        if(generateMethod == null) return new byte[0];
        //TODO make params be an object of known type
        try {
            return (byte[]) generateMethod.invoke(null, (Object) new Object[]{size, params});
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
