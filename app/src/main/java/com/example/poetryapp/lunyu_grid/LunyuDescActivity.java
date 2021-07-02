package com.example.poetryapp.lunyu_grid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.poetryapp.DBFile;
import com.example.poetryapp.R;
import com.example.poetryapp.bean_lunyu.LunyuBean;

import java.io.IOException;

public class LunyuDescActivity extends AppCompatActivity {
    TextView chapterTv1,chapterTv2,descTv;
    ImageView backIv,bigPicIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunyu_desc);
        //调用动态获取读写权限的函数
        requestMyPermissions();
        //将SQlite数据库文件拷贝到手机存储当中
        DBFile dbfile = new DBFile(LunyuDescActivity.this);
        try {
            dbfile.CopyDBFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initView();
        // 接受上一级页面传来的数据
        Intent intent = getIntent();
        LunyuBean lunyuBean = (LunyuBean) intent.getSerializableExtra("chapter");
        chapterTv1.setText(lunyuBean.getChapter());
        chapterTv2.setText(lunyuBean.getChapter());
        String str1 = lunyuBean.getContent();
        str1 = str1.replace("|","\n");
        str1 = str1.replace("“","\n”");
        descTv.setText(str1);
        bigPicIv.setImageResource(lunyuBean.getPicId());
    }

    private void initView() {
        chapterTv1 = findViewById(R.id.lunyudesc_tv_chapter1);
        chapterTv2 = findViewById(R.id.lunyudesc_tv_chapter2);
        descTv = findViewById(R.id.lunyudesc_tv_desc);
        backIv = findViewById(R.id.lunyudesc_iv_back);
        bigPicIv = findViewById(R.id.lunyudesc_iv_bigpic);

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
            ActivityCompat.requestPermissions(LunyuDescActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(LunyuDescActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

}