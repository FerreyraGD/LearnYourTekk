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

    //creates Activity, sets view and creates or loads database using progress bar to track status
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.progress_text);



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

        realm = Realm.getDefaultInstance();

        //Method to read from file to get all filenames and initialize/populate a String array
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

        //TESTING IF DATABASE OBJECT CAN BE PASSED AND REALM WILL NEVER HAVE TO BE CALLED AGAIN IF IT PERSISTS
        //IT CAN OMG!!!
        //These objects will be passed through intents until they are needed to be used to acquire database information
        RealmResults<CharacterData> charDatabase = getCharacterDatabase();
        RealmResults<BasicMoves> basicDatabase = getBasicMovesDatabase();

        //testing character info
        CharacterData character = charDatabase.where().equalTo("name", "Akuma").findFirst();
        Log.d(TAG, character.getName() + " " + character.getDifficulty());

        //testing Akuma's moves
        //EXAMPLE OF HOW TO QUERY FOR SPECIAL MOVES (I.E HOMING, TAIL SPIN ETC)
        RealmResults<BasicMoves> charMoves = basicDatabase.where().equalTo("character.name", "Akuma").and().isNotNull("notes").findAll();
        RealmResults<BasicMoves> rageMoves = charMoves.where().contains("notes", "homing", Case.INSENSITIVE).findAll();
        for(int i = 0; i < rageMoves.size(); i++){
            Log.d(TAG, rageMoves.get(i).toString());
        }


        //DONE loading everything
        status = true;

        //Adding delay so splashscreen stays up for a small amount of time then switching to MainMenu activity
        if(status == true) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Intent myIntent = new Intent(SplashScreen.this, MainMenu.class);
                    //TODO myIntent.putExtra("framedata", variable);
                    //SplashScreen.this.startActivity(myIntent);
                }
            }).start();
        }


        /*
        //PROGRESS BAR
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //Intent myIntent = new Intent(SplashScreen.this, MainMenu.class);
                //TODO myIntent.putExtra("framedata", variable);
                //SplashScreen.this.startActivity(myIntent);
            }
        }).start();
        */

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
                    //Log.d(TAG, "Getting Asset Manager");
                    //AssetManager am = getAssets();
                    //String path = getFilesDir().getAbsolutePath();
                    Log.d(TAG, "Loading string variable with JSON String for character");
                    //InputStream is = am.open("Akuma.json");
                    //InputStream is = new FileInputStream(getAssets().open("Akuma.json"));
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

    //OBSOLETE METHOD
    //NO LONGER USING THE EXTRA ATTRIBUTES TO JUDGE THE STATUSES IN THE NOTES SECTION
    //TODO WILL EVENTUALLY DELETE
    private void updateNoteValues(ArrayList<String> charNames, String status){
        //EXAMPLE OF HOW TO QUERY LINKED OBJECTS IN REALM DATABASE
        for(int k = 0; k < charNames.size(); k++) {
            RealmQuery<CharacterData> query = realm.where(CharacterData.class);
            RealmResults<BasicMoves> result = realm.where(BasicMoves.class).equalTo("character.name", charNames.get(k)).and().isNotNull("notes").findAll();
            RealmResults<BasicMoves> secondResult = result.where().contains("notes", status, Case.INSENSITIVE).findAll();

            realm.beginTransaction();
            if (secondResult.size() != 0) {
                for (int i = 0; i < secondResult.size(); i++) {
                    if(status.equalsIgnoreCase("Rage art")) {
                        secondResult.get(i).setRage_art("Yes");
                    }

                    if(status.equalsIgnoreCase("Rage Drive")){
                        secondResult.get(i).setRage_drive("Yes");
                    }

                    if(status.equalsIgnoreCase("Tail Spin")){
                        secondResult.get(i).setTail_spin("Yes");
                    }

                    if(status.equalsIgnoreCase("Homing")){
                        secondResult.get(i).setHoming("Yes");
                    }

                    if(status.equalsIgnoreCase("Power Crush")){
                        secondResult.get(i).setPower_crush("Yes");
                    }

                    if(status.equalsIgnoreCase("Wall Bounce")){
                        secondResult.get(i).setWall_bounce("Yes");
                    }

                    if(status.equalsIgnoreCase("Wall Break")){
                        secondResult.get(i).setWall_break("Yes");
                    }
                }
            }
            realm.commitTransaction();

            Log.d(TAG, result.toString());
            Log.d(TAG, secondResult.toString());
        }
    }

    private RealmResults<CharacterData> getCharacterDatabase(){
        RealmQuery<CharacterData> query = realm.where(CharacterData.class);
        RealmResults<CharacterData> result = query.findAll();
        return result;
    }

    private RealmResults<BasicMoves> getBasicMovesDatabase(){
        RealmQuery<BasicMoves> query = realm.where(BasicMoves.class);
        RealmResults<BasicMoves> result = query.findAll();
        return result;
    }
}

