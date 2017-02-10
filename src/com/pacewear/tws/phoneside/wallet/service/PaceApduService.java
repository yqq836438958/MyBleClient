
package com.pacewear.tws.phoneside.wallet.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.pacewear.tws.phoneside.wallet.DeviceInfo;
import com.pacewear.tws.phoneside.wallet.IPaceApduService;
import com.pacewear.tws.phoneside.wallet.IPaceInvokeCallback;

import java.util.concurrent.atomic.AtomicBoolean;

public class PaceApduService extends Service {
    public static final String TAG = PaceApduService.class.getSimpleName();
    private AtomicBoolean isDeviceConnect = new AtomicBoolean(false);
    private String mDeviceAddr = "";
    private ISeInvoker mSeInvoker = new WalletSeInvoker();
    private RemoteCallbackList<IPaceInvokeCallback> mCallbacks = new RemoteCallbackList<IPaceInvokeCallback>();

    @Override
    public IBinder onBind(Intent intent) {
        printf("service onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        printf("service on Create:" + Thread.currentThread().getName());
    }

    @Override
    public void onDestroy() {
        printf("service on destroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        printf("service on unbind");
        return super.onUnbind(intent);
    }

    public void onRebind(Intent intent) {
        printf("service on rebind");
        super.onRebind(intent);
    }

    private void printf(String str) {
        Log.d(TAG, "###################------ " + str + "------");
    }

    private IPaceApduService.Stub mBinder = new IPaceApduService.Stub() {

        @Override
        public byte[] transmit(byte[] apdus) throws RemoteException {
            Log.d(TAG, "transmit Thread:" + Thread.currentThread().getName());
            if (!isDeviceConnect.get()) {
                return null;
            }
            return mSeInvoker.transmit(apdus);
        }

        @Override
        public int selectAid(String aid) throws RemoteException {
            Log.d(TAG, "selectAid Thread:" + Thread.currentThread().getName());
            if (!isDeviceConnect.get()) {
                return -1;
            }
            return mSeInvoker.selectAID(aid);
        }

        @Override
        public int getDeviceInfo(DeviceInfo info) throws RemoteException {
            info.setConnect(true);
            info.setMacAddr(mDeviceAddr);
            return 0;
        }

        @Override
        public int close() throws RemoteException {
            if (!isDeviceConnect.get()) {
                return -1;
            }
            return mSeInvoker.close();
        }

        @Override
        public int create(IPaceInvokeCallback callback) throws RemoteException {
            if (callback != null) {
                mCallbacks.register(callback);
            }
            return 0;
        }

        @Override
        public int destory(IPaceInvokeCallback callback) throws RemoteException {
            if (callback != null) {
                mCallbacks.unregister(callback);
            }
            return 0;
        }

        @Override
        public int connect(String macId) throws RemoteException {
            isDeviceConnect.set(true);
            dispatchConnectCallbacks(true, macId);
            return 0;
        }

        @Override
        public int disconnect() throws RemoteException {
            isDeviceConnect.set(false);
            dispatchConnectCallbacks(false, "");
            return 0;
        }

        @Override
        public int scan() throws RemoteException {
            // TODO Auto-generated method stub
            return 0;
        }
    };

    private void dispatchScanCallback(DeviceInfo[] list) {
        final int N = mCallbacks.beginBroadcast();
        for (int i = 0; i < N; i++) {
            try {
                mCallbacks.getBroadcastItem(i).onScanResult(list);
            } catch (RemoteException e) {
            }
        }
        mCallbacks.finishBroadcast();
    }

    private void dispatchConnectCallbacks(boolean connect, String mac) {
        final int N = mCallbacks.beginBroadcast();
        for (int i = 0; i < N; i++) {
            try {
                mCallbacks.getBroadcastItem(i).onConnectResult(isDeviceConnect.get(), mac);
            } catch (RemoteException e) {
            }
        }
        mCallbacks.finishBroadcast();
    }

}
