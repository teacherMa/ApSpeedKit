package com.example.teacherma.apspeedtest.future.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.example.teacherma.apspeedtest.R;
import com.example.teacherma.apspeedtest.framework.BaseActivity;
import com.example.teacherma.apspeedtest.utils.BaseUtil;
import com.example.teacherma.apspeedtest.utils.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> {
    private static final int STORAGE_REQUEST_CODE = 82;
    @BindView(R.id.main_view)
    MainView mMainView;

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission_group.STORAGE}, STORAGE_REQUEST_CODE);
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (STORAGE_REQUEST_CODE != requestCode) {
            return;
        }
        if (grantResults.length != permissions.length) {
            BaseUtil.showToast(BaseUtil.getString(R.string.permission_error));
        }
    }
}
