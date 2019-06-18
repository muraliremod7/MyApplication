package com.omnispace.marketing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.omnispace.marketing.Data.PersonalListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;
import java.util.List;

public class PersonalLeadsAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<PersonalListModel> modelpersonallist;
    ArrayList<PersonalListModel> arraypersonallist;
    public PersonalLeadsAdapter(Context context, List<PersonalListModel> modelpersonallist) {
        mContext = context;
        this.modelpersonallist = modelpersonallist;
        inflater = LayoutInflater.from(mContext);
        this.arraypersonallist = new ArrayList<PersonalListModel>();
        this.arraypersonallist.addAll(modelpersonallist);
    }

    public class ViewHolder {
        TextView mNameTvpersonal, mPhonenumberTvpersonal, mLoactionTvpersonal,mTypeTvpersonal,mDateTvpersonal;


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
       PersonalLeadsAdapter.ViewHolder holder;
        if (view == null) {
            holder = new PersonalLeadsAdapter.ViewHolder();
           view = inflater.inflate(R.layout.personal_lead_item_row, null);
            holder.mTypeTvpersonal = view.findViewById(R.id.txtType);
            holder.mNameTvpersonal = view.findViewById(R.id.txtnamepersonal);
            holder.mPhonenumberTvpersonal = view.findViewById(R.id.listpersonalphnnumber);
            holder.mLoactionTvpersonal = view.findViewById(R.id.txtLoactionpersonal);
            holder.mDateTvpersonal = view.findViewById(R.id.txtpersonaleddate);
            view.setTag(holder);

        } else {
            holder = (PersonalLeadsAdapter.ViewHolder) view.getTag();

        }
        holder.mTypeTvpersonal.setText(modelpersonallist.get(position).getType());
        holder.mNameTvpersonal.setText(modelpersonallist.get(position).getName());
        holder.mPhonenumberTvpersonal.setText(modelpersonallist.get(position).getPhonenumber());
        holder.mLoactionTvpersonal.setText(modelpersonallist.get(position).getLocation());
        holder.mDateTvpersonal.setText(modelpersonallist.get(position).getDate());




        return null;
    }
}
