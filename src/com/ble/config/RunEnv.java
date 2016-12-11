
package com.ble.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Log;

public class RunEnv {
    public static final String BLESRV_UUID = "0000b001-0000-1000-8000-00805f9b34fb";
    public static final String UUID_WRITE = "0000b002-0000-1000-8000-00805f9b34fb";
    public static final String UUID_INDICATE = "0000b003-0000-1000-8000-00805f9b34fb";
    public static final int BLESRV_SIG = 276486;
    public static final String SE_ISD = "A000000151000000";
    public static boolean sBleDatPackAutoComp = false; // 蓝牙数据自动补全

}
