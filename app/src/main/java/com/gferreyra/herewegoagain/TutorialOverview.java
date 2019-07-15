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

//Activity that changes to fit which item was selected in the Tutorial Menu
//Based off what was selected, images and text will be changed to fit accordingly
//Instead of creating an activity for each unique option that can be selected in the tutorial menu, I am reusing the same activity and
//just checking what option was selected in the previous activity, then setting the content accordingly
public class TutorialOverview extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private TextView customTitle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.tutorial_overview);

        //sets custom toolbar
        toolbar = findViewById(R.id.tutorial_toolbar);
        setSupportActionBar(toolbar);

        //sets title in the custom toolbar
        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);

        textView = findViewById(R.id.tutorial_text);
        imageView = findViewById(R.id.tutorial_image);

        changeTitleFont(); //changes title font to custom font

        //Checks what the previously selected option in the activity before this then sets the image/text content accordingly
        //"News" selected
        if(title.equals("News")){
            textView.setText(R.string.nocurrent);
            imageView.setImageResource(R.drawable.tekken7title);
        }

        //DEFAULT
        else{
            textView.setText("Yo heres the test");
            imageView.setImageResource(R.drawable.tempart);
        }

    }

    //Changes title font to a custom font style
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
