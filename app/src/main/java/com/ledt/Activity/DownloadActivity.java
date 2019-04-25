package com.ledt.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ledt.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Lenovo on 2019/4/25.
 */

public class DownloadActivity extends Activity {
    Button achievefile;
    Button achievepic;
    String url="http://www.runoob.com/try/download/SocketUploadBigFile.zip";
    String TAG="DownloadActivity";
    ProgressBar mProgressBar;
    ImageView image;
    Button uploadfile;
    TextView errmsg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        achievefile=findViewById(R.id.achievefile);
        mProgressBar=findViewById(R.id.progressbar);
        uploadfile=findViewById(R.id.uploadfile);
        achievepic=findViewById(R.id.achievepic);
        image=findViewById(R.id.image);
        errmsg=findViewById(R.id.errmsg);
        achievefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                achievefilee();
            }
        });
        uploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiFileUpload();
            }
        });
        achievepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
    }

    private void achievefilee() {
//        String url = "http://192.168.10.168:8080/oppo.mp4";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "okhttpoppo.zip")//
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Log.e(TAG, "onResponse :" + response.getAbsolutePath());
                    }

                    @Override
                    public void onBefore(Request request, int id) {
                        super.onBefore(request, id);
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
//                        super.inProgress(progress, total, id);
                        mProgressBar.setProgress((int) (100 * progress));
                    }

                });
    }

    public void getImage()
    {
        errmsg.setText("");
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(5000)
                .readTimeOut(5000)
                .writeTimeOut(5000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        errmsg.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {
                        //设置图片
                        image.setImageBitmap(response);
                    }
                });
    }


    public void multiFileUpload()
    {
        File file = new File("/storage/emulated/0/okhttpoppo.zip");
//        File file = new File("/storage/emulated/0/okhttpoppo.zip", "okhttpoppo.zip");
//        File file2 = new File(Environment.getExternalStorageDirectory(), "2.txt");
        if (!file.exists())
        {
            Toast.makeText(DownloadActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        //        params.put("username", "杨光福");
        //        params.put("password", "123");

        String url = "http://project.thinghigh.cn/index.php/api/v1/uploadTxt";
        OkHttpUtils.post()//
                .addFile("mFile", "oppo.zip", file)//
//                .addFile("mFile", "afua.txt", file2)//
                .url(url)
                .params(params)//
                .build()//
                .execute(new MyStringCallback());
    }
    public class MyStringCallback extends StringCallback
    {
        @Override
        public void inProgress(float progress,long total, int id)
        {
            Log.e(TAG, "inProgress:" + progress);
            mProgressBar.setProgress((int) (100 * progress));
        }



        @Override
        public void onError(Call call, Exception e, int id) {
            errmsg.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            errmsg.setText("onResponse" + response);
        }
    }

}
