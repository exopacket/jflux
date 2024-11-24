package com.inteliense.jflux.jarg;

import com.inteliense.jflux.todash.__;

public class Help {

    public static void getFromKeywords(Keywords keywords) {
        System.out.println("Usage: " + keywords.getExecutable() + " command [options] <value>");
        System.out.println("Options: \n");
        System.out.println("    -h, --help      Display this help message");
        for(String help : keywords.commandHelp()) {
            System.out.println(help);
        }
    }

    public Help(HandlesCommands keyword) {

    }

    public void print() {

    }

}
