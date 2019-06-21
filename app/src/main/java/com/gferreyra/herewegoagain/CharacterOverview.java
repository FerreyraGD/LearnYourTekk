package com.gferreyra.herewegoagain;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CharacterOverview extends AppCompatActivity {

    private String TAG = "CharacterOverview";
    private  TextView textView;
    private Drawable drawable;
    private ImageView imageView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_overview);

        toolbar = findViewById(R.id.overview_toolbar);
        setSupportActionBar(toolbar);


        String name = getIntent().getStringExtra("name");
        Log.d(TAG, "name is: " + name);

        getSupportActionBar().setTitle(name);
        textView = findViewById(R.id.overview_text);
        imageView = findViewById(R.id.overview_image);
        changeTitleFont();


        switch(name){
            case "Akuma":
                textView.setText(R.string.akuma_overview);
                imageView.setImageResource(R.drawable.akuma);
                break;
            case "Alisa":
                imageView.setImageResource(R.drawable.alisa);
                break;
            case "Anna":
                imageView.setImageResource(R.drawable.anna);
                break;
            case "ArmorKing":
                imageView.setImageResource(R.drawable.armorking);
                break;
            case "Asuka":
                imageView.setImageResource(R.drawable.asuka);
                break;
            case "Bob":
                imageView.setImageResource(R.drawable.bob);
                break;
            case "Bryan":
                imageView.setImageResource(R.drawable.bryan);
                break;
            case "Claudio":
                imageView.setImageResource(R.drawable.claudio);
                break;
            case "DevilJin":
                imageView.setImageResource(R.drawable.deviljin);
                break;
            case "Dragunov":
                imageView.setImageResource(R.drawable.dragunov);
                break;
            case "Eddy":
                imageView.setImageResource(R.drawable.eddy);
                break;
            case "Eliza":
                imageView.setImageResource(R.drawable.eliza);
                break;
            case "Feng":
                imageView.setImageResource(R.drawable.feng);
                break;
            case "Geese":
                imageView.setImageResource(R.drawable.geese);
                break;
            case "Gigas":
                imageView.setImageResource(R.drawable.gigas);
                break;
            case "Heihachi":
                imageView.setImageResource(R.drawable.heihachi);
                break;
            case "Hwoarang":
                imageView.setImageResource(R.drawable.hwoarang);
                break;
            case "Jack7":
                imageView.setImageResource(R.drawable.jack7);
                break;
            case "Jin":
                imageView.setImageResource(R.drawable.jin);
                break;
            case "Josie":
                imageView.setImageResource(R.drawable.josie);
                break;
            case "Julia":
                imageView.setImageResource(R.drawable.julia);
                break;
            case "Katarina":
                imageView.setImageResource(R.drawable.katarina);
                break;
            case "Kazumi":
                imageView.setImageResource(R.drawable.kazumi);
                break;
            case "Kazuya":
                imageView.setImageResource(R.drawable.kazuya);
                break;
            case "King":
                imageView.setImageResource(R.drawable.king);
                break;
            case "Kuma":
                imageView.setImageResource(R.drawable.kuma);
                break;
            case "Lars":
                imageView.setImageResource(R.drawable.lars);
                break;
            case "Law":
                imageView.setImageResource(R.drawable.law);
                break;
            case "Lee":
                imageView.setImageResource(R.drawable.lee);
                break;
            case "Lei":
                imageView.setImageResource(R.drawable.lei);
                break;
            case "Leo":
                imageView.setImageResource(R.drawable.leo);
                break;
            case "Lili":
                imageView.setImageResource(R.drawable.lili);
                break;
            case "Ling":
                imageView.setImageResource(R.drawable.ling);
                break;
            case "LuckyChloe":
                imageView.setImageResource(R.drawable.luckychloe);
                break;
            case "Marduk":
                imageView.setImageResource(R.drawable.marduk);
                break;
            case "MasterRaven":
                imageView.setImageResource(R.drawable.masterraven);
                break;
            case "Miguel":
                imageView.setImageResource(R.drawable.miguel);
                break;
            case "Negan":
                imageView.setImageResource(R.drawable.negan);
                break;
            case "Nina":
                imageView.setImageResource(R.drawable.nina);
                break;
            case "Noctis":
                imageView.setImageResource(R.drawable.noctis);
                break;
            case "Panda":
                imageView.setImageResource(R.drawable.panda);
                break;
            case "Paul":
                imageView.setImageResource(R.drawable.paul);
                break;
            case "Shaheen":
                imageView.setImageResource(R.drawable.shaheen);
                break;
            case "Steve":
                imageView.setImageResource(R.drawable.steve);
                break;
            case "Yoshimitsu":
                imageView.setImageResource(R.drawable.yoshimitsu);
                break;
            default:
                textView.setText("DEFAULT TEXT WOAH");
                imageView.setImageResource(R.drawable.tempart);
                break;
        }
    }

    private void changeTitleFont(){
        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                if(tv.getText().equals(toolbar.getTitle())){
                    tv.setTypeface((Typeface.createFromAsset(this.getAssets(), "fonts/capture.ttf")));
                    break;
                }
            }
        }
    }


}