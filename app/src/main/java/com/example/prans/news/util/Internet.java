package com.example.prans.news.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

public class Internet {

//  Cek Koneksi
    public static boolean checkConnection(@NonNull Context context) {
        return ((ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
