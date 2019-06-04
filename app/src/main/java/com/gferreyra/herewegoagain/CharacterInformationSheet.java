package com.gferreyra.herewegoagain;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gferreyra.herewegoagain.ui.character_sheet.CharacterSectionsPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class CharacterInformationSheet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_info_sheet);
        CharacterSectionsPageAdapter sectionsPageAdapter = new CharacterSectionsPageAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.character_info_view_pager);
        viewPager.setAdapter(sectionsPageAdapter);
        TabLayout tabs = findViewById(R.id.character_info_tabs);
        tabs.setupWithViewPager(viewPager);

        //get character name and set as title
        String title = getIntent().getExtras().getString("name");
        setTitle(title);

        //getSupportActionBar().hide(); // example of how to hide Action bar title
    }
}
