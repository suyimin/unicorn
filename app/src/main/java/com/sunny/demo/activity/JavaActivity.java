package com.sunny.demo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.sunny.demo.R;
import com.xdroid.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        String sss = "1 2010,2 2011,3 2012";
        String[] arr = sss.split(" |,");//根据“ ”和“,”区分
        LogUtil.e(java.util.Arrays.toString(arr));//遍历输出数组

        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        String[] toBeStored = list.toArray(new String[list.size()]);
        for (String s : toBeStored) {
            LogUtil.e(s);
        }

        String[] arr2 = new String[]{"1", "2"};
        List list2 = Arrays.asList(arr2);
        LogUtil.d(list2.toString());

    }
}
