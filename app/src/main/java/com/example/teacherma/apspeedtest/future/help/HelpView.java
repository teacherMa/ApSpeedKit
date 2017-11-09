package com.example.teacherma.apspeedtest.future.help;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.teacherma.apspeedtest.R;
import com.example.teacherma.apspeedtest.framework.BaseView;

public class HelpView extends BaseView<HelpContract.Presenter> implements HelpContract.View {


    public HelpView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.help_view;
    }
}
