package com.omnispace.marketing.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.omnispace.marketing.Adapters.PersonalLeadsAdapter;
import com.omnispace.marketing.Data.PersonalListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;

public class PersonalLeadsActivity extends Activity {
    ListView listViewpersonalleads;
    PersonalLeadsAdapter personaladapter;
    String [] type;
    String[] name;
    String[]phonenumber;
    String[]loction;
    String[]date;
    ArrayList<PersonalListModel> arrayListpersonalViews = new ArrayList<PersonalListModel>();
    Spinner spinnerpersonal;
    String[] personal = {"select","All", "Hostel", "Institute"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_leads);
        spinnerpersonal= (Spinner) findViewById(R.id.spinnerpersonalleads);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, personal);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpersonal.setAdapter(adapter);

       /* type = new String[]{"1","2","3","4","5"};
            name = new String[]{"MogliHostel", "WomensHostel", "PGHostel", "PGBoysHostel", "BoysHostel"};
            phonenumber = new String[]{"8106631059", "8790101175", "7995184672", "8790411979", "8919394979"};
            loction = new String[]{"BenzCircle", "VeterenaryColony", "MGRoad", "Ashok Nagar", "BandharRoad"};
            date = new String[]{"30/3/19", "31/3/19", "1/4/19", "2/4/19", "BandharRoad"};

            for (int i =0; i<name.length; i++){
               PersonalListModel model = new PersonalListModel(type[i], name[i], phonenumber[i], loction[i],date[i]);
                arrayListpersonalViews.add(model);
            }
            listViewpersonalleads = findViewById(R.id.list_view_personalleads);
            personaladapter = new PersonalLeadsAdapter(this,arrayListpersonalViews);
            listViewpersonalleads.setAdapter(adapter);
        }
*/
   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
*/
    }
}

