package com.gferreyra.herewegoagain;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.realm.RealmRecyclerViewAdapter;

//Main Menu where user can select different options
public class MainMenu extends Activity {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        //Initalizes process to populate recyclerview with images and text for each option in menu
        initImageBitmaps();
    }

    private void initImageBitmaps() {
        //THESE are the main menu options. Currently there are only 3 which the user can select, a forth is commented out to be possible added in future

        //mNames.add("Tekken 7\n Fundamentals");
        //mImageUrls.add("tekken7img");

        mNames.add("Frame Data");
        mImageUrls.add("ninamenu");

        mNames.add("Character Overviews");
        mImageUrls.add("characterselect");

       mNames.add("News");
       mImageUrls.add("tekken7img");

       //initalizes recyclerview, sets the adapter which sets the layout, image in imageview and text for each item in recyclerview
        //then sets a gridview and creates/shows recyclerview for the main menu to user
        initRecyclerView();
    }

    private void initRecyclerView() {
        //set recyclerview custom adapter
        RecyclerView recyclerView = findViewById(R.id.recycler_main);
        MainMenuAdapter adapter = new MainMenuAdapter(mNames, mImageUrls, this, this.getIntent());
        recyclerView.setAdapter(adapter);
        //sets grid layout with only 2 items per row
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
