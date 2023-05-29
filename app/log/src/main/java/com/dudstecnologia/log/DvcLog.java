package com.dudstecnologia.log;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.util.Log;

public class DvcLog {

    public void log1(String msg) {
        Log.d("DVC_LOG", "Log 1: " + msg);
    }

    public void log2(String msg) {
        Log.d("DVC_LOG", "Log 2: " + msg);
    }

    public void sendEvent(Context ctx, String event) {
        DatabaseHelper databaseHelper = new DatabaseHelper(ctx);
        HttpService httpService = new HttpService();

        LogModel logModel = new LogModel(
                event,
                String.valueOf(getBatteryLevel(ctx)),
                "0",
                getWifiSsid(ctx),
                "1",
                "2022-05-29"
        );

        long idLog = databaseHelper.saveLog(logModel);
        logModel = databaseHelper.getLog(idLog);

        httpService.sendLog(ctx, logModel);
    }

    private float getBatteryLevel(Context ctx) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = ctx.registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        return level * 100 / (float)scale;
    }

    private String getWifiSsid(Context ctx) {
        WifiManager wifiManager = (WifiManager) ctx.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo;
        String ssid = "";

        wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
            ssid = wifiInfo.getSSID();
        }

        return ssid;
    }
}
