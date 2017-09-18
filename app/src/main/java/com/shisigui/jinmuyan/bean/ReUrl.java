package com.shisigui.jinmuyan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by song on 2017/9/14.
 */

public class ReUrl implements Serializable {
    private List<String> url;
    private String previous;
    private String next;

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
