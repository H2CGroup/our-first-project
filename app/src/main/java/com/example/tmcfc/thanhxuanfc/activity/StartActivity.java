package com.example.tmcfc.thanhxuanfc.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tmcfc.thanhxuanfc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnDangNhap;
    private EditText edtTaiKhoan, edtMatKhau;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        edtTaiKhoan = findViewById(R.id.edtTaiKhoan);
        edtMatKhau = findViewById(R.id.edtMatKhau);

        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(this);

        fAuth = FirebaseAuth.getInstance();
        updateUI();
    }

    private void updateUI() {
        if(fAuth.getCurrentUser() == null){
            Log.i("StartActivity", "fAuth != null");
        }else{
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDangNhap:
                login();
        }
    }

    private void login() {
        String name = edtTaiKhoan.getText().toString().trim() + "@gmail.com";
        String password = edtMatKhau.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging in, please wait...");
            progressDialog.show();

            fAuth.signInWithEmailAndPassword(name, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(new Intent(StartActivity.this, MainActivity.class));
                                finish();
                                Toast.makeText(StartActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(StartActivity.this, "ERROR:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
