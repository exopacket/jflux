package com.inteliense.jflux.files;

import com.inteliense.jflux.sys.PlatformUtils;

import java.util.HashMap;

public class PlatformPath {

    private HashMap<PlatformUtils.OpSys, PathObject> map = new HashMap<>();

    public PlatformPath() { }

    public void addPlatform(PlatformUtils.OpSys os, PathObject path) {
        map.put(os, path);
    }

    public PathObject get() {
        PlatformUtils.OpSys os = PlatformUtils.getOsType(containsDistros());
        return map.get(os);
    }

    private boolean containsDistros() {
        for(PlatformUtils.OpSys os : map.keySet()) {
            switch(os) {
                case ALPINE_LINUX:
                case ARCH_LINUX:
                case DEBIAN_LINUX:
                case FREE_BSD_LINUX:
                case REDHAT_LINUX:
                case MINT_LINUX:
                case UBUNTU_LINUX:
                case FEDORA_LINUX:
                case OPENSUS_LINUX:
                case CENTOS_LINUX:
                case SLACKWARE_LINUX:
                    return true;
            }
        }
        return false;
    }

}
