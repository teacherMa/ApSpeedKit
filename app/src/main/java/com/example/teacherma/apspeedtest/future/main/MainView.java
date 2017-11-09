package com.example.teacherma.apspeedtest.future.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.teacherma.apspeedtest.R;
import com.example.teacherma.apspeedtest.api.NewTestCreatedCallback;
import com.example.teacherma.apspeedtest.custom.view.NewTestPopupWindow;
import com.example.teacherma.apspeedtest.framework.BaseView;
import com.example.teacherma.apspeedtest.future.help.HelpFragment;
import com.example.teacherma.apspeedtest.future.info.InfoFragment;
import com.example.teacherma.apspeedtest.model.bean.TestResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainView extends BaseView<MainContract.Presenter> implements MainContract.View,
        NewTestCreatedCallback, NavigationView.OnNavigationItemSelectedListener {
    private static final String KEY_HELP_FRAGMENT = "HELP_FRAGMENT";
    private static final String KEY_INFO_FRAGMENT = "INFO_FRAGMENT";

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
    private AlertDialog mDialog;

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

        mNavView.setNavigationItemSelectedListener(this);

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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.wait_bar, null);
        mDialog = new AlertDialog.Builder(getContext())
                .setView(view).create();
        mDialog.show();
    }

    @Override
    public void testResult(TestResult result) {
        List<TestResult> results = new ArrayList<>();
        results.add(result);
        mAdapter.refreshData(results);
        if (null != mDialog) {
            mDialog.dismiss();
        }
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = ((MainActivity) getContext()).getSupportFragmentManager();
        if (null != fragmentManager.findFragmentByTag(KEY_HELP_FRAGMENT)) {
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentByTag(KEY_HELP_FRAGMENT))
                    .commit();
        }
        if (null !=fragmentManager.findFragmentByTag(KEY_INFO_FRAGMENT)) {
            fragmentManager.beginTransaction()
                    .remove(fragmentManager.findFragmentByTag(KEY_INFO_FRAGMENT))
                    .commit();
        }
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_main_page:
                mHistoryResult.setVisibility(VISIBLE);
                break;
            case R.id.nav_menu_help:
                mHistoryResult.setVisibility(INVISIBLE);
                HelpFragment helpFragment = (HelpFragment) fragmentManager.findFragmentByTag(KEY_HELP_FRAGMENT);
                if (null == helpFragment) {
                    helpFragment = new HelpFragment();
                }
                fragmentManager.beginTransaction()
                        .add(R.id.content_layout, helpFragment, KEY_HELP_FRAGMENT).commit();
                break;
            case R.id.nav_menu_about:
                mHistoryResult.setVisibility(INVISIBLE);
                InfoFragment infoFragment = (InfoFragment) fragmentManager.findFragmentByTag(KEY_INFO_FRAGMENT);
                if (null == infoFragment) {
                    infoFragment = new InfoFragment();
                }
                fragmentManager.beginTransaction()
                        .add(R.id.content_layout, infoFragment, KEY_INFO_FRAGMENT).commit();
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
