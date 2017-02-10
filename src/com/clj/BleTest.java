
package com.clj;

import android.R.integer;
import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;

import com.ble.common.ByteUtil;
import com.ble.common.ErrCode;
import com.ble.common.ThreadUtils;
import com.ble.data.BleBufferReader;
import com.ble.data.BleInBuffer;
import com.ble.data.BleOutBuffer;
import com.clj.fastble.BleManager;
import com.clj.fastble.conn.BleCharacterCallback;
import com.clj.fastble.exception.BleException;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import BmacBp.BeijingCard.AuthReq;
import BmacBp.BeijingCard.AuthResp;
import BmacBp.BeijingCard.BaseReq;
import BmacBp.BeijingCard.DevInfoReq;
import BmacBp.BeijingCard.DevInfoResp;
import BmacBp.BeijingCard.DevPowerReq;
import BmacBp.BeijingCard.DevPowerResp;
import BmacBp.BeijingCard.EmDataType;
import BmacBp.BeijingCard.EmDevPower;
import BmacBp.BeijingCard.IccReq;
import BmacBp.BeijingCard.IccResp;

public class BleTest {
    public static BleOutBuffer mAuthSendBuffers = null;
    public static BleOutBuffer mIccSendBuffers = null;
    public static BleOutBuffer mDevInfoSendBuffers = null;
    public static BleOutBuffer mPowerSendBuffers = null;
    // private static BleOutBuffer m
    public static int mAPduSize = 0;
    public static int mDevInfSize = 0;
    public static int mAuthSize = 0;
    public static int mPowerSize = 0;
    public static int mIndex = 0;
    public static BleBufferReader mReader = null;

    public static final int TYPE_AUTH = 0;
    public static final int TYPE_DEVINF = 1;
    public static final int TYPE_ICC = 2;
    public static final int TYPE_POWER = 3;

    public static interface IRecvCallback {
        public void onRecvSuc(String result);

        public void onRecvFail(int ret);

    }

    public static interface ISendCallback {
        public void onSendSuc(byte[] val);

        public void onSendFail(int ret);
    }

    private static byte[] sDefaultApdus = new byte[] {
            (byte) 0x80, (byte) 0x50, (byte) 0x0c, (byte) 0x00, (byte) 0x08, (byte) 0x26,
            (byte) 0x36, (byte) 0x70, (byte) 0x18, (byte) 0x62, (byte) 0x6c, (byte) 0xa4,
            (byte) 0x3d, (byte) 0x00
    };

    private static void genAuthData() {
        AuthReq.Builder builder = AuthReq.newBuilder();
        BaseReq.Builder base = BaseReq.newBuilder();
        builder.setBaseReq(base);
        builder.setEmDataType(EmDataType.EDT_plaintext);
        byte[] src = builder.build().toByteArray();
        mAuthSendBuffers = new BleOutBuffer((byte) 0xA0, src, false);
        mAuthSize = mAuthSendBuffers.getDataList().size();
    }

    private static void genDevInfRet() {
        DevInfoReq.Builder builder = DevInfoReq.newBuilder();
        BaseReq.Builder base = BaseReq.newBuilder();
        builder.setBaseReq(base);
        byte[] result = builder.build().toByteArray();
        mDevInfoSendBuffers = new BleOutBuffer((byte) 0xA3, result, false);
        mDevInfSize = mDevInfoSendBuffers.getDataList().size();
    }

    private static void genPowerCommand(int powOn) {
        DevPowerReq.Builder builder = DevPowerReq.newBuilder();
        BaseReq.Builder base = BaseReq.newBuilder();
        builder.setBaseReq(base);
        builder.setEmDevPower((powOn == 1) ? EmDevPower.EDP_power_on : EmDevPower.EDP_power_off);
        byte[] result = builder.build().toByteArray();
        mPowerSendBuffers = new BleOutBuffer((byte) 0xA2, result);
        mPowerSize = mPowerSendBuffers.getDataList().size();
    }

    private static void genIccApdus(byte[] src) {

        IccReq.Builder req = IccReq.newBuilder();
        BaseReq.Builder base = BaseReq.newBuilder();
        req.setBaseReq(base);
        req.setData(ByteString.copyFrom(src));
        byte[] result = req.build().toByteArray();
        mIccSendBuffers = new BleOutBuffer((byte) 0xA1, result, false);
        mAPduSize = mIccSendBuffers.getDataList().size();
    }

    private static void onRecv(byte[] _data, IRecvCallback callback) {
        int ret = mReader.readBleBuffer(_data);
        if (ret != ErrCode.ERR_HANDLE_OK) {
            return;
        }
        BleInBuffer resultBuf = mReader.getCurIndexBuffer();
        if (resultBuf == null) {
            return;
        }
        byte[] tmp = resultBuf.getData();
        byte type = resultBuf.getType();
        switch (type) {
            case (byte) 0xA0:
                onParseAuthRsp(tmp, callback);
                break;
            case (byte) 0xA3:
                onParseDevInfRsp(tmp, callback);
                break;
            case (byte) 0xA1:
                onParseIccRsp(tmp, callback);
                break;
            case (byte) 0xA2:
                onParseDevPowRsp(tmp, callback);
                break;
            default:
                break;
        }
    }

    private static void onParseIccRsp(byte[] tmp, IRecvCallback callback) {
        IccResp resp;
        try {
            resp = IccResp.parseFrom(tmp);
            byte[] apdu = resp.getData().toByteArray();
            Log.d("yqq", "parse recv data:" + ByteUtil.toHexString(apdu));
            if (callback != null) {
                callback.onRecvSuc(ByteUtil.toHexString(apdu));
            }
        } catch (InvalidProtocolBufferException e) {
            if (callback != null) {
                callback.onRecvFail(-1);
            }
            e.printStackTrace();
        }
    }

    private static void onParseAuthRsp(byte[] tmp, IRecvCallback callback) {
        AuthResp resp;
        try {
            resp = AuthResp.parseFrom(tmp);
            int iRet = resp.getBaseResp().getEmRetCode();
            int protoVer = resp.getProtoVersion();
            byte[] macAddr = resp.getMacAddress().toByteArray();
            String facId = resp.getFacId().toStringUtf8();
            String devType = resp.getDeviceType().toStringUtf8();
            StringBuilder builder = new StringBuilder();
            String result = builder.append("iRet:").append(iRet).append(",protoVer:")
                    .append(protoVer).append(",macAddr:").append(macAddr).append(",facId:")
                    .append(facId).append(",devType:").append(devType)
                    .toString();
            Log.e("yqq", "recv->" + resp.getProtoVersion());
            Log.e("yqq", "recv->" + ByteUtil.toHexString(macAddr));
            Log.e("yqq", "recv->" + ByteUtil.toHexString(resp.getFacId().toByteArray()));
            Log.e("yqq", "recv->" + ByteUtil.toHexString(resp.getDeviceType().toByteArray()));
            if (callback != null) {
                callback.onRecvSuc(result);
            }
        } catch (InvalidProtocolBufferException e) {
            if (callback != null) {
                callback.onRecvFail(-1);
            }
            e.printStackTrace();
        }
    }

    private static void onParseDevPowRsp(byte[] tmp, IRecvCallback callback) {
        DevPowerResp resp;
        try {
            resp = DevPowerResp.parseFrom(tmp);
            int iRet = resp.getBaseResp().getEmRetCode();
            Log.e("yqq", "recv iRet:" + iRet);
            if (callback != null) {
                callback.onRecvSuc(iRet + "");
            }
        } catch (InvalidProtocolBufferException e) {
            if (callback != null) {
                callback.onRecvFail(-1);
            }
        }

    }

    private static void onParseDevInfRsp(byte[] tmp, IRecvCallback callback) {
        try {
            DevInfoResp resp = DevInfoResp.parseFrom(tmp);
            int iRet = resp.getBaseResp().getEmRetCode();
            int batt = resp.getBattery();
            String devId = resp.getDevId();
            String devName = resp.getDevName();
            String devVer = resp.getDevVer();

            Log.e("yqq", "recv bat:" + batt);
            Log.e("yqq", "recv devId:" + devId);
            Log.e("yqq", "recv name:" + devName);
            Log.e("yqq", "recv dev ver:" + devVer);
            Log.e("yqq", "recv ret:" + iRet);
            StringBuilder builder = new StringBuilder();
            String result = builder.append("iRet:").append(iRet).append(",battery:").append(batt)
                    .append(",devId:").append(devId).append(",devName:").append(devName)
                    .append(",dev Ver:").append(devVer)
                    .toString();
            if (callback != null) {
                callback.onRecvSuc(result);
            }
        } catch (InvalidProtocolBufferException e) {
            if (callback != null) {
                callback.onRecvFail(-1);
            }
            e.printStackTrace();
        }
    }

    private static void sendAuth(BleManager bleManager, String serviceUUID,
            final String characterUUID, BleCharacterCallback callback) {
        int index = 0;
        byte[] tmp = null;
        for (index = 0; index < mAuthSize; index++) {
            tmp = mAuthSendBuffers.getDataList().get(index).get();
            bleManager.writeDevice(serviceUUID, characterUUID, tmp, callback);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendDevInfo(BleManager bleManager, String serviceUUID,
            final String characterUUID, BleCharacterCallback callback) {
        int index = 0;
        byte[] tmp = null;
        for (index = 0; index < mDevInfSize; index++) {
            tmp = mDevInfoSendBuffers.getDataList().get(index).get();
            bleManager.writeDevice(serviceUUID, characterUUID, tmp, callback);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendIccs(BleManager bleManager, String serviceUUID,
            final String characterUUID, BleCharacterCallback callback) {
        int index = 0;
        byte[] tmp = null;
        for (index = 0; index < mAPduSize; index++) {
            tmp = mIccSendBuffers.getDataList().get(index).get();
            Log.d("yqq", "sendIccs:" + index);
            Log.d("yqq", "senddata icc:" + ByteUtil.toHexString(tmp));
            bleManager.writeDevice(serviceUUID, characterUUID, tmp, callback);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void sendDevPower(BleManager bleManager, String serviceUUID,
            final String characterUUID, BleCharacterCallback callback) {
        int index = 0;
        byte[] tmp = null;
        for (index = 0; index < mPowerSize; index++) {
            tmp = mPowerSendBuffers.getDataList().get(index).get();
            bleManager.writeDevice(serviceUUID, characterUUID, tmp, callback);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendDat(final int type, final byte[] val, final ISendCallback sendCallback,
            final IRecvCallback recvCallback) {
        final BleManager bleManager = BleManager.getInstance();
        mReader = BleBufferReader.getInstance();
        ThreadUtils.getWorkerHandler().post(new Runnable() {

            @Override
            public void run() {
                bleManager.indicateDevice("0000b001-0000-1000-8000-00805f9b34fb",
                        "0000b003-0000-1000-8000-00805f9b34fb",
                        new BleCharacterCallback() {

                    @Override
                    public void onSuccess(BluetoothGattCharacteristic characteristic) {
                        Log.d("yqq", "indicate suc.");
                        BleTest.onRecv(characteristic.getValue(), recvCallback);
                    }

                    @Override
                    public void onFailure(BleException exception) {
                        Log.d("yqq", "fail  indefiy ...");
                        if (recvCallback != null) {
                            recvCallback.onRecvFail(-1);
                        }
                    }
                });
                BleCharacterCallback characterCallback = new BleCharacterCallback() {

                    @Override
                    public void onSuccess(BluetoothGattCharacteristic characteristic) {
                        Log.e("yqq", "sendDevInfo suc");
                        if (sendCallback != null) {
                            sendCallback.onSendSuc(characteristic.getValue());
                        }
                    }

                    @Override
                    public void onFailure(BleException exception) {
                        Log.e("yqq", "sendDevInfo onFailure");
                        if (sendCallback != null) {
                            sendCallback.onSendFail(-1);
                        }
                    }
                };
                switch (type) {
                    case TYPE_AUTH:
                        genAuthData();
                        sendAuth(bleManager, "0000b001-0000-1000-8000-00805f9b34fb",
                                "0000b002-0000-1000-8000-00805f9b34fb", characterCallback);
                        break;
                    case TYPE_ICC:
                        genIccApdus(val);
                        sendIccs(bleManager, "0000b001-0000-1000-8000-00805f9b34fb",
                                "0000b002-0000-1000-8000-00805f9b34fb", characterCallback);
                        break;
                    case TYPE_DEVINF:
                        genDevInfRet();
                        sendDevInfo(bleManager, "0000b001-0000-1000-8000-00805f9b34fb",
                                "0000b002-0000-1000-8000-00805f9b34fb", characterCallback);
                        break;
                    case TYPE_POWER:
                        genPowerCommand((int) val[0]);
                        sendDevPower(bleManager, "0000b001-0000-1000-8000-00805f9b34fb",
                                "0000b002-0000-1000-8000-00805f9b34fb", characterCallback);
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
