package com.ledt.Main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ledt.R;
import com.ledt.fragment.FirstFragment;
import com.ledt.fragment.SecondFragment;
import com.ledt.fragment.ThirdFragment;

/**
 * Created by 13126 on 2017/8/14.
 */

public class Main extends Activity {
    private ImageButton bt1;
    private ImageButton bt2;
    private ImageButton bt3;
    private FrameLayout ly_content;
    private Fragment f1,f2,f3;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        setListener();
    }
    //影藏所有fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }

    }
    private void setListener() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                f1 = new FirstFragment("第1个Fragment");
//                transaction.add(R.id.fragment_container,f1);
//                transaction.show(f1);
                switchabc(1);

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchabc(2);

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchabc(3);


            }
        });



    }

    private void switchabc(int i) {
        transaction = getFragmentManager().beginTransaction();
        switch(i){
            case 1:
                hideAllFragment(transaction);
                if(f1==null){
                    f1 = new FirstFragment("第一个Fragment");
                    transaction.add(R.id.fragment_container,f1);
                }else{
                    transaction.show(f1);
                }
                break;

            case 2:
                hideAllFragment(transaction);
                if(f2==null){
                    f2 = new SecondFragment("第二个Fragment");
                    transaction.add(R.id.fragment_container,f2);
                }else{
                    transaction.show(f2);
                }
                break;

            case 3:
                hideAllFragment(transaction);
                if(f3==null){
                    f3 = new ThirdFragment("第三个Fragment");
                    transaction.add(R.id.fragment_container,f3);
                }else{
                    transaction.show(f3);
                }
                break;


        }
        transaction.commit();
    }

    private void initView() {
        bt1= (ImageButton) findViewById(R.id.bt1);
        bt2= (ImageButton) findViewById(R.id.bt2);
        bt3= (ImageButton) findViewById(R.id.bt3);
        ly_content = (FrameLayout) findViewById(R.id.fragment_container);





    }

}
