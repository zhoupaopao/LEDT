package com.ledt.Activity;


import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jimmy.common.base.app.BaseFragment;
import com.ledt.R;
import com.ledt.fragment.RealScheduleFragment;
import com.ledt.fragment.ScheduleFragment;

import java.util.Calendar;

/**
 * Created by Lenovo on 2018/6/15.
 */

public class RealCalendarActivity extends BaseActivity {
    private TextView tvTitleMonth, tvTitleDay, tvTitle;
    private LinearLayout llTitleDate;
    private String[] mMonthText;
    private BaseFragment mScheduleFragment, mEventSetFragment;
    @Override
    protected void bindView() {
        setContentView(R.layout.activity_real_calendar);
        llTitleDate = searchViewById(R.id.llTitleDate);
        tvTitleMonth = searchViewById(R.id.tvTitleMonth);
        tvTitleDay = searchViewById(R.id.tvTitleDay);
        tvTitle = searchViewById(R.id.tvTitle);
        initUi();
        gotoScheduleFragment();
    }

    private void initUi() {
        mMonthText = getResources().getStringArray(R.array.calendar_month);
        llTitleDate.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);
        tvTitleMonth.setText(mMonthText[Calendar.getInstance().get(Calendar.MONTH)]);
        tvTitleDay.setText(getString(R.string.calendar_today));
//        if (Build.VERSION.SDK_INT < 19) {
//            TextView tvMenuTitle = searchViewById(R.id.tvMenuTitle);
//            tvMenuTitle.setGravity(Gravity.CENTER_VERTICAL);
//        }

    }
    private void gotoScheduleFragment() {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        if(mScheduleFragment==null){
            //日历控件
            mScheduleFragment= RealScheduleFragment.getInstance();
            ft.add(R.id.flMainContainer,mScheduleFragment);
        }
        if(mEventSetFragment!=null){
            ft.hide(mEventSetFragment);
        }
        ft.show(mScheduleFragment);
        ft.commit();
        llTitleDate.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);


    }
}
