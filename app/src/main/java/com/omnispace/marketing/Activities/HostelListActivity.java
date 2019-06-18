package com.omnispace.marketing.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.omnispace.marketing.Adapters.ListViewAdapter;
import com.omnispace.marketing.Data.HostelListModel;
import com.omnispace.marketing.R;

import java.util.ArrayList;

public class HostelListActivity extends Activity {
    ListView listView;
    ListViewAdapter adapter;
   String[] name;
    String[]phonenumber;
    String[]loction;
    int[]icon;
    ArrayList<HostelListModel> arrayList = new ArrayList<HostelListModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hstl_list);
        /*getList();*/
      name = new String[]{"MogliHostel", "WomensHostel", "PGHostel", "PGBoysHostel", "BoysHostel"};
        phonenumber = new String[]{"8106631059", "8790101175", "7995184672", "8790411979", "8919394979"};
         loction = new String[]{"BenzCircle", "VeterenaryColony", "MGRoad", "Ashok Nagar", "BandharRoad"};
        icon = new int[]{R.drawable.spinnericon, R.drawable.spinnericon, R.drawable.spinnericon, R.drawable.spinnericon, R.drawable.spinnericon};

        for (int i =0; i<name.length; i++){
           HostelListModel model = new HostelListModel(name[i], phonenumber[i], loction[i],icon[i]);
           arrayList.add(model);
        }
        listView = findViewById(R.id.list_view_hstl);
        adapter = new ListViewAdapter(this, arrayList);
        listView.setAdapter(adapter);
    }
        /*private void getList(){
        JsonArrayRequest jsonreq = new JsonArrayRequest(Config.TARGETS_lIST,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());

                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    HostelListModel hostelListModel = new HostelListModel();
                                    hostelListModel.setName(obj.getString("name"));
                                    hostelListModel.setPhonenumber(obj.getString("phonenumber"));
                                    hostelListModel.setLocation(obj.getString("location"));
                                    arrayList.add(hostelListModel);

                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }

                            }
                            adapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });
           AppController.getInstance().addToRequestQueue(jsonreq);
        }*/
        }













