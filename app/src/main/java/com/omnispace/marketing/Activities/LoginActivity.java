package com.omnispace.marketing.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.omnispace.marketing.Networking.Config;
import com.omnispace.marketing.R;
import com.omnispace.marketing.helper.AlertDialogManager;
import com.omnispace.marketing.helper.PrefManager;
import com.omnispace.marketing.helper.ProgressDailog;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity implements View.OnClickListener {
    private static String TAG = "LoginActivity";
    private EditText editTextMobileNumber;
    private EditText editTextPassword;
    private Button buttonLogin;
    private AlertDialogManager alertDialogManager;
    private ProgressDailog progressDailog;
    private CheckBox mCbShowPwd;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        editTextMobileNumber = (EditText) findViewById(R.id.edtmblnum);
        editTextPassword = (EditText) findViewById(R.id.edtpwd);

        progressDailog = new ProgressDailog(this);
        alertDialogManager = new AlertDialogManager();
        prefManager = new PrefManager(this);

        buttonLogin = (Button) findViewById(R.id.loginbtn);
        buttonLogin.setOnClickListener(this);
        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);
        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked) {
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginbtn:
                progressDailog.showDailog();
                String mobilenumber = editTextMobileNumber.getText().toString();
                String password = editTextPassword.getText().toString();
                if(isValidPhoneNumber(mobilenumber)){
                    login(mobilenumber,password);
                }else {
                    progressDailog.dismissDailog();
                    alertDialogManager.showAlertDialog(LoginActivity.this,"Enter Valid Mobile Number",false);
                }
                break;
        }


    }

    private void login(String mobilenumber, String password) {
        Ion.with(getApplicationContext())
                .load(Config.LOGIN_URL+"ph="+mobilenumber+"&pwd="+password)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                     if(e!=null){
                        progressDailog.dismissDailog();
                         alertDialogManager.showAlertDialog(LoginActivity.this,"Something Went Wrong",false);
                     }else {
                         try {
                             JSONObject jsonObject = new JSONObject(result);

                             JSONObject response = jsonObject.getJSONObject("status");
                             int status = response.getInt("response");
                             String msg = response.getString("message");
                             if(status==0){
                                alertDialogManager.showAlertDialog(LoginActivity.this,msg,false);
                             }else {
                                 Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();
                                 JSONObject details = jsonObject.getJSONObject("0");
                                 String id = details.getString("id");
                                 String empcode = details.getString("empcode");
                                 String roletype = details.getString("roletype");
                                 String name = details.getString("name");
                                 String phone = details.getString("phone");
                                 String email = details.getString("email");
                                 prefManager.createLogin(id,empcode,roletype,name,email,phone);
                                 Intent intent = new Intent(LoginActivity.this, DataActivity.class);
                                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                 startActivity(intent);
                                 finish();

                             }
                         } catch (JSONException e1) {
                             e1.printStackTrace();
                         }
                     }
                    }
                });
    }
    private static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }
}

