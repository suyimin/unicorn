package com.sunny.demo.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xdroid.library.activity.BaseActivity;
import com.sunny.demo.R;
import com.sunny.demo.adapter.ItemAdapter;
import com.sunny.demo.bean.AsRole;
import com.sunny.demo.bean.PermList;
import com.sunny.demo.bean.Platform;
import com.sunny.demo.bean.PlatformListData;
import com.sunny.demo.data.MapPerm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 根据getMapPerm.txt里面的配置信息生成表格
 */
public class ListViewActivity extends BaseActivity {

    @BindView(R.id.ll_middle2)
    LinearLayout llMiddle2;
    @BindView(R.id.sv_middle)
    ScrollView svMiddle;

    /**
     * 当前系统缺省角色和权限配置列表
     */
    private List<PlatformListData> mData;
    /**
     * 当前所有角色列表
     */
    private List<AsRole> mRole;
    //
    private List<ItemAdapter> mAdapter;
    //
    private List<List<Platform>> mPlatforms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);
        logic();
    }

    private void logic() {
        //初始化角色和权限布局
        mData = new ArrayList<>();
        getData();
        if (null == mData) return;
        mRole = new ArrayList<>();
        mAdapter = new ArrayList<>();
        mPlatforms = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.middle_item, null);
            TextView tv_names = (TextView) v.findViewById(R.id.tv_names);
            ImageView iv_check = (ImageView) v.findViewById(R.id.iv_check);
            ListView lv_show = (ListView) v.findViewById(R.id.lv_show);
            List<Platform> listPopedom = mData.get(i).getPlatformList();  //权限列表
            mPlatforms.add(listPopedom);

            AsRole ar = new AsRole();
            ar.setRoleId(mData.get(i).getRole_id());
            ar.setRoleName(mData.get(i).getRole_name());
            ar.setChecked(false);
            mRole.add(ar);

            ItemAdapter adapter = new ItemAdapter(this, mPlatforms.get(i));
            mAdapter.add(adapter);
            lv_show.setAdapter(mAdapter.get(i));
            setListViewHeightBasedOnChildren(lv_show);

            tv_names.setText(mData.get(i).getRole_name());

            iv_check.setBackgroundResource(R.mipmap.no_choice);
            iv_check.setTag(i);
            iv_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    if (mRole.get(pos).isChecked()) {
                        mRole.get(pos).setChecked(false);
                        v.setBackgroundResource(R.mipmap.no_choice);
                        UpdateAllPopedom(pos, false);
                    } else {
                        mRole.get(pos).setChecked(true);
                        v.setBackgroundResource(R.mipmap.choice);
                        UpdateAllPopedom(pos, true);
                    }

                }
            });
            llMiddle2.addView(v);
        }
    }

    /**
     * 计算ListView的高度
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {

        if (null == listView) return;

        //获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) return;

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   //listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);  //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight();  //统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        //listView.getDividerHeight()获取子项间分隔符占用的高度
        //params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * 更新权限配置列表的所有状态为同一状态（全选或全不选）
     */
    private void UpdateAllPopedom(int pos, boolean flag) {

        List<Platform> data = mPlatforms.get(pos);
        for (int i = 0; i < data.size(); i++) {
            List<PermList> pl = data.get(i).getPermList();
            if (null == pl || pl.size() == 0) continue;
            for (int j = 0; j < pl.size(); j++) {
                pl.get(j).setStatus(flag);
            }
        }
        mAdapter.get(pos).Refresh();
    }

    /**
     * 获取数据
     */
    private void getData() {
        mData = MapPerm.getMapPerm(this);
    }
}
