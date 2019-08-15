package com.ira.friendster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    //    NIM:10116574
    //    Nama:Ira Yuti
    //    Kelas:If-13
    //    Tanggal Pengerjaan:15/08/2019
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
