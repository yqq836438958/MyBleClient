
package com.pacewear.tws.phoneside.wallet.service;

import android.util.Log;

import com.ble.common.ByteUtil;

import java.util.concurrent.Semaphore;

public class WalletSeInvoker implements ISeInvoker {
    public static final String TAG = "Se";

    @Override
    public int selectAID(String aid) {
        Log.d(TAG, "selectAID" + aid);
        return 0;
    }

    @Override
    public int close() {
        Log.d(TAG, "close");
        return 0;
    }

    @Override
    public byte[] transmit(byte[] apdus) {
        Log.d(TAG, "transmit:" + ByteUtil.toHexString(apdus));
        return ByteUtil.toByteArray("a1a2a3a4");
    }

}
