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

public class StateButtonFragment extends BaseFragment{


    public static StateButtonFragment newInstance(){
        StateButtonFragment stateButtonFragment = new StateButtonFragment();
        return stateButtonFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_state_button,container,false);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}
