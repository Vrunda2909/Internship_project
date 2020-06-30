package com.example.sqllearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;

    GoogleSignInClient mGoogleSignInClient;
    TextView name,email;
    ImageView image;
    NavigationView nav1,nav2;
    private FirebaseAuth mAuth;
    private DatabaseReference reff;
    String newemail,Name,Email;
    private Button module1btn,module2btn,module3btn,navlogout;
    String uid;

    private int quizvalue = 0,queryvalue = 0,finalscore = 0;
    private int quizone = 0,quiztwo= 0,quizthree= 0,quizfour= 0,quizfive= 0,queryone= 0,querytwo= 0,querythree= 0,queryfour= 0,queryfive= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StudyFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_study);
        }

        reff = FirebaseDatabase.getInstance().getReference().child("user");
        View navHeaderView=navigationView.getHeaderView(0);
        mAuth = FirebaseAuth.getInstance();
        name=(TextView)navHeaderView.findViewById(R.id.name);
        email=(TextView)navHeaderView.findViewById(R.id.email);
        image=(ImageView)navHeaderView.findViewById(R.id.imageView);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,  googleSignInOptions);
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            String photo = String.valueOf(user.getPhotoUrl());
            Picasso.get().load(photo).into(image);
            //image.setImageURI(signInAccount.getPhotoUrl());
            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
            adduser();
            //Toast.makeText(this,"data inserted ...",Toast.LENGTH_LONG).show();
        }
    }

    private void adduser() {
        Name = name.getText().toString().trim();
        Email = email.getText().toString().trim();

        reff.orderByChild("email").equalTo(Email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() > 0) {
                            //username found
                            //Toast.makeText(getApplicationContext(), "Email found....", Toast.LENGTH_LONG).show();

                        } else {
                            // username not found
                            //Toast.makeText(getApplicationContext(), "Email not found....", Toast.LENGTH_LONG).show();
                            if (!TextUtils.isEmpty(Email)) {
                                //String id = reff.push().getKey();
                                user use = new user(Name, Email,quizvalue,queryvalue,finalscore,quizone,quiztwo,quizthree,quizfour,quizfive,queryone,querytwo,querythree,queryfour,queryfive);
                                reff.child(uid).setValue(use);
                                // Toast.makeText(getApplicationContext(), "Add..", Toast.LENGTH_LONG).show();
                            } else {
                                //Toast.makeText(getApplicationContext(), "enter Email.", Toast.LENGTH_LONG).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void onBackPressed() {
        new AlertDialog.Builder(HomeActivity.this).setTitle("Exit")
                .setMessage("Would you like to exit from Application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent intent = new Intent(nav.this, MainActivity.class);
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        SharedPreferences sp = PreferenceManager
                                .getDefaultSharedPreferences(HomeActivity.this);
                        SharedPreferences.Editor edit = sp.edit();
                        edit.clear();
                        edit.commit();
                        startActivity(intent);

                        finish();  // Call finish here.
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // user doesn't want to logout
                    }
                })
                .show();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        switch(id){
            case R.id.nav_study:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StudyFragment()).commit();
                break;
            case R.id.nav_quiz:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new QuizFragment()).commit();
                break;
            case R.id.nav_query:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new QueryFragment()).commit();
                break;
            case R.id.nav_score:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ScoreFragment()).commit();
                break;
            case R.id.nav_cirtificate:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CirtificateFragment()).commit();
                break;
            case R.id.nav_logout:
//                Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                mGoogleSignInClient.signOut();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
