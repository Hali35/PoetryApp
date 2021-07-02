package com.example.poetryapp;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBFile {
    //数据库文件名
    private final String DB_NAME = "test21.db";

    private Context context;

    public DBFile(Context context){
        this.context = context;
    }

    // 复制和加载区域数据库中的数据
    public String CopyDBFile() throws IOException{

        // 第一次运行程序时，加载数据库到data/data/当前的包的名称/databases/<db_name>
        // 获取准确的路径，context.getPackageName()得到包名
        File dir = context.getExternalFilesDir("databases");
        System.out.println(dir.toString());
        // 如果文件夹不存在就创建文件
        if (!dir.exists() || !dir.isDirectory()){
            dir.mkdir();
        }
        // 声明文件
        File file = new File(dir, DB_NAME);
        // 输入流
        InputStream inputStream = null;
        // 输出流
        OutputStream outputStream = null;
        // 如果不存在，通过IO流的方式，将assets目录下的数据库文件，写入手机中
        if (!file.exists()){
            try {
                // 创建文件
                file.createNewFile();
                // 通过路径加载文件
                inputStream = context.getClass().getClassLoader().getResourceAsStream("assets/" + DB_NAME);
                // 输出到文件
                outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int len;
                // 按字节写入
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                System.out.println("success");
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                // 关闭资源
                if (outputStream != null){
                    outputStream.flush();
                    outputStream.close();
                }
                if (inputStream != null){
                    inputStream.close();
                }
            }
        }
        return file.getPath();
    }
}

