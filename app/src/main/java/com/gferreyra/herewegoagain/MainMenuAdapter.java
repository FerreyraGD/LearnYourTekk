package com.gferreyra.herewegoagain;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder>{
    private static final String TAG = "MainMenuAdapter";
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private Intent mIntent;

    public MainMenuAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
    }

    public MainMenuAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext, Intent myIntent) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
        this.mIntent = mIntent;
    }

    @NonNull
    @Override
    public MainMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mainmenuitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        String img = mImages.get(i).toString();
        Glide.with(mContext)
                .asBitmap()
                .load("")
                .placeholder(mContext.getResources().getIdentifier(img, "drawable", mContext.getPackageName()))
                .into(viewHolder.image);

        viewHolder.imageName.setText(mImageNames.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mImageNames.get(i), Toast.LENGTH_SHORT).show();

                if(mImageNames.get(i).equals("Character Overviews")){
                    Intent myIntent = new Intent(mContext, CharacterSelect.class);
                    myIntent.putExtra("menuTitle", "Character Overviews");
                    //myIntent.putExtra("allCharacterNames", allCharacterNames);
                    mContext.startActivity(myIntent);
                }

                if(mImageNames.get(i).equals("Frame Data")){
                    Intent myIntent = new Intent(mContext, CharacterSelect.class);
                    myIntent.putExtra("menuTitle", "Frame Data");
                    //myIntent.putExtra("allCharacterNames", allCharacterNames);
                    mContext.startActivity(myIntent);
                }

                if(mImageNames.get(i).equals("Tekken 7\n Fundamentals")){
                    Intent myIntent = new Intent(mContext, TekkenTutorialMenu.class);
                    mContext.startActivity(myIntent);
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return mImageNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.mm_image);
            imageName = itemView.findViewById(R.id.mm_image_name);
            parentLayout = itemView.findViewById(R.id.mm_parent_layout);
        }
    }
}
