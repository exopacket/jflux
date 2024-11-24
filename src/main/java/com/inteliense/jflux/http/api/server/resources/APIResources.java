package com.inteliense.jflux.http.api.server.resources;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIResources {

    private ArrayList<String> resources = new ArrayList<String>();
    private ArrayList<APIResource> definitions = new ArrayList<APIResource>();

    public void removeAt(int index) {
        resources.remove(index);
        definitions.remove(index);
    }
    public ArrayList<String> getResourcesList() {
        return resources;
    }

    public int getSize() {
        return resources.size();
    }

    public int getIndex(String resource) {

        boolean b = true;
        int index = -1;
        for(int i=0; i< resources.size(); i++) {
            String wVariables = resources.get(i);
            String[] wVaraiblesSplit = wVariables.split("/");
            String[] actualSplit = resource.split("/");
            for(int x=0; x< wVaraiblesSplit.length; x++) {
                String wVar = wVaraiblesSplit[x];
                Pattern p = Pattern.compile("\\{.*\\}");
                Matcher m = p.matcher(wVar);
                if(m.matches()) continue;
                if(!wVar.equals(actualSplit[x])) {
                    b = false;
                    index = i;
                    break;
                }
            }
            if(!b) break;
        }

        return index;

    }

    public boolean inList(String resource) {
        if(resource.equals("/")) return resources.contains("/");

        boolean b = true;
        for(int i=0; i< resources.size(); i++) {
            String wVariables = resources.get(i);
            String[] wVaraiblesSplit = wVariables.split("/");
            String[] actualSplit = resource.split("/");
            for(int x=0; x< wVaraiblesSplit.length; x++) {
                String wVar = wVaraiblesSplit[x];
                Pattern p = Pattern.compile("\\{.*\\}");
                Matcher m = p.matcher(wVar);
                if(m.matches()) continue;
                if(!wVar.equals(actualSplit[x])) {
                    b = false;
                    break;
                }
            }
            if(!b) break;
        }
        return b;
    }

    public APIResource getResource(String value) {

        int index = getIndex(value);
        return definitions.get(index);

    }

    public String getResourcePath(String value) {

        int index = getIndex(value);
        return resources.get(index);

    }

    public void addResource(String path, APIResource definition) {

        resources.add(path);
        definitions.add(definition);

    }

    public void addResource(String path, String[] parameters, APIResource definition) {

        resources.add(path);
        definitions.add(definition);
        getResource(path).setParameters(parameters);

    }

    public void addResource(String path, ArrayList<String> parameters, APIResource definition) {

        resources.add(path);
        definitions.add(definition);
        getResource(path).setParameters(parameters);

    }

    public void addResource(String path, boolean isAsync, APIResource definition) {

        resources.add(path);
        definitions.add(definition);
        getResource(path).isAsync(isAsync);

    }

    public void addResource(String path, boolean isAsync, String[] parameters, APIResource definition) {

        resources.add(path);
        definitions.add(definition);
        getResource(path).setParameters(parameters);
        getResource(path).isAsync(isAsync);

    }

    public void addResource(String path, boolean isAsync, ArrayList<String> parameters, APIResource definition) {

        resources.add(path);
        definitions.add(definition);
        getResource(path).setParameters(parameters);
        getResource(path).isAsync(isAsync);

    }


}
