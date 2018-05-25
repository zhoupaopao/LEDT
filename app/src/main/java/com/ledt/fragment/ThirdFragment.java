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
 * Created by 13126 on 2017/8/15.
 */

public class ThirdFragment extends Fragment {
    private String context;
    private TextView mTextView;
    public ThirdFragment(String context){
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third,container,false);
        mTextView = (TextView)view.findViewById(R.id.txt_content3);
        //mTextView = (TextView)getActivity().findViewById(R.id.txt_content);
        mTextView.setText(context);
        return view;
    }
}
