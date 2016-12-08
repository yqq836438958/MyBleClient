
package com.clj.blesample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ble.common.ByteUtil;
import com.clj.APDU;
import com.clj.BleTest;
import com.clj.BleTest.IRecvCallback;
import com.clj.BleTest.ISendCallback;
import com.clj.fastble.BleManager;

public class TestBeiJingTongActvity extends Activity {
    private Button mQueryMoney = null;
    private Button mQueryCardNum = null;
    private TextView mResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_bjt);
        mResult = (TextView) findViewById(R.id.tv_result);
        mQueryCardNum = (Button) findViewById(R.id.query2);
        mQueryMoney = (Button) findViewById(R.id.query);
        mQueryMoney.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                queryMoeny();
            }
        });
        mQueryCardNum.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                queryCardNum();
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
                mResult.setText(str);
            }
        });
    }

    private void queryMoeny() {
        BleTest.sendDat(BleTest.TYPE_ICC, APDU.selectFS(), mSendCallback, new IRecvCallback() {

            @Override
            public void onRecvSuc(String result) {
                if (!result.endsWith("9000")) {
                    return;
                }
                BleTest.sendDat(BleTest.TYPE_ICC, APDU.getCardAmount(), mSendCallback,
                        new IRecvCallback() {

                    @Override
                    public void onRecvSuc(String result) {
                        showResult("余额：" + APDU.parseMoney(result) + "分");
                    }

                    @Override
                    public void onRecvFail(int ret) {
                        showResult("读取余额失败");
                    }
                });
            }

            @Override
            public void onRecvFail(int ret) {
                showResult("选择分区失败");
            }
        });
    }

    private void queryCardNum() {
        BleTest.sendDat(BleTest.TYPE_ICC, APDU.selectFS(), mSendCallback, new IRecvCallback() {

            @Override
            public void onRecvSuc(String result) {
                if (!result.endsWith("9000")) {
                    return;
                }
                BleTest.sendDat(BleTest.TYPE_ICC, APDU.getCardNum(), mSendCallback,
                        new IRecvCallback() {

                    @Override
                    public void onRecvSuc(String result) {
                        showResult("卡号：" + APDU.parseCardNum(result));
                    }

                    @Override
                    public void onRecvFail(int ret) {
                        showResult("读取卡号失败");
                    }
                });
            }

            @Override
            public void onRecvFail(int ret) {
                showResult("选择分区失败");
            }
        });
    }
}
