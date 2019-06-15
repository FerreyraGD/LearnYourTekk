package com.gferreyra.herewegoagain;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CustomApplication extends Application {

    private String TAG = "CustomApplication";
    private RealmConfiguration realmConfig;

    @Override
    public void onCreate(){
        super.onCreate();

        //Set up Realm for DB Creation
        Realm.init(this);
        Log.d(TAG, "Realm Initiated");

        //Configure Realm Database name and version
        realmConfig = new RealmConfiguration.Builder()
                .name("tekken7.realm")
                .schemaVersion(2)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        Log.d(TAG, "Realm Configured");

    }
}
