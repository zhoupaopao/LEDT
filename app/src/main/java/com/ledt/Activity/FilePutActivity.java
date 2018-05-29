package com.ledt.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ledt.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by Lenovo on 2018/5/24.
 */

public class FilePutActivity extends Activity {
    Button write;
    Button read;
    private EditText et_msg;
    private TextView read_msg;
    private TextView file_path;
    Context context=null;
    private static final int BAIDU_READ_PHONE_STATE =100;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileput);
        checkPermission();
        initView();
        initListener();
    }

    private void initView() {
        write=findViewById(R.id.write);
        read=findViewById(R.id.read);
        read_msg=findViewById(R.id.read_msg);
        et_msg=findViewById(R.id.et_msg);
        file_path=findViewById(R.id.file_path);
        context=this;

    }

    private void initListener() {

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=et_msg.getText().toString().trim();
//                writeFileData("test.txt",message);
                //这个方法能够很好的创建文件夹，文件。
                //同时最终能找到这个文件，读取是只需要用这个路径就行
                String filePath = "/sdcard/Test/";
                writeTxtToFile(message, filePath, "test1.txt");
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //读取文件内容
                String mmm=readFileData("test.txt");
//                read_msg.setText(mmm);
//                String path=context.getFilesDir().getPath();
                String path="/sdcard/Test/test1.txt";
                file_path.setText(path+"/test.txt");
                Log.i("onClick: ", path);
                //读取指定路径的文件的内容
                //这个读取的方式两种方法存入的都可以读取
//               String res= loadFromSDFile(path+"/test.txt");
                String res= loadFromSDFile(path);
                read_msg.setText(res);
                Log.i("onClick: ", res);
            }
        });
    }
    private String loadFromSDFile(String fname) {
//        fname="/"+fname;
        String result=null;
        try {
            File f=new File(fname);
            int length=(int)f.length();
            byte[] buff=new byte[length];
            FileInputStream fin=new FileInputStream(f);
            fin.read(buff);
            fin.close();
            result=new String(buff,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(FilePutActivity.this,"没有找到指定文件",Toast.LENGTH_SHORT).show();
        }
        return result;
    }
    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PERMISSION_GRANTED|| ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                //当用户拒绝过一次后，再次请求的时候需要给用户提醒
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, BAIDU_READ_PHONE_STATE);

//            checkPermission();
        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("checkPermission", "checkPermission: 已经授权！");
        }
    }
//用户点击拒绝权限后的事件
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PERMISSION_GRANTED) {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //向指定的文件中写入指定的数据
    public void writeFileData(String filename, String content){

        try {
//加上MODE_APPEND，代表着数据是往后添加的
            FileOutputStream fos = this.openFileOutput(filename, MODE_PRIVATE|MODE_APPEND  );//获得FileOutputStream

            //将要写入的字符串转换为byte数组

            byte[]  bytes = content.getBytes();

            fos.write(bytes);//将byte数组写入文件

            fos.close();//关闭文件输出流

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //打开指定文件，读取其数据，返回字符串对象
    public String readFileData(String fileName){

        String result="";

        try{

            FileInputStream fis = this.openFileInput(fileName);

            //获取文件长度
            int lenght = fis.available();

            byte[] buffer = new byte[lenght];

            fis.read(buffer);

            //将byte数组转换成指定格式的字符串
            result = new String(buffer, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  result;
    }

    // 将字符串写入到文本文件中
    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath+fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }
    }
    // 生成文件
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }
}
