package com.gferreyra.herewegoagain;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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

        //TODO read from file to get all filenames and initialize/populate a String array
        ArrayList<String> charNames = new ArrayList<>();
        try {
            charNames = loadCharNamesFromAsset(this);
            Log.d(TAG, Integer.toString(charNames.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        //CODE IS USED TO PULL FROM JSON FILES AND EDIT THEM WITH NEW ATTRIBUTES, DO NOT CURRENTLY NEED SINCE DATA IS UP TO DATE
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        //TODO READ FROM JSON FILE
        JSONObject obj = null;
        ArrayList<JSONObject> allCharacterJSONObjects = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            String jsonString = loadJSONFromAsset(this, charNames.get(i));
            try{
                obj = new JSONObject(jsonString);
                String fileName = obj.getString("name");
                Log.d(TAG, "HEY HERE IS THE FILENAME: " + fileName);
                int arrayLength = obj.getJSONArray("basicmoves").length();
                for(int j = 0; j < arrayLength; j++){
                    JSONObject newObj = obj.getJSONArray("basicmoves").getJSONObject(j).put("rage_art", "No");
                    newObj = obj.getJSONArray("basicmoves").getJSONObject(j).put("rage_drive", "No");
                    newObj = obj.getJSONArray("basicmoves").getJSONObject(j).put("tail_spin", "No");
                    newObj = obj.getJSONArray("basicmoves").getJSONObject(j).put("homing", "No");
                    newObj = obj.getJSONArray("basicmoves").getJSONObject(j).put("power_crush", "No");
                    newObj = obj.getJSONArray("basicmoves").getJSONObject(j).put("wall_bounce", "No");
                    newObj = obj.getJSONArray("basicmoves").getJSONObject(j).put("wall_break", "No");
                    writeToFile(newObj.toString(1), this, fileName);
                }
            } catch (JSONException e){
                e.printStackTrace();
            }
        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////



        //example code that i will use for future methods
        /*
        String jsonString = loadJSONFromAsset(this, charName);
        JSONObject obj = null;
        try {
            obj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject newObj = obj.getJSONArray("basicmoves").getJSONObject(0).put("testing", "okay");
            //writeToFile(newObj.toString(), this);
            File path = this.getFilesDir();
            Log.d(TAG, obj.getJSONArray("basicmoves").getJSONObject(0).toString());
            Log.d(TAG, path.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public String loadJSONFromAsset(Context context, String name){
        String json = null;
        try{
            InputStream is = context.getAssets().open(name + ".json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch(IOException ex){
            return null;
        }
        return json;
    }

    public ArrayList<String> loadCharNamesFromAsset(Context context) throws IOException {
        String line = "blank";
        InputStream is;
        BufferedReader reader;
        ArrayList<String> listOfChars = new ArrayList<>();


        try{
            is = context.getAssets().open("charnames.txt");
            reader = new BufferedReader(new InputStreamReader(is));
            while(line != null) {
                line = reader.readLine();
                if(line == null) {
                    break;
                }
                listOfChars.add(line);
                Log.d(TAG, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfChars;
    }

    private void writeToFile(String data, Context context, String fileName) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName + ".txt", Context.MODE_APPEND));
            outputStreamWriter.write(data + ",\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}



