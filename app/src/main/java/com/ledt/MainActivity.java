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
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.BuildBean;
import com.ledt.Activity.CalendarActivity;
import com.ledt.Activity.CarouselActivity;
import com.ledt.Activity.ChooseUpPicActivity;
import com.ledt.Activity.CoverActivity;
import com.ledt.Activity.DataTimeActivity;
import com.ledt.Activity.DialogUiActivity;
import com.ledt.Activity.DownloadActivity;
import com.ledt.Activity.FilePutActivity;
import com.ledt.Activity.FragmentPageActivity;
import com.ledt.Activity.HttpActivity;
import com.ledt.Activity.JpushActivity;
import com.ledt.Activity.LeftBackActivity;
import com.ledt.Activity.LeftDrawerActivity;
import com.ledt.Activity.LinearRecyclerViewActivity;
import com.ledt.Activity.LocationNoMap;
import com.ledt.Activity.ManualaddActivity;
import com.ledt.Activity.MapOverLayoutActivity;
import com.ledt.Activity.NavigationListActivity;
import com.ledt.Activity.NewWebviewActivity;
import com.ledt.Activity.OkHttpActivity;
import com.ledt.Activity.PicLoadActivity;
import com.ledt.Activity.RadioGroupActivity;
import com.ledt.Activity.RealCalendarActivity;
import com.ledt.Activity.RecyclerViewActivity;
import com.ledt.Activity.RefreshActivity;
import com.ledt.Activity.SYActivity;
import com.ledt.Activity.ServiceActivity;
import com.ledt.Activity.ShowAndHideActivity;
import com.ledt.Activity.ShowPICActivity;
import com.ledt.Activity.SqlActivity;
import com.ledt.Activity.SynchronizedActivity;
import com.ledt.Activity.UpTouchActivity;
import com.ledt.Activity.ViewPagerActivity;
import com.ledt.Activity.WebServiceActivity;
import com.ledt.Activity.WebViewActivity;
import com.ledt.Activity.WeilanActivity;
import com.ledt.Activity.ZDActivity;
import com.ledt.List.ListActivity1;
import com.ledt.List.ListActivity2;
import com.ledt.List.ListActivity3;
import com.ledt.List.ListActivity4;
import com.ledt.List.MyListview;
import com.ledt.Login.LoginActivity;
import com.ledt.task.ContactInterface;
import com.ledt.task.PostTask;
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
        implements NavigationView.OnNavigationItemSelectedListener, HttpCycleContext {
    protected final String HTTP_TASK_KEY = "HttpTaskKey_" + hashCode();
    private TextView hello;
    private ActionBarDrawerToggle toggle;
    private ImageView toolBarIcon;
    private DrawerLayout mDrawerLayout;
    SharedPreferences sp = null;
    private BuildBean dialog;
    final BuildBean[] dialog1 = new BuildBean[1];
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mContext = getApplication();
        DialogUIUtils.init(mContext);
        sp = getSharedPreferences("Userinfo", MODE_PRIVATE);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        hello = (TextView) findViewById(R.id.hello);
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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                System.out.println(msg.getData().getString("value"));
                Toast.makeText(MainActivity.this, msg.getData().getString("value"), Toast.LENGTH_SHORT).show();
            }
        }
    };


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
        params.addFormDataPart("username", "test");
        params.addFormDataPart("password", "654654");
        HttpRequest.post(Api.Login, params, new JsonHttpRequestCallback() {
            @Override
            protected void onSuccess(Headers headers, JSONObject jsonObject) {
                super.onSuccess(headers, jsonObject);
                Log.i("onSuccess: ", jsonObject.toString());
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token", jsonObject.getString("Token"));
                editor.putString("userid", jsonObject.getString("UserID"));
                editor.commit();
                DialogUIUtils.showToast("123");
                DialogUIUtils.showToast("默认的Toast弹出方式");
                showToast("登录成功");

            }

            @Override
            public void onStart() {
                super.onStart();
                dialog = DialogUIUtils.showLoading(MainActivity.this, "加载中...", false, false, true, true);
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
            Toast.makeText(this, "设置", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(this, VPager.class);
            startActivity(intent);
            return true;
        } else if (id == android.R.id.home) {
            toggle.onOptionsItemSelected(item);
        } else if (id == R.id.action_edit) {
            Toast.makeText(this, "编辑", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_share) {
            Toast.makeText(this, "分享", Toast.LENGTH_LONG).show();
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

    public void cl(View v) throws InterruptedException {
        Log.i("CoverActivity", v.getId() + "");
        if (v.getId() == R.id.cover) {
//            Log.i("CoverActivity", "1");
            Intent intent = new Intent();
            Log.i("CoverActivity", "1");
            intent.setClass(MainActivity.this, CoverActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.abc) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, DataTimeActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.showwebView) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.popup) {
            Log.i("CoverActivity", "3");
            Intent intent = new Intent();
            intent.setClass(this, MyListview.class);
            startActivity(intent);
//            Intent intent=new Intent();
//            intent.setClass(MainActivity.this, popupActivity.class);
//            startActivity(intent);
        } else if (v.getId() == R.id.showhide) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ShowAndHideActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.showpic) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ShowPICActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.weilan) {
            //围栏
            Intent intent = new Intent();
            intent.setClass(this, WeilanActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.Carousel) {
            //围栏
            Intent intent = new Intent();
            intent.setClass(this, CarouselActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.recycler) {
            Intent intent = new Intent();
            intent.setClass(this, RecyclerViewActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.radio_group) {
            Intent intent = new Intent();
            intent.setClass(this, RadioGroupActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.dialogui) {
            Intent intent = new Intent();
            intent.setClass(this, DialogUiActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tuisong) {
            Intent intent = new Intent();
            intent.setClass(this, JpushActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.pic_chose_upload) {
            Intent intent = new Intent();
            intent.setClass(this, ChooseUpPicActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.Navigation_list) {
            Intent intent = new Intent();
            intent.setClass(this, NavigationListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.Fold_list) {
            Intent intent = new Intent();
            intent.setClass(this, ZDActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.request_interface) {
            Intent intent = new Intent();
            intent.setClass(this, HttpActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.viewpager_fragment) {
            Intent intent = new Intent();
            intent.setClass(this, ViewPagerActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.match_page) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.gyf.immersionbar", "com.gyf.immersionbar.activity.MainActivity");
            intent.setComponent(cn);
            startActivity(intent);
        } else if (v.getId() == R.id.activity_fragment) {
            Intent intent = new Intent();
            intent.setClass(this, FragmentPageActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.map_overlay) {
            Intent intent = new Intent();
            intent.setClass(this, MapOverLayoutActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.file_put) {
            Intent intent = new Intent();
            intent.setClass(this, FilePutActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.SQL_test) {
            //数据库的使用
            Intent intent = new Intent();
            intent.setClass(this, SqlActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.refresh) {
            //上拉刷新下拉加载
            Intent intent = new Intent();
            intent.setClass(this, RefreshActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.location) {
            //无地图模块定位
            Intent intent = new Intent();
            intent.setClass(this, LocationNoMap.class);
            startActivity(intent);

        } else if (v.getId() == R.id.sever) {
            //启动服务
            Intent intent = new Intent();
            intent.setClass(this, ServiceActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.list_lan) {
            //列表图片懒加载
            Intent intent = new Intent();
            intent.setClass(this, PicLoadActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.shyc) {
            //列表上滑隐藏
            Intent intent = new Intent();
            intent.setClass(this, UpTouchActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.sylist) {
            //带有索引的列表
            Intent intent = new Intent();
            intent.setClass(this, SYActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.left_back) {
            //侧滑返回
            Intent intent = new Intent();
            intent.setClass(this, LeftBackActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.calendar) {
            //日历控件
            Intent intent = new Intent();
            intent.setClass(this, CalendarActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.showleft) {
            //展示左侧列表
            Intent intent = new Intent();
            intent.setClass(this, LeftDrawerActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.hand_calendar) {
            //手动实现日历
            Intent intent = new Intent();
            intent.setClass(this, RealCalendarActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.webview) {
            //webview使用
            Intent intent = new Intent();
            intent.setClass(this, NewWebviewActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.webservice) {
            //使用soap获取webservice的天气信息
            Intent intent = new Intent();
            intent.setClass(this, WebServiceActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.postall) {
            //统一请求回调
            RequestParams params;
            params = new RequestParams(this);
            params.addFormDataPart("username","test");
            params.addFormDataPart("password","654654");
            new PostTask().PostTask(MainActivity.this,Api.Login,params, new ContactInterface() {
                @Override
                public void callBackByTel(String answer) {
                    Log.i("callBackByTel: ", answer);
                    Toast.makeText(MainActivity.this, "这是统一请求后返回的回调方法" + answer, Toast.LENGTH_SHORT).show();
                }
            });
//             new PostTask().setCallBack("231","ques",new ContactInterface(){
//
//                 @Override
//                 public void callBackByTel(String answer) {
//                     Log.i("callBackByTel: ", answer);
//                 }
//             });
        } else if (v.getId() == R.id.waitthread) {
            //等待线程完成后再执行其他的
            System.out.println("main start");
            Toast.makeText(MainActivity.this, "main start", Toast.LENGTH_SHORT).show();
            Thread t1 = new Thread(new Worker("thread-1"));
//            Thread t2 = new Thread(new Worker("thread-2"));

            t1.start();
            t1.join();
//            t2.start();
//
//
//            t2.join();
            Toast.makeText(MainActivity.this, "main end", Toast.LENGTH_SHORT).show();
            System.out.println("main end");
        } else if (v.getId() == R.id.Synchronized) {
            //Synchronized使用同步方法
            Intent intent = new Intent();
            intent.setClass(this, SynchronizedActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.downloadfile) {
            //下载文件
            Intent intent = new Intent();
            intent.setClass(this, DownloadActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.socket) {
            //使用socket和列表的使用，下拉刷新上拉加载
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName("com.example.sockettest", "com.example.sockettest.MainActivity");
            intent.setComponent(cn);
            startActivity(intent);
        }else if(v.getId()==R.id.bottomdialog){
            BottomSheetDialog dialog=new BottomSheetDialog(MainActivity.this);
            View dialogView = View.inflate(getBaseContext(), R.layout.bottom_layout, null);
            dialog.setContentView(dialogView);
//            dialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(getResources().getColor(android.R.color.transparent));
            dialog.show();
        }else if(v.getId()==R.id.Manualadd){
            Intent intent = new Intent();
            intent.setClass(this, ManualaddActivity.class);
            startActivity(intent);
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            hello.setText("nav_camera");
            Intent intent = new Intent();
            intent.setClass(this, ListActivity1.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            hello.setText("nav_gallery");
            Intent intent = new Intent();
            intent.setClass(this, ListActivity2.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            hello.setText("nav_slideshow");
            Intent intent = new Intent();
            intent.setClass(this, ListActivity3.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            hello.setText("nav_manage");
            Intent intent = new Intent();
            intent.setClass(this, ListActivity4.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            hello.setText("nav_share");
            //测试okhttp
            Intent intent = new Intent();
            intent.setClass(this, OkHttpActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {
            //尝试使用webview
            Intent intent = new Intent();
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

    class Worker implements Runnable {

        private String name;

        public Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("value", name);
                msg.setData(bundle);
                handler.sendMessage(msg);
//                System.out.println(name);
                Log.i("run: ", name);

            }
        }

    }
}
