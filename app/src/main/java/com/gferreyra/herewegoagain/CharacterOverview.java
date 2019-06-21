package com.gferreyra.herewegoagain;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
    private Map<String, Integer> stringForResourceIDMap = createMap();
    private  TextView textView;
    private Drawable drawable;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_overview);

        Toolbar toolbar = findViewById(R.id.overview_toolbar);
        setSupportActionBar(toolbar);


        String name = getIntent().getStringExtra("name");
        Log.d(TAG, "name is: " + name);

        getSupportActionBar().setTitle(name);
        textView = findViewById(R.id.overview_text);
        imageView = findViewById(R.id.overview_image);


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


    private Map<String, Integer> createMap() {
        Map<String, Integer> result = new HashMap<>();

        result.put("Akuma", R.string.akuma_overview);
        /*
        result.put("Alisa", R.string.alisa_overview);
        result.put("Anna", R.string.anna_overview);
        result.put("ArmorKing", R.string.armorking_overview);
        result.put("Asuka", R.string.asuka_overview);
        result.put("Bob", R.string.bob_overview);
        result.put("Bryan", R.string.bryan_overview);
        result.put("Claudio", R.string.claudio_overview);
        result.put("DevilJin", R.string.deviljin_overview);
        result.put("Dragunov", R.string.dragunov_overview);
        result.put("Eddy", R.string.eddy_overview);
        result.put("Eliza", R.string.eliza_overview);
        result.put("Feng", R.string.feng_overview);
        result.put("Geese", R.string.geese_overview);
        result.put("Gigas", R.string.gigas_overview);
        result.put("Heihachi", R.string.heihachi_overview);
        result.put("Hwoarang", R.string.hwoarang_overview);
        result.put("Jack7", R.string.jack7_overview);
        result.put("Jin", R.string.jin_overview);
        result.put("Josie", R.string.josie_overview);
        result.put("Julia", R.string.julia_overview);
        result.put("Katarina", R.string.katarina_overview);
        result.put("Kazumi", R.string.kazumi_overview);
        result.put("Kazuya", R.string.kazuya_overview);
        result.put("King", R.string.king_overview);
        result.put("Kuma", R.string.kuma_overview);
        result.put("Lars", R.string.lars_overview);
        result.put("Law", R.string.law_overview);
        */


        return result;
    }

    private String getMyStringResource(String lookUpString){
        int resourceID = stringForResourceIDMap.get(lookUpString);
        return getString(resourceID);
    }


}