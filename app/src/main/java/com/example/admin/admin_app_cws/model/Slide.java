package com.example.admin.admin_app_cws.model;

/**
 * Created by Admin on 11/24/2017.
 */

public class Slide {

  public   String pic1;
  public   String pic2;
  public   String pic3;



    public Slide() {
    }

    public Slide(String pic1) {
        this.pic1 = pic1;

    }


  public Slide(String pic1, String pic2, String pic3) {
    this.pic1 = pic1;
    this.pic2 = pic2;
    this.pic3 = pic3;
  }
}
