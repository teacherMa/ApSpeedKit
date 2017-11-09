package com.example.teacherma.apspeedtest.future.help;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.teacherma.apspeedtest.R;
import com.example.teacherma.apspeedtest.framework.BaseActivity;
import com.example.teacherma.apspeedtest.framework.BaseFragment;
import com.example.teacherma.apspeedtest.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpFragment extends BaseFragment<HelpPresenter> {
    @BindView(R.id.help_view)
    HelpView mHelpView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @NonNull
    @Override
    protected HelpPresenter createPresenter() {
        return new HelpPresenter(
                Injection.provideMainRepository(),
                mHelpView
        );
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.help_frag;
    }

}
