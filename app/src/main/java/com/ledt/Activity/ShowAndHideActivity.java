package com.ledt.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ledt.List.ExpandableListView1;
import com.ledt.R;

/**
 * Created by 13126 on 2017/9/28.
 */

public class ShowAndHideActivity extends Activity {
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    Button show;
    Button hide;
    TranslateAnimation mShowAction;
    TranslateAnimation mHiddenAction;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showandhide);
        initView();
    }

    private void initView() {
        rl1= (RelativeLayout) findViewById(R.id.rl1);
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(500);
        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(500);
    }

    public void change(View v){
        if(v.getId()==R.id.show){
//            rl1.startAnimation(mShowAction);
//            rl2.startAnimation(mShowAction);
            rl1.setVisibility(View.VISIBLE);
//            rl2.setVisibility(View.VISIBLE);
        }else if(v.getId()==R.id.hide){
//            rl1.startAnimation(mHiddenAction);
//            rl2.startAnimation(mHiddenAction);
            rl1.setVisibility(View.GONE);
//            rl2.setVisibility(View.GONE);
        }else if(v.getId()==R.id.kuozhan){
            Intent intent=new Intent();
            intent.setClass(ShowAndHideActivity.this, ExpandableListView1.class);
            startActivity(intent);
        }
    }
}
