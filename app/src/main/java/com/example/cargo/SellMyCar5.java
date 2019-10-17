package com.example.cargo;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SellMyCar5 extends AppCompatActivity {


    ImageView imageView;
    int test;
    private RecyclerView recyclerView;
    private ArrayList<Uri> images = new ArrayList<>();

    private static final String TAG = MainActivity.class.getSimpleName();

    private int PICK_IMAGE_REQUEST = 1;




    private ArrayList<String> SecondaryPictures = new ArrayList<>();
    private String MainPicture ;




    private TextView Finish;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;
    String imageUrl22;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_my_car5);


        imageView = findViewById(R.id.iv_mainimage);
        imageView.setVisibility(View.INVISIBLE);
        Finish = findViewById(R.id.finish);
        mProgressBar = findViewById(R.id.progress_bar);




        mProgressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);













        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(getApplicationContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFileMutli();


                }





            }
        });


        Button SelectMainImage = findViewById(R.id.bt_Mainimage);

        SelectMainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test = 1;

                chooseImage();

            }
        });


        Button SelectSecondImage = findViewById(R.id.bt_SecondaryImages);

        SelectSecondImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test = 2;
                chooseMultiImage();
            }
        });




        ///// work for the database
        /// work for image upload

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");


        /// work for image upload (upload done above at uploadfile() and uploadfileMulti()


        //Data base firestore work

        //Data base firestore work

    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    public void chooseMultiImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (test == 1) {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                mImageUri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri);

                    // Log.d(TAG, String.valueOf(bitmap));

                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }    else if(test == 2){

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK ) {

                Uri uri = data.getData();
                if(uri!=null){
                    images.add(uri);
                }else if(requestCode == PICK_IMAGE_REQUEST && requestCode == RESULT_OK){

                    ClipData clipData = data.getClipData();
                    if(clipData != null){

                       for(int i =0 ; i< clipData.getItemCount() ; i++){
                           ClipData.Item item = clipData.getItemAt(i);
                           images.add(item.getUri());
                       }

                    }
                }



            }


            }

        recycleviewcreate3();


    }


    private void recycleviewcreate3(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_pictures);
        sellimageRVadapter adapter = new sellimageRVadapter(images, getApplicationContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(),  LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }




    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
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

/*
                                            String i= result.getResult().toString();
*/
                                            imageUrl22 = uri.toString();
                                            Toast.makeText(getApplicationContext(), "Car is listed For Sale", Toast.LENGTH_LONG).show();

                                            addtodatabaseFirebase();

                                            Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                                            startActivity(intent);


                                            //createNewPost(imageUrl);
                                        }
                                    });
                                }
                            }




                            /*
                            Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);

                             */
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


    private void uploadFileMutli() {

        int i =0;
        for( i = 0 ; i<images.size() ; i++) {
            if (images.get(i) != null) {
                final int i2 =i;
                StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                        + "." + getFileExtension(images.get(i)));

                mUploadTask = fileReference.putFile(images.get(i))
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
                                        Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {


                                                String imageUrl = uri.toString();
                                                SecondaryPictures.add(imageUrl);
                                                if (i2 == images.size()-1){
                                                    uploadFile();
                                                }


                                                //createNewPost(imageUrl);
                                            }
                                        });
                                    }
                                }




                            /*
                            Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);



                             */


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
    }


    void addtodatabaseFirebase(){

        final Bundle extras = getIntent().getExtras();

        final String Brand = extras.getString("Brand");
        final String Model = extras.getString("Model");
        final String Year = extras.getString("Year");
        final String Color = extras.getString("Color");
        final String Kilometers = extras.getString("Kilometers");
        final String Fuel = extras.getString("Fuel");
        final String Trasmission  = extras.getString("Transmission");
        final String engine =  extras.getString("engine");
        final String Type  = extras.getString("Type");
        final String ExtraInformation = extras.getString("ExtraInformation");
        String ListedForSale = extras.getString("ListedForSale");
        String ListedForBadl = extras.getString("ListedForBadl");
        String PaymentCash = extras.getString("PaymentCash");
        String PaymentTakset = extras.getString("PaymentTakset");
        final String Price = extras.getString("Price");


        final ArrayList<String> PaymentMethod = new ArrayList<>();
        final ArrayList<String> ListedFor = new ArrayList<>();

        ListedFor.add(ListedForSale);
        ListedFor.add(ListedForBadl);

        PaymentMethod.add(PaymentCash);
        PaymentMethod.add(PaymentTakset);


        final FirebaseFirestore db = FirebaseFirestore.getInstance();


        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final String id = firebaseAuth.getUid();

        final String[] Name = new String[1];
        final String[] City = new String[1];


            /// get the user name and city

        db.collection("Users")
                .document(id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Name[0] = documentSnapshot.getString("Name");
                        City[0] = documentSnapshot.getString("City");


                        // get the car information
                        Map < String, Object > user = new HashMap<>();
                        user.put("Brad", Brand);
                        user.put("Model", Model);
                        user.put("Year", Year);
                        user.put("Color", Color);
                        user.put("Kilometers", Kilometers);
                        user.put("Fuel", Fuel);
                        user.put("Trasmission", Trasmission);
                        user.put("engine", engine);
                        user.put("Type", Type);
                        user.put("Price" , Price);
                        user.put("ExtraInformation", ExtraInformation);
                        user.put("ListedFor", ListedFor);
                        user.put("PaymentMethod", PaymentMethod);
                        user.put("MainPicture", imageUrl22 );
                        user.put("SecondaryPhotos", SecondaryPictures);
                        user.put("SpecialOffer" , 0);
                        user.put("City" , City[0]);
                        user.put("Dealer" , Name[0]);
                        user.put("Location" , City[0]);
                        user.put("Phone" ,  firebaseAuth.getCurrentUser().getPhoneNumber() );

                        // Add a new document with a generated ID
                        db.collection("CarsForSale")
                                .add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });



                    }
                });












    }




}
