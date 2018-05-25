package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.ledt.R;

/**
 * Created by 13126 on 2017/9/26.
 */

public class CoverActivity extends Activity {
    private Button add;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("CoverActivity", "2");
        super.onCreate(savedInstanceState);
        Log.i("CoverActivity", "3");
        setContentView(R.layout.marker_window);
        add= (Button) findViewById(R.id.add);
        ImageView iv_bs= (ImageView) findViewById(R.id.iv_BS);
            iv_bs.setImageResource(R.drawable.nomall45);

    }
}
