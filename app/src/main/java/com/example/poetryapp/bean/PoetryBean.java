package com.example.poetryapp.bean;

import java.io.Serializable;

public class PoetryBean implements Serializable {
    private String title;
    private String author;
    private String content;
    private int picId;

    public PoetryBean() {
    }

    public PoetryBean(String title, String author, String content, int picId) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.picId = picId;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public int getPicId() { return picId; }

    public void setPicId(int picId) { this.picId = picId; }





//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public int getPicId() { return picId; }
//
//    public void setPicId(int picId) { this.picId = picId; }
}

