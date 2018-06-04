package com.ledt.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.dou361.dialogui.bean.TieBean;
import com.dou361.dialogui.listener.DialogUIItemListener;
import com.ledt.R;
import com.ledt.adapter.ImagePickerAdapter;
import com.ledt.loader.GlideImageLoader1;
import com.ledt.utils.BitmapUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.dou361.dialogui.DialogUIUtils.showToast;

/**
 * Created by Lenovo on 2018/5/21.
 */

public class ChooseUpPicActivity extends Activity implements ImagePickerAdapter.OnRecyclerViewItemClickListener{

    //    ImageView iv_picc;
    Button choose_pic;
    ImageView last_pic;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    Object backst;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 8;               //允许选择图片最大数
//    private String url =  "http://ring.thinghigh.cn/index.php//api/v1/upImg";
private String url = "http://project.thinghigh.cn/index.php/api/v1/uploadTxt";
     private BuildBean dialog;
//    RecyclerView
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pic);
        initWidget();
        initView();
        initData();
        initListener();
    }

    private void initView() {
        choose_pic=findViewById(R.id.choose_pic);
        last_pic=findViewById(R.id.last_pic);
//        iv_picc=findViewById(R.id.iv_picc);
    }

    private void initData() {

    }

    private void initListener() {
        choose_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //上传图片
                //不正确
//                uploadImage(selImageList);
//                DialogUIUtils.dismiss();
                dialog=DialogUIUtils.showLoading(ChooseUpPicActivity.this, "加载中...", false, true, true, true);
                dialog.show();

                uploadMultiFile(selImageList.get(0).path);
            }
        });
//        iv_picc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //图片的点击事件
//
//            }
//        });
    }
    private void uploadMultiFile(String imgUrl) {
        String imageType = "multipart/form-data";
        File file = new File(imgUrl);//imgUrl为图片位置
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        Bitmap bmp = BitmapFactory.decodeFile(selImageList.get(0).path);
        String imgba=Bitmap2StrByBase64(bmp);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image[]", "123.jpg", fileBody)
                //下面这个是传送base64文件的
//                .addFormDataPart("image[]", "data:image/jpeg;base64,"+imgba)
//                .addFormDataPart("imagetype", imageType)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", "onFailure: ");
                Toast.makeText(ChooseUpPicActivity.this,"上传失败",Toast.LENGTH_SHORT).show();
                DialogUIUtils.dismiss();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
//                Log.i("result", "http://ring.thinghigh.cn"+htmlStr);
                com.alibaba.fastjson.JSONObject jsonObject= (com.alibaba.fastjson.JSONObject) JSON.parse(htmlStr);
                com.alibaba.fastjson.JSONObject datamsg=jsonObject.getJSONObject("data");
                String img_name=datamsg.getString("path");
//                String img_name=jsonObject.getString("data");
//                Toast.makeText(ChooseUpPicActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                final String IMAGE_URL=img_name;
                Log.i("result", IMAGE_URL);
                new Thread(new Runnable(){
                    Drawable drawable = loadImageFromNetwork(IMAGE_URL);
                    @Override
                    public void run() {

                        // post() 特别关键，就是到UI主线程去更新图片
                        last_pic.post(new Runnable(){
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                last_pic.setImageDrawable(drawable) ;
                                dialog.dialog.dismiss();
                            }}) ;
                    }

                }).start()  ;

            }
        });
    }
    private Drawable loadImageFromNetwork(String imageUrl)
    {
        Drawable drawable = null;
        String []imglist=imageUrl.split("/");
        String imgname=imglist[imglist.length-1];
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), imgname);
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }

        return drawable ;
    }
    private void uploadImage(ArrayList<ImageItem> selImageList) {
        Bitmap bmp = BitmapFactory.decodeFile(selImageList.get(0).path);
        MyTask myTask = new MyTask();

        Map<String,Object> params = new HashMap<String, Object>();
        String imgba=Bitmap2StrByBase64(bmp);
        params.put("base64","data:image/jpeg;base64,"+imgba);
        myTask.execute(params.toString());
//        new MyThread().start();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
//                Toast.makeText(MainActivity.this,backst.toString(),Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);

        }
    };
    public class MyThread extends Thread {
        @Override
        public void run() {
            String newPath = BitmapUtils.compressImageUpload(selImageList.get(0).path);
            Bitmap bmp = BitmapFactory.decodeFile(selImageList.get(0).path);
            String imgba=Bitmap2StrByBase64(bmp);
            backst=setImgByStr(imgba,"IMG_20180510_104820.jpg");
            handler.sendEmptyMessage(0);
        }
    }

    public static Object setImgByStr(String imgStr,String imgName){
        String url =  "http://project.thinghigh.cn/index.php/api/v1/uploadTxt";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("image[]",imgStr);
        return getValues(params, url);
    }
    public static Object getValues(Map<String, Object> params, String url) {
        String token = "";
        HttpResponse response = post(params, url);
        if (response != null) {
            try {
                token = EntityUtils.toString(response.getEntity());
                response.removeHeaders("operator");
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return token;
    }
    public class MyTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            String responseXml = "";//返回的值
            try {

                String requestXml = params[0];//从入参中获得请求参数
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);
                ByteArrayEntity byteArrayEntity = new ByteArrayEntity(requestXml.getBytes("UTF-8"));
                post.setEntity(byteArrayEntity);
                post.setHeader("Accept-Charset", "UTF-8");

                //获取response响应
                HttpResponse httpResponse = client.execute(post);
                HttpEntity httpEntity = httpResponse.getEntity();
                byte[] responseContent = EntityUtils.toByteArray(httpEntity);

                responseXml = new String(responseContent,"UTF-8");
                Log.i("doInBackground: ", responseXml);
            } catch (Exception e) {
                e.printStackTrace();

                responseXml = "出现了错误！";
            }
            return responseXml;
        }
    }
        public static HttpResponse post(Map<String, Object> params, String url) {

        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("charset", HTTP.UTF_8);
        httpPost.setHeader("Content-Type",
                "application/x-www-form-urlencoded; charset=utf-8");
        HttpResponse response = null;
        if (params != null && params.size() > 0) {
            List<NameValuePair> nameValuepairs = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                nameValuepairs.add(new BasicNameValuePair(key, (String) params
                        .get(key)));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuepairs,
                        HTTP.UTF_8));
                response = client.execute(httpPost);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response = client.execute(httpPost);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response;

    }
    public String Bitmap2StrByBase64(Bitmap bit){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes=bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
    public String bitmaptoString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 30, baos);
        byte[] bytes = baos.toByteArray();

        //base64 encode
        byte[] encode = Base64.encode(bytes,Base64.DEFAULT);
        String encodeString = new String(encode);
        // 将Bitmap转换成字符串

//        String string = null;
//        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 40, bStream);
//        byte[] bytes = bStream.toByteArray();
//        string = Base64.encodeToString(bytes, Base64.DEFAULT);

        return encodeString;
    }
    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("onItemClick: ",position+"21" );
        //要进行application注册
        switch (position) {

            case IMAGE_ITEM_ADD:
                List<TieBean> strings = new ArrayList<TieBean>();
                strings.add(new TieBean("拍照"));
                strings.add(new TieBean("相册"));
                DialogUIUtils.showSheet(ChooseUpPicActivity.this, strings, "取消", Gravity.BOTTOM, true, true, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
//                        showToast(text + "---" + position);
                        switch (position) {
                            case 0: // 直接调起相机
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(ChooseUpPicActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS,true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(ChooseUpPicActivity.this, ImageGridActivity.class);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onBottomBtnClick() {
                        showToast("取消");
                    }
                }).show();
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS,true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }
    //要么在application中注册，要么就在这里进行注册，不然会报空指针
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader( new GlideImageLoader1());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(8);              //选中数量限制
        imagePicker.setMultiMode(false);                      //多选
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null){
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null){
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }
}
