package com.example.poetryapp.bean_songci;

import com.example.poetryapp.R;

import java.util.ArrayList;
import java.util.List;

public class SongciUtils {
    public static String[] title = {};
    public static String[] author = {};
    public static String[] content = {};
    public static int resId = R.drawable.songci_logo;

    public static List<SongciBean> getAllSongciList() {
        List<SongciBean> list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            SongciBean bean = new SongciBean(title[i],author[i],content[i],resId);
            list.add(bean);
        }
        return list;
    }
}
