package com.omnispace.marketing.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.omnispace.marketing.Preferences.SharedPrefManager;
import com.omnispace.marketing.R;
import com.omnispace.marketing.helper.PrefManager;

public class DataActivity extends Activity  implements AdapterView.OnItemSelectedListener {
    Button buttontargets,buttoncs,buttoninstitutes,buttongyms,buttonhstls,buttonperonalleads,buttonlogout;
    Spinner spinner1;
    String[] users = {"","completed Tragets", "pending Targets", "rejected Targets"};
    AlertDailogManager alert = new AlertDailogManager();
    SharedPrefManager session;
    private PrefManager pref;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);
        session = new SharedPrefManager(getApplicationContext());
        buttontargets = (Button) findViewById(R.id.btntar);
        buttoncs = (Button) findViewById(R.id.btcs);
        buttoninstitutes = (Button) findViewById(R.id.btninstitute);
        buttongyms = (Button) findViewById(R.id.btngym);
        buttonhstls = (Button)findViewById(R.id.btnhstl) ;
        buttonperonalleads = (Button)findViewById(R.id.btnpleads);
        buttonlogout = (Button)findViewById(R.id.btlogout);
        pref = new PrefManager(this);

        if (!pref.isLoggedIn()) {
            logout();
        }
        spinner1= (Spinner) findViewById(R.id.spinnerdata);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        //here iw rote intent where is u r

        buttontargets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this, TargetsActivity.class);
                startActivity(intent);
            }
        });
        buttoninstitutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this,InstitutesActivity.class);
                startActivity(intent);
            }
        });
        buttongyms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this, GymsActivity.class);
                startActivity(intent);
            }
        });
        buttonhstls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this,HostelActivity.class);
                startActivity(intent);
            }
        });
        buttoncs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this, CsActivity.class);
                startActivity(intent);

            }
        });
        buttonperonalleads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this, PersonalLeadsActivity.class);
                startActivity(intent);

            }
        });
        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 1) {
            startActivity(new Intent(this, CompletedActivity.class));
        }
        if (position==2) {
            startActivity(new Intent(this, PendingActivity.class));
        }if (position==3) {
            startActivity(new Intent(this,RejectedActivity.class));
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void logout() {
        pref.clearSession();
        Intent intent = new Intent(DataActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
