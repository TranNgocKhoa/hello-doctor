package com.hellodoctor.auth.models.fb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Thumbnail {

    private static final String URL = "url";
    private static final String HEIGHT = "height";
    private static final String WIDTH = "width";

    @JsonProperty("data")
    private Map<String, Object> data;

    @JsonIgnore
    private int height;

    @JsonIgnore
    private int width;

    @JsonIgnore
    private String url;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
        setHeight(Integer.valueOf(data.get(HEIGHT).toString()));
        setWidth(Integer.valueOf(data.get(WIDTH).toString()));
        setUrl(data.get(URL).toString());
    }
}
