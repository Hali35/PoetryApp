package com.example.poetryapp.lunyu_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.poetryapp.DBFile;
import com.example.poetryapp.R;
import com.example.poetryapp.bean_lunyu.LunyuBean;
import com.example.poetryapp.bean_lunyu.LunyuUtils;
import com.example.poetryapp.lunyu_grid.LunyuDescActivity;
import com.example.poetryapp.poetry_list.InfoListAdapter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoBtn2Activity extends AppCompatActivity implements View.OnClickListener{
    EditText searchEt;
    ImageView searchIv,flushIv;
    ListView showLv;
    //ListView内部数据源
    List<LunyuBean> mDatas;
    List<LunyuBean> allLunyuList;
    private InfoList2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_btn2);
        //调用动态获取读写权限的函数
        requestMyPermissions();
        //将SQlite数据库文件拷贝到手机存储当中
        DBFile dbfile = new DBFile(InfoBtn2Activity.this);
        try {
            dbfile.CopyDBFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据
        getData();
        //查找控件
        initView();
        //2.找到ListView对应的数据源
        mDatas = new ArrayList<>();
        allLunyuList = LunyuUtils.getAllLunyuList();
        mDatas.addAll(allLunyuList);
        //3.创建适配器 BaseAdapter的子类
        adapter = new InfoList2Adapter(this, mDatas);
        showLv.setAdapter(adapter);//4.设置适配器
        // 设置单向点击监听
        setListener();
    }

    private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LunyuBean LunyuBean = mDatas.get(position);
                Intent intent = new Intent(InfoBtn2Activity.this, LunyuDescActivity.class);
                intent.putExtra("chapter",LunyuBean);
                startActivity(intent);
            }
        });
    }


    //定义获取数据的函数
    public void getData() {
        SQLiteDatabase db = openOrCreateDatabase(this.getExternalFilesDir("databases").toString() + "/test21.db",
                MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("select * from lunyu",null);
        String[] chapter = {};
        String[] content = {};
        if (cursor.moveToFirst()){
            do {
                String chapter1 = cursor.getString(cursor.getColumnIndex("chapter"));
                String content1 = cursor.getString(cursor.getColumnIndex("content"));
                chapter = insert(chapter,chapter1);
                content = insert(content,content1);
            }while (cursor.moveToNext());
        }
        cursor.close();
        LunyuUtils.chapter = chapter;
        LunyuUtils.content = content;
    }

    //往字符串数组追加新数据
    private static String[] insert(String[] arr, String str) {
        int size = arr.length;  //获取数组长度
        String[] tmp = new String[size + 1];  //新建临时字符串数组，在原来基础上长度加一
        for (int i = 0; i < size; i++){  //先遍历将原来的字符串数组数据添加到临时字符串数组
            tmp[i] = arr[i];
        }
        tmp[size] = str;  //在最后添加上需要追加的数据
        return tmp;  //返回拼接完成的字符串数组
    }

    private void initView() {
        searchEt = findViewById(R.id.lunyu_info_et_search);
        searchIv = findViewById(R.id.lunyu_info_iv_search);
        flushIv = findViewById(R.id.lunyu_info_iv_flush);
        showLv = findViewById(R.id.infobtn2_lv);
        searchIv.setOnClickListener(this);//添加点击事件的监听器
        flushIv.setOnClickListener(this);
    }


    // 定义动态获取读写权限的函数
    private void requestMyPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(InfoBtn2Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(InfoBtn2Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lunyu_info_iv_flush:  // 刷新重置点击
                searchEt.setText("");
                mDatas.clear();
                mDatas.addAll(allLunyuList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.lunyu_info_iv_search:  // 搜索点击
                // 1.获取输入内容，判断不为空
                String msg = searchEt.getText().toString().trim();  //获取输入的信息
                if (TextUtils.isEmpty(msg)) {
                    Toast.makeText(this,"输入内容不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                // 2.判断所有诗词列表的标题是否包含输入内容，若包含则将其添加到小集合中
                List<LunyuBean> list = new ArrayList<>();
                for (int i = 0; i < allLunyuList.size(); i++){
                    String title = allLunyuList.get(i).getChapter();
                    if (title.contains(msg)) {
                        list.add(allLunyuList.get(i));
                    }
                }
                mDatas.clear();  //清空ListView的适配器数据源内容
                mDatas.addAll(list);  // 添加新的数据到数据源中
                adapter.notifyDataSetChanged();  // 提示适配器更新
                break;
        }
    }

}