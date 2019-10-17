package com.example.cargo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;
import com.sucho.placepicker.MapType;
import com.sucho.placepicker.PlacePicker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateDealer extends AppCompatActivity {

    EditText Name,Phone,Phone2,Phone3,FB,Insta,City,Street;
    Button ProfilePicture,Location,Finish;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    ImageView imageView;
    String imageUrl;


    private StorageReference mStorageRef;
    private StorageTask mUploadTask;


    ProgressBar mProgressBar;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dealer);





        Finish = findViewById(R.id.createDealer_finish);
        ProfilePicture = findViewById(R.id.createDealer_profilepicture);
        Location = findViewById(R.id.createDealer_location);

        Name = findViewById(R.id.createDealer_name);
        Phone = findViewById(R.id.createDealer_Phone1);
        Phone2 = findViewById(R.id.createDealer_Phone2);
        Phone3 = findViewById(R.id.createDealer_Phone3);
        FB = findViewById(R.id.createDealer_fburl);
        Insta = findViewById(R.id.createDealer_instaurl);
        City = findViewById(R.id.createDealer_City);
        Street = findViewById(R.id.createDealer_street);
        imageView = findViewById(R.id.createDealer_Picture);
        mProgressBar = findViewById(R.id.progress_bar);


        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");









        ProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseImage();


            }
        });
        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                uploadFile();



            }
        });



        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new PlacePicker.IntentBuilder()
                        .setLatLong(40.748672, -73.985628)
                        .showLatLong(true)
                        .setMapRawResourceStyle(R.raw.map_style)
                        .setMapType(MapType.NORMAL)
                        .build(CreateDealer.this);

                startActivityForResult(intent, Constants.PLACE_PICKER_REQUEST);



            }
        });

    }


    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                mImageUri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri);

                    // Log.d(TAG, String.valueOf(bitmap));

                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }



        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                AddressData addressData = data.getParcelableExtra(Constants.ADDRESS_INTENT);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }







    }


    private void uploadFile() {
        if (mImageUri != null) {

            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);


                            if (taskSnapshot.getMetadata() != null) {
                                if (taskSnapshot.getMetadata().getReference() != null) {
                                    final Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {


                                            imageUrl = uri.toString();
                                            Toast.makeText(getApplicationContext(), "Dealer is Created", Toast.LENGTH_LONG).show();

                                            addtodatabaseFirebase();

                                            Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                                            startActivity(intent);


                                            //createNewPost(imageUrl);
                                        }
                                    });
                                }
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }



    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    public void addtodatabaseFirebase(){


        final Map<String, Object> Dealer = new HashMap<>();
        Dealer.put("Name", Name.getText().toString());
        Dealer.put("Phone", Phone.getText().toString());
        Dealer.put("Facebook", FB.getText().toString());
        Dealer.put("Instagram", Insta.getText().toString());
        Dealer.put("City", City.getText().toString());
        Dealer.put("Street", Street.getText().toString());
        Dealer.put("ProfilePicture", imageUrl);
        Dealer.put("Location", "" );

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final String id = firebaseAuth.getUid();


        db.collection("Dealers").document(id)
                .set(Dealer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext() , "Dealer Created" , Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });


    }

}
