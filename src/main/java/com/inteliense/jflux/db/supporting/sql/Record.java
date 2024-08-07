package com.inteliense.jflux.db.supporting.sql;

import com.inteliense.jflux.json.JSON;
import com.inteliense.jflux.todash.__;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

;

public class Record {

    Field[] fields;
    private int currField = 0;
    private String table = "";

    public Record(ArrayList<Field> fields, String table) {
        this.fields = new Field[fields.size()];
        this.fields = fields.toArray(this.fields);
        this.table = table;
    }

    public Object[] pair(int index) {
        Field current = fields[index];
        if(current.getType() == Column.class) return new Object[]{column(index), val(index)};
        return new String[]{};
    }

    public Object[][] toArray() {
        Object[][] arr = new Object[fields.length][2];
        for(int i=0; i<fields.length; i++) {
            arr[i] = pair(i);
        }
        return arr;
    }

    public String toString() {
        return Arrays.deepToString(toArray());
    }

    public String json() {
        Object[][] objArr = toArray();
        JSONObject root = new JSONObject();
        JSONArray arr = new JSONArray();
        for(int i=0; i< objArr.length; i++) {
            JSONObject data = new JSONObject();
            data.put((String) objArr[i][0].toString(), objArr[i][1]);
            arr.add(data);
        }
        root.put(table, arr);
        return JSON.getString(root);
    }



    public String column(int index) {
        return fields[index].column().name();
    }

    public String function(int index) {
        return fields[index].function().name();
    }

    public Object val(int index) {
        return fields[index].get();
    }

    public Object val(String name) {
        for(int i=0; i<fields.length; i++) if(__.same(fields[i].column().name(), name)) return fields[i].get();
        return new Object();
    }


}
