package com.swmofang.fqlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.swmofang.fqlib.ui.base.BaseFragmentActivity;
import com.swmofang.fqlib.ui.fragment.MainFragment;
import com.swmofang.fqlib.ui.fragment.RootFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findFragment(RootFragment.class)==null){
            loadRootFragment(R.id.rl_fragment_container,RootFragment.newInstance());
        }
    }
}
