package com.omnispace.marketing.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.omnispace.marketing.Adapters.PendingListAdapter;
import com.omnispace.marketing.Data.PendingListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;

public class PendingActivity extends Activity {
    ListView listViewpending;
   PendingListAdapter pendingListAdapter;
    String[] namepending;
    String[]phonenumberpending;
    String[]loctionpending;
    String[]reasonpending;
    String[]datepending;
    int[]iconpending;
    ArrayList<PendingListModel> arrayListpending = new ArrayList<PendingListModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_layout);
        listViewpending = findViewById(R.id.list_view_pending);
        namepending = new String[]{"MogliHostel", "WomensHostel", "PGHostel", "PGBoysHostel", "BoysHostel"};
        phonenumberpending = new String[]{"8106631059", "8790101175", "7995184672", "8790411979", "8919394979"};
        loctionpending = new String[]{"BenzCircle", "VeterenaryColony", "MGRoad", "Ashok Nagar", "BandharRoad"};
        reasonpending = new String[]{"","","","",""};
        datepending = new String[]{"30/3/19", "31/3/19", "1/4/19", "2/4/19", "BandharRoad"};
        iconpending = new int[]{R.drawable.spinnericon, R.drawable.spinnericon, R.drawable.spinnericon, R.drawable.spinnericon, R.drawable.spinnericon};


        for (int i =0; i<namepending.length; i++){
           PendingListModel model = new PendingListModel(namepending[i], phonenumberpending[i], loctionpending[i],reasonpending[i],datepending[i],iconpending[i]);
            arrayListpending.add(model);
        }
        pendingListAdapter = new PendingListAdapter(this, arrayListpending);
        listViewpending.setAdapter(pendingListAdapter);

    }
}
