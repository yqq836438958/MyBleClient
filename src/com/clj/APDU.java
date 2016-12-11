
package com.clj;

import android.text.TextUtils;

import com.ble.common.ByteUtil;

public class APDU {
    public static byte[] selectCRS() {
        return ByteUtil.toByteArray("00A404000CA0000003335342540000000D00");
    }

    // public static byte[]
    public static byte[] selectFS() {
        return ByteUtil.toByteArray("00A40000023F00");
    }

    public static byte[] getCardNum() {
        return ByteUtil.toByteArray("00B0840000");
    }

    public static byte[] getCardMoneyArea() {
        return ByteUtil.toByteArray("00A40000021001");
    }

    public static byte[] getCardAmount() {
        return ByteUtil.toByteArray("805C000204");
    }

    public static byte[] selectAid() {
        return ByteUtil.toByteArray("00A4040008915600001401000100");
    }

    public static float parseMoney(String money) {
        if (TextUtils.isEmpty(money) || !money.endsWith("9000")) {
            return -1f;
        }
        byte[] tmp = ByteUtil.toByteArray(money);
        int len = tmp.length;
        byte[] bResult = new byte[len - 2];
        System.arraycopy(tmp, 0, bResult, 0, len - 2);
        int iMoney = toInt(bResult, 0, 4);

        return iMoney / 100.00f;
    }

    public static String parseCardNum(String result) {
        if (TextUtils.isEmpty(result) || !result.endsWith("9000")) {
            return "null";
        }
        byte[] card = ByteUtil.toByteArray(result);
        int len = card.length;
        byte[] bResult = new byte[8];
        System.arraycopy(card, 0, bResult, 0, 8);
        return ByteUtil.toHexString(bResult);
    }

    private static int toInt(byte[] b, int s, int n) {
        int ret = 0;

        final int e = s + n;
        for (int i = s; i < e; ++i) {
            ret <<= 8;
            ret |= b[i] & 0xFF;
        }
        return ret;
    }
}
