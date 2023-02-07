package com.example.tmapandroid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.Iterator;
import java.util.Vector;

public class LocationNoti {
    private boolean CKey;
    private final String DEFAULT = "DEFAULT";
    private Vector<String> Suc_Result = new Vector<>();
    private LocationQuery loc;
    private Context mcontext;

    public LocationNoti(Context context) {
        this.mcontext = context;
    }

    public void createNotificationChannel(String channelId, String channelName, int importance) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) this.mcontext.getSystemService("notification")).createNotificationChannel(new NotificationChannel(channelId, channelName, importance));
        }
    }

    public void createNotification(String channelId, int id, String title, String text) {
        ((NotificationManager) this.mcontext.getSystemService("notification")).notify(id, new NotificationCompat.Builder(this.mcontext, channelId).setPriority(1).setSmallIcon(C2135R.C2137drawable.ic_launcher_foreground).setContentTitle(title).setContentText(text).setDefaults(3).build());
    }

    public void checkAlarm(Vector<GPSData> QVector) {
        int check = 0;
        try {
            Iterator<GPSData> it = QVector.iterator();
            while (it.hasNext()) {
                GPSData query = it.next();
                this.CKey = true;
                Iterator<String> it2 = this.Suc_Result.iterator();
                while (it2.hasNext()) {
                    if (it2.next().equals(query.getKey())) {
                        check++;
                        this.CKey = false;
                    }
                }
                if (this.CKey) {
                    this.Suc_Result.add(query.getKey());
                    System.out.println(query.getKey());
                }
            }
            Log.d("ddd", "check : " + check);
            Log.d("ddd", "QVector : " + QVector.size());
            Log.d("ddd", "Suc_Result : " + this.Suc_Result.size());
            if (check != QVector.size()) {
                createNotification("DEFAULT", 1, "경고", "비상차량이 500M내로 접근 중입니다");
            }
        } catch (Exception e) {
            Log.d("ddd", "LocationNoti" + e);
            throw e;
        }
    }
}
