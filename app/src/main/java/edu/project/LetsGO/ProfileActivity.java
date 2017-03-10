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

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "Profile Activity";
    private static final int PICK_IMAGE = 0;
    private ImageView profilePhoto;
    private EditText firstname, lastname, emailadd, phonenumb;
    private Button letsGO;
    private FirebaseAuth auth;
    private FirebaseUser user;
    //private StorageReference mStorageRef;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase Auth and Database Reference
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
//            //Go to login
//            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
//            finish();
//        } else {
//            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        }


//        if (user != null) {
//            // Name, email address, and profile photo Url
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            // Check if user's email is verified
//            boolean emailVerified = user.isEmailVerified();
//
//             The user's ID, unique to the Firebase project. Do NOT use this value to
//             authenticate with your backend server, if you have one. Use
//             FirebaseUser.getToken() instead.
//            String uid = user.getUid();
//
//            System.out.println(name + email + emailVerified + photoUrl + uid);
//        }


        letsGO = (Button) findViewById(R.id.letsGO);
        firstname = (EditText) findViewById(R.id.first_name);
        profilePhoto = (ImageView) findViewById(R.id.profile_pic);
        lastname = (EditText) findViewById(R.id.last_name);
        emailadd = (EditText) findViewById(R.id.email_add);
        phonenumb = (EditText) findViewById(R.id.phone_number);

        final String firstName = firstname.getText().toString();
        final String lastName = lastname.getText().toString();
        final String emailAdd = emailadd.getText().toString();
        final String phoneNumb = firstname.getText().toString();
//        final User uinfo= new User();
//        uinfo.setFirstName(firstname);

        letsGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = user.getUid();
                System.out.println("UID" + uid);
                mDatabase.child("Users").child(uid).child("First Name").setValue(firstName);
                mDatabase.child("Users").child(uid).child("Last Name").setValue(lastName);
                mDatabase.child("Users").child(uid).child("Email").setValue(emailAdd);
                mDatabase.child("Users").child(uid).child("Phone").setValue(phoneNumb);
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  For Taking picture
                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, 1);//zero can be replaced with any action code

                //for picking photo from gallery
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                //                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                //                Intent intent = new Intent();
                //// Show only images, no videos or anything else
                //                intent.setType("image/*");
                //                intent.setAction(Intent.ACTION_GET_CONTENT);
                //// Always show the chooser (if there are multiple options available)
                //                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
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

                ImageView imageView = (ImageView) findViewById(R.id.profile_pic);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
