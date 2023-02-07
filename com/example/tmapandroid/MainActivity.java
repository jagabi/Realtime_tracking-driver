package com.example.tmapandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    Button Btn_Location;
    Button Btn_MapDisplay;
    private String TAG = "MainActivity";
    Button btn;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    EditText edit1;
    EditText edit2;

    public MainActivity() {
        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        this.database = instance;
        this.databaseReference = instance.getReference();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2135R.layout.activity_main);
        this.btn = (Button) findViewById(C2135R.C2138id.btn);
        this.edit1 = (EditText) findViewById(C2135R.C2138id.edit1);
        this.edit2 = (EditText) findViewById(C2135R.C2138id.edit2);
        this.btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        Button button = (Button) findViewById(C2135R.C2138id.Btn_MapDisplay);
        this.Btn_MapDisplay = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseMessaging.getInstance().getToken();
    }
}
