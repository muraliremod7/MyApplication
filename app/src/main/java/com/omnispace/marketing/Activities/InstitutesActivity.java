package com.omnispace.marketing.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.asksira.bsimagepicker.BSImagePicker;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.omnispace.marketing.Networking.Config;
import com.omnispace.marketing.R;
import com.omnispace.marketing.helper.FilePath;
import com.omnispace.marketing.helper.ProgressDailog;
import com.omnispace.marketing.mfs.MFS100_final;
import com.omnispace.marketing.services.GetAddressIntentService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

public class InstitutesActivity extends AppCompatActivity implements View.OnClickListener, BSImagePicker.OnMultiImageSelectedListener  {
    private EditText institutename,institutegst,institutestate,institutecity,instituteplace,instiaddress,instituteemail,insnameofowner,incontactofowner,inaadharnum,inowneremail,inosfees,insdescription,intbankname,inbankifsc,inaccnumber;
    private String selectedFilePath,imageone,imagetwo,imagethree,imagefour,imagefive,imagesix;
    private ProgressDailog progressDailog;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    private LocationAddressResultReceiver addressResultReceiver;
    private Location currentLocation;
    private LocationCallback locationCallback;
    private String latlan,addressDetails,pincode,fingerdata;
    private Button addpictures,submitinsform,capturefingers;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.institute_form);

        institutename = (EditText)findViewById(R.id.institute_name);
        institutegst = (EditText)findViewById(R.id.institute_gst);
        institutestate = (EditText)findViewById(R.id.institute_state);
        institutecity = (EditText)findViewById(R.id.institute_city);
        instituteplace = (EditText)findViewById(R.id.institute_place);
        instiaddress = (EditText)findViewById(R.id.institute_address);
        instituteemail = (EditText)findViewById(R.id.intitute_email);
        insnameofowner = (EditText)findViewById(R.id.intitute_owner_name);
        incontactofowner = (EditText)findViewById(R.id.insitute_owner_contact);
        inaadharnum = (EditText)findViewById(R.id.institute_owner_aadhar);
        inowneremail = (EditText)findViewById(R.id.institute_owner_email);
        inosfees = (EditText)findViewById(R.id.institute_osfees);
        insdescription = (EditText)findViewById(R.id.institute_desciption);
        intbankname = (EditText)findViewById(R.id.institute_bank_name);
        inbankifsc = (EditText)findViewById(R.id.institute_bank_ifsccode);
        inaccnumber = (EditText)findViewById(R.id.institute_bank_accountnum);
        progressDailog = new ProgressDailog(this);
        addpictures = (Button)findViewById(R.id.institute_Add_Pictures);
        addpictures.setOnClickListener(this);
        submitinsform = (Button)findViewById(R.id.submitinst);
        submitinsform.setOnClickListener(this);
        capturefingers = (Button)findViewById(R.id.takefingerdata);
        capturefingers.setOnClickListener(this);
        addressResultReceiver = new LocationAddressResultReceiver(new Handler());
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
                latlan = currentLocation.getLatitude()+","+currentLocation.getLongitude();
                getAddress();
            };
        };
        startLocationUpdates();
    }
    private class LocationAddressResultReceiver extends ResultReceiver {
        LocationAddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultCode == 0) {
                //Last Location can be null for various reasons
                //for example the api is called first time
                //so retry till location is set
                //since intent service runs on background thread, it doesn't block main thread
                Log.d("Address", "Location null retrying");
                getAddress();
            }

            if (resultCode == 1) {
                Toast.makeText(InstitutesActivity.this,
                        "Address not found, " ,
                        Toast.LENGTH_SHORT).show();
            }
            Address address = resultData.getParcelable("address_result");
            String state = address.getAdminArea();
            String city = address.getLocality();
            String place = address.getSubLocality();
            pincode = address.getPostalCode();
            institutestate.setText(state);
            institutecity.setText(city);
            instituteplace.setText(place);
            addressDetails = resultData.getString("address");
            instiaddress.setText(addressDetails);
            //showResults(currentAdd);
        }
    }
    @Override
    public void onMultiImageSelected(List<Uri> uriList, String tag) {
        for (int i=0; i < uriList.size(); i++) {
            if (i >= 6) return;
            ImageView iv;
            switch (i) {
                case 0:
                    imageone = uriList.get(0).toString();
                    Uri selectedFileUri = Uri.parse(imageone);
                    imageone = FilePath.getPath(this,selectedFileUri);
                    break;
                case 1:
                    imagetwo = uriList.get(1).toString();
                    Uri selectedFileUri1 = Uri.parse(imagetwo);
                    imagetwo = FilePath.getPath(this,selectedFileUri1);
                    break;
                case 2:
                    imagethree = uriList.get(2).toString();
                    Uri selectedFileUri2 = Uri.parse(imagethree);
                    imagethree = FilePath.getPath(this,selectedFileUri2);
                    break;
                case 3:
                    imagefour = uriList.get(3).toString();
                    Uri selectedFileUri3 = Uri.parse(imagefour);
                    imagefour = FilePath.getPath(this,selectedFileUri3);
                    break;
                case 4:
                    imagefive = uriList.get(4).toString();
                    Uri selectedFileUri4 = Uri.parse(imagefive);
                    imagefive = FilePath.getPath(this,selectedFileUri4);
                    break;
                case 5:
                    imagesix = uriList.get(5).toString();
                    Uri selectedFileUri5 = Uri.parse(imagesix);
                    imagesix = FilePath.getPath(this,selectedFileUri5);
                default:

            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }
    @SuppressWarnings("MissingPermission")
    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            @SuppressLint("RestrictedApi")
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(2000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("MissingPermission")
    private void getAddress() {

        if (!Geocoder.isPresent()) {
            Toast.makeText(InstitutesActivity.this,
                    "Can't find current address, ",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, GetAddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.institute_Add_Pictures:
                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.example.myapplication.fileprovider")
                        .setMaximumDisplayingImages(Integer.MAX_VALUE)
                        .isMultiSelect()
                        .setMaximumMultiSelectCount(6)
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
                break;
            case R.id.takefingerdata:
                Intent intent = new Intent(InstitutesActivity.this, MFS100_final.class);
                startActivityForResult(intent,6);
                break;
            case R.id.submitinst:
                String uid = "0";
                String insName = institutename.getText().toString().replace(" ","%20");
                String insgst = institutegst.getText().toString();
                String insstate = institutestate.getText().toString().replace(" ","%20");
                String inscity = institutecity.getText().toString().replace(" ","%20");
                String insplace = instituteplace.getText().toString().replace(" ","%20");
                String insaddress = instiaddress.getText().toString().replace(" ","%20");
                String insemail = instituteemail.getText().toString();
                String insownername = insnameofowner.getText().toString().replace(" ","%20");
                String insownernumber = incontactofowner.getText().toString();
                String insaadhar = inaadharnum.getText().toString();
                String insowneremail = inowneremail.getText().toString();
                String insosfees = inosfees.getText().toString();
                String insdesc = insdescription.getText().toString().replace(" ","%20");
                String insbankname = intbankname.getText().toString().replace(" ","%20");
                String insbankifsc = inbankifsc.getText().toString();
                String insbankacnumber = inaccnumber.getText().toString();
                String insownerpin = "";
                progressDailog.showDailog();
                submitinstituteForm(uid,insName,insstate,inscity,insplace,insgst,insemail,insownername,insownernumber,insowneremail,insaadhar,insbankname,insbankacnumber,insbankifsc,insdesc,insaddress,insosfees,insownerpin,imageone,imagetwo,imagethree,imagefour,imagefive,imagesix,fingerdata);
                break;
        }
    }

    private void submitinstituteForm(String uid, String insName, String insstate, String inscity, String insplace, String insgst, String insemail, String insownername, String insownernumber, String insowneremail, String insaadhar, String insbankname, String insbankacnumber, String insbankifsc, String insdesc, String insaddress, String insosfees, String insownerpin,String imageone,String imagetwo,String imagethree,String imagefour,String five,String imagesix,String fingerdata) {
        File imgone = new File(imageone);
        File imgtwo = new File(imagetwo);
        File imgthree = new File(imagethree);
        File imgfour = new File(imagefour);
        File imgfive = new File(five);
        File imgsix = new File(imagesix);
        Ion.with(InstitutesActivity.this)
                .load(Config.SAVEINSTITUTEDATA+"uid="+uid+"&institute_name="+insName+"&state="+insstate+"&city="+inscity+"&place="+insplace+"&gst="+insgst+"&emailid="+insemail+"&owner_name="+insownername+"&owner_contact="+insownernumber+"&owner_email="+insowneremail+"&owner_adhar="+insaadhar+"&bank_name="+insbankname+"&bank_account_number="+insbankacnumber+"&bank_ifsc_code="+insbankifsc+"&description="+insdesc+"&address="+insaddress+"&o_s_price="+insosfees+"&owner_pin="+insownerpin+"&latlan="+latlan+"&pincode="+pincode+"&fingerdata="+fingerdata)
                .setHeader("encType","multipart/form-data")
                .setMultipartFile("image1","image/jpg",imgone)
                .setMultipartFile("image2","image/jpg",imgtwo)
                .setMultipartFile("image3","image/jpg",imgthree)
                .setMultipartFile("image4","image/jpg",imgfour)
                .setMultipartFile("image5","image/jpg",imgfive)
                .setMultipartFile("image6","image/jpg",imgsix)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){
                            progressDailog.dismissDailog();
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                Toast.makeText(InstitutesActivity.this,"Institute Saved Successfully",Toast.LENGTH_LONG).show();
                                Log.d("output",result);
                                progressDailog.dismissDailog();
                            } catch (JSONException e1) {
                                progressDailog.dismissDailog();
                                e1.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                } else {
                    Toast.makeText(this, "Location permission not granted, " +
                                    "restart the app if you want the feature",
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==2){
            fingerdata = data.getStringExtra("fingerdata");
        }
    }
}
