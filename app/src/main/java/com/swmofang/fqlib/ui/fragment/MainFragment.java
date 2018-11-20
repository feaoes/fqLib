package com.swmofang.fqlib.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.swmofang.fqlib.MainActivity;
import com.swmofang.fqlib.R;
import com.swmofang.fqlib.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author :feiqing
 * Created on 2018/11/20
 * Motify on 2018/11/20
 * Version : 1.0
 * Description :
 */

public class MainFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static MainFragment newInstance() {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;

    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        ArrayList<String> data = new ArrayList<>();
        generateData(data);
        MyListAdapter adapter = new MyListAdapter(data);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object o = adapter.getData().get(position);
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        start(MyViewFragment.newInstance());
                        break;
                }
            }
        });
    }

    private void generateData(ArrayList<String> data) {
        data.add("material Dialog");
        data.add("CustomPopwindow");
        data.add("工具类utilCode");
        data.add("自定义View");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MyListAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

        public MyListAdapter(List<String> data) {
            super(android.R.layout.simple_list_item_1,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            TextView convertView = (TextView) helper.getConvertView();
            convertView.setText(item);
        }
    }
}
