package com.example.poetryapp.bean;

import com.example.poetryapp.R;

import java.util.ArrayList;
import java.util.List;

public class PoetryUtils {
    public static String[] title = {};
    public static String[] author = {};
    public static String[] content = {};
    public static int resId = R.drawable.shi_logo;

    public static List<PoetryBean> getAllPoetryList() {
        List<PoetryBean> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            PoetryBean bean = new PoetryBean(title[i],author[i],content[i],resId);
            list.add(bean);
        }
        return list;
    }
}

