package com.ledt;


/**
 * 下拉刷新
 * 查看activity和fragment的生命周期
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.ledt.Activity.CarouselActivity;
import com.ledt.Activity.ChooseUpPicActivity;
import com.ledt.Activity.CoverActivity;
import com.ledt.Activity.DataTimeActivity;
import com.ledt.Activity.DialogUiActivity;
import com.ledt.Activity.FilePutActivity;
import com.ledt.Activity.FragmentPageActivity;
import com.ledt.Activity.HttpActivity;
import com.ledt.Activity.JpushActivity;
import com.ledt.Activity.LinearRecyclerViewActivity;
import com.ledt.Activity.MapOverLayoutActivity;
import com.ledt.Activity.NavigationListActivity;
import com.ledt.Activity.OkHttpActivity;
import com.ledt.Activity.RadioGroupActivity;
import com.ledt.Activity.RecyclerViewActivity;
import com.ledt.Activity.ShowAndHideActivity;
import com.ledt.Activity.ShowPICActivity;
import com.ledt.Activity.ViewPagerActivity;
import com.ledt.Activity.WebViewActivity;
import com.ledt.Activity.WeilanActivity;
import com.ledt.Activity.ZDActivity;
import com.ledt.List.ListActivity1;
import com.ledt.List.ListActivity2;
import com.ledt.List.ListActivity3;
import com.ledt.List.ListActivity4;
import com.ledt.List.MyListview;
import com.ledt.Login.LoginActivity;
import com.ledt.viewpager.VPager;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.Set;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.finalteam.okhttpfinal.RequestParams;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import okhttp3.Headers;

import static com.dou361.dialogui.DialogUIUtils.showToast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private TextView hello;
    private ActionBarDrawerToggle toggle;
    private ImageView toolBarIcon;
    private DrawerLayout mDrawerLayout;
    SharedPreferences sp=null;
    private BuildBean dialog;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mContext = getApplication();
        DialogUIUtils.init(mContext);
        sp=getSharedPreferences("Userinfo", MODE_PRIVATE);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        hello= (TextView) findViewById(R.id.hello);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("My Title");
//// Sub Title
//        toolbar.setSubtitle("Sub title");
//        toolbar.setNavigationIcon(R.drawable.ab_edit);
//
////这个是左侧栏的实现
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        toolbar.setTitle("My Title");
// Sub Title
        toolbar.setSubtitle("Sub title");
        toolbar.setNavigationIcon(R.drawable.ab_edit);
        setSupportActionBar(toolbar);
        //把开关和DrawerLayout关联
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
//        new MyThread1().start();
        Login();

    }

    @Override
    public String getHttpTaskKey() {
        return HTTP_TASK_KEY;
    }

    public class MyThread1 extends Thread {
        @Override
        public void run() {
            super.run();
            Login();
        }
    }

    private void Login() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        com.loopj.android.http.RequestParams params = new com.loopj.android.http.RequestParams();
//        params.put("UserID", sp.getString("parentId", ""));
//        params.put("TokenString", sp.getString("token", ""));
//        params.setContentEncoding("UTF-8");
//        client.post("http://m1api.chetxt.com:8083/Customer.asmx/GetRecursiveUserByUserID", params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                try {
//                    String json = new String(bytes, "UTF-8").toString().trim();
//                    JSONTokener jt = new JSONTokener(json);
//                    org.json.JSONObject object1 = (org.json.JSONObject) jt.nextValue();
//
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//
//            }
//
//            @Override
//            public void onStart() {
//                super.onStart();
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//            }
//        });
        Log.i("Login: ", "Login: ");
        RequestParams params;
         params = new RequestParams(MainActivity.this);
        params.addFormDataPart("username","test");
        params.addFormDataPart("password","654654");
        HttpRequest.post(Api.Login,params,new JsonHttpRequestCallback(){
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.i("onSuccess: ",jsonObject.toString() );
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("token",jsonObject.getString("Token"));
                editor.putString("userid",jsonObject.getString("UserID"));
                editor.commit();
                DialogUIUtils.showToast("123");
                DialogUIUtils.showToast("默认的Toast弹出方式");
                showToast("登录成功");

            }

            @Override
            public void onStart() {
                super.onStart();
                dialog= DialogUIUtils.showLoading(MainActivity.this, "加载中...", false, false, true, true);
                dialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dialog.dismiss();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                showToast("登录失败");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        switch (item.getItemId()){
//            case android.R.id.home:
//                toggle.onOptionsItemSelected(item);
//        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"设置",Toast.LENGTH_LONG).show();
            Intent intent=new Intent();
            intent.setClass(this, VPager.class);
            startActivity(intent);
            return true;
        }else if(id ==android.R.id.home){
            toggle.onOptionsItemSelected(item);
        } else if(id == R.id.action_edit){
            Toast.makeText(this,"编辑",Toast.LENGTH_LONG).show();
            Intent intent=new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_share){
            Toast.makeText(this,"分享",Toast.LENGTH_LONG).show();
            JPushInterface.setAlias(this, "aaa",
                    new TagAliasCallback() {

                        @Override
                        public void gotResult(int responseCode,
                                              String alias, Set<String> tags) {
                            Log.e("responseCode", responseCode + "time");
                            if (responseCode == 0) {
                                Log.e("jieguo", "成功");

                            } else if (responseCode == 6002) {
//                            mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000* 60);
                            }
                        }
                    });
//            Inten?

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void cl(View v){
        Log.i("CoverActivity", v.getId()+"");
        if( v.getId()==R.id.cover){
//            Log.i("CoverActivity", "1");
            Intent intent=new Intent();
            Log.i("CoverActivity", "1");
            intent.setClass(MainActivity.this, CoverActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.abc){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this, DataTimeActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.showwebView){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.popup){
            Log.i("CoverActivity", "3");
            Intent intent =new Intent();
            intent.setClass(this, MyListview.class);
            startActivity(intent);
//            Intent intent=new Intent();
//            intent.setClass(MainActivity.this, popupActivity.class);
//            startActivity(intent);
        }else if(v.getId()==R.id.showhide){
                        Intent intent=new Intent();
            intent.setClass(MainActivity.this, ShowAndHideActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.showpic){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this, ShowPICActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.weilan){
            //围栏
            Intent intent=new Intent();
            intent.setClass(this, WeilanActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.Carousel){
            //围栏
            Intent intent=new Intent();
            intent.setClass(this, CarouselActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.recycler){
            Intent intent=new Intent();
            intent.setClass(this,RecyclerViewActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.radio_group){
            Intent intent=new Intent();
            intent.setClass(this,RadioGroupActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.dialogui){
            Intent intent=new Intent();
            intent.setClass(this,DialogUiActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.tuisong){
            Intent intent=new Intent();
            intent.setClass(this,JpushActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.pic_chose_upload){
            Intent intent=new Intent();
            intent.setClass(this,ChooseUpPicActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.Navigation_list){
            Intent intent=new Intent();
            intent.setClass(this,NavigationListActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.Fold_list){
            Intent intent=new Intent();
            intent.setClass(this,ZDActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.request_interface){
            Intent intent=new Intent();
            intent.setClass(this,HttpActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.viewpager_fragment){
            Intent intent=new Intent();
            intent.setClass(this,ViewPagerActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.match_page){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.gyf.immersionbar", "com.gyf.immersionbar.activity.MainActivity");
            intent.setComponent(cn);
            startActivity(intent);
        }else if(v.getId()==R.id.activity_fragment){
            Intent intent=new Intent();
            intent.setClass(this,FragmentPageActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.map_overlay){
            Intent intent=new Intent();
            intent.setClass(this,MapOverLayoutActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.file_put){
            Intent intent=new Intent();
            intent.setClass(this,FilePutActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.SQL_test){
            //数据库的使用
        }else if(v.getId()==R.id.refresh){
            //上拉刷新下拉加载
        }else if(v.getId()==R.id.location){

        }else if(v.getId()==R.id.sever){
            //启动服务
        }else if(v.getId()==R.id.list_lan){
            //列表图片懒加载
        }else if(v.getId()==R.id.shyc){
            //列表上滑隐藏
        }

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            hello.setText("nav_camera");
            Intent intent =new Intent();
            intent.setClass(this, ListActivity1.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            hello.setText("nav_gallery");
            Intent intent =new Intent();
            intent.setClass(this, ListActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            hello.setText("nav_slideshow");
            Intent intent =new Intent();
            intent.setClass(this, ListActivity3.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            hello.setText("nav_manage");
            Intent intent =new Intent();
            intent.setClass(this, ListActivity4.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            hello.setText("nav_share");
            //测试okhttp
            Intent intent =new Intent();
            intent.setClass(this, OkHttpActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            //尝试使用webview
            Intent intent =new Intent();
            intent.setClass(this, WebViewActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void showToast(CharSequence msg) {
        DialogUIUtils.showToastLong(msg.toString());
    }
}
