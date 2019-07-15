package com.gferreyra.herewegoagain;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

//Adapter for RecyclerView in Character Select Activity
public class CharacterSelectAdapter extends RecyclerView.Adapter<CharacterSelectAdapter.ViewHolder> {
    private static final String TAG = "CharacterSelectAdapter";
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private Intent mIntent;
    private String menuTitle;

    //Constructors
    public CharacterSelectAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
    }

    public CharacterSelectAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext, Intent intent, String menuTitle) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
        this.mIntent = mIntent;
        this.menuTitle = menuTitle;
    }

    //Creates viewHolder that loads custom layout for each item in recyclerview grid list and loads it into holder
    @NonNull
    @Override
    public CharacterSelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_characterselectitem, parent, false);
        CharacterSelectAdapter.ViewHolder holder = new CharacterSelectAdapter.ViewHolder(view);
        return holder;
    }

    //Loads the image into the custom circular imageview and sets text as character's name
    @Override
    public void onBindViewHolder(@NonNull CharacterSelectAdapter.ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        //get character's name
        String img = mImages.get(i).toString();

        //sets image of character to circular imageview
        Glide.with(mContext)
                .asBitmap()
                .fitCenter()
                .centerCrop()
                .load(mContext.getResources().getIdentifier("avatar_" + img.toLowerCase(), "drawable", mContext.getPackageName()))
                //.placeholder(mContext.getResources().getIdentifier("avatar_" + img.toLowerCase(), "drawable", mContext.getPackageName()))
                .into(viewHolder.image);

        //sets text to be character's name
        viewHolder.imageName.setText(mImageNames.get(i));

        //WHEN CLICKED calls activity based on what was clicked in PREVIOUS menu
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.d(TAG, "menuTitle is: " + menuTitle);

                    //IF Character Overviews was selected in main menu previously, then will move to CharacterOverview activity
                    if(menuTitle.equals("Character Overviews")){
                        Intent myIntent = new Intent(mContext, CharacterOverview.class);
                        myIntent.putExtra("name", mImageNames.get(i)); //pass name of character to new activity
                        mContext.startActivity(myIntent);
                    }

                    //IF Frame Data was selected in main menu previously, then will move to FrameDataTable activity
                    if(menuTitle.equals("Frame Data")){
                        Intent myIntent = new Intent(mContext, FrameDataTable.class);
                        myIntent.putExtra("name", mImageNames.get(i)); //pass name of character to new activity
                        mContext.startActivity(myIntent);
                    }
            }
        });

    }
    //Sets how many views get inserted into the recyclerView
    @Override
    public int getItemCount() {
        return mImageNames.size();
    }


    //Custom ViewHolder that is used as a container to load the image and character name into recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        //constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cs_image);
            imageName = itemView.findViewById(R.id.cs_image_name);
            parentLayout = itemView.findViewById(R.id.cs_parent_layout);
        }
    }
}
