package com.swmofang.fqlib.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swmofang.fqlib.R;
import com.swmofang.fqlib.ui.base.BaseFragment;

/**
 * Author :feiqing
 * Created on 2018/11/20
 * Motify on 2018/11/20
 * Version : 1.0
 * Description :
 */

public class RootFragment extends BaseFragment {

    public static RootFragment newInstance(){
        RootFragment rootFragment = new RootFragment();
        return rootFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_root, container, false);
        if(findFragment(MainFragment.class)==null){
            start(MainFragment.newInstance());
//            loadRootFragment(R.id.fl_fragment_root,SplashFragment.newInstance());
        }
        return inflate;
    }
}
