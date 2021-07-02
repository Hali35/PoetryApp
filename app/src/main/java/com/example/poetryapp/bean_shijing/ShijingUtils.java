package com.example.poetryapp.bean_shijing;

import com.example.poetryapp.R;

import java.util.ArrayList;
import java.util.List;

public class ShijingUtils {
    public static String[] title = {};
    public static String[] section = {};
    public static String[] content = {};
    public static int resId = R.drawable.shijing_logo;

    public static List<ShijingBean> getAllShijingList() {
        List<ShijingBean> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            ShijingBean bean = new ShijingBean(title[i],section[i],content[i],resId);
            list.add(bean);
        }
        return list;
    }
}
