package com.example.myrecyclerview;

import org.json.JSONArray;
import org.json.JSONException;

public class MyModelClass {

    private String name;
    private String country;
    private String state;
    private String alphaCode;
    private String domain;
    private JSONArray webPages;

    public MyModelClass(){}
    public MyModelClass(String name, String country, String state,
                        String alphaCode, String domain, JSONArray webPages)
    {
        this.name=name;
        this.country=country;
        this.state=state;
        this.alphaCode=alphaCode;
        this.domain=domain;
        this.webPages=webPages;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAlphaCode() {
        return alphaCode;
    }

    public void setAlphaCode(String alphaCode) {
        this.alphaCode = alphaCode;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWebPages() {

        try {
            return webPages.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setWebPages(JSONArray webPages) {
        this.webPages = webPages;
    }
}
