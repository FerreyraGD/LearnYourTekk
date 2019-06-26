package com.gferreyra.herewegoagain;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
                textView.setText(R.string.alisa_overview);
                imageView.setImageResource(R.drawable.alisa);
                break;
            case "Anna":
                textView.setText(R.string.anna_overview);
                imageView.setImageResource(R.drawable.anna);
                break;
            case "ArmorKing":
                textView.setText(R.string.armorking_overview);
                imageView.setImageResource(R.drawable.armorking);
                break;
            case "Asuka":
                textView.setText(R.string.asuka_overview);
                imageView.setImageResource(R.drawable.asuka);
                break;
            case "Bob":
                textView.setText(R.string.bob_overview);
                imageView.setImageResource(R.drawable.bob);
                break;
            case "Bryan":
                textView.setText(R.string.bryan_overview);
                imageView.setImageResource(R.drawable.bryan);
                break;
            case "Claudio":
                textView.setText(R.string.claudio_overview);
                imageView.setImageResource(R.drawable.claudio);
                break;
            case "DevilJin":
                textView.setText(R.string.deviljin_overview);
                imageView.setImageResource(R.drawable.deviljin);
                break;
            case "Dragunov":
                textView.setText(R.string.dragunov_overview);
                imageView.setImageResource(R.drawable.dragunov);
                break;
            case "Eddy":
                textView.setText(R.string.eddy_overview);
                imageView.setImageResource(R.drawable.eddy);
                break;
            case "Eliza":
                textView.setText(R.string.eliza_overview);
                imageView.setImageResource(R.drawable.eliza);
                break;
            case "Feng":
                textView.setText(R.string.feng_overview);
                imageView.setImageResource(R.drawable.feng);
                break;
            case "Geese":
                textView.setText(R.string.geese_overview);
                imageView.setImageResource(R.drawable.geese);
                break;
            case "Gigas":
                textView.setText(R.string.gigas_overview);
                imageView.setImageResource(R.drawable.gigas);
                break;
            case "Heihachi":
                textView.setText(R.string.heihachi_overview);
                imageView.setImageResource(R.drawable.heihachi);
                break;
            case "Hwoarang":
                textView.setText(R.string.hwoarang_overview);
                imageView.setImageResource(R.drawable.hwoarang);
                break;
            case "Jack7":
                textView.setText(R.string.jack7_overview);
                imageView.setImageResource(R.drawable.jack7);
                break;
            case "Jin":
                textView.setText(R.string.jin_overview);
                imageView.setImageResource(R.drawable.jin);
                break;
            case "Josie":
                textView.setText(R.string.josie_overview);
                imageView.setImageResource(R.drawable.josie);
                break;
            case "Julia":
                textView.setText(R.string.julia_overview);
                imageView.setImageResource(R.drawable.julia);
                break;
            case "Katarina":
                textView.setText(R.string.katarina_overview);
                imageView.setImageResource(R.drawable.katarina);
                break;
            case "Kazumi":
                textView.setText(R.string.kazumi_overview);
                imageView.setImageResource(R.drawable.kazumi);
                break;
            case "Kazuya":
                textView.setText(R.string.kazuya_overview);
                imageView.setImageResource(R.drawable.kazuya);
                break;
            case "King":
                textView.setText(R.string.king_overview);
                imageView.setImageResource(R.drawable.king);
                break;
            case "Kuma":
                textView.setText(R.string.kuma_overview);
                imageView.setImageResource(R.drawable.kuma);
                break;
            case "Lars":
                textView.setText(R.string.lars_overview);
                imageView.setImageResource(R.drawable.lars);
                break;
            case "Law":
                textView.setText(R.string.law_overview);
                imageView.setImageResource(R.drawable.law);
                break;
            case "Lee":
                textView.setText(R.string.lee_overview);
                imageView.setImageResource(R.drawable.lee);
                break;
            case "Lei":
                textView.setText(R.string.lei_overview);
                imageView.setImageResource(R.drawable.lei);
                break;
            case "Leo":
                textView.setText(R.string.leo_overview);
                imageView.setImageResource(R.drawable.leo);
                break;
            case "Lili":
                textView.setText(R.string.lili_overview);
                imageView.setImageResource(R.drawable.lili);
                break;
            case "LuckyChloe":
                textView.setText(R.string.luckychloe_overview);
                imageView.setImageResource(R.drawable.luckychloe);
                break;
            case "Marduk":
                textView.setText(R.string.marduk_overview);
                imageView.setImageResource(R.drawable.marduk);
                break;
            case "MasterRaven":
                textView.setText(R.string.masterraven_overview);
                imageView.setImageResource(R.drawable.masterraven);
                break;
            case "Miguel":
                textView.setText(R.string.miguel_overview);
                imageView.setImageResource(R.drawable.miguel);
                break;
            case "Negan":
                textView.setText(R.string.negan_overview);
                imageView.setImageResource(R.drawable.negan);
                break;
            case "Nina":
                textView.setText(R.string.nina_overview);
                imageView.setImageResource(R.drawable.nina);
                break;
            case "Noctis":
                textView.setText(R.string.noctis_overview);
                imageView.setImageResource(R.drawable.noctis);
                break;
            case "Panda":
                textView.setText(R.string.panda_overview);
                imageView.setImageResource(R.drawable.panda);
                break;
            case "Paul":
                textView.setText(R.string.paul_overview);
                imageView.setImageResource(R.drawable.paul);
                break;
            case "Shaheen":
                textView.setText(R.string.shaheen_overview);
                imageView.setImageResource(R.drawable.shaheen);
                break;
            case "Steve":
                textView.setText(R.string.steve_overview);
                imageView.setImageResource(R.drawable.steve);
                break;
            case "Xiaoyu":
                textView.setText(R.string.xiaoyu_overview);
                imageView.setImageResource(R.drawable.xiaoyu);
                break;
            case "Yoshimitsu":
                textView.setText(R.string.yoshimitsu_overview);
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