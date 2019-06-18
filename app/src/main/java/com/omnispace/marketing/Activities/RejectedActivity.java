package com.omnispace.marketing.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.omnispace.marketing.Adapters.RejectedListAdapter;
import com.omnispace.marketing.Data.RejectedListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;

public class RejectedActivity extends Activity {
    ListView listViewrejected;
   RejectedListAdapter rejectedListAdapter;
    String[] namerejected;
    String[]phonenumberrejected;
    String[]loctionrejected;
    String[]reasonrejected;
    String []daterejected;
    ArrayList<RejectedListModel> arrayListrejected = new ArrayList<RejectedListModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rejected_layout);
        listViewrejected = findViewById(R.id.list_view_rejected);
        namerejected = new String[]{"Hostel1", "Hostel2", "Hostel3", "Hostel4", "Hoostel6"};
        phonenumberrejected = new String[]{"810663105", "879010117", "799518467", "879041197", "891939497"};
        loctionrejected = new String[]{"BenzCircl", "VeterenaryColon", "MGRoa", "AshokNagar", "BandarRoad"};
        reasonrejected = new String[]{"","","","",""};
        daterejected = new String[]{ "31/3/19", "1/4/19", "2/4/19", "BandharRoad","30/3/19"};

        for (int i =0; i<namerejected.length; i++){
           RejectedListModel model = new RejectedListModel(namerejected[i], phonenumberrejected[i], loctionrejected[i],reasonrejected[i],daterejected[i]);
            arrayListrejected.add(model);
        }
        rejectedListAdapter = new RejectedListAdapter(this, arrayListrejected);
        listViewrejected.setAdapter(rejectedListAdapter);


    }

}
