package com.gferreyra.herewegoagain;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CharacterSelect extends Activity {
    private String TAG = "CharacterSelect";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_select_menu);

        try {
            initImageBitmaps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //loads all 45 character's names and inserts them into ArrayLists for future use
    //and moves on to recyclerview method
    private void initImageBitmaps() throws IOException {
        ArrayList<String> charList = loadCharNamesFromAsset(this);
        for(int i = 0; i < charList.size(); i++){
            mNames.add(charList.get(i));
            mImageUrls.add(charList.get(i));
        }
        initRecyclerView();
    }

    //Creates and sets adapter for recyclerview
    //Populates recyclerview with images and names of characters in a grid list
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_character_select);
        String previousTitle = (String) getIntent().getExtras().get("menuTitle");
        Log.d(TAG, "PreviousTitle is: " + previousTitle);
        CharacterSelectAdapter adapter = new CharacterSelectAdapter(mNames, mImageUrls, this, getIntent(), previousTitle);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
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
}
