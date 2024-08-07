package com.inteliense.jflux.jarg;

public class Arg {

    private String name;
    private char option = '\0';
    private String value = null;
    private boolean flag = false;
    private boolean required = false;
    private String helpText = "";

    public Arg(String name) {
        if(name.contains("--")) this.flag = true;
        this.name = name.replace("-", "");
    }

    public Arg(char option) {
        this.option = option;
    }

    public Arg(String name, boolean requiresValue) {
        if(name.contains("--")) this.flag = true;
        this.name = name.replace("-", "");
        this.required = requiresValue;
    }

    public Arg(String name, char option) {
        if(name.contains("--")) this.flag = true;
        this.name = name.replace("-", "");
        this.option = option;
    }

    public Arg(String name, char option, boolean requiresValue) {
        if(name.contains("--")) this.flag = true;
        this.name = name.replace("-", "");
        this.option = option;
        this.required = requiresValue;
    }

    public Arg(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setHelpText(String value) {
        this.helpText = value;
    }

    public boolean requiresValue() {
        return required;
    }

    public boolean isFlag() {
        return this.flag;
    }

    public boolean isOption() {
        return this.option != '\0';
    }

    public String getOption() {
        return "" + option;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

}
