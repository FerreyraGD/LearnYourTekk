package com.gferreyra.herewegoagain;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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
                .schemaVersion(2)
                .build();
        Realm.setDefaultConfiguration(realmConfig);


        //used to destroy database
        //Realm.deleteRealm(realmConfig);

        Log.d(TAG, "Realm configured");

        realm = Realm.getDefaultInstance();
        Log.d(TAG, "just did Realm default instance thing");

        //realm.beginTransaction();






        //EXAMPLE of how to query for something in Realm Database
        /*
        RealmQuery<Character> query = realm.where(Character.class);

        query.equalTo("name", "jin");

        RealmResults<Character> result = query.findAll();
        */



        //END OF DATABASE SET UP


        //EXAMPLE OF A TEST WRITE TO DATABASE
        /*
        if(result.isEmpty()) {
            realm.beginTransaction();
            Log.d(TAG, "beginning transaction");

            Log.d(TAG, "creating Character object for jin");
            Character ch = realm.createObject(Character.class, "01");
            ch.setName("jin");
            ch.setDifficulty("Intermediate");
            realm.commitTransaction();
            name = "DATABASE BARELY CREATED";

        }

        else{
            Character woo = new Character();
            woo = result.get(0);
            name = woo.getName();
        }
        */

        //Method to read from file to get all filenames and initialize/populate a String array
        final ArrayList<String> allCharNames = getAllCharacterNames();


        //POPULATE JSON STRING FROM FILE AND CREATE JSON OBJECT
        //POPULATE DB WITH JSON STRING
        if(realm.isEmpty()) {
            for (int i = 0; i < allCharNames.size(); i++) {
                JSONObject charObj = createJSONObjectForChar(allCharNames.get(i));
                addJSONObjectToDatabase(charObj, allCharNames.get(i));
            }
        }

        /*
        //TODO EDIT ALL THE EXTRA ADDED ATTRITBUTES(RAGE_ART, HOMING, TAIL_SPIN, ETC) TO BE YES/NO BASED OFF NOTES SECTION
        //EXAMPLE OF HOW TO QUERY LINKED OBJECTS IN REALM DATABASE
        RealmQuery<CharacterData> query = realm.where(CharacterData.class);

        //query.equalTo("name", "Akuma");
       // RealmResults<CharacterData> result = query.findAll();

        RealmResults<BasicMoves> result = realm.where(BasicMoves.class).equalTo("character.name", "Akuma").and().isNotNull("notes").findAll();
        BasicMoves secondResult = result.where().equalTo("notes", "Rage art").findFirst();
        realm.beginTransaction();
        secondResult.setRage_art("Yes");
        realm.commitTransaction();

        Log.d(TAG, result.toString());
        Log.d(TAG, secondResult.toString());
        */

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        //CODE IS USED TO PULL FROM JSON FILES AND EDIT THEM WITH NEW ATTRIBUTES, DO NOT CURRENTLY NEED SINCE DATA IS UP TO DATE
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
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

        */
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
                String value = "jin";
                Intent myIntent = new Intent(MainActivity.this, SplashScreen.class);
                //myIntent.putExtra("name", value);
                myIntent.putExtra("allCharacterNames", allCharNames);
                MainActivity.this.startActivity(myIntent);
                /*
                Snackbar.make(view, "Here is the character: " + name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
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

    private ArrayList<String> getAllCharacterNames(){
        ArrayList<String> charNames = null;
        try {
            charNames = loadCharNamesFromAsset(this);
            Log.d(TAG, Integer.toString(charNames.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charNames;
    }

    private JSONObject createJSONObjectForChar(String charName){
        //POPULATE JSON STRING FROM FILE AND CREATE JSON OBJECT
        JSONObject jsonObj = null;
        String jString = loadJSONFromAsset(this, charName);

        try {
            jsonObj = new JSONObject(jString);
            //int size = jsonObj.getJSONArray("basicmoves").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    private void addJSONObjectToDatabase(JSONObject jObj, final String name){
        //POPULATE DB WITH JSON STRING
        Log.d(TAG, "Attempting to execute Transactions");
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    //Log.d(TAG, "Getting Asset Manager");
                    //AssetManager am = getAssets();
                    //String path = getFilesDir().getAbsolutePath();
                    Log.d(TAG, "Loading string variable with JSON String for character");
                    //InputStream is = am.open("akuma.json");
                    //InputStream is = new FileInputStream(getAssets().open("akuma.json"));
                    String jString = loadJSONFromAsset(getApplicationContext(), name);
                    JSONObject jObj = new JSONObject(jString);
                    Log.d(TAG, "Test 2");
                    //realm.createObjectFromJson(CharacterData.class, jObj);
                    realm.createOrUpdateObjectFromJson(CharacterData.class, jObj);
                    Log.d(TAG, "DONE WITH TRANSACTION");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}



