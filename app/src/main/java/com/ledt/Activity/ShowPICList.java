package com.ledt.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.ledt.R;

/**
 * Created by 29423 on 2017/11/9 0009.
 */

public class ShowPICList extends Activity {
    ListView list;
    ListViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_showpic);
        list=(ListView)findViewById(R.id.list);
        adapter=new ListViewAdapter(this, mStrings);
        list.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        list.setAdapter(null);
        super.onDestroy();
        adapter.destroy();
    }

    private String[] mStrings={
            "http://news.21-sun.com/UserFiles/x_Image/x_20150606083511_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150606082847_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605230546_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605180710_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605174514_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605175520_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605175520_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605173250_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605164601_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605161332_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605150505_0.jpg",
            "http://news.21-sun.com/UserFiles/x_Image/x_20150605144634_0.jpg"
    };
}
