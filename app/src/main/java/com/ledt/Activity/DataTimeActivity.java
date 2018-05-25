package com.ledt.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ledt.R;

import java.util.Calendar;

/**
 * Created by 13126 on 2017/9/26.
 */

public class DataTimeActivity extends Activity {
    int mYear, mMonth, mDay,mHour,mMinutes;
    Button btn;
    Button btn1;
    TextView dateDisplay;
    TextView dateDisplay1;
    final int DATE_DIALOG = 1;
    final int TIME_DIALOG = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datatime);
        btn = (Button) findViewById(R.id.dateChoose);
        btn1 = (Button) findViewById(R.id.timeChoose);
        dateDisplay = (TextView) findViewById(R.id.dateDisplay);
        dateDisplay1 = (TextView) findViewById(R.id.dateDisplay1);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG);
            }
        });

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mHour = ca.get(Calendar.HOUR_OF_DAY);
        mMinutes=ca.get(Calendar.MINUTE);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                DatePickerDialog dpd=new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
                dpd.setButton3("时间", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("时间","时间");
                        showDialog(TIME_DIALOG);
                    }
                });
//                dpd.setButton(DialogInterface.BUTTON_POSITIVE, "确定",new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Log.i("确定","确定");
//                    }
//                });
//                //添加时间框的取消按钮
//                dpd.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //将文本内容置为空
//                        Log.i("取消","取消");
//                    }
//                });
                return dpd;
            case TIME_DIALOG:
//                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
                return new TimePickerDialog(this,mtimeListener,mHour,mMinutes,true);
        }
        return null;
    }

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        dateDisplay.setText(new StringBuffer().append(mMonth + 1).append("-").append(mDay).append("-").append(mYear).append(" "));
    }
    public void display1() {
        dateDisplay1.setText(new StringBuffer().append(mHour).append("-").append(mMinutes).append(" "));
    }
    private TimePickerDialog.OnTimeSetListener mtimeListener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mYear = hourOfDay;
            mMonth = minute;

            display1();
        }
    };

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            display();
        }


    };
}

