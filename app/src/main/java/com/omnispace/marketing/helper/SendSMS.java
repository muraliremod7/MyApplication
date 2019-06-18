package com.omnispace.marketing.helper;

import android.content.Context;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.omnispace.marketing.Networking.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SendSMS {
    private Context context;
    public SendSMS(Context context){
        this.context = context;
    }
    public void sendSMS(String mobilenumber,String msg){
        Ion.with(context)
                .load(Config.SEND_SMS+"phone="+mobilenumber+"&message="+msg)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){
                            //alertDialogManager.showAlertDialog(ViewUserprofileActivity.this,"Some thing went wrong", false);
                        }
                        else {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                JSONArray jsonArray = jsonObject.getJSONArray("msg_id");
                                String[] arr = new String[jsonArray.length()];
                                for(int i = 0; i < jsonArray.length(); i++){
                                    arr[i] = jsonArray.getString(i);
                                    if(arr[i]=="Invalid Number"){

                                    }else {

                                    }
                                }


                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
    }
}
