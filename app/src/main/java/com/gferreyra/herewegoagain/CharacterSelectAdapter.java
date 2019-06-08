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

public class CharacterSelectAdapter extends RecyclerView.Adapter<CharacterSelectAdapter.ViewHolder> {
    private static final String TAG = "CharacterSelectAdapter";
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private Intent mIntent;
    private String menuTitle;

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

    @NonNull
    @Override
    public CharacterSelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_characterselectitem, parent, false);
        CharacterSelectAdapter.ViewHolder holder = new CharacterSelectAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterSelectAdapter.ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        String img = mImages.get(i).toString();
        Glide.with(mContext)
                .asBitmap()
                .load("")
                .placeholder(mContext.getResources().getIdentifier(img.toLowerCase(), "drawable", mContext.getPackageName()))
                .into(viewHolder.image);

        viewHolder.imageName.setText(mImageNames.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mImageNames.get(i), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "menuTitle is: " + menuTitle);
                    if(menuTitle.equals("Character Overviews")){
                        Intent myIntent = new Intent(mContext, CharacterInformationSheet.class);
                        myIntent.putExtra("name", mImageNames.get(i));
                        mContext.startActivity(myIntent);
                    }

                    if(menuTitle.equals("Frame Data")){
                        Intent myIntent = new Intent(mContext, FrameDataTable.class);
                        myIntent.putExtra("name", mImageNames.get(i));
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
        ImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cs_image);
            imageName = itemView.findViewById(R.id.cs_image_name);
            parentLayout = itemView.findViewById(R.id.cs_parent_layout);
        }
    }
}
