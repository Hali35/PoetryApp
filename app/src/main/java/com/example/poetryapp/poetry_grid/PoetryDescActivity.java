package com.example.poetryapp.poetry_grid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poetryapp.DBFile;
import com.example.poetryapp.R;
import com.example.poetryapp.bean.PoetryBean;
import com.example.poetryapp.bean.PoetryUtils;
import com.example.poetryapp.poetry_list.InfoBtn1Activity;

import java.io.IOException;

public class PoetryDescActivity extends AppCompatActivity {
    TextView titleTv1,titleTv2,descTv;
    ImageView backIv,bigPicIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry_desc);
//        调用动态获取读写权限的函数
        requestMyPermissions();
//        将SQlite数据库文件拷贝到手机存储当中
        DBFile dbfile = new DBFile(PoetryDescActivity.this);
        try {
            dbfile.CopyDBFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initView();
        // 接受上一级页面传来的数据
        Intent intent = getIntent();
        PoetryBean poetryBean = (PoetryBean) intent.getSerializableExtra("title");
        // 设置显示控件
        titleTv1.setText(poetryBean.getTitle());
        titleTv2.setText(poetryBean.getAuthor());
        String str1 = poetryBean.getContent();
        str1 = str1.replace("|","\n");
        descTv.setText(str1);
        bigPicIv.setImageResource(poetryBean.getPicId());
    }

    private void initView() {
        titleTv1 = findViewById(R.id.poetrydesc_tv_title1);
        titleTv2 = findViewById(R.id.poetrydesc_tv_title2);
        descTv = findViewById(R.id.poetrydesc_tv_desc);
        backIv = findViewById(R.id.poetrydesc_iv_back);
        bigPicIv = findViewById(R.id.poetrydesc_iv_bigpic);

        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();   //销毁当前的Activity
            }
        });
    }

    // 定义动态获取读写权限的函数
    private void requestMyPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(PoetryDescActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(PoetryDescActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }



}