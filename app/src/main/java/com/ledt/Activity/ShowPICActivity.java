package com.ledt.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ledt.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 29423 on 2017/11/6 0006.
 */

public class ShowPICActivity extends Activity implements View.OnClickListener {
    private ImageView image;
    private ProgressDialog progress;
    private Button btn_download;
    private Button btn_showimglist;
//    private ListView list_image;
    HashMap<String,Object>msg;
//    ArrayList<Bitmap>bit=new ArrayList<>();
//    ArrayList<HashMap<String,Object>>alllist=new ArrayList<HashMap<String,Object>>();
    private static String URL="http://pic86.huitu.com/res/20160918/1112112_20160918163711835600_1.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpic);

        image=(ImageView) findViewById(R.id.iv_image);
//        list_image=(ListView) findViewById(R.id.image_list);
        btn_download=(Button) findViewById(R.id.btn_download);
        btn_showimglist=(Button) findViewById(R.id.btn_showimglist);
        progress=new ProgressDialog(this);
        progress.setIcon(R.drawable.user_icon);
        progress.setTitle("提示信息");
        progress.setMessage("正在下载，请稍候...");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        btn_download.setOnClickListener((View.OnClickListener) this);
        btn_showimglist.setOnClickListener((View.OnClickListener) this);
//        getInformation();
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v.getId()==R.id.btn_download){
            Log.i("btn_download", "1: ");
            new MyAsyncTask().execute(URL);
        }else if(v.getId()==R.id.btn_showimglist){
            Log.i("btn_showimglist", "1: ");
            Intent intent=new Intent();
            intent.setClass(this,ShowPICList.class);
            startActivity(intent);
        }

    }

//    public void getInformation() {
//            for(int i=0;i<10;i++){
//                msg=new HashMap<>();
//                msg.put("Url",URL);
//                msg.put("Number",i);
//                alllist.add(msg);
//            }
//        MyAdapter myAdapter=new MyAdapter(this);
//        list_image.setAdapter(myAdapter);
//    }




    /*
     * String*********对应我们的URL类型
     * Integer********进度条的进度值
     * BitMap*********异步任务完成后返回的类型
     * */
    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        //执行异步任务（doInBackground）之前执行，并且在ui线程中执行
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if(image!=null){
                image.setVisibility(View.GONE);
            }
//            开始下载 对话框进度条显示
            progress.show();
            progress.setProgress(0);
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            //params是一个可变长的数组 在这里我们只传进来了一个url
            String url=params[0];
            Bitmap bitmap=null;
            URLConnection connection;
            InputStream is;//用于获取数据的输入流
            ByteArrayOutputStream bos;//可以捕获内存缓冲区的数据，转换成字节数组。
            int len;
            float count=0,total;//count为图片已经下载的大小 total为总大小
            try {
                //获取网络连接对象
                connection=(URLConnection) new java.net.URL(url).openConnection();
                //获取当前页面的总长度
                total=(int)connection.getContentLength();
                //获取输入流
                is=connection.getInputStream();
                bos=new ByteArrayOutputStream();
                byte []data=new byte[1024];
                while((len=is.read(data))!=-1){
                    count+=len;
                    bos.write(data,0,len);
                    //调用publishProgress公布进度,最后onProgressUpdate方法将被执行
                    publishProgress((int)(count/total*100));
                    //为了显示出进度 人为休眠0.5秒
                    Log.i("count", count+"/"+total);
//                    Thread.sleep(500);
                }
                bitmap= BitmapFactory.decodeByteArray(bos.toByteArray(), 0, bos.toByteArray().length);
                is.close();
                bos.close();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return bitmap;
        }
        //在ui线程中执行 可以操作ui
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // TODO Auto-generated method stub
            super.onPostExecute(bitmap);
            //下载完成 对话框进度条隐藏
            progress.cancel();
            image.setImageBitmap(bitmap);
            image.setVisibility(View.VISIBLE);
        }
        /*
         * 在doInBackground方法中已经调用publishProgress方法 更新任务的执行进度后
         * 调用这个方法 实现进度条的更新
         * */
        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            progress.setProgress(values[0]);
        }

}
}
