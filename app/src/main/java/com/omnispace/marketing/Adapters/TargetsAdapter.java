package com.omnispace.marketing.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omnispace.marketing.R;
import com.omnispace.marketing.commonclass.TargetsDAO;
import com.omnispace.marketing.helper.AlertDialogManager;
import com.omnispace.marketing.helper.ProgressDailog;

import java.util.ArrayList;

public class TargetsAdapter extends RecyclerView.Adapter<TargetsAdapter.TargetsViewHolder>  {
    private Context context;
    private ProgressDailog progressDailog;
    private AlertDialogManager alertDialogManager;
    private String targetId;
    public static class TargetsViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView target_name;
        TextView target_number;
        TextView target_location;
        TextView target_type;
        ImageView target_update,target_delete;

        TargetsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card);
            target_name = (TextView)itemView.findViewById(R.id.target_name);
            target_number = (TextView)itemView.findViewById(R.id.target_number);
            target_location = (TextView)itemView.findViewById(R.id.target_location);
            target_type = (TextView)itemView.findViewById(R.id.target_type);
            target_update = (ImageView)itemView.findViewById(R.id.target_update);
            target_delete = (ImageView)itemView.findViewById(R.id.target_delete);
        }
    }
    ArrayList<TargetsDAO> targetsDAOS;

    public TargetsAdapter(ArrayList<TargetsDAO> targetsDAOS,Context context){
        this.targetsDAOS = targetsDAOS;
        this.context = context;
        progressDailog = new ProgressDailog(context);
        alertDialogManager = new AlertDialogManager();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public TargetsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_target_layout, viewGroup, false);
        TargetsViewHolder pvh = new TargetsViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(TargetsViewHolder holder, int i) {
        TargetsDAO targetsDAO = targetsDAOS.get(i);
        holder.target_name.setText(targetsDAO.getTargetName());
        holder.target_number.setText(targetsDAO.getPhoneNum());
        holder.target_location.setText(targetsDAO.getTargetLocation());
        holder.target_type.setText(targetsDAO.getType());
        holder.target_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.target_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return targetsDAOS.size();
    }
}
