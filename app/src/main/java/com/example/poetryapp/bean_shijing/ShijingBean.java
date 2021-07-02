package com.example.poetryapp.bean_shijing;

import java.io.Serializable;

public class ShijingBean  implements Serializable {
    private String title;
    private String section;
    private String content;
    private int picId;

    public ShijingBean() {
    }

    public ShijingBean(String title, String section, String content, int picId) {
        this.title = title;
        this.section = section;
        this.content = content;
        this.picId = picId;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getSection() { return section; }

    public void setSection(String section) { this.section = section; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public int getPicId() { return picId; }

    public void setPicId(int picId) { this.picId = picId; }
}
