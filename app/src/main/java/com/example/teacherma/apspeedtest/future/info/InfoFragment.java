package com.example.teacherma.apspeedtest.future.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.teacherma.apspeedtest.R;
import com.example.teacherma.apspeedtest.framework.BaseActivity;
import com.example.teacherma.apspeedtest.framework.BaseFragment;
import com.example.teacherma.apspeedtest.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoFragment extends BaseFragment<InfoPresenter> {
    @BindView(R.id.info_view)
    InfoView mInfoView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
    }

    @NonNull
    @Override
    protected InfoPresenter createPresenter() {
        return new InfoPresenter(
                Injection.provideMainRepository(),
                mInfoView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.info_frag;
    }

}
