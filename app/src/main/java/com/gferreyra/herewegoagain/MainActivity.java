package com.gferreyra.herewegoagain;

import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.internal.IOException;

public class MainActivity extends AppCompatActivity {
    public String TAG = "MainActivity";
    public String name = "Nothing";
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "HEY HERE IS THE BEGINNING!");
        //DATABASE SET UP
        Realm.init(this);
        Log.d(TAG, "Realm initiated");

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("tester.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfig);


        //used to destroy database
        //Realm.deleteRealm(realmConfig);

        Log.d(TAG, "Realm configured");

        realm = Realm.getDefaultInstance();




        RealmQuery<Character> query = realm.where(Character.class);

        query.equalTo("name", "Jin");

        RealmResults<Character> result = query.findAll();




        //END OF DATABASE SET UP


        //TEST WRITE TO DATABASE
        if(result.isEmpty()) {
            realm.beginTransaction();
            Log.d(TAG, "beginning transaction");

            Log.d(TAG, "creating Character object for Jin");
            Character ch = realm.createObject(Character.class, "01");
            ch.setName("Jin");
            ch.setDifficulty("Intermediate");
            realm.commitTransaction();
            name = "DATABASE BARELY CREATED";

        }

        else{
            Character woo = new Character();
            woo = result.get(0);
            name = woo.getName();
        }


        /*
        Log.d(TAG, "creating Command object");
        Command comm = realm.createObject(Command.class);
        comm.setInput("1");

        Log.d(TAG , "creating FrameData object");
        FrameData fd = realm.createObject(FrameData.class);
        fd.setHitlevel("h");
        fd.setDamage("7");
        fd.setStartFrame("10");
        fd.setBlockFrame("+1");
        fd.setHitFrame("+8");
        fd.setChFrame("+8");

        Log.d(TAG , "creating Effect object");
        Effect eff = realm.createObject(Effect.class);
        eff.setEffect("None");


        //SETTINGS RELATIONSHIPS BETWEEN OBJECTS
        Log.d(TAG , "setting relationships up");
        ch.setCommands(comm);
        comm.setFramedata(fd);
        fd.setEffects(eff);

        Log.d(TAG , "committing transactions");
        realm.commitTransaction();

        Log.d(TAG , "closing DB");
        realm.close();
        */


        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here is the character: " + name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
