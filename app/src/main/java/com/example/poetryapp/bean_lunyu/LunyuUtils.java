package com.example.poetryapp.bean_lunyu;

import com.example.poetryapp.R;

import java.util.ArrayList;
import java.util.List;

public class LunyuUtils {
    public static String[] chapter = {};
    public static String[] content = {};
    public static int resId = R.drawable.lunyu_logo;

    public static List<LunyuBean> getAllLunyuList() {
        List<LunyuBean> list = new ArrayList<>();
        for (int i = 0; i < chapter.length; i++) {
            LunyuBean bean = new LunyuBean(chapter[i],content[i],resId);
            list.add(bean);
        }
        return list;
    }

}
