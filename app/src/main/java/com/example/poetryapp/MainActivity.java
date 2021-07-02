package com.example.poetryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    int time = 5;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if (msg.what == 1){
                time--;
                if (time == 0){
                    //跳转页面
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, HomeMenuActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    tv.setText(time+"");
                    handler.sendEmptyMessageDelayed(1,1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //调用动态获取读写权限的函数
        requestMyPermissions();

        //将SQlite数据库文件拷贝到手机存储当中
        DBFile dbfile = new DBFile(MainActivity.this);
        try {
            dbfile.CopyDBFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //执行sql查询语句
//        initview();

        tv = findViewById(R.id.main_tv);
        handler.sendEmptyMessageDelayed(1,1000);


    }

    // 定义动态获取读写权限的函数
    private void requestMyPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

//    //查询语句
//    private void initview(){
//        SQLiteDatabase db = openOrCreateDatabase(this.getExternalFilesDir("databases").toString() + "/test21.db",
//                MODE_PRIVATE, null);
//        Cursor cursor = db.query("poetry",null,null,null,null,null,null);
//        cursor = db.rawQuery("select * from poetry2", null);
//        if (cursor.moveToFirst()){
//            do {
//                System.out.println(cursor.getString(3));
//            }while (cursor.moveToNext());
//        }
//        cursor.close();
//    }
}