package com.ira.friendster;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
//    NIM:10116574
//    Nama:Ira Yuti
//    Kelas:If-13
//    Tanggal Pengerjaan:13/08/2019
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("friendster.db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
