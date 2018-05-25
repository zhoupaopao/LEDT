package com.ledt.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ledt.R;

/**
 * Created by 13126 on 2017/8/31.
 */

public class Activity1 extends Activity {
    TextView phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        phone= (TextView) findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 调用系统拨号界面拨打电话
                String number2 = phone.getText().toString().trim(); // 获取电话接听者号码
                if (!number2.equals("")) {
                    Uri callUri = Uri.parse("tel:" + number2);
                    Intent intent = new Intent(Intent.ACTION_DIAL, callUri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(Activity1.this,
                            "接听者号码不能为空,请填写!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
