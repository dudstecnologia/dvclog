package com.dudstecnologia.log;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class HttpService {
    public void sendLog(Context ctx, LogModel logModel) {
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        DatabaseHelper databaseHelper = new DatabaseHelper(ctx);

        StringRequest requestValidate = new StringRequest(Request.Method.POST, "http://192.168.15.3:8000/api/dvclog",
                response -> {
                    databaseHelper.deleteLog(logModel.getId());
                }, error -> {
            Log.d("DVC_LOG", "PASSOU EM ERRO");
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("event", logModel.getEvent());
                params.put("batery", logModel.getBatery());
                params.put("charge", logModel.getCharge());
                params.put("network", logModel.getNetwork());
                params.put("is_wifi", logModel.getIsWifi());
                params.put("date", logModel.getDate());

                return params;
            }
        };

        requestQueue.add(requestValidate);
    }
}
