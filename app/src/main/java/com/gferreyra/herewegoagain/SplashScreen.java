package com.gferreyra.herewegoagain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmCollection;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.SyncManager;
import io.realm.SyncSession;


public class SplashScreen extends AppCompatActivity {

    private String TAG = "SplashScreen";
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();
    private Realm realm;
    private RealmConfiguration realmConfig;
    private boolean status = false;

    //Creates SplashScreen activity that stays as long as the database is loading
    //Will create database is it does not previously exist(so usually only on first launch or if database has been updated)
    //Loads data from JSON and .txt files into database for each character in order for it to persist in a local Realm database
    //If database is already created, then will skip and end, loading the next activity the Main Menu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.progress_text);

        //GETS Realm instance which was created in the CustomApplication class and was loaded when application launched
        //realm is how we access database for insertion/deletion/queries
        realm = Realm.getDefaultInstance();

        //Method to read from file to get all filenames/characternames and initialize/populate a String array with them
        final ArrayList<String> allCharNames = getAllCharacterNames();
        Log.d(TAG, "Loaded character names");

        //Creating Realm Database if it does not exist
        if (realm.isEmpty()) {
            for (int i = 0; i < allCharNames.size(); i++) {
                Log.d(TAG, "Creating Realm Database");
                JSONObject charObj = createJSONObjectForChar(allCharNames.get(i));
                addJSONObjectToDatabase(charObj, allCharNames.get(i));
            }
        }

        Log.d(TAG, "Database completed");

        //Queries database for the tables in the database
        //these variables(charDatabase and basicDatabase) act as a link to the database and allow us to query those specific database tables in real time
        //The first table is the CharacterData table and second is the BasicMoves table
        RealmResults<CharacterData> charDatabase = getCharacterDatabase();
        RealmResults<BasicMoves> basicDatabase = getBasicMovesDatabase();

        realm.close(); //close database instance


        //DONE loading everything so set Status flag to true
        status = true;

        //Adding delay so splashscreen stays up for a small amount of time then switching to MainMenu activity
        if(status) {
            //separate Thread in order to allow splash screen to show at least for a small period of time, since if database is loaded then it will instantly
            //and start main menu activity without having enough time to show splash screen to user
            new Thread(new Runnable() {
                public void run() {
                    try {
                        // Sleep for 1000 milliseconds.
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Starts MainMenu activity
                    Intent myIntent = new Intent(SplashScreen.this, MainMenu.class);
                    SplashScreen.this.startActivity(myIntent);
                    Log.d(TAG, "we get here");
                    finish();
                }
            }).start();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }

    //Method used to load a JSON file from Assets directory and convert it to String
    //Will be used to load the JSON files into Strings for conversion into JSON Objects for future use
    public String loadJSONFromAsset(Context context, String name) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(name + ".json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            return null;
        }
        return json;
    }

    //Method to load all character names from a .txt file in Assets folder
    //Will be used to keep an ArrayList of all character names for future use
    public ArrayList<String> loadCharNamesFromAsset(Context context) throws IOException {
        String line = "blank";
        InputStream is;
        BufferedReader reader;
        ArrayList<String> listOfChars = new ArrayList<>();

        try {
            is = context.getAssets().open("charnames.txt");
            reader = new BufferedReader(new InputStreamReader(is));
            while (line != null) {
                line = reader.readLine();
                if (line == null) {
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


    //Method to get ArrayList containing all character names
    private ArrayList<String> getAllCharacterNames() {
        ArrayList<String> charNames = null;
        try {
            charNames = loadCharNamesFromAsset(this);
            Log.d(TAG, Integer.toString(charNames.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charNames;
    }

    //Returns JSONObject for a particular character's frame data
    private JSONObject createJSONObjectForChar(String charName) {
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

    //Saves the JSONObject of a character to the database
    private void addJSONObjectToDatabase(JSONObject jObj, final String name) {
        //POPULATE DB WITH JSON STRING
        Log.d(TAG, "Attempting to execute Transactions");
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    Log.d(TAG, "Loading string variable with JSON String for character");
                    String jString = loadJSONFromAsset(getApplicationContext(), name);
                    JSONObject jObj = new JSONObject(jString);
                    realm.createOrUpdateObjectFromJson(CharacterData.class, jObj);
                    Log.d(TAG, "DONE WITH TRANSACTION");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Queries database for CharacterData table
    private RealmResults<CharacterData> getCharacterDatabase(){
        RealmQuery<CharacterData> query = realm.where(CharacterData.class);
        RealmResults<CharacterData> result = query.findAll();
        return result;
    }

    //Queries database for BasicMoves table
    private RealmResults<BasicMoves> getBasicMovesDatabase(){
        RealmQuery<BasicMoves> query = realm.where(BasicMoves.class);
        RealmResults<BasicMoves> result = query.findAll();
        return result;
    }
}

