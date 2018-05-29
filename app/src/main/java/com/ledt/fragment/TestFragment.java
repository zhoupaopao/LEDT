package com.ledt.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ledt.R;

/**
 * Created by Lenovo on 2018/5/29.
 */

public class TestFragment extends Fragment {
    private String text;
    TextView mTextView;
    public TestFragment(String text){
        this.text=text;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_first,container,false);
        mTextView = (TextView)view.findViewById(R.id.txt_content);
        mTextView.setText(text);
        return view;
    }
}
