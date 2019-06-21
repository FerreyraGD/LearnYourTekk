package com.gferreyra.herewegoagain;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

public class TutorialOverview extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private TextView customTitle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.tutorial_overview);

        toolbar = findViewById(R.id.tutorial_toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);

        textView = findViewById(R.id.tutorial_text);
        imageView = findViewById(R.id.tutorial_image);

        textView.setText("Yo heres the test");
        imageView.setImageResource(R.drawable.tempart);

        changeTitleFont();

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
