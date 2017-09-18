package com.shisigui.jinmuyan.bean;

import java.io.Serializable;

/**
 * Created by song on 2017/9/10.
 */

public class Data implements Serializable {
    private String title;
    private String url;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
