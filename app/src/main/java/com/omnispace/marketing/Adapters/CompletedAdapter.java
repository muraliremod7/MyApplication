package com.omnispace.marketing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.omnispace.marketing.Data.CompletedListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;
import java.util.List;

public class CompletedAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<CompletedListModel> commodellist;
    ArrayList<CompletedListModel> comarrayList;
    public CompletedAdapter(Context context, List<CompletedListModel> commodellist) {
        mContext = context;
        this.commodellist = commodellist;
        inflater = LayoutInflater.from(mContext);
        this.comarrayList = new ArrayList<CompletedListModel>();
        this.comarrayList.addAll(commodellist);
    }

    public class ViewHolder {
        TextView mNameTv, mPhonenumberTv, mLoactionTv,mdateTv;


    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
       CompletedAdapter.ViewHolder holder;
        if (view == null) {
            holder = new CompletedAdapter.ViewHolder();
            view = inflater.inflate(R.layout.completed_row_item, null);
            holder.mNameTv = view.findViewById(R.id.nametxthostelcom);
            holder.mPhonenumberTv = view.findViewById(R.id.phnnumcom);
            holder.mLoactionTv = view.findViewById(R.id.txtLoactionhstlcom);
            holder.mdateTv = view.findViewById(R.id.txtcomdate);
            view.setTag(holder);

        } else {
            holder = (CompletedAdapter.ViewHolder) view.getTag();

        }

        holder.mNameTv.setText(commodellist.get(position).getName());
        holder.mPhonenumberTv.setText(commodellist.get(position).getPhonenumber());
        holder.mLoactionTv.setText(commodellist.get(position).getLocation());
        holder.mdateTv.setText(commodellist.get(position).getDate());
        return null;
    }
}
