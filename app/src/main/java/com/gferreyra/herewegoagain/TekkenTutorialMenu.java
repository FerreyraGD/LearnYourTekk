package com.gferreyra.herewegoagain;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TekkenTutorialMenu extends Activity {
    private ArrayList<String> mTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.tekken_tutorial_menu);

        initBitmaps();
    }

    private void initBitmaps() {
        //Add titles of the tutorial menu
        mTitles.add("Controls");
        mTitles.add("Movement");
        mTitles.add("Spacing");
        mTitles.add("Offense");
        mTitles.add("Defense");
        mTitles.add("Punishing");
        mTitles.add("Poking");

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.tekken_recycler);
        TekkenTutorialMenuAdapter adapter = new TekkenTutorialMenuAdapter(mTitles, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
