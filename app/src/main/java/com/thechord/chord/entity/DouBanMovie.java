package com.thechord.chord.entity;

import com.thechord.chord.util.JsonFieldAnnotation;

/**
 * Created by Neway on 2015/8/20.
 */
public class DouBanMovie {

    private String id;
    private String title;

    @JsonFieldAnnotation("original_title")
    private String originalTitle;
    //private String [] aka;
    private String alt;

    private String mobileURL;

    @JsonFieldAnnotation("year")
    private String year;

    //private Rating rating
    private int ratingsCount;
    private int wishCount;
    private int collectCount;


    @JsonFieldAnnotation(className = "com.thechord.chord.entity.Image")
    private Image images;


    private String subType;
    private String website;
    //private String [] genres; // movie types

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }


    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getMobileURL() {
        return mobileURL;
    }

    public void setMobileURL(String mobileURL) {
        this.mobileURL = mobileURL;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public int getWishCount() {
        return wishCount;
    }

    public void setWishCount(int wishCount) {
        this.wishCount = wishCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public Image getImage() {
        return images;
    }

    public void setImage(Image image) {
        this.images = image;
    }

}
