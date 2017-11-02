package com.example.teacherma.apspeedtest.future.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.teacherma.apspeedtest.R;
import com.example.teacherma.apspeedtest.framework.BaseActivity;
import com.example.teacherma.apspeedtest.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> {
    @BindView(R.id.main_view)
    MainView mMainView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(
                Injection.provideMainRepository(),
                mMainView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_act;
    }
}
