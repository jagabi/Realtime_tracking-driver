package com.example.tmapandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    /* access modifiers changed from: private */
    public EditText mEtEmail;
    /* access modifiers changed from: private */
    public EditText mEtPwd;
    /* access modifiers changed from: private */
    public FirebaseAuth mFirebaseAuth;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2135R.layout.activity_login);
        this.mFirebaseAuth = FirebaseAuth.getInstance();
        this.mDatabaseRef = FirebaseDatabase.getInstance().getReference("TMAPandroid");
        this.mEtEmail = (EditText) findViewById(C2135R.C2138id.et_email);
        this.mEtPwd = (EditText) findViewById(C2135R.C2138id.et_pwd);
        ((Button) findViewById(C2135R.C2138id.btn_login)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.mFirebaseAuth.signInWithEmailAndPassword(LoginActivity.this.mEtEmail.getText().toString(), LoginActivity.this.mEtPwd.getText().toString()).addOnCompleteListener((Activity) LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            LoginActivity.this.finish();
                            return;
                        }
                        Toast.makeText(LoginActivity.this, "로그인 실패", 0).show();
                    }
                });
            }
        });
        ((Button) findViewById(C2135R.C2138id.btn_register)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
