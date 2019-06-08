package com.gferreyra.herewegoagain;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.realm.RealmRecyclerViewAdapter;

public class MainMenu extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        /*
        recyclerView = (RecyclerView) findViewById(R.id.recycler_main);

        //using this setting to improve performance since changes in the content does not change the layout size of RecyclerView
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);

        mAdapter = new MainMenuAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
        */

        initImageBitmaps();
    }

    private void initImageBitmaps() {
        mNames.add("Character Overviews");
        mImageUrls.add("characterselect");

        mNames.add("Frame Data");
        mImageUrls.add("ninamenu");

        mNames.add("Tekken 7\n Fundamentals");
        mImageUrls.add("tekken7img");

        mNames.add("Frame Data \nExplained");
        mImageUrls.add("framedata");

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_main);
        MainMenuAdapter adapter = new MainMenuAdapter(mNames, mImageUrls, this, this.getIntent());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
