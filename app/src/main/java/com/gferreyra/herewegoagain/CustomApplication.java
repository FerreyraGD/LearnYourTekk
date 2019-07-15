package com.gferreyra.herewegoagain;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

//Custom Application class to load database when application is launched so that way the database is set ahead of time
//This allows us to have access to database throughout the entire application(in any activity)
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

        //Realm.deleteRealm(realmConfig);
    }
}
