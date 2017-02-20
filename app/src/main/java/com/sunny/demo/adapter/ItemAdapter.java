package com.sunny.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sunny.demo.R;
import com.sunny.demo.bean.Platform;

import java.util.List;

public class ItemAdapter extends BaseAdapter {


    private List<Platform> platformList;
    private Context mContent;
    private LayoutInflater inflater;

    public ItemAdapter(Context mContent, List<Platform> platformList) {
        this.mContent = mContent;
        this.platformList = platformList;
        inflater = LayoutInflater.from(mContent);
    }

    public void Refresh() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return null == platformList ? 0 : platformList.size();
    }

    @Override
    public Object getItem(int i) {
        return platformList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int p, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContent).inflate(R.layout.table_layout, null);

        //二级菜单标题
        TextView tvPlatform = (TextView) view.findViewById(R.id.tv_platform);
        tvPlatform.setText(platformList.get(p).getPlatform_name());

        //三级菜单
        TableLayout tab = (TableLayout) view.findViewById(R.id.tab_main);
        tab.setStretchAllColumns(false);

        int size = (null == platformList.get(p).getPermList()) ? 0 : platformList.get(p).getPermList().size();
        int display = 4;
        int total = size / display;
        if (0 != size % display)
            total++;
        for (int i = 0; i < total; i++) {
            TableRow row = new TableRow(mContent);
            for (int x = 0; x < display; x++) {
                if (i * display + x < size) {
                    View v = LayoutInflater.from(mContent).inflate(R.layout.table_item, null);
                    //设置三级菜单标题
                    TextView tvform = (TextView) v.findViewById(R.id.tv_names);
                    tvform.setText(platformList.get(p).getPermList().get(i * display + x).getPerm_name());
                    ImageView iv = (ImageView) v.findViewById(R.id.iv_check);
                    //根据实际状态设置权限配置指示图标
                    if (platformList.get(p).getPermList().get(i * display + x).getStatus())
                        iv.setBackgroundResource(R.mipmap.choice);
                    else
                        iv.setBackgroundResource(R.mipmap.no_choice);
                    v.setMinimumWidth(220);
                    v.setMinimumHeight(50);
                    row.addView(v);
                } else {
                    break;
                }
            }
            tab.addView(row);
        }
        return view;
    }

}
