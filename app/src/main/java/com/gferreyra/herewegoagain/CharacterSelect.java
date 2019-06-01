package com.gferreyra.herewegoagain;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CharacterSelect extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_select_menu);

        initImageBitmaps();
    }

    private void initImageBitmaps() {
        mNames.add("Akuma");
        mImageUrls.add("@drawable/");

        mNames.add("Alisa");
        mImageUrls.add("@drawable/");

        mNames.add("Anna");
        mImageUrls.add("@drawable/");

        mNames.add("Armor King");
        mImageUrls.add("@drawable/");

        mNames.add("Asuka");
        mImageUrls.add("@drawable/");

        mNames.add("Bob");
        mImageUrls.add("@drawable/");

        mNames.add("Bryan");
        mImageUrls.add("@drawable/");

        mNames.add("Claudio");
        mImageUrls.add("@drawable/");

        mNames.add("Devil Jin");
        mImageUrls.add("@drawable/");


        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_character_select);
        CharacterSelectAdapter adapter = new CharacterSelectAdapter(mNames, mImageUrls, this, this.getIntent());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
    }
}
