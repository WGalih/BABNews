package com.example.prans.news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("title")
    @Expose
    private String mTitle;

    @SerializedName("author")
    @Expose
    private String mAuthor;

    @SerializedName("url")
    @Expose
    private String mUrl;

    @SerializedName("urlToImage")
    @Expose
    private String mUrlToImage;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("source")
    @Expose
    private Source source;

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    public static final int NEWS_IMAGE_TYPE = 0;
    public static final int NEWS_WITHOUT_IMAGE_TYPE = 1;
    private int type;

    public News(String mTitle, String mUrl, String mUrlToImage, int type) {
        this.mTitle = mTitle;
        this.mUrl = mUrl;
        this.mUrlToImage = mUrlToImage;
        this.type = type;
    }

    public int getType() {
        if (mUrlToImage == null) {
            return NEWS_WITHOUT_IMAGE_TYPE;
        } else {
            return NEWS_IMAGE_TYPE;
        }
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getTitle() {
        return mTitle.replace("/<(.*?)\\>", "");
    }

    public String getUrl() {
        return mUrl;
    }

    public String getUrlToImage() {
        if (mUrlToImage.equals("")) {
            return mUrlToImage = null;
        } else {
            return mUrlToImage;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
