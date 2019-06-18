package com.omnispace.marketing.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.omnispace.marketing.Adapters.TargetsAdapter;
import com.omnispace.marketing.Networking.Config;
import com.omnispace.marketing.R;
import com.omnispace.marketing.commonclass.TargetsDAO;
import com.omnispace.marketing.helper.AlertDialogManager;
import com.omnispace.marketing.helper.PrefManager;
import com.omnispace.marketing.helper.ProgressDailog;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TargetsActivity extends Activity {

    private RecyclerView targetsRecyclerview;
    private TargetsAdapter targetsAdapter;
    private ArrayList<TargetsDAO> targetsDAOS;
    private AlertDialogManager alertDialogManager;
    private ProgressDailog progressDailog;
    private PrefManager prefManager;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.targets);

        targetsRecyclerview = (RecyclerView)findViewById(R.id.targets_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        targetsRecyclerview.setLayoutManager(llm);
        targetsRecyclerview.setHasFixedSize(true);

        targetsDAOS = new ArrayList<>();
        alertDialogManager = new AlertDialogManager();
        progressDailog  = new ProgressDailog(this);
        getTargetsData();
    }

    private void getTargetsData() {
        progressDailog.showDailog();
        Ion.with(getApplicationContext())
                .load(Config.TARGETS_lIST+"user="+"9")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){
                            progressDailog.dismissDailog();
                            alertDialogManager.showAlertDialog(TargetsActivity.this,"Something Went wrong",false);
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(result);

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
    }
}





