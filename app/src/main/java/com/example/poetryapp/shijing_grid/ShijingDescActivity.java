package com.example.poetryapp.shijing_grid;

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
import com.example.poetryapp.bean_shijing.ShijingBean;

import java.io.IOException;

public class ShijingDescActivity extends AppCompatActivity {
    TextView titleTv,sectionTv,descTv;
    ImageView backIv,bigPicIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shijing_desc);

        //调用动态获取读写权限的函数
        requestMyPermissions();
        //将SQlite数据库文件拷贝到手机存储当中
        DBFile dbfile = new DBFile(ShijingDescActivity.this);
        try {
            dbfile.CopyDBFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initView();
        // 接受上一级页面传来的数据
        Intent intent = getIntent();
        ShijingBean shijingBean = (ShijingBean) intent.getSerializableExtra("title");
        // 设置显示控件
        titleTv.setText(shijingBean.getTitle());
        sectionTv.setText(shijingBean.getSection());
        String str1 = shijingBean.getContent();
        str1 = str1.replace("|","\n");
        str1 = str1.replace("。","\n");
        descTv.setText(str1);
        bigPicIv.setImageResource(shijingBean.getPicId());
    }

    private void initView() {
        titleTv = findViewById(R.id.shijingdesc_tv_title);
        sectionTv = findViewById(R.id.shijingdesc_tv_section);
        descTv = findViewById(R.id.shijingdesc_tv_desc);
        backIv = findViewById(R.id.shijingdesc_iv_back);
        bigPicIv = findViewById(R.id.shijingdesc_iv_bigpic);

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
            ActivityCompat.requestPermissions(ShijingDescActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(ShijingDescActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
    }

}