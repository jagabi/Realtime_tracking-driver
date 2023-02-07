package com.example.tmapandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;
import java.util.Vector;

public class MapActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {
    String API_Key = "l7xx142e47211df24cef9d4dd87c89b824c0";
    private final String DEFAULT = "DEFAULT";
    /* access modifiers changed from: private */
    public Vector<GPSData> DVector = new Vector<>();
    /* access modifiers changed from: private */
    public Vector<GPSData> QVector = new Vector<>();
    LocationNoti mNoti = new LocationNoti(this);
    private TMapGpsManager tMapGPS = null;
    private TMapView tMapView = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2135R.layout.activity_map);
        this.mNoti.createNotificationChannel("DEFAULT", "default channel", 4);
        TMapView tMapView2 = new TMapView(this);
        this.tMapView = tMapView2;
        tMapView2.setSKTMapApiKey(this.API_Key);
        this.tMapView.setZoomLevel(20);
        this.tMapView.setIconVisibility(true);
        this.tMapView.setMapType(0);
        this.tMapView.setLanguage(0);
        this.tMapView.setIconVisibility(true);
        this.tMapView.setCompassMode(true);
        this.tMapView.setTrackingMode(true);
        this.tMapView.setSightVisible(true);
        ((LinearLayout) findViewById(C2135R.C2138id.linearLayoutTmap)).addView(this.tMapView);
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        }
        TMapGpsManager tMapGpsManager = new TMapGpsManager(this);
        this.tMapGPS = tMapGpsManager;
        tMapGpsManager.setMinTime(1000);
        this.tMapGPS.setMinDistance(0.0f);
        this.tMapGPS.setProvider(TMapGpsManager.NETWORK_PROVIDER);
        this.tMapGPS.setProvider(TMapGpsManager.GPS_PROVIDER);
        this.tMapGPS.OpenGps();
    }

    public void onLocationChange(Location location) {
        this.tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        this.tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());
        LocationQuery loc = new LocationQuery(location.getLatitude(), location.getLongitude());
        loc.setOnGetDataListener(new OnGetDataListener() {
            public void onStart(Vector<GPSData> gpsData, Vector<GPSData> distanceData) {
                Log.d("ddd", "ddd : " + gpsData.size());
                Vector unused = MapActivity.this.QVector = gpsData;
                Vector unused2 = MapActivity.this.DVector = distanceData;
                Log.d("ddd", "QVector : " + MapActivity.this.QVector.size());
                Log.d("ddd", "DVector : " + MapActivity.this.DVector.size());
                if (MapActivity.this.QVector != null) {
                    MapActivity.this.mNoti.checkAlarm(MapActivity.this.QVector);
                    MapActivity mapActivity = MapActivity.this;
                    mapActivity.ViewMarker(mapActivity.DVector);
                }
            }
        });
        loc.start();
    }

    public void ViewMarker(Vector<GPSData> DVector2) {
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), C2135R.C2137drawable.emergency);
            for (int i = 0; i < DVector2.size(); i++) {
                TMapMarkerItem markerItem1 = new TMapMarkerItem();
                TMapPoint point = new TMapPoint(DVector2.get(i).getLatitude(), DVector2.get(i).getLongitude());
                markerItem1.setIcon(bitmap);
                markerItem1.setTMapPoint(point);
                this.tMapView.addMarkerItem("markerItem" + i, markerItem1);
            }
        } catch (Exception e) {
            Log.d("ddd", "ViewLoc" + e);
            throw e;
        }
    }

    public void setQVector(Vector<GPSData> QVector2) {
        this.QVector = QVector2;
    }
}
