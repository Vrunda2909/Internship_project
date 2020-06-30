package com.example.sqllearn;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class QuizOneActivity extends AppCompatActivity {

    private TextView question,no_counter;
    private LinearLayout options_layout;
    private Button next_btn;
    private int count = 0;
    private List<QuestionModel> list;
    private int position = 0;
    private int score =0 ;

    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseUser muser;
    private DatabaseReference reff,ref;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_one);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();
//        Toast.makeText(getApplicationContext(),"uid :"+uid,Toast.LENGTH_LONG).show();
        reff = FirebaseDatabase.getInstance().getReference().child("user").child(uid);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        question = findViewById(R.id.question);
        no_counter = findViewById(R.id.no_counter);
        options_layout = findViewById(R.id.option_layout);
        next_btn = findViewById(R.id.next_btn);

        list = new ArrayList<QuestionModel>();
        list.add(new QuestionModel("What does SQL stand for?(Introduction)",
                "Structured Query Language",
                "Strong Question Language",
                "Structured Question Language",
                "Structured Query Language"));
        list.add(new QuestionModel("A database consists of: (Introduction)",
                "Tables",
                "Columns",
                "Rows",
                "Tables"));
        list.add(new QuestionModel("Which choice is the correct command to use when creating a table?(Create)",
                "FIND TABLE",
                "MAKE TABLE",
                "CREATE TABLE",
                "CREATE TABLE"));
        list.add(new QuestionModel("When inserting the data into a table,(insert)",
                "The number of columns in the insert statement and the number of column of the table must be the same.",
                "We don’t have to insert values for all columns in the table.",
                "Names of the columns must always be mentioned in the insert statements.",
                "We don’t have to insert values for all columns in the table."));
        list.add(new QuestionModel("Choose from the options below to select distinct names from the “students” table, ordered by name.(Distinct, Order by)\n" +
                "SELECT ________ name _________ students\n" +
                "________ name;\n",
                "FROM , DISTINCT, ORDER BY",
                "DISTINCT, FROM, ORDER BY",
                "ORDER BY, FROM, DISTINCT",
                "DISTINCT, FROM, ORDER BY"));

        for(int i=0;i<3;i++){
            options_layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer((Button)v);
                }
            });
        }

        playAnim(question,0,list.get(position).getQuestion());
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_btn.setEnabled(false);
                next_btn.setAlpha(0.07f);
                enableoption(true);
                position++;

                if(position == list.size()){

                        reff.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String  squizone = dataSnapshot.child("quizone").getValue().toString();
                                String  squiztwo = dataSnapshot.child("quiztwo").getValue().toString();
                                String  squizthree = dataSnapshot.child("quizthree").getValue().toString();
                                String  squizfour = dataSnapshot.child("quizfour").getValue().toString();
                                String  squizfive = dataSnapshot.child("quizfive").getValue().toString();
                                String  squizvalue = dataSnapshot.child("quizvalue").getValue().toString();

                                int iquizone =  Integer.parseInt(squizone);
                                int iquiztwo =  Integer.parseInt(squiztwo);
                                int iquizthree =  Integer.parseInt(squizthree);
                                int iquizfour =  Integer.parseInt(squizfour);
                                int iquizfive =  Integer.parseInt(squizfive);
                                int iquizvalue =  Integer.parseInt(squizvalue);
                                //Toast.makeText(getApplicationContext(),"quizone : :"+score,Toast.LENGTH_LONG).show();

                                if(iquizone == 0){
                                    reff.child("quizone").setValue(score);
                                }else{
                                    if(iquizone > score){
                                        reff.child("quizone").setValue(iquizone);
                                    }else{
                                        reff.child("quizone").setValue(score);
                                    }
                                }

                                if (iquizvalue == 0){
                                    reff.child("quizvalue").setValue(score);

                                    String  squeryvalue_final = dataSnapshot.child("queryvalue").getValue().toString();
                                    String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                    int iqueryvalue_final =  Integer.parseInt(squeryvalue_final);
                                    int ifinalscore =  Integer.parseInt(sfinalscore);

                                    if(score == 0 && iqueryvalue_final == 0){
                                        reff.child("finalscore").setValue(0);
                                    } else if(score != 0 && iqueryvalue_final == 0){
                                        reff.child("finalscore").setValue(score);
                                    } else if(score == 0 && iqueryvalue_final != 0) {
                                        reff.child("finalscore").setValue(iqueryvalue_final);
                                    } else {
                                        float ffinalscore = (score + iqueryvalue_final) / 2;
                                        reff.child("finalscore").setValue(ffinalscore);
                                    }

                                } else {
                                    if(score > iquizone){
                                        if(iquiztwo == 0 && iquizthree == 0 && iquizfour == 0 && iquizfive ==0){

                                            reff.child("quizvalue").setValue(score);

                                            String  squeryvalue_final = dataSnapshot.child("queryvalue").getValue().toString();
                                            String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                            int iqueryvalue_final =  Integer.parseInt(squeryvalue_final);
                                            int ifinalscore =  Integer.parseInt(sfinalscore);

                                            if(score == 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(0);
                                            } else if(score != 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(score);
                                            } else if(score == 0 && iqueryvalue_final != 0) {
                                                reff.child("finalscore").setValue(iqueryvalue_final);
                                            } else {
                                                float ffinalscore = (score + iqueryvalue_final) / 2;
                                                reff.child("finalscore").setValue(ffinalscore);
                                            }

                                        }else if(iquiztwo != 0 && iquizthree == 0 && iquizfour == 0 && iquizfive ==0){

                                            float fquizvalue = (score + iquiztwo)/2;
                                            reff.child("quizvalue").setValue(fquizvalue);

                                            String  squeryvalue_final = dataSnapshot.child("queryvalue").getValue().toString();
                                            String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                            int iqueryvalue_final =  Integer.parseInt(squeryvalue_final);
                                            int ifinalscore =  Integer.parseInt(sfinalscore);

                                            if(fquizvalue == 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(0);
                                            } else if(fquizvalue != 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(fquizvalue);
                                            } else if(fquizvalue == 0 && iqueryvalue_final != 0) {
                                                reff.child("finalscore").setValue(iqueryvalue_final);
                                            } else {
                                                float ffinalscore = (fquizvalue + iqueryvalue_final) / 2;
                                                reff.child("finalscore").setValue(ffinalscore);
                                            }


                                        } else if(iquiztwo != 0 && iquizthree != 0 &&iquizfour == 0 && iquizfive ==0){

                                            float fquizvalue = (score + iquiztwo +iquizthree)/3;
                                            reff.child("quizvalue").setValue(fquizvalue);

                                            String  squeryvalue_final = dataSnapshot.child("queryvalue").getValue().toString();
                                            String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                            int iqueryvalue_final =  Integer.parseInt(squeryvalue_final);
                                            int ifinalscore =  Integer.parseInt(sfinalscore);

                                            if(fquizvalue == 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(0);
                                            } else if(fquizvalue != 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(fquizvalue);
                                            } else if(fquizvalue == 0 && iqueryvalue_final != 0) {
                                                reff.child("finalscore").setValue(iqueryvalue_final);
                                            } else {
                                                float ffinalscore = (fquizvalue + iqueryvalue_final) / 2;
                                                reff.child("finalscore").setValue(ffinalscore);
                                            }


                                        } else if(iquiztwo != 0 && iquizthree != 0 &&iquizfour != 0 && iquizfive == 0) {

                                            float fquizvalue = (score + iquiztwo +iquizthree +iquizfour)/4;
                                            reff.child("quizvalue").setValue(fquizvalue);

                                            String  squeryvalue_final = dataSnapshot.child("queryvalue").getValue().toString();
                                            String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                            int iqueryvalue_final =  Integer.parseInt(squeryvalue_final);
                                            int ifinalscore =  Integer.parseInt(sfinalscore);

                                            if(fquizvalue == 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(0);
                                            } else if(fquizvalue != 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(fquizvalue);
                                            } else if(fquizvalue == 0 && iqueryvalue_final != 0) {
                                                reff.child("finalscore").setValue(iqueryvalue_final);
                                            } else {
                                                float ffinalscore = (fquizvalue + iqueryvalue_final) / 2;
                                                reff.child("finalscore").setValue(ffinalscore);
                                            }


                                        } else {

                                            float fquizvalue = (score + iquiztwo +iquizthree +iquizfour +iquizfive)/5;
                                            reff.child("quizvalue").setValue(fquizvalue);

                                            String  squeryvalue_final = dataSnapshot.child("queryvalue").getValue().toString();
                                            String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                            int iqueryvalue_final =  Integer.parseInt(squeryvalue_final);
                                            int ifinalscore =  Integer.parseInt(sfinalscore);

                                            if(fquizvalue == 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(0);
                                            } else if(fquizvalue != 0 && iqueryvalue_final == 0){
                                                reff.child("finalscore").setValue(fquizvalue);
                                            } else if(fquizvalue == 0 && iqueryvalue_final != 0) {
                                                reff.child("finalscore").setValue(iqueryvalue_final);
                                            } else {
                                                float ffinalscore = (fquizvalue + iqueryvalue_final) / 2;
                                                reff.child("finalscore").setValue(ffinalscore);
                                            }

                                        }

                                    } else {
                                        reff.child("quizvalue").setValue(iquizvalue);

                                        String  squeryvalue_final = dataSnapshot.child("queryvalue").getValue().toString();
                                        String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                        int iqueryvalue_final =  Integer.parseInt(squeryvalue_final);
                                        int ifinalscore =  Integer.parseInt(sfinalscore);

                                        if(iquizvalue == 0 && iqueryvalue_final == 0){
                                            reff.child("finalscore").setValue(0);
                                        } else if(iquizvalue != 0 && iqueryvalue_final == 0){
                                            reff.child("finalscore").setValue(iquizvalue);
                                        } else if(iquizvalue == 0 && iqueryvalue_final != 0) {
                                            reff.child("finalscore").setValue(iqueryvalue_final);
                                        } else {
                                            float ffinalscore = (iquizvalue + iqueryvalue_final) / 2;
                                            reff.child("finalscore").setValue(ffinalscore);
                                        }
                                    }
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    Intent scoreintent = new Intent(getApplicationContext(), ScoreActivity.class);
                    finish();
                    scoreintent.putExtra("score",score);
                    scoreintent.putExtra("total",list.size());
                    startActivity(scoreintent);
                    return;

                }

                count =0 ;
                playAnim(question,0,list.get(position).getQuestion());
            }
        });
    }




    private void enableoption(boolean enable) {
        for(int i=0;i<3;i++){
            options_layout.getChildAt(i).setEnabled(enable);
            if(enable){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    options_layout.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#3d3636")));
                }
            }
        }
    }

    private void playAnim(final View view,final int value,final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(1).setDuration(500).setStartDelay(50)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value == 0 && count<3){
                    String option = "";
                    if(count == 0){
                        option = list.get(position).getOptionA();
                    }
                    else if(count ==1){
                        option = list.get(position).getOptionB();
                    }
                    else if(count ==2){
                        option = list.get(position).getOptionC();
                    }
                    playAnim(options_layout.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(value ==0){
                    try{
                        ((TextView)view).setText(data);
                        no_counter.setText(position+1+"/"+list.size());
                    }
                    catch (ClassCastException e){
                        ((Button)view).setText(data);
                    }
                    view.setTag(data);

                    playAnim(view,1,data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void checkAnswer(Button selectedoption) {
        enableoption(false);
        next_btn.setEnabled(true);
        next_btn.setAlpha(1);
        if(selectedoption.getText().toString().equals(list.get(position).getCorrectAnswer())) {
            score = score + 20;
//            score++;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0e9913")));
            }
            //Toast.makeText(getApplicationContext(), "Right Answer", Toast.LENGTH_LONG).show();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            }
            Button correctoption = (Button) options_layout.findViewWithTag(list.get(position).getCorrectAnswer());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0e9913")));
            }
            //Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_LONG).show();
        }
    }


    public void onBackPressed(){
        new AlertDialog.Builder(QuizOneActivity.this).setTitle("Exit")
                .setMessage("Would you like to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent intent = new Intent(nav.this, MainActivity.class);
                        Intent intent = new Intent(getApplicationContext(), QuizFragment.class);
                        SharedPreferences sp = PreferenceManager
                                .getDefaultSharedPreferences(QuizOneActivity.this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == android.R.id.home){
//            Intent intent = new Intent(this, QuizFragment.class);
//            startActivity(intent);
//            finish();
//        }
        new AlertDialog.Builder(QuizOneActivity.this).setTitle("Exit")
                .setMessage("Would you like to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent intent = new Intent(nav.this, MainActivity.class);
                        Intent intent = new Intent(getApplicationContext(), QuizFragment.class);
                        SharedPreferences sp = PreferenceManager
                                .getDefaultSharedPreferences(QuizOneActivity.this);
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
        return super.onOptionsItemSelected(item);
    }
}
