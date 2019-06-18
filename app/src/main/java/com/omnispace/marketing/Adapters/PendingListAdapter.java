package com.omnispace.marketing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.omnispace.marketing.Data.PendingListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;
import java.util.List;

public class PendingListAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<PendingListModel> modellistpending;
    ArrayList<PendingListModel> arrayListpending;
    public PendingListAdapter(Context context, List<PendingListModel> modellistpending) {
        mContext = context;
        this.modellistpending = modellistpending;
        inflater = LayoutInflater.from(mContext);
        this.arrayListpending = new ArrayList<PendingListModel>();
        this.arrayListpending.addAll(modellistpending);
    }

    public class ViewHolder {
        TextView pendingName,pendingLoaction,PendingPhoneNumber,pendingReason,PendingDate;
        ImageView pendingAction;

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
    public View getView( final int position, View view, ViewGroup parent) {
        PendingListAdapter.ViewHolder holder;
        if (view == null) {
            holder = new PendingListAdapter.ViewHolder();
            view = inflater.inflate(R.layout.pending_row_item, null);
            holder.pendingName = view.findViewById(R.id.nametxthostelpending);
            holder.PendingPhoneNumber= view.findViewById(R.id.phnnumpending);
            holder.pendingLoaction = view.findViewById(R.id.txtLoactionhstlpending);
            holder.pendingReason= view.findViewById(R.id.txtreasontxt);
            holder.PendingDate= view.findViewById(R.id.txtpendingdate);
            holder.pendingAction = view.findViewById(R.id.mainIconpending);

            view.setTag(holder);

        } else {
            holder = (PendingListAdapter.ViewHolder) view.getTag();

        }

        holder.pendingName.setText(modellistpending.get(position).getName());
        holder.PendingPhoneNumber.setText(modellistpending.get(position).getPhonenumber());
        holder.pendingLoaction.setText(modellistpending.get(position).getLocation());
        holder.pendingReason.setText(modellistpending.get(position).getReason());
        holder.PendingDate.setText(modellistpending.get(position).getDate());
        holder.pendingAction.setImageResource(modellistpending.get(position).getPendingIcon());

        return null;
    }
}
