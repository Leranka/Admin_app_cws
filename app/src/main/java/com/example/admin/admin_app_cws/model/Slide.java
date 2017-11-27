package com.example.admin.admin_app_cws.model;

/**
 * Created by Admin on 11/24/2017.
 */

public class Slide {

    String url;
    String url2;
    String url3;


    public Slide() {
    }

    public Slide(String url, String url2, String url3) {
        this.url = url;
        this.url2 = url2;
        this.url3 = url3;
    }

    public String getUrl() {
        return url;
    }

    public String getUrl2() {
        return url2;
    }

    public String getUrl3() {
        return url3;
    }
}
