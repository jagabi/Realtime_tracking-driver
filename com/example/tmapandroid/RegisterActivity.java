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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private Button mBtnRegister;
    /* access modifiers changed from: private */
    public DatabaseReference mDatabaseRef;
    /* access modifiers changed from: private */
    public EditText mEtEmail;
    /* access modifiers changed from: private */
    public EditText mEtPwd;
    /* access modifiers changed from: private */
    public FirebaseAuth mFirebaseAuth;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C2135R.layout.activity_register);
        this.mFirebaseAuth = FirebaseAuth.getInstance();
        this.mDatabaseRef = FirebaseDatabase.getInstance().getReference("TMAPandroid");
        this.mEtEmail = (EditText) findViewById(C2135R.C2138id.et_email);
        this.mEtPwd = (EditText) findViewById(C2135R.C2138id.et_pwd);
        Button button = (Button) findViewById(C2135R.C2138id.btn_register);
        this.mBtnRegister = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String strEmail = RegisterActivity.this.mEtEmail.getText().toString();
                final String strPwd = RegisterActivity.this.mEtPwd.getText().toString();
                RegisterActivity.this.mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener((Activity) RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = RegisterActivity.this.mFirebaseAuth.getCurrentUser();
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailid(firebaseUser.getEmail());
                            account.setPassword(strPwd);
                            RegisterActivity.this.mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                            Toast.makeText(RegisterActivity.this, "회원가입 성공", 0).show();
                            RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            return;
                        }
                        Toast.makeText(RegisterActivity.this, "회원가입 실패", 0).show();
                    }
                });
            }
        });
    }
}
