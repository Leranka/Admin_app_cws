package com.example.admin.admin_app_cws;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.admin_app_cws.model.Slide;
import com.example.admin.admin_app_cws.model.details;
import com.example.admin.admin_app_cws.model.working_hours;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRefPlaces, mDatabaseRefSlide, mDatabaseRefWorkingHours;
    private ImageView imageView;
    FirebaseDatabase database;

    // fields of the details
    String placeLongitude, placeLatitude, placeAddress, placeCell, placeHours, placeInfo, placeName, PlacePrice, placeWebsite, openTime, closeTime;

    String urI, uri2, uri3;

    String featUri,featUr2,featUri3;

    boolean valid = false;
    private EditText EdtPlaceName, EdtPlaceInfor, EdtAddress, EdtCell, edtWorkingHours, EdtWebsite, edtLongitude, edtLatitude, edtPrice, edtCloseTime, edtOpenTime;
    private Uri imgUri, imgUri2, imgUri3;


    public static final String FB_STORAGE_PATH = "new_places/";

    public static final int REQUEST_CODE = 1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mStorageRef = FirebaseStorage.getInstance().getReference();

        mDatabaseRefPlaces = FirebaseDatabase.getInstance().getReference("new_places");
        mDatabaseRefSlide = FirebaseDatabase.getInstance().getReference("new_Slide");
        database = FirebaseDatabase.getInstance();


        mDatabaseRefWorkingHours = database.getReference( ).child("new_working_hours");

        imageView = (ImageView) findViewById(R.id.ImageView);

        EdtPlaceName = (EditText) findViewById(R.id.EdtPlaceName);
        EdtPlaceInfor = (EditText) findViewById(R.id.EdtPlaceInfor);
        EdtAddress = (EditText) findViewById(R.id.EdtAddress);
        EdtCell = (EditText) findViewById(R.id.EdtCell);
        edtWorkingHours = (EditText) findViewById(R.id.edtWorkingHours);
        EdtWebsite = (EditText) findViewById(R.id.EdtWebsite);
        edtLongitude = (EditText) findViewById(R.id.edtLongitude);
        edtLatitude = (EditText) findViewById(R.id.edtLatitude);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtCloseTime = (EditText) findViewById(R.id.edtCloseTime);
        edtOpenTime = (EditText) findViewById(R.id.edtOpenTime);

    }

    //Browse image to upload for pic 1
    public void upSlidePic(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), REQUEST_CODE);

    }

    //Browse image to upload for pic 2
    public void upSlidePic2(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), REQUEST_CODE);
    }

    //Browse image to upload for pic 3
    public void upSlidePic3(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Slide pic 1
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Slide pic 2
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri2 = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri2);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        //Slide pic 3
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imgUri3 = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri3);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public String getIamgeExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    public String getIamgeExt2(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    public String getIamgeExt3(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    //Uploading image
    @SuppressWarnings("VisibleForTests")
    public void btnUpload_Click(View view) {


        /*if (EdtPlaceName.length() > 0 &&
                EdtPlaceInfor.length() > 0 &&
                EdtAddress.length() > 0 &&
                edtLongitude.length() > 0 &&
                EdtCell.length() > 0 &&
                edtWorkingHours.length() > 0 &&
                EdtWebsite.length() > 0 &&
                edtLongitude.length()>0 &&
                edtLatitude.length() >0) {*/

        if (imgUri != null) {
            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("saving");
            dialog.show();

            //Get the storage reference
            StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getIamgeExt(imgUri));
            StorageReference ref2 = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getIamgeExt2(imgUri2));
            StorageReference ref3 = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getIamgeExt3(imgUri3));


//for silde 2
            ref2.putFile(imgUri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    uri2 = taskSnapshot.getDownloadUrl().toString();

                }
            });

//for silde 3
            ref3.putFile(imgUri3).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    uri3 = taskSnapshot.getDownloadUrl().toString();

                }
            });


            ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    //Dimiss dialog when success
                    dialog.dismiss();
                    //Display success toast msg
                    Toast.makeText(getApplicationContext(), "saved", Toast.LENGTH_SHORT).show();

                    placeLongitude = edtLongitude.getText().toString().trim();
                    placeLatitude = edtLatitude.getText().toString().trim();
                    placeAddress = EdtAddress.getText().toString();
                    placeCell = EdtCell.getText().toString().trim();
                    placeHours = edtWorkingHours.getText().toString().trim();
                    placeInfo = EdtPlaceInfor.getText().toString().trim();
                    placeName = EdtPlaceName.getText().toString();
                    PlacePrice = edtPrice.getText().toString();
                    placeWebsite = EdtWebsite.getText().toString().trim();


                    urI = taskSnapshot.getDownloadUrl().toString();

                    String key = mDatabaseRefPlaces.push().getKey();

                    details details = new details(placeLatitude, placeLongitude, placeAddress, placeCell, placeHours, placeInfo, placeName, PlacePrice, placeWebsite);

                    Slide slide = new Slide(urI + "", uri2 + "", "" + uri3);


                    mDatabaseRefPlaces.child(key).child("details").setValue(details);
                    mDatabaseRefSlide.child(placeName).setValue(slide);
                    saveHours();

                    //clearing the EditText
                    EdtPlaceName.getText().clear();
                    EdtPlaceInfor.getText().clear();
                    EdtAddress.getText().clear();
                    EdtCell.getText().clear();

                    edtWorkingHours.getText().clear();
                    EdtWebsite.getText().clear();
                    edtLongitude.getText().clear();
                    edtLatitude.getText().clear();
                    edtPrice.getText().clear();


                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            //Dimiss dialog when error
                            dialog.dismiss();
                            //Display err toast msg
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            //Show upload progress

                            double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            dialog.setMessage((int) progress + "%");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Please upload a picture", Toast.LENGTH_SHORT).show();


        }
       /* } else {

            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }*/


    }


    public void saveHours( ) {



        if (valid == false) {

            openTime = edtOpenTime.getText().toString().trim();
            closeTime = edtCloseTime.getText().toString().trim();


            mDatabaseRefWorkingHours.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                   working_hours hours = new working_hours(closeTime, openTime);
                    mDatabaseRefWorkingHours.child(placeName).setValue(hours);
                    valid =true;
                    /*mDatabaseRefWorkingHours = FirebaseDatabase.getInstance().getReference("new_working_hours");

                    mDatabaseRefWorkingHours.setValue(hours);*/
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {
            Toast.makeText(this, " already saved", Toast.LENGTH_SHORT).show();
        }

    }

}
