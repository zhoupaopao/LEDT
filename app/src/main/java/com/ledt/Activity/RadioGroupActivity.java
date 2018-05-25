package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ledt.R;

/**
 * Created by Lenovo on 2018/5/17.
 */

public class RadioGroupActivity extends Activity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiogroup);
        initView();
        initListener();
    }

    private void initListener() {
        rg.setOnCheckedChangeListener(this);
    }

    private void initView() {
        rg=findViewById(R.id.rg);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb1:
                Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb2:
                Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rb3:
                Toast.makeText(this,"3",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
