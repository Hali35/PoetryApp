package com.example.poetryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.poetryapp.songci_list.InfoBtn3Activity;
import com.example.poetryapp.lunyu_list.InfoBtn2Activity;
import com.example.poetryapp.poetry_list.InfoBtn1Activity;
import com.example.poetryapp.shijing_list.InfoBtn4Activity;

import java.io.IOException;

public class HomeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);

        //调用动态获取读写权限的函数
        requestMyPermissions();

        //将SQlite数据库文件拷贝到手机存储当中
        DBFile dbfile = new DBFile(HomeMenuActivity.this);
        try {
            dbfile.CopyDBFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.home_btn1:
                intent.setClass(HomeMenuActivity.this, InfoBtn1Activity.class);
                break;
            case R.id.home_btn2:
                intent.setClass(HomeMenuActivity.this, InfoBtn2Activity.class);
                break;
            case R.id.home_btn3:
                intent.setClass(HomeMenuActivity.this, InfoBtn3Activity.class);
                break;
            case R.id.home_btn4:
                intent.setClass(HomeMenuActivity.this, InfoBtn4Activity.class);
                break;
        }
        startActivity(intent);
    }

    // 定义动态获取读写权限的函数
    private void requestMyPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(HomeMenuActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(HomeMenuActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

}