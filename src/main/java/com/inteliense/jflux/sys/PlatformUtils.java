package com.inteliense.jflux.sys;

import java.nio.file.FileSystems;
import static com.jsoftbiz.utils.OS.OS;

public class PlatformUtils {

    public static String javaVersion() {
        return System.getProperty("java.version");
    }

    public static String userDirectory() {
        return System.getProperty("user.dir");
    }

    public static String arch() {
        return System.getProperty("os.arch");
    }

    public static String user() {
        return System.getProperty("user.name");
    }

    public static String javaHome() {
        return System.getProperty("java.hone");
    }

    public static String lineSeparator() {
        return System.lineSeparator();
    }

    public static String fileSeparator() {
        return FileSystems.getDefault().getSeparator();
    }

    public static String osInfo() {
        return OS.getPlatformName();
    }

    public static String osVersion() {
        return OS.getVersion();
    }

    public static String osName() {
        return OS.getName();
    }

    public static OpSys getOsType() {
        return OSUtil.get(false);
    }

    public static OpSys getOsType(boolean withLinuxDistro) {
        return OSUtil.get(withLinuxDistro);
    }

    public enum OpSys {
        WINDOWS,
        MAC_OSX,
        LINUX,
        ALPINE_LINUX,
        ARCH_LINUX,
        DEBIAN_LINUX,
        FREE_BSD_LINUX,
        REDHAT_LINUX,
        MINT_LINUX,
        UBUNTU_LINUX,
        FEDORA_LINUX,
        OPENSUS_LINUX,
        CENTOS_LINUX,
        SLACKWARE_LINUX,
        UNKNOWN
    }

    private static class OSUtil {

        public static OpSys parse(OpSys sys) {
            switch(sys) {
                case MAC_OSX:
                    return OpSys.MAC_OSX;
                case WINDOWS:
                    return OpSys.WINDOWS;
                case UNKNOWN:
                    return OpSys.UNKNOWN;
                default:
                    return OpSys.LINUX;
            }
        }

        public static OpSys get(boolean getLinuxDistros) {
            if(OS.isWindows()) return OpSys.WINDOWS;
            if(OS.isUnix() && getLinuxDistros) return getLinux();
            if(OS.isUnix() && !getLinuxDistros) return OpSys.LINUX;
            if(OS.isMac()) return OpSys.MAC_OSX;
            return OpSys.UNKNOWN;
        }

        public static OpSys getLinux() {
            if(!OS.isUnix()) return OpSys.UNKNOWN;
            String os = osName().toUpperCase();
            if(os.contains("ALPINE")) return OpSys.ALPINE_LINUX;
            if(os.contains("ARCH")) return OpSys.ARCH_LINUX;
            if(os.contains("DEBIAN")) return OpSys.DEBIAN_LINUX;
            if(os.contains("FREEBSD")) return OpSys.FREE_BSD_LINUX;
            if(os.contains("REDHAT")) return OpSys.REDHAT_LINUX;
            if(os.contains("MINT")) return OpSys.MINT_LINUX;
            if(os.contains("UBUNTU")) return OpSys.UBUNTU_LINUX;
            if(os.contains("FEDORA")) return OpSys.FEDORA_LINUX;
            if(os.contains("OPENSUS")) return OpSys.OPENSUS_LINUX;
            if(os.contains("CENTOS")) return OpSys.CENTOS_LINUX;
            if(os.contains("SLACKWARE")) return OpSys.SLACKWARE_LINUX;
            return OpSys.UNKNOWN;
        }

    }

}
