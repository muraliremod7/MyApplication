package com.omnispace.marketing.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.omnispace.marketing.Activities.HostelActivity;
import com.omnispace.marketing.Activities.PendingActivity;
import com.omnispace.marketing.Activities.RejectedActivity;
import com.omnispace.marketing.Data.HostelListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<HostelListModel> modellist;
    ArrayList<HostelListModel> arrayList;
    public ListViewAdapter(Context context, List<HostelListModel> modellist) {
        mContext = context;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<HostelListModel>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder {
        TextView mNameTv, mPhonenumberTv, mLoactionTv;
        ImageView mSpinnerIv;

    }

    @Override
    public int getCount() {
        return modellist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int postition, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.hst_list_row_item, null);
            holder.mNameTv = view.findViewById(R.id.nametxthostel);
            holder.mPhonenumberTv = view.findViewById(R.id.phnnum);
            holder.mLoactionTv = view.findViewById(R.id.txtLoactionhstl);
            holder.mSpinnerIv = view.findViewById(R.id.mainIcon);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();

        }

        holder.mNameTv.setText(modellist.get(postition).getName());
        holder.mPhonenumberTv.setText(modellist.get(postition).getPhonenumber());
        holder.mLoactionTv.setText(modellist.get(postition).getLocation());
        holder.mSpinnerIv.setImageResource(modellist.get(postition).getIcon());

        holder.mSpinnerIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog alertDialog = new AlertDialog.Builder(
                        mContext).create();


                alertDialog.setButton2("Pending", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       Intent intent = new Intent(mContext,PendingActivity.class);
                        mContext.startActivity(intent);


                    }


                });
                alertDialog.setButton("Rejected", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext, RejectedActivity.class);
                        mContext.startActivity(intent);


                    }

                });
                alertDialog.setButton3("Form", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext, HostelActivity.class);
                        mContext.startActivity(intent);

                    }

                });
             alertDialog.show();


            }
        });
        return view;
    }
}








