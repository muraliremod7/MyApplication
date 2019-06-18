package com.omnispace.marketing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.omnispace.marketing.Data.RejectedListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;
import java.util.List;

public class RejectedListAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<RejectedListModel> modellistrejected;
    ArrayList<RejectedListModel> arrayListrejected;
    public RejectedListAdapter(Context context, List<RejectedListModel> modellistrejected) {
        mContext = context;
        this.modellistrejected = modellistrejected;
        inflater = LayoutInflater.from(mContext);
        this.arrayListrejected = new ArrayList<RejectedListModel>();
        this.arrayListrejected.addAll(modellistrejected);
    }

    public class ViewHolder {
        TextView rejectedName,rejectedLoaction,rejectedPhoneNumber,rejectedReason,rejectedDate;


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
        RejectedListAdapter.ViewHolder holder;
        if (view == null) {
            holder = new RejectedListAdapter.ViewHolder();
            view = inflater.inflate(R.layout.rejected_row_item, null);
            holder.rejectedName = view.findViewById(R.id.nametxthostelrejected);
            holder.rejectedPhoneNumber = view.findViewById(R.id.phnnumrejected);
            holder.rejectedLoaction = view.findViewById(R.id.txtLoactionhstlrejected);
            holder.rejectedReason = view.findViewById(R.id.txtreasonrejected);
            holder.rejectedDate = view.findViewById(R.id.txdaterejected);
            view.setTag(holder);

        } else {
            holder = (RejectedListAdapter.ViewHolder) view.getTag();

        }

        holder.rejectedName.setText(modellistrejected.get(position).getName());
        holder.rejectedPhoneNumber.setText(modellistrejected.get(position).getPhonenumber());
        holder.rejectedLoaction.setText(modellistrejected.get(position).getLocation());
        holder.rejectedReason.setText(modellistrejected.get(position).getReason());
        holder.rejectedDate.setText(modellistrejected.get(position).getDate());

        return null;
    }
}
