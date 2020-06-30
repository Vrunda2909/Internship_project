package com.example.sqllearn;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QueryOneActivity extends AppCompatActivity {

    private TextView question,no_counter,linktext;
    private EditText option;
    private LinearLayout options_layout;
    private Button next_btn,check_btn;
    private int count = 0;
    private List<QueryQuestionModel> list;
    private int position = 0;
    private int score;
    private WebView webView;
    String check,nospace;
    private ImageView setimage;
//    int []  imagedefaultlist = {R.drawable.thumb,R.drawable.mini,R.drawable.thumb,R.drawable.mini,R.drawable.thumb};
    String []  imagedefaultlist = {"queryone_defaultone","notable","queryone_defaultone","queryone_defaultone","queryone_defaultone"};
    String []  imagetruelist = {"queryone_trueone","queryone_truetwo","queryone_truethree","queryone_truefour","queryone_truefive"};
    String []  imagefalselist = {"wrong","wrong","wrong","wrong","wrong"};
//    int []  imagetruelist = {R.drawable.onetrue,R.drawable.twotrue};
//    int []  imagefalselist = {R.drawable.onefalse,R.drawable.twofasle};
    int []  linklist = {R.string.queryone_linkone,R.string.queryone_linktwo,R.string.queryone_linkthree,R.string.queryone_linkfour,R.string.queryone_linkfive};
    int imagecounter = 0;

    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseUser muser;
    private DatabaseReference reff;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_one);

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
        option = findViewById(R.id.optionbtn);
        linktext = findViewById(R.id.linktext);
//        setimage = findViewById(R.id.setimage);
        check_btn = findViewById(R.id.check_btn);
        webView = findViewById(R.id.setimage);
        linktext.setMovementMethod(LinkMovementMethod.getInstance());

        list = new ArrayList<QueryQuestionModel>();
        list.add(new QueryQuestionModel("Write the SQL statement selects the “Name\" and \" Country \" columns from the \"Customers\" table:",
                "","selectname,countryfromcustomers;"));
        list.add(new QueryQuestionModel("Creates a table called \"Persons\" that contains five columns: PersonID, LastName, FirstName, Address, and City:",
                "","createtablepersons(personidint,lastnamevarchar(255),firstnamevarchar(255),addressvarchar(255),cityvarchar(255));"));
        list.add(new QueryQuestionModel("Write the SQL statement selects all fields from \"Customers\" where Country is \"Canada\":",
                "","select*fromcustomerswherecity=canada;"));
        list.add(new QueryQuestionModel("Write the SQL statement selects all customers from the \"Customers\" table, sorted by the \"Country\" column",
                "","select*fromcustomersorderbycountry;"));
        list.add(new QueryQuestionModel("Write the SQL statement inserts a new record in the \"Customers\" table",
                "","insertintocustomers(id,name,city,mobno.,email,dob)values('11','cardinal','america','1102340','cardianl@mc.ca','20/10/98');"));

        option.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                next_btn.setEnabled(true);
                next_btn.setAlpha(1);
                check_btn.setEnabled(true);
                check_btn.setAlpha(1);
                check = option.getText().toString();
                nospace = check.replaceAll("\\s", "");
               // Toast.makeText(getApplicationContext(), "No space :"+nospace, Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        for(int i=0;i<4;i++){
//            options_layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    checkAnswer((Button)v);
//                }
//            });
//        }

        playAnim(question,0,list.get(position).getQuestion());

//        setimage.setImageResource(imagedefaultlist[imagecounter]);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl("file:///android_asset/"+imagedefaultlist[imagecounter]+".html");
        webView.setWebViewClient(new WebViewClient());



        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linktext.setText("");
//                setimage.setImageResource(imagedefaultlist[imagecounter+1]);
                webView.loadUrl("file:///android_asset/"+imagedefaultlist[imagecounter+1]+".html");
                next_btn.setEnabled(false);
                next_btn.setAlpha(0.07f);
                check_btn.setEnabled(false);
                check_btn.setAlpha(0.07f);
                //enableoption(true);
                checkAnswer(nospace);
                position++;
                imagecounter++;

                if(position == list.size()){

                    reff.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String  squeryone = dataSnapshot.child("queryone").getValue().toString();
                            String  squerytwo = dataSnapshot.child("querytwo").getValue().toString();
                            String  squerythree = dataSnapshot.child("querythree").getValue().toString();
                            String  squeryfour = dataSnapshot.child("queryfour").getValue().toString();
                            String  squeryfive = dataSnapshot.child("queryfive").getValue().toString();
                            String  squeryvalue = dataSnapshot.child("queryvalue").getValue().toString();
                            int iqueryone =  Integer.parseInt(squeryone);
                            int iquerytwo =  Integer.parseInt(squerytwo);
                            int iquerythree =  Integer.parseInt(squerythree);
                            int iqueryfour =  Integer.parseInt(squeryfour);
                            int iqueryfive =  Integer.parseInt(squeryfive);
                            int iqueryvalue =  Integer.parseInt(squeryvalue);
                            //Toast.makeText(getApplicationContext(),"quizone : :"+score,Toast.LENGTH_LONG).show();

                            if(iqueryone == 0){
                                reff.child("queryone").setValue(score);
                            }else{
                                if(iqueryone > score){
                                    reff.child("queryone").setValue(iqueryone);
                                }else{
                                    reff.child("queryone").setValue(score);
                                }
                            }

                            if (iqueryvalue == 0){
                                reff.child("queryvalue").setValue(score);

                                String  squizvalue_final = dataSnapshot.child("quizvalue").getValue().toString();
                                String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                int iquizvalue_final =  Integer.parseInt(squizvalue_final);
                                int ifinalscore =  Integer.parseInt(sfinalscore);

                                if(score == 0 && iquizvalue_final == 0){
                                    reff.child("finalscore").setValue(0);
                                } else if(score != 0 && iquizvalue_final == 0){
                                    reff.child("finalscore").setValue(score);
                                } else if(score == 0 && iquizvalue_final != 0) {
                                    reff.child("finalscore").setValue(iquizvalue_final);
                                } else {
                                    float ffinalscore = (score + iquizvalue_final) / 2;
                                    reff.child("finalscore").setValue(ffinalscore);
                                }

                            } else {

                                if(score > iqueryone){
                                    if(iquerytwo == 0 && iquerythree == 0 && iqueryfour == 0 && iqueryfive ==0){

                                        reff.child("queryvalue").setValue(score);

                                        String  squizvalue_final = dataSnapshot.child("quizvalue").getValue().toString();
                                        String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                        int iquizvalue_final =  Integer.parseInt(squizvalue_final);
                                        int ifinalscore =  Integer.parseInt(sfinalscore);

                                        if(score == 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(0);
                                        } else if(score != 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(score);
                                        } else if(score == 0 && iquizvalue_final != 0) {
                                            reff.child("finalscore").setValue(iquizvalue_final);
                                        } else {
                                            float ffinalscore = (score + iquizvalue_final) / 2;
                                            reff.child("finalscore").setValue(ffinalscore);
                                        }

                                    }else if(iquerytwo != 0 && iquerythree == 0 && iqueryfour == 0 && iqueryfive ==0){

                                        float fqueryvalue = (score + iquerytwo)/2;
                                        reff.child("queryvalue").setValue(fqueryvalue);

                                        String  squizvalue_final = dataSnapshot.child("quizvalue").getValue().toString();
                                        String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                        int iquizvalue_final =  Integer.parseInt(squizvalue_final);
                                        int ifinalscore =  Integer.parseInt(sfinalscore);

                                        if(fqueryvalue == 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(0);
                                        } else if(fqueryvalue != 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(fqueryvalue);
                                        } else if(fqueryvalue == 0 && iquizvalue_final != 0) {
                                            reff.child("finalscore").setValue(iquizvalue_final);
                                        } else {
                                            float ffinalscore = (fqueryvalue + iquizvalue_final) / 2;
                                            reff.child("finalscore").setValue(ffinalscore);
                                        }



                                    } else if(iquerytwo != 0 && iquerythree != 0 &&iqueryfour == 0 && iqueryfive ==0){

                                        float fqueryvalue = (score + iquerytwo +iquerythree)/3;
                                        reff.child("queryvalue").setValue(fqueryvalue);

                                        String  squizvalue_final = dataSnapshot.child("quizvalue").getValue().toString();
                                        String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                        int iquizvalue_final =  Integer.parseInt(squizvalue_final);
                                        int ifinalscore =  Integer.parseInt(sfinalscore);

                                        if(fqueryvalue == 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(0);
                                        } else if(fqueryvalue != 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(fqueryvalue);
                                        } else if(fqueryvalue == 0 && iquizvalue_final != 0) {
                                            reff.child("finalscore").setValue(iquizvalue_final);
                                        } else {
                                            float ffinalscore = (fqueryvalue + iquizvalue_final) / 2;
                                            reff.child("finalscore").setValue(ffinalscore);
                                        }


                                    } else if(iquerytwo != 0 && iquerythree != 0 &&iqueryfour != 0 && iqueryfive == 0) {

                                        float fqueryvalue = (score + iquerytwo +iquerythree +iqueryfour)/4;
                                        reff.child("queryvalue").setValue(fqueryvalue);

                                        String  squizvalue_final = dataSnapshot.child("quizvalue").getValue().toString();
                                        String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                        int iquizvalue_final =  Integer.parseInt(squizvalue_final);
                                        int ifinalscore =  Integer.parseInt(sfinalscore);

                                        if(fqueryvalue == 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(0);
                                        } else if(fqueryvalue != 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(fqueryvalue);
                                        } else if(fqueryvalue == 0 && iquizvalue_final != 0) {
                                            reff.child("finalscore").setValue(iquizvalue_final);
                                        } else {
                                            float ffinalscore = (fqueryvalue + iquizvalue_final) / 2;
                                            reff.child("finalscore").setValue(ffinalscore);
                                        }



                                    } else if(iquerytwo != 0 && iquerythree != 0 &&iqueryfour != 0 && iqueryfive != 0){

                                        float fqueryvalue = (score + iquerytwo +iquerythree +iqueryfour +iqueryfive)/5;
                                        reff.child("queryvalue").setValue(fqueryvalue);

                                        String  squizvalue_final = dataSnapshot.child("quizvalue").getValue().toString();
                                        String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                        int iquizvalue_final =  Integer.parseInt(squizvalue_final);
                                        int ifinalscore =  Integer.parseInt(sfinalscore);

                                        if(fqueryvalue == 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(0);
                                        } else if(fqueryvalue != 0 && iquizvalue_final == 0){
                                            reff.child("finalscore").setValue(fqueryvalue);
                                        } else if(fqueryvalue == 0 && iquizvalue_final != 0) {
                                            reff.child("finalscore").setValue(iquizvalue_final);
                                        } else {
                                            float ffinalscore = (fqueryvalue + iquizvalue_final) / 2;
                                            reff.child("finalscore").setValue(ffinalscore);
                                        }


                                    }

                                } else {
                                    reff.child("queryvalue").setValue(iqueryvalue);

                                    String  squizvalue_final = dataSnapshot.child("quizvalue").getValue().toString();
                                    String  sfinalscore = dataSnapshot.child("finalscore").getValue().toString();

                                    int iquizvalue_final =  Integer.parseInt(squizvalue_final);
                                    int ifinalscore =  Integer.parseInt(sfinalscore);

                                    if(iqueryvalue == 0 && iquizvalue_final == 0){
                                        reff.child("finalscore").setValue(0);
                                    } else if(iqueryvalue != 0 && iquizvalue_final == 0){
                                        reff.child("finalscore").setValue(iqueryvalue);
                                    } else if(iqueryvalue == 0 && iquizvalue_final != 0) {
                                        reff.child("finalscore").setValue(iquizvalue_final);
                                    } else {
                                        float ffinalscore = (iqueryvalue + iquizvalue_final) / 2;
                                        reff.child("finalscore").setValue(ffinalscore);
                                    }
                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Intent scoreintent = new Intent(getApplicationContext(),ScoreActivity.class);
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

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnsweranddisplay(nospace);
            }

        });


    }



//    private void enableoption(boolean enable) {
//        for(int i=0;i<4;i++){
//            options_layout.getChildAt(i).setEnabled(enable);
//            if(enable){
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    options_layout.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#3d3636")));
//                }
//            }
//        }
//    }

    private void playAnim(final View view,final int value,final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(1).setDuration(500).setStartDelay(50)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(value == 0 && count<1){
                    String option = "";
                    if(count == 0){
                        option = list.get(position).getOption();
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
                        ((EditText)view).setText(data);
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


    private void checkAnswer(String value) {
       // Toast.makeText(getApplicationContext(), "value :"+value, Toast.LENGTH_LONG).show();
        //enableoption(false);
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        int result = value.compareToIgnoreCase(list.get(position).getCorrectAnswer());
        if(result == 0) {
            score = score + 20;
//            Toast.makeText(getApplicationContext(), "Right Answer", Toast.LENGTH_LONG).show();
        }else {
//            Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_LONG).show();
        }
    }

    private void checkAnsweranddisplay(String value) {
        // Toast.makeText(getApplicationContext(), "value :"+value, Toast.LENGTH_LONG).show();
        //enableoption(false);
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        int result = value.compareToIgnoreCase(list.get(position).getCorrectAnswer());
        if(result == 0) {
//            setimage.setImageResource(imagetruelist[imagecounter]);
            webView.loadUrl("file:///android_asset/"+imagetruelist[imagecounter]+".html");
//            Toast.makeText(getApplicationContext(), "Right Answer", Toast.LENGTH_LONG).show();
        }else {
            linktext.setText(linklist[imagecounter]);
//            setimage.setImageResource(imagefalselist[imagecounter]);
            webView.loadUrl("file:///android_asset/"+imagefalselist[imagecounter]+".html");
//            Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_LONG).show();
        }
    }


    public void onBackPressed(){
        new AlertDialog.Builder(QueryOneActivity.this).setTitle("Exit")
                .setMessage("Would you like to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent intent = new Intent(nav.this, MainActivity.class);
                        Intent intent = new Intent(getApplicationContext(),QueryFragment.class);
                        SharedPreferences sp = PreferenceManager
                                .getDefaultSharedPreferences(QueryOneActivity.this);
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
        new AlertDialog.Builder(QueryOneActivity.this).setTitle("Exit")
                .setMessage("Would you like to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent intent = new Intent(nav.this, MainActivity.class);
                        Intent intent = new Intent(getApplicationContext(),QueryFragment.class);
                        SharedPreferences sp = PreferenceManager
                                .getDefaultSharedPreferences(QueryOneActivity.this);
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
