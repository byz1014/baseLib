package com.example.baselib;

public class HttpSerializable {
     private String json;
     private int code;
     private String method;

    public HttpSerializable(String json, int code, String method) {
        this.json = json;
        this.code = code;
        this.method = method;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("json=")
                .append(json)//
                .append("\ncode=")
                .append(code)
                .append("\nmethod=")
                .append(method);
        return stringBuilder.toString();
    }
}
