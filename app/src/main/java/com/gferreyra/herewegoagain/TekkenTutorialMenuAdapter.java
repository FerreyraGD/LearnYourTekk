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

//CURRENTLY WORKING ON
//Custom Adapter to initalize/create recyclerview and populate it with options in the menu for the user to select
public class TekkenTutorialMenuAdapter extends RecyclerView.Adapter<TekkenTutorialMenuAdapter.ViewHolder>{
    private String TAG = "TekkenTutorialMenuAdapter";
    private ArrayList<String> mTitles = new ArrayList<>();
    private Context mContext;

    //Constructor
    public TekkenTutorialMenuAdapter(ArrayList<String> mTitles, Context mContext){
        this.mTitles = mTitles;
        this.mContext = mContext;
    }


    //ViewHolder that populates holder with views using the custom layout for items in the tutorial menu
    @NonNull
    @Override
    public TekkenTutorialMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tekkentutorialitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //Sets images and text for each item in the recyclerView tutorial menu
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.titleName.setText(mTitles.get(position)); //sets title name

        //set image of each item in the tutorial menu
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

        //Detects if/when a user selects an item in the list of the tutorial menu and starts the SAME reused activity(TutorialOverview)
        //passing the name of the selected option to the new activity
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTitles.get(position).equals("Controls")){
                    Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(mContext, TutorialOverview.class);
                    myIntent.putExtra("title", mTitles.get(position));
                    mContext.startActivity(myIntent);

                }

                if(mTitles.get(position).equals("Movement")){
                    Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(mContext, TutorialOverview.class);
                    myIntent.putExtra("title", mTitles.get(position));
                    mContext.startActivity(myIntent);
                }

                if(mTitles.get(position).equals("Spacing")){
                    Toast.makeText(mContext, mTitles.get(position), Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(mContext, TutorialOverview.class);
                    myIntent.putExtra("title", mTitles.get(position));
                    mContext.startActivity(myIntent);
                }
            }
        });

    }

    //sets how many items appear in recyclerview
    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    //Custom ViewHolder class that holds the items in the recyclerview
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
