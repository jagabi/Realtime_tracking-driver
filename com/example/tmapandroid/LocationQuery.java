package com.example.tmapandroid;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.Vector;

public class LocationQuery {
    private Vector<String> Suc_Result = new Vector<>();
    /* access modifiers changed from: private */
    public double lat;
    /* access modifiers changed from: private */
    public double lon;
    /* access modifiers changed from: private */
    public OnGetDataListener onGetDataListener;
    /* access modifiers changed from: private */
    public double s_lat = 10.0d;
    /* access modifiers changed from: private */
    public double s_lon = 10.0d;

    public void setOnGetDataListener(OnGetDataListener onGetDataListener2) {
        this.onGetDataListener = onGetDataListener2;
    }

    public LocationQuery(double lat2, double lon2) {
        this.lat = lat2;
        this.lon = lon2;
    }

    public void start() {
        Query Query_location_1 = FirebaseDatabase.getInstance().getReference("emergency").child("location").orderByChild("latitude").endAt(this.lat + 0.004d).startAt(this.lat - 0.004d);
        final Vector<GPSData> Query_data = new Vector<>();
        final Vector<GPSData> Distance_Result = new Vector<>();
        Query_location_1.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Query_data.add((GPSData) postSnapshot.getValue(GPSData.class));
                    ((GPSData) Query_data.get(i)).setKey(postSnapshot.getKey());
                    double unused = LocationQuery.this.s_lat = ((GPSData) Query_data.get(i)).getLatitude();
                    double unused2 = LocationQuery.this.s_lon = ((GPSData) Query_data.get(i)).getLongitude();
                    if (LocationQuery.this.lon + 0.0055d < LocationQuery.this.s_lon || LocationQuery.this.lon - 0.0055d > LocationQuery.this.s_lon) {
                        Query_data.remove(i);
                        i--;
                    } else {
                        double distance = Math.acos((Math.cos(Math.toRadians(LocationQuery.this.s_lat)) * Math.cos(Math.toRadians(LocationQuery.this.lat)) * Math.cos(Math.toRadians(LocationQuery.this.lon) - Math.toRadians(LocationQuery.this.s_lon))) + (Math.sin(Math.toRadians(LocationQuery.this.s_lat)) * Math.sin(Math.toRadians(LocationQuery.this.lat)))) * 6371.0d;
                        if (distance < 0.08d) {
                            ((GPSData) Query_data.get(i)).setDistance(distance);
                        }
                        if (distance < 0.04d) {
                            ((GPSData) Query_data.get(i)).setDistance(distance);
                            Distance_Result.add((GPSData) Query_data.get(i));
                        }
                    }
                    i++;
                }
                LocationQuery.this.onGetDataListener.onStart(Query_data, Distance_Result);
            }

            public void onCancelled(DatabaseError error) {
            }
        });
    }
}
