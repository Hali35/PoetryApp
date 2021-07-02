package com.example.poetryapp.bean_lunyu;

import java.io.Serializable;

public class LunyuBean implements Serializable {
    private String chapter;
    private String content;
    private int picId;

    public LunyuBean() {
    }

    public LunyuBean(String chapter, String content, int picId) {
        this.chapter = chapter;
        this.content = content;
        this.picId = picId;
    }

    public String getChapter() { return chapter; }

    public void setChapter(String chapter) { this.chapter = chapter; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public int getPicId() { return picId; }

    public void setPicId(int picId) { this.picId = picId; }
}

