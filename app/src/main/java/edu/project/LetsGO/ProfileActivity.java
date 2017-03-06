package edu.project.LetsGO;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "Profile Activity";
    private static final int PICK_IMAGE = 0;
    private ImageView profilePhoto;
    private EditText inputFullName;
    private Button letsGO;
    private FirebaseAuth auth;
    private FirebaseUser user;
    //    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase Auth and Database Reference
        auth = FirebaseAuth.getInstance();
//        uid= auth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        letsGO = (Button) findViewById(R.id.letsGO);
        inputFullName = (EditText) findViewById(R.id.full_name);
        profilePhoto = (ImageView) findViewById(R.id.imageView);
        final String uid = user.getUid();

        letsGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                mDatabase.child("users").child(uid).child("name").setValue(inputFullName);
            }
        });

        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

/*
        mDatabase.child("users").child(uid).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                adapter.add((String) dataSnapshot.child("title").getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.remove((String) dataSnapshot.child("title").getValue());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }


    //show selected image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}


//For Taking picture
//Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//startActivityForResult(takePicture, 0);//zero can be replaced with any action code

//for picking photo from gallery
//Intent pickPhoto = new Intent(Intent.ACTION_PICK,
//      android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
