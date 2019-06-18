package com.omnispace.marketing.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.omnispace.marketing.Adapters.CompletedAdapter;
import com.omnispace.marketing.Data.CompletedListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;

public class CompletedActivity extends Activity {
    ListView listViewcom;
    CompletedAdapter adapter;
    String[] namecompleted;
    String[]phonenumbercompleted;
    String[]loctioncompleted;
    String[] datecompleted;
    ArrayList<CompletedListModel> comarrayListcompleted = new ArrayList<CompletedListModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_screen);
        listViewcom = findViewById(R.id.list_view_completed);
        namecompleted = new String[]{"MogliHostel", "WomensHostel", "PGHostel", "PGBoysHostel", "BoysHostel"};
        phonenumbercompleted = new String[]{"8106631059", "8790101175", "7995184672", "8790411979", "8919394979"};
        loctioncompleted = new String[]{"BenzCircle", "VeterenaryColony", "MGRoad", "Ashok Nagar", "BandharRoad"};
        datecompleted = new String[]{"30/3/19", "31/3/19", "1/4/19", "2/4/19", "BandharRoad"};

        for (int i =0; i<namecompleted.length; i++){
           CompletedListModel model = new CompletedListModel(namecompleted[i], phonenumbercompleted[i], loctioncompleted[i],datecompleted[i]);
             comarrayListcompleted.add(model);
        }
        adapter = new CompletedAdapter(this, comarrayListcompleted);
        listViewcom.setAdapter(adapter);


    }
    }

