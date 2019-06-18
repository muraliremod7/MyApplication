package com.omnispace.marketing.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
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
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.asksira.bsimagepicker.BSImagePicker;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.omnispace.marketing.Networking.Config;
import com.omnispace.marketing.R;
import com.omnispace.marketing.commonclass.ShareAddDAO;
import com.omnispace.marketing.helper.FilePath;
import com.omnispace.marketing.helper.ProgressDailog;
import com.omnispace.marketing.mfs.MFS100_final;
import com.omnispace.marketing.services.GetAddressIntentService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HostelActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, BSImagePicker.OnMultiImageSelectedListener {
    private EditText hostelname,hostelgst,hostelownername,hostelownerconatct,hostelowneremail,hostelowneraadhar,hostelcontact,hosteladress,hostelstate,bankname,accountnumber,ifsccode,omnispacefeesday,omnispacefeesmonth;
    private CheckBox wifiamen,tvamen,foodamen,washingmachineame,oneacshare,twoacshare,threeacshare,onenonacshare,twononacshare,threenonacshare,menshostel,womenshostel,bothhostel;
    private EditText hostelplace,hostellocation;
    private ImageView acshareadd,nonacshareadd;
    private Button addpictures,addextraaddressproofs,adddocumets,uploadData,capturefingers;
    private ArrayList<ShareAddDAO> shareAddDAOS = new ArrayList<>();
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    private LocationAddressResultReceiver addressResultReceiver;
    private Location currentLocation;
    private LocationCallback locationCallback;
    private String latlan,addressDetails,pincode,fingerdata;
    private String wifi,tv,food,washingmachine,selectedFilePath,imageone,imagetwo,imagethree,imagefour,imagefive,imagesix,uploadextraproof,uploaddocuments,selectiontype;
    private String hostelType;
    final int CAMERA_CAPTURE = 1;
    private ProgressDailog progressDailog;
    private boolean first = true;
    private String dayormonth_value;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostelform);

        hostelname = (EditText)findViewById(R.id.hostel_name);
        hostelgst = (EditText)findViewById(R.id.hostel_gst);
        hostelownername = (EditText)findViewById(R.id.hostel_owner_name);
        hostelownerconatct = (EditText)findViewById(R.id.hostel_owner_contact);
        hostelowneremail = (EditText)findViewById(R.id.hostel_owner_email);
        hostelowneraadhar = (EditText)findViewById(R.id.hostel_owner_aadhar);
        hostelcontact = (EditText)findViewById(R.id.contact_hostel);
        hosteladress = (EditText)findViewById(R.id.hostel_address);
        bankname = (EditText)findViewById(R.id.hostel_bankname);
        accountnumber = (EditText)findViewById(R.id.hostel_accnumber);
        ifsccode = (EditText)findViewById(R.id.hostel_ifsc);
        omnispacefeesday = (EditText)findViewById(R.id.hostel_os_fees_day);
        omnispacefeesmonth = (EditText)findViewById(R.id.hostel_os_fees_month);
        wifiamen = (CheckBox)findViewById(R.id.hostel_wifi);
        wifiamen.setOnCheckedChangeListener(this);
        tvamen = (CheckBox)findViewById(R.id.hostel_tv);
        tvamen.setOnCheckedChangeListener(this);
        foodamen = (CheckBox)findViewById(R.id.hostel_food);
        foodamen.setOnCheckedChangeListener(this);
        washingmachineame = (CheckBox)findViewById(R.id.hostel_washingmachine);
        washingmachineame.setOnCheckedChangeListener(this);
        oneacshare = (CheckBox)findViewById(R.id.hostel_acone_sharing);
        oneacshare.setOnCheckedChangeListener(this);
        twoacshare = (CheckBox)findViewById(R.id.hostel_actwo_sharing);
        twoacshare.setOnCheckedChangeListener(this);
        threeacshare = (CheckBox)findViewById(R.id.hostel_acthree_sharing);
        threeacshare.setOnCheckedChangeListener(this);
        onenonacshare = (CheckBox)findViewById(R.id.hostel_nonacone_sharing);
        onenonacshare.setOnCheckedChangeListener(this);
        twononacshare = (CheckBox)findViewById(R.id.hostel_nonactwo_sharing);
        twononacshare.setOnCheckedChangeListener(this);
        threenonacshare = (CheckBox)findViewById(R.id.hostel_nonacthree_sharing);
        threenonacshare.setOnCheckedChangeListener(this);

        menshostel = (CheckBox)findViewById(R.id.hostel_menshostel);
        menshostel.setOnCheckedChangeListener(this);
        womenshostel = (CheckBox)findViewById(R.id.hostel_womenshostel);
        womenshostel.setOnCheckedChangeListener(this);
        bothhostel = (CheckBox)findViewById(R.id.hostel_bothhostel);
        bothhostel.setOnCheckedChangeListener(this);

        hostelstate = (EditText)findViewById(R.id.hostel_state);
        hostelplace = (EditText)findViewById(R.id.hostel_place);
        hostellocation = (EditText)findViewById(R.id.hostel_location);

        acshareadd = (ImageView)findViewById(R.id.hostel_ac_addsharing);
        acshareadd.setOnClickListener(this);
        nonacshareadd = (ImageView)findViewById(R.id.hostel_nonac_addsharing);
        nonacshareadd.setOnClickListener(this);
        addpictures = (Button)findViewById(R.id.hostel_Add_Pictures);
        addpictures.setOnClickListener(this);
        addextraaddressproofs = (Button)findViewById(R.id.hostel_upload_extra_addressproof);
        addextraaddressproofs.setOnClickListener(this);
        adddocumets = (Button)findViewById(R.id.hostel_upload_documents);
        adddocumets.setOnClickListener(this);
        uploadData = (Button)findViewById(R.id.hosteldata_submit);
        uploadData.setOnClickListener(this);
        capturefingers = (Button)findViewById(R.id.takefingerdata_hostel);
        capturefingers.setOnClickListener(this);
        progressDailog = new ProgressDailog(this);

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
    @SuppressWarnings("MissingPermission")
    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            @SuppressLint("RestrictedApi")
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(2000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback,
                    null);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @SuppressWarnings("MissingPermission")
    private void getAddress() {

        if (!Geocoder.isPresent()) {
            Toast.makeText(HostelActivity.this,
                    "Can't find current address, ",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, GetAddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermission() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_CAPTURE);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else {

        }
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.hostel_wifi:
                if(isChecked){
                    wifi = "1";
                Toast.makeText(HostelActivity.this,"Wifi",Toast.LENGTH_LONG).show();
                }else {
                    wifi = "0";
                }
                break;
            case R.id.hostel_tv:
                if(isChecked){
                    tv = "1";
                    Toast.makeText(HostelActivity.this,"TV",Toast.LENGTH_LONG).show();
                }else {
                    tv = "0";
                }
                break;
            case R.id.hostel_food:
                if(isChecked){
                    food = "1";
                    Toast.makeText(HostelActivity.this,"food",Toast.LENGTH_LONG).show();
                }else {
                    food = "0";
                }
                break;
            case R.id.hostel_washingmachine:
                if(isChecked){
                    washingmachine = "1";
                    Toast.makeText(HostelActivity.this,"washing machine",Toast.LENGTH_LONG).show();
                }else {
                    washingmachine = "0";
                }
                break;
            case R.id.hostel_acone_sharing:
                if(isChecked){
                    showDailog("1","1");
                }else if(!isChecked){
                    removesharing("1","1");
                }
                break;
            case R.id.hostel_actwo_sharing:
                if(isChecked){
                    showDailog("1","2");
                }
                else if(!isChecked){
                    removesharing("1","2");
                }
                break;
            case R.id.hostel_acthree_sharing:
                if(isChecked){
                    showDailog("1","3");
                }
                else if(!isChecked){
                    removesharing("1","3");
                }
                break;
            case R.id.hostel_nonacone_sharing:
                if(isChecked){
                    showDailog("0","1");
                }else if(!isChecked){
                    removesharing("0","1");
                }
                break;
            case R.id.hostel_nonactwo_sharing:
                if(isChecked){
                    showDailog("0","2");
                }
                else if(!isChecked){
                    removesharing("0","2");
                }
                break;
            case R.id.hostel_nonacthree_sharing:
                if(isChecked){
                    showDailog("0","3");
                }
                else if(!isChecked){
                    removesharing("0","3");
                }
                break;
            case R.id.hostel_menshostel:
                if(isChecked){
                    hostelType = "men";
                }
                else if(!isChecked){
                   hostelType = "";
                }
                break;
            case R.id.hostel_womenshostel:
                if(isChecked){
                    hostelType = "women";
                }
                else if(!isChecked){
                    hostelType = "";
                }
                break;
            case R.id.hostel_bothhostel:
                if(isChecked){
                    hostelType = "both";
                }
                else if(!isChecked){
                    hostelType = "";
                }
                break;
        }
    }

    private void removesharing(String s, String s1) {
        for(int i=0;i<shareAddDAOS.size();i++){
            if(s.equals(shareAddDAOS.get(i).getRoomtype())){
                if(s1.equals(shareAddDAOS.get(i).getSharingtype())){
                    shareAddDAOS.remove(i);
                }

            }
        }
    }

    private void showDailog(final String roomtype, final String sharingtype) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(HostelActivity.this);
        View mView = layoutInflaterAndroid.inflate(R.layout.sharingalertdailog, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(HostelActivity.this);
        alertDialogBuilderUserInput.setView(mView);
        final EditText sharingType = (EditText)mView.findViewById(R.id.sharingType_hostel);
        sharingType.setText(sharingtype);
        final EditText roomcount = (EditText)mView.findViewById(R.id.rooms_hostel);
        final EditText pricepermonth = (EditText)mView.findViewById(R.id.pricepermonth_hostel);
        final EditText priceperday = (EditText)mView.findViewById(R.id.priceperday_hostel);
        final EditText omnispaceprice = (EditText)mView.findViewById(R.id.omnispaceprice_hostel);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                    ShareAddDAO shareAddDAO = new ShareAddDAO();
                    shareAddDAO.setRoomtype(roomtype);
                    shareAddDAO.setSharingtype(sharingtype);
                    shareAddDAO.setRoomcount(roomcount.getText().toString());
                    shareAddDAO.setPricepermonth(pricepermonth.getText().toString());
                    shareAddDAO.setPriceperday(priceperday.getText().toString());
                    shareAddDAO.setOsprice(omnispaceprice.getText().toString());
                    shareAddDAOS.add(shareAddDAO);
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });
        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
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
                Toast.makeText(HostelActivity.this,
                        "Address not found, " ,
                        Toast.LENGTH_SHORT).show();
            }
            Address address = resultData.getParcelable("address_result");
            String state = address.getAdminArea();
            String city = address.getLocality();
            String place = address.getSubLocality();
            if(first==true){
                pincode = address.getPostalCode();
                hostelstate.setText(state);
                hostelplace.setText(city);
                hostellocation.setText(place);
                addressDetails = resultData.getString("address");
                hosteladress.setText(addressDetails);
                first = false;
            }else {

            }

            //showResults(currentAdd);
        }
    }
        private void showResults(String currentAdd){

        }

    private void selectImage (String type) {
            selectiontype = type;
            final CharSequence[] options = {"Take Photo", "Choose from Gallery","Select Document", "Cancel"};


            AlertDialog.Builder builder = new AlertDialog.Builder(HostelActivity.this);

            builder.setTitle("Add Address Proff");

            builder.setItems(options, new DialogInterface.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override

                public void onClick(DialogInterface dialog, int item) {

                    if (options[item].equals("Take Photo")) {

                        requestPermission();

                    } else if (options[item].equals("Choose from Gallery")) {

                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        startActivityForResult(intent, 2);


                    } else if (options[item].equals("Select Document")) {

                        Intent intent = new Intent();
                        //sets the select file to all types of files
                        intent.setType("*/*");
                        //allows to select data and return it
                        intent.setAction(Intent.ACTION_GET_CONTENT);

                        //starts new activity to select file and return data
                        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),3);


                    }else if (options[item].equals("Cancel")) {

                        dialog.dismiss();

                    }

                }

            });

            builder.show();

        }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    private void capture() {
        try
        {
            // use standard intent to capture an image
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // we will handle the returned data in onActivityResult
            if(captureIntent.resolveActivity(getPackageManager()) != null){
                //Create a file to store the image
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this, "com.omnispace.marketing.provider", photoFile);
                    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            photoURI);
                    startActivityForResult(captureIntent,
                            CAMERA_CAPTURE);
                }
            }
        }
        catch (ActivityNotFoundException anfe)
        {
            // display an error message
            String errorMessage = "Whoops - your device doesn't support capturing images!";
            Toast toast = Toast.makeText(this, errorMessage,Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        if(selectiontype.equals("1")){
            uploadextraproof = image.getAbsolutePath().toString();
        }else {
            uploaddocuments = image.getAbsolutePath().toString();
        }

        return image;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==6){
            fingerdata = data.getStringExtra("fingerdata");
        }
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {


            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));

                if(selectiontype.equals("1")){
                    uploadextraproof = picturePath;
                }else {
                    uploaddocuments = picturePath;
                }
               // imagehstl.setImageBitmap(thumbnail);

            }
            if(requestCode == 3){
                if(data == null){
                    //no data present
                    return;
                }


                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this,selectedFileUri);

                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    if(selectiontype.equals("1")){
                        uploadextraproof = selectedFilePath;
                    }else {
                        uploaddocuments = selectedFilePath;
                    }
                }else{
                    Toast.makeText(this,"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }
            }

        }

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
            case CAMERA_CAPTURE : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    capture();                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takefingerdata:
                Intent intent = new Intent(HostelActivity.this, MFS100_final.class);
                startActivityForResult(intent,6);
                break;
            case R.id.hostel_ac_addsharing:
                showDailog("1","");
                break;
            case R.id.hostel_nonac_addsharing:
                showDailog("0","");
                break;
            case R.id.hostel_Add_Pictures:
                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.example.myapplication.fileprovider")
                        .setMaximumDisplayingImages(Integer.MAX_VALUE)
                        .isMultiSelect()
                        .setMaximumMultiSelectCount(6)
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
                break;
            case R.id.hostel_upload_extra_addressproof:
                selectImage("1");
                break;
            case R.id.hostel_upload_documents:
                selectImage("2");
                break;
            case R.id.hosteldata_submit:
                progressDailog.showDailog();
                String emailOwner = hostelowneremail.getText().toString();

                String hostelName = hostelname.getText().toString().replace(" ","%20");
                String gst = hostelgst.getText().toString();
                String nameOfOwner = hostelownername.getText().toString().replace(" ","%20");
                String contactOwner = hostelownerconatct.getText().toString();
                String aadharOwner = hostelowneraadhar.getText().toString();
                String conactHostel = hostelcontact.getText().toString();
                String addressHostel = hosteladress.getText().toString().replace(" ","%20");
                String hostelState = hostelstate.getText().toString().replace(" ","%20");
                String hostelCity = hostelplace.getText().toString().replace(" ","%20");
                String hostelplace = hostellocation.getText().toString().replace(" ","%20");
                String bankName = bankname.getText().toString().replace(" ","%20");
                String accountNumber = accountnumber.getText().toString();
                String ifscCode = ifsccode.getText().toString();
                String osFeesday = omnispacefeesday.getText().toString();
                String osFeesmonth = omnispacefeesmonth.getText().toString();
                if(emailOwner.isEmpty()||hostelName.isEmpty()||nameOfOwner.isEmpty()||contactOwner.isEmpty()||aadharOwner.isEmpty()||conactHostel.isEmpty()||addressHostel.isEmpty()||bankName.isEmpty()||ifscCode.isEmpty()||accountNumber.isEmpty()||osFeesday.isEmpty()||osFeesmonth.isEmpty()){
                    Toast.makeText(HostelActivity.this,"Enter Should be all fields",Toast.LENGTH_LONG).show();
                    progressDailog.dismissDailog();
                }
                else {
                    if(gst.isEmpty()){
                        gst = "no";
                        submitHostelform(hostelName,gst,nameOfOwner,contactOwner,emailOwner,aadharOwner,conactHostel,addressHostel,hostelState,hostelCity,hostelplace,bankName,accountNumber,ifscCode,osFeesday,osFeesmonth,wifi,tv,food,washingmachine,hostelType,imageone,imagetwo,imagethree,imagefour,imagefive,imagesix,shareAddDAOS,uploadextraproof,uploaddocuments,fingerdata);
                    }
                    else {
                        submitHostelform(hostelName,gst,nameOfOwner,contactOwner,emailOwner,aadharOwner,conactHostel,addressHostel,hostelState,hostelCity,hostelplace,bankName,accountNumber,ifscCode,osFeesday,osFeesmonth,wifi,tv,food,washingmachine,hostelType,imageone,imagetwo,imagethree,imagefour,imagefive,imagesix,shareAddDAOS,uploadextraproof,uploaddocuments,fingerdata);
                    }
                }


                break;
        }
    }

    private void submitHostelform(String hostelName, String gst, String nameOfOwner, String contactOwner, String emailOwner, String aadharOwner, String conactHostel, String addressHostel, String hostelState, String hostelCity, String hostelplace, String bankName, String accountNumber, String ifscCode, String osFeesday,String osFeesmonth, String wifi, String tv, String food, String washingmachine, String hostelType, String imageone, String imagetwo, String imagethree, String imagefour, String imagefive, String imagesix, ArrayList<ShareAddDAO> shareAddDAOS, String uploadextraproof, String uploaddocuments,String fingerdata) {
        File imgone = new File(imageone);
        File imgtwo = new File(imagetwo);
        File imgthree = new File(imagethree);
        File imgfour = new File(imagefour);
        File imgfive = new File(imagefive);
        File imgsix = new File(imagesix);
        File extraAddressprooffile = new File(uploadextraproof);
        File document = new File(uploaddocuments);

        Gson gson = new Gson();
        // This can be any object. Does not have to be an arraylist.
        String json = gson.toJson(shareAddDAOS);
        Ion.with(HostelActivity.this)
                .load("POST",Config.SAVEHOSTELDATA+"hostelname="+hostelName+"&gst="+gst+"&owner_name="+nameOfOwner+"&owner_contact="+contactOwner+"&emailid="+emailOwner+"&owner_email="+emailOwner+"&owner_adhar="+aadharOwner+"&hostel_contact_number="+conactHostel+"&address="+addressHostel+"&state="+hostelState+"&city="+hostelCity+"&place="+hostelplace+"&bank_name="+bankName+"&bank_account_number="+accountNumber+"&bank_ifsc_code="+ifscCode+"&wifi="+wifi+"&tv="+tv+"&food="+food+"&washingmachine="+washingmachine+"&hosteltype="+hostelType+"&osfeeday="+osFeesday+"&osfeemonth="+osFeesmonth+"&sharinglist="+json+"&latlan="+latlan+"&pincode="+pincode+"&fingerdata="+fingerdata)
                .setHeader("encType","multipart/form-data")
                .setMultipartFile("image1","image/jpg",imgone)
                .setMultipartFile("image2","image/jpg",imgtwo)
                .setMultipartFile("image3","image/jpg",imgthree)
                .setMultipartFile("image4","image/jpg",imgfour)
                .setMultipartFile("image5","image/jpg",imgfive)
                .setMultipartFile("image6","image/jpg",imgsix)
                .setMultipartFile("upload_extra","multipart/form-data",extraAddressprooffile)
                .setMultipartFile("doc","multipart/form-data",document)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){
                            progressDailog.dismissDailog();
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                Toast.makeText(HostelActivity.this,"Hostel Saved Successfully",Toast.LENGTH_LONG).show();

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

}


