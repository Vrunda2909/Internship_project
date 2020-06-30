package com.example.sqllearn;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ScoreFragment extends Fragment {
    private DrawerLayout drawer;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseUser muser;
    private DatabaseReference reff;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String uid;
    private TextView name,email,quizvalue,queryvalue,finalscore,quizone,quiztwo,quizthree,quizfour,quizfive,queryone,querytwo,querythree,queryfour,queryfive;
    private TextView quizvaluepass,queryvaluepass,finalscorepass,quizonepass,quiztwopass,quizthreepass,quizfourpass,quizfivepass,queryonepass,querytwopass,querythreepass,queryfourpass,queryfivepass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scorecard, container, false);
        Toolbar toolbar = root.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        drawer = root.findViewById(R.id.drawer_layout);

        name = root.findViewById(R.id.nametxt);
        email = root.findViewById(R.id.emailtxt);
        quizvalue = root.findViewById(R.id.quizvaluetxt);
        queryvalue = root.findViewById(R.id.queryvaluetxt);
        finalscore = root.findViewById(R.id.finalscoretxt);
        quizone = root.findViewById(R.id.quizonetxt);
        quiztwo = root.findViewById(R.id.quiztwotxt);
        quizthree = root.findViewById(R.id.quizthreetxt);
        quizfour = root.findViewById(R.id.quizfourtxt);
        quizfive = root.findViewById(R.id.quizfivetxt);
        queryone = root.findViewById(R.id.queryonetxt);
        querytwo = root.findViewById(R.id.querytwotxt);
        querythree = root.findViewById(R.id.querythreetxt);
        queryfour = root.findViewById(R.id.queryfourtxt);
        queryfive = root.findViewById(R.id.queryfivetxt);

        quizvaluepass = root.findViewById(R.id.quizvaluepasstxt);
        queryvaluepass = root.findViewById(R.id.queryvaluepasstxt);
        finalscorepass = root.findViewById(R.id.finalscorepasstxt);
        quizonepass = root.findViewById(R.id.quizonepasstxt);
        quiztwopass = root.findViewById(R.id.quiztwopasstxt);
        quizthreepass = root.findViewById(R.id.quizthreepasstxt);
        quizfourpass = root.findViewById(R.id.quizfourpasstxt);
        quizfivepass = root.findViewById(R.id.quizfivepasstxt);
        queryonepass = root.findViewById(R.id.queryonepasstxt);
        querytwopass = root.findViewById(R.id.querytwopasstxt);
        querythreepass = root.findViewById(R.id.querythreepasstxt);
        queryfourpass = root.findViewById(R.id.queryfourpasstxt);
        queryfivepass = root.findViewById(R.id.queryfivepasstxt);


        muser = FirebaseAuth.getInstance().getCurrentUser();
        //uid = muser.getUid();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();
        //Toast.makeText(getContext(),"uid :"+uid,Toast.LENGTH_LONG).show();

        reff = FirebaseDatabase.getInstance().getReference().child("user").child(uid);


        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(),  googleSignInOptions);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String  squizvalue = dataSnapshot.child("quizvalue").getValue().toString();
                String  squizone = dataSnapshot.child("quizone").getValue().toString();
                String  squiztwo = dataSnapshot.child("quiztwo").getValue().toString();
                String  squizthree = dataSnapshot.child("quizthree").getValue().toString();
                String  squizfour = dataSnapshot.child("quizfour").getValue().toString();
                String  squizfive = dataSnapshot.child("quizfive").getValue().toString();
                String  squeryvalue = dataSnapshot.child("queryvalue").getValue().toString();
                String  squeryone = dataSnapshot.child("queryone").getValue().toString();
                String  squerytwo = dataSnapshot.child("querytwo").getValue().toString();
                String  squerythree = dataSnapshot.child("querythree").getValue().toString();
                String  squeryfour = dataSnapshot.child("queryfour").getValue().toString();
                String  squeryfive = dataSnapshot.child("queryfive").getValue().toString();
                String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                int iqueryone =  Integer.parseInt(squeryone);
                int iquerytwo =  Integer.parseInt(squerytwo);
                int iquerythree =  Integer.parseInt(squerythree);
                int iqueryfour =  Integer.parseInt(squeryfour);
                int iqueryfive =  Integer.parseInt(squeryfive);
                float iqueryvalue = Float.parseFloat(squeryvalue);
                int iquizone =  Integer.parseInt(squizone);
                int iquiztwo =  Integer.parseInt(squiztwo);
                int iquizthree =  Integer.parseInt(squizthree);
                int iquizfour =  Integer.parseInt(squizfour);
                int iquizfive =  Integer.parseInt(squizfive);
                float iquizvalue =  Float.parseFloat(squizvalue);
                float ifinalscore =  Float.parseFloat(sfinalscore);

                String fsfinalscore = Float.toString(ifinalscore);
                String fsquizvalue = Float.toString(iquizvalue);
                String fsqueryvalue = Float.toString(iqueryvalue);


//                String fsquizvalue =Integer.toString(iquizvalue);
//                String fsquizone =Integer.toString(iquizone);
//                String fsquiztwo =Integer.toString(iquiztwo);
//                String fsquizthree =Integer.toString(iquizthree);
//                String fsquizfour =Integer.toString(iquizfour);
//                String fsquizfive =Integer.toString(iquizfive);
//                String fsqueryvalue =Integer.toString(iqueryvalue);
//                String fsqueryone =Integer.toString(iqueryone);
//                String fsquerytwo =Integer.toString(iquerytwo);
//                String fsquerythree =Integer.toString(iquerythree);
//                String fsqueryfour =Integer.toString(iqueryfour);
//                String fsqueryfive =Integer.toString(iqueryfive);
//                String fsfinalscore =Integer.toString(ifinalscore);


                if(iquizvalue > 70){
                    quizvalue.setTextColor(getResources().getColor(R.color.colorgreen));
                    quizvalue.setText(fsquizvalue);
                    quizvaluepass.setVisibility(View.VISIBLE);
                }else{
                    quizvalue.setTextColor(getResources().getColor(R.color.colorRed));
                    quizvalue.setText(fsquizvalue);
                }

                if(iquizone > 70){
                    quizone.setTextColor(getResources().getColor(R.color.colorgreen));
                    quizone.setText(squizone);
                    quizonepass.setVisibility(View.VISIBLE);
                }else{
                    quizone.setTextColor(getResources().getColor(R.color.colorRed));
                    quizone.setText(squizone);
                }

                if(iquiztwo > 70){
                    quiztwo.setTextColor(getResources().getColor(R.color.colorgreen));
                    quiztwo.setText(squiztwo);
                    quiztwopass.setVisibility(View.VISIBLE);
                }else{
                    quiztwo.setTextColor(getResources().getColor(R.color.colorRed));
                    quiztwo.setText(squiztwo);
                }

                if(iquizthree > 70){
                    quizthree.setTextColor(getResources().getColor(R.color.colorgreen));
                    quizthree.setText(squizthree);
                    quizthreepass.setVisibility(View.VISIBLE);
                }else{
                    quizthree.setTextColor(getResources().getColor(R.color.colorRed));
                    quizthree.setText(squizthree);
                }

                if(iquizfour > 70){
                    quizfour.setTextColor(getResources().getColor(R.color.colorgreen));
                    quizfour.setText(squizfour);
                    quizfourpass.setVisibility(View.VISIBLE);
                }else{
                    quizfour.setTextColor(getResources().getColor(R.color.colorRed));
                    quizfour.setText(squizfour);
                }

                if(iquizfive > 70){
                    quizfive.setTextColor(getResources().getColor(R.color.colorgreen));
                    quizfive.setText(squizfive);
                    quizfivepass.setVisibility(View.VISIBLE);
                }else{
                    quizfive.setTextColor(getResources().getColor(R.color.colorRed));
                    quizfive.setText(squizfive);
                }

                if(iqueryvalue > 70){
                    queryvalue.setTextColor(getResources().getColor(R.color.colorgreen));
                    queryvalue.setText(fsqueryvalue);
                    queryvaluepass.setVisibility(View.VISIBLE);
                }else{
                    queryvalue.setTextColor(getResources().getColor(R.color.colorRed));
                    queryvalue.setText(fsqueryvalue);
                }

                if(iqueryone > 70){
                    queryone.setTextColor(getResources().getColor(R.color.colorgreen));
                    queryone.setText(squeryone);
                    queryonepass.setVisibility(View.VISIBLE);
                }else{
                    queryone.setTextColor(getResources().getColor(R.color.colorRed));
                    queryone.setText(squeryone);
                }

                if(iquerytwo > 70){
                    querytwo.setTextColor(getResources().getColor(R.color.colorgreen));
                    querytwo.setText(squerytwo);
                    querytwopass.setVisibility(View.VISIBLE);
                }else{
                    querytwo.setTextColor(getResources().getColor(R.color.colorRed));
                    querytwo.setText(squerytwo);
                }

                if(iquerythree > 70){
                    querythree.setTextColor(getResources().getColor(R.color.colorgreen));
                    querythree.setText(squerythree);
                    querythreepass.setVisibility(View.VISIBLE);
                }else{
                    querythree.setTextColor(getResources().getColor(R.color.colorRed));
                    querythree.setText(squerythree);
                }

                if(iqueryfour > 70){
                    queryfour.setTextColor(getResources().getColor(R.color.colorgreen));
                    queryfour.setText(squeryfour);
                    queryfourpass.setVisibility(View.VISIBLE);
                }else{
                    queryfour.setTextColor(getResources().getColor(R.color.colorRed));
                    queryfour.setText(squeryfour);
                }

                if(iqueryfive > 70){
                    queryfive.setTextColor(getResources().getColor(R.color.colorgreen));
                    queryfive.setText(squeryfive);
                    queryfivepass.setVisibility(View.VISIBLE);
                }else{
                    queryfive.setTextColor(getResources().getColor(R.color.colorRed));
                    queryfive.setText(squeryfive);
                }

                if(ifinalscore > 70){
                    finalscore.setTextColor(getResources().getColor(R.color.colorgreen));
                    finalscore.setText(fsfinalscore);
                    finalscorepass.setVisibility(View.VISIBLE);
                }else{
                    finalscore.setTextColor(getResources().getColor(R.color.colorRed));
                    finalscore.setText(fsfinalscore);
                }


//                quizvalue.setText(iquizvalue);
//                quizone.setText(iquizone);
//                quiztwo.setText(iquiztwo);
//                quizthree.setText(iquizthree);
//                quizfour.setText(iquizfour);
//                quizfive.setText(iquizfive);
//                queryvalue.setText(iqueryvalue);
//                queryone.setText(iqueryone);
//                querytwo.setText(iquerytwo);
//                querythree.setText(iquerythree);
//                queryfour.setText(iqueryfour);
//                queryfive.setText(iqueryfive);
//                finalscore.setText(ifinalscore);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(signInAccount != null){
            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
            //Toast.makeText(this,"data inserted ...",Toast.LENGTH_LONG).show();
        }

        return root;
    }
}
