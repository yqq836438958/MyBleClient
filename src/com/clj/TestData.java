
package com.clj;

import android.util.Log;

import com.ble.data.BleBufferReader;
import com.ble.data.BleInBuffer;
import com.ble.data.BleMetaData;
import com.ble.data.BleOutBuffer;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.lsk.lyphone.LYPhoneMessage.LogonReqMessage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import BmacBp.BeijingCard.BaseReq;
import BmacBp.BeijingCard.IccReq;

public class TestData {
    private static BleOutBuffer mSendBuffers = null;
    private static int dataSize = 0;
    private static BleBufferReader mReader = BleBufferReader.getInstance();

    public static byte[] getData(String strIndex) {
        // BleMetaData data =
        int index = Integer.parseInt(strIndex);
        return mSendBuffers.getDataList().get(index).get();
    }

    public static void genObject() {
        byte[] src = new byte[] {
                (byte) 0x80, (byte) 0x50, (byte) 0x0c, (byte) 0x00, (byte) 0x08, (byte) 0x26,
                (byte) 0x36, (byte) 0x70, (byte) 0x18, (byte) 0x62, (byte) 0x6c, (byte) 0xa4,
                (byte) 0x3d, (byte) 0x00
        };
        IccReq.Builder req = IccReq.newBuilder();
        BaseReq.Builder base = BaseReq.newBuilder();
        req.setBaseReq(base);
        req.setData(ByteString.copyFrom(src));
        byte[] result = req.build().toByteArray();
        mSendBuffers = new BleOutBuffer((byte) 0xA1, result);
        dataSize = mSendBuffers.getDataList().size();
    }

    public static int genInputData(byte[] tmpData) {
        return mReader.readBleBuffer(tmpData);
    }

    public static void handleInputData() {
        BleInBuffer bleInBuffer = mReader.getCurIndexBuffer();
        byte[] tmp = bleInBuffer.getData();
        IccReq req = null;
        try {
            req = IccReq.parseFrom(tmp);
        } catch (InvalidProtocolBufferException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (req == null) {
            return;
        }
        byte[] tmp2 = req.getData().toByteArray();
        Log.d("aa", "bb");
    }
}
