
package com.clj.blesample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.clj.APDU;
import com.clj.BleTest;
import com.clj.BleTest.IRecvCallback;
import com.clj.BleTest.ISendCallback;

public class TestBeiJingTongActvity extends Activity implements IUIUpdate {
    private Button mQueryMoney = null;
    private Button mQueryCardNum = null;
    private Button mDevInfo = null;
    private Button mPowerOn = null;
    private Button mPowerOff = null;
    private Button mAuth = null;
    private TextView mResult = null;
    private ViewGroup mLoadingView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_bjt);
        mResult = (TextView) findViewById(R.id.tv_result);
        mQueryCardNum = (Button) findViewById(R.id.query2);
        mQueryMoney = (Button) findViewById(R.id.query);
        mDevInfo = (Button) findViewById(R.id.devInf);
        mPowerOn = (Button) findViewById(R.id.poweron);
        mPowerOff = (Button) findViewById(R.id.poweroff);
        mAuth = (Button) findViewById(R.id.auth);
        mLoadingView = (ViewGroup) findViewById(R.id.loading);
        mLoadingView.setVisibility(View.GONE);
        mQueryMoney.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mLoadingView.setVisibility(View.VISIBLE);
                queryMoeny();
            }
        });
        mQueryCardNum.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mLoadingView.setVisibility(View.VISIBLE);
                queryCardNum();
            }
        });
        mDevInfo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mLoadingView.setVisibility(View.VISIBLE);
                queryDevInf();
            }
        });
        mPowerOn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mLoadingView.setVisibility(View.VISIBLE);
                powerOn();
            }
        });
        mPowerOff.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mLoadingView.setVisibility(View.VISIBLE);
                powerOff();
            }
        });
        mAuth.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                auth();
            }
        });
    }

    private ISendCallback mSendCallback = new ISendCallback() {

        @Override
        public void onSendSuc(byte[] val) {
            showResult("命令发送成功");
        }

        @Override
        public void onSendFail(int ret) {
            showResult("命令发送失败");
        }
    };

    private void showResult(final String str) {
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mLoadingView.setVisibility(View.GONE);
                mResult.setText(str);
            }
        });
    }

    private void queryMoeny() {
        new ApduStepModule(0, mSendCallback, this);
    }

    private void queryCardNum() {
        new ApduStepModule(1, mSendCallback, this);
    }

    private void queryDevInf() {
        BleTest.sendDat(BleTest.TYPE_DEVINF, null, mSendCallback, new IRecvCallback() {

            @Override
            public void onRecvSuc(String result) {
                showResult(result);
            }

            @Override
            public void onRecvFail(int ret) {
                showResult("读取蓝牙设备信息失败");
            }
        });
    }

    private void auth() {
        BleTest.sendDat(BleTest.TYPE_AUTH, null, mSendCallback, new IRecvCallback() {

            @Override
            public void onRecvSuc(String result) {
                showResult(result);
            }

            @Override
            public void onRecvFail(int ret) {
                showResult("认证设备失败");
            }
        });
    }

    private void powerOn() {
        byte[] tmp = new byte[] {
                0x01
        };
        BleTest.sendDat(BleTest.TYPE_POWER, tmp, mSendCallback, new IRecvCallback() {

            @Override
            public void onRecvSuc(String result) {
                showResult(result);
            }

            @Override
            public void onRecvFail(int ret) {
                showResult("设备上电失败");
            }
        });
    }

    private void powerOff() {
        byte[] tmp = new byte[] {
                0x00
        };
        BleTest.sendDat(BleTest.TYPE_POWER, tmp, mSendCallback, new IRecvCallback() {

            @Override
            public void onRecvSuc(String result) {
                showResult(result);
            }

            @Override
            public void onRecvFail(int ret) {
                showResult("设备上电失败");
            }
        });
    }

    @Override
    public void onShowResult(String ret) {
        showResult(ret);
    }
}
