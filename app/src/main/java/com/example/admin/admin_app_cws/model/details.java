package com.example.admin.admin_app_cws.model;



public class details {

    //Data members
    public String Latitude;
    public String Longitude;
    public String PlaceAddress;
    public String PlaceCell;
    public String PlaceHours;
    public String PlaceInfo;
    public String PlaceName;
    public String PlacePrice;
    public String PlaceWebsite;



    public details() {

    }

    public details(String latitude, String longitude, String placeAddress, String placeCell, String placeHours, String placeInfo, String placeName, String placePrice, String placeWebsite ) {
        Latitude = latitude;
        Longitude = longitude;
        PlaceAddress = placeAddress;
        PlaceCell = placeCell;
        PlaceHours = placeHours;
        PlaceInfo = placeInfo;
        PlaceName = placeName;
        PlacePrice = placePrice;
        PlaceWebsite = placeWebsite;

    }
}
