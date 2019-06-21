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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TekkenTutorialMenuAdapter extends RecyclerView.Adapter<TekkenTutorialMenuAdapter.ViewHolder>{
    private String TAG = "TekkenTutorialMenuAdapter";
    private ArrayList<String> mTitles = new ArrayList<>();
    private Context mContext;

    public TekkenTutorialMenuAdapter(ArrayList<String> mTitles, Context mContext){
        this.mTitles = mTitles;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public TekkenTutorialMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tekkentutorialitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.titleName.setText(mTitles.get(position));
        switch(mTitles.get(position)){
            case "Controls":
                holder.titleName.setBackgroundResource(R.drawable.alisa);
                break;
            case "Movement":
                holder.titleName.setBackgroundResource(R.drawable.anna);
                break;
            case "Spacing":
                holder.titleName.setBackgroundResource(R.drawable.kazumi);
                break;
            case "Offense":
                holder.titleName.setBackgroundResource(R.drawable.julia);
                break;
            case "Defense":
                holder.titleName.setBackgroundResource(R.drawable.eliza);
                break;
            case "Punishing":
                holder.titleName.setBackgroundResource(R.drawable.jin);
                break;
            case "Poking":
                holder.titleName.setBackgroundResource(R.drawable.josie);
                break;
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTitles.get(position).equals("Controls")){
                    Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();
                }

                if(mTitles.get(position).equals("Movement")){
                    Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();
                }

                if(mTitles.get(position).equals("Spacing")){
                    Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleName = itemView.findViewById(R.id.tekken_textview);
            parentLayout = itemView.findViewById(R.id.tekken_parent_layout);
        }
    }
}
