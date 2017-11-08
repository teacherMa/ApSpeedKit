package com.example.teacherma.apspeedtest.future.main;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teacherma.apspeedtest.R;
import com.example.teacherma.apspeedtest.api.OnItemClickListener;
import com.example.teacherma.apspeedtest.framework.BaseViewHolder;
import com.example.teacherma.apspeedtest.model.bean.TestResult;

import butterknife.BindView;

import static android.view.View.INVISIBLE;

public class MainVH extends BaseViewHolder<TestResult> {

    @BindView(R.id.time_value)
    TextView mTimeValue;
    @BindView(R.id.ip_value)
    TextView mIpValue;
    @BindView(R.id.port_value)
    TextView mPortValue;
    @BindView(R.id.tcp_upward_value)
    TextView mTcpUpwardValue;
    @BindView(R.id.tcp_down_value)
    TextView mTcpDownValue;
    @BindView(R.id.udp_upward_value)
    TextView mUdpUpwardValue;
    @BindView(R.id.udp_down_value)
    TextView mUdpDownValue;

    public MainVH(Context context, ViewGroup root) {
        super(context, root, R.layout.history_item);
    }

    @Override
    protected void bindData(TestResult itemValue, int position, OnItemClickListener listener) {
        mTimeValue.setText(itemValue.getTime());
        mIpValue.setText(itemValue.getIp());
        mPortValue.setText(itemValue.getPort());
        mTcpUpwardValue.setText(itemValue.getTcpUpwardSpeed());
        mTcpDownValue.setText(itemValue.getTcpDownSpeed());
//        mUdpUpwardValue.setText(itemValue.getUdpUpwardSpeed());
        mUdpDownValue.setVisibility(INVISIBLE);
        mUdpUpwardValue.setVisibility(INVISIBLE);
//        mUdpDownValue.setText(itemValue.getUdpDownSpeed());
    }
}
