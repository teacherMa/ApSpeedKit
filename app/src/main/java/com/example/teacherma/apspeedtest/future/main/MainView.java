package com.example.teacherma.apspeedtest.future.main;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;

import com.example.teacherma.apspeedtest.R;
import com.example.teacherma.apspeedtest.api.NewTestCreatedCallback;
import com.example.teacherma.apspeedtest.custom.view.NewTestPopupWindow;
import com.example.teacherma.apspeedtest.framework.BaseView;
import com.example.teacherma.apspeedtest.model.bean.TestResult;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainView extends BaseView<MainContract.Presenter> implements MainContract.View,
        NewTestCreatedCallback {

    @BindView(R.id.history_result)
    RecyclerView mHistoryResult;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.new_test)
    FloatingActionButton mNewTest;

    private MainAdapter mAdapter;

    private String mIp;
    private String mPort;

    public MainView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView(Context context, AttributeSet attrs, int defStyleAttr) {

        mAdapter = new MainAdapter();
        mHistoryResult.setItemAnimator(new DefaultItemAnimator());
        mHistoryResult.setLayoutManager(new LinearLayoutManager(getContext()));
        mHistoryResult.setAdapter(mAdapter);
//        mHistoryResult.addItemDecoration(new TestHistoryItemDecoration());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle((Activity) getContext(), mDrawerLayout,
                mToolBar, R.string.nav_drawer_open, R.string.nav_drawer_closed);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.main_view;
    }

    @Override
    public void loadingHistory(List<TestResult> historyResults) {
        mAdapter.refreshData(historyResults);
    }

    @Override
    public void onRequestNewTestResult(int resultCode) {
        getPresenter().testSpeed(mIp, mPort);
    }

    @Override
    public void testResult(TestResult result) {

    }

    @OnClick(R.id.new_test)
    public void onViewClicked() {
        new NewTestPopupWindow(getContext())
                .setTestCreatedCallback(this)
                .showAtLocation(this, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onNewTestCreated(String ip, String port) {
        mIp = ip;
        mPort = port;
        getPresenter().requestBuildNewTest();
    }
}
