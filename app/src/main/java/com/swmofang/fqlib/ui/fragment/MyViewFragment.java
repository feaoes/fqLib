package com.swmofang.fqlib.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swmofang.fqlib.R;
import com.swmofang.fqlib.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyViewFragment extends BaseFragment {

    @BindView(R.id.recyclerView_tablayout)
    android.support.design.widget.TabLayout tablayout;
    @BindView(R.id.vp_recyclerView_viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    public MyViewFragment() {
    }

    public static MyViewFragment newInstance() {
        MyViewFragment fragment = new MyViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_view, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        List<BaseFragment> fragList = new ArrayList<>();
        StateButtonFragment stateButtonFragment = StateButtonFragment.newInstance();
        ArrowDividerLayoutFragment arrowDividerLayoutFragment = ArrowDividerLayoutFragment.newInstance();
        ImageMenuFragment imageMenuFragment = ImageMenuFragment.newInstance();
        fragList.add(stateButtonFragment);
        fragList.add(arrowDividerLayoutFragment);
        fragList.add(imageMenuFragment);

        viewPager.setAdapter(new MViewPagerAdapter(getFragmentManager(),fragList));
        tablayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tabAt = tablayout.getTabAt(i);
            tabAt.setText(String.format(Locale.CHINA,"第%d页",i+1));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class MViewPagerAdapter extends FragmentPagerAdapter {


        private final List<BaseFragment> list;

        public MViewPagerAdapter(android.support.v4.app.FragmentManager supportFragmentManager, List<BaseFragment> fragList) {
            super(supportFragmentManager);
            this.list = fragList;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list ==null?0: list.size();
        }
    }
}
