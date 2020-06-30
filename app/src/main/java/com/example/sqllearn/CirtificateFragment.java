package com.example.sqllearn;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.widget.Toast.LENGTH_LONG;

public class CirtificateFragment extends Fragment {

    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    DatabaseReference reff,ref;
    String username;
    private Button cirtificatebtn;
    private TextView text;
    PDFView pdfView;
    Bitmap bmp,scaledbmp;
    int pagewidth = 1080,pageheight = 765;
    String pdfname,cmp;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    String uid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cirtificate, container, false);

        cirtificatebtn = root.findViewById(R.id.cirtificatebtn);
        text = root.findViewById(R.id.text);
        pdfView = root.findViewById(R.id.pdfviewer);
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.cirtificate);
        scaledbmp = Bitmap.createScaledBitmap(bmp,1080,765,false);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(),  googleSignInOptions);
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();
        reff =FirebaseDatabase.getInstance().getReference("users");
        ref = FirebaseDatabase.getInstance().getReference().child("user").child(uid);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
        if(signInAccount != null){
            username = signInAccount.getDisplayName();
            //email.setText(signInAccount.getEmail());
            //Toast.makeText(this,"data inserted ...",Toast.LENGTH_LONG).show();
        }

        pdfname = username + "_cirtificate.pdf";
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/SQL Learn/";
        String targetPdf = directory_path + pdfname;
        File filePath = new File(targetPdf);
        if(filePath.exists()){
                pdfView.setVisibility(View.VISIBLE);
                pdfView.fromFile(filePath).load();
        }


        ref.addValueEventListener(new ValueEventListener() {
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
                float iqueryvalue =  Float.parseFloat(squeryvalue);
                int iquizone =  Integer.parseInt(squizone);
                int iquiztwo =  Integer.parseInt(squiztwo);
                int iquizthree =  Integer.parseInt(squizthree);
                int iquizfour =  Integer.parseInt(squizfour);
                int iquizfive =  Integer.parseInt(squizfive);
                float iquizvalue =  Float.parseFloat(squizvalue);
                float ifinalscore =  Float.parseFloat(sfinalscore);

                if(iquizone != 0 && iquiztwo != 0 && iquizthree != 0 && iquizfour != 0 && iquizfive != 0 && iquizvalue != 0 &&
                iqueryvalue != 0 && iqueryone != 0 && iquiztwo != 0 && iquizthree != 0 && iqueryfour != 0 && iqueryfive != 0 &&
                ifinalscore != 0){
                    if(ifinalscore > 70.0  && iquizvalue >70.0 && iqueryvalue > 70.0){
                        cirtificatebtn.setAlpha(1);
                        cirtificatebtn.setEnabled(true);
//                        Toast.makeText(getActivity(),"Congratulation !!!", LENGTH_LONG).show();
                        text.setText("Congratulation!!! Your Cirtificate is ready.");
                        cirtificatebtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if(username.length() == 0){
                                    Toast.makeText(getActivity(),"name is empty", LENGTH_LONG).show();
                                }
                                PdfDocument myPdfDocument = new PdfDocument();
                                Paint myPaint = new Paint();
                                Paint title = new Paint();


                                PdfDocument.PageInfo myPageInfoone = new PdfDocument.PageInfo.Builder(1080,765,1).create();
                                PdfDocument.Page myPageone = myPdfDocument.startPage(myPageInfoone);
                                Canvas canvas = myPageone.getCanvas();
                                canvas.drawBitmap(scaledbmp,0,0,myPaint);
                                title.setTextAlign(Paint.Align.CENTER);
                                title.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                                title.setTextSize(30);
                                canvas.drawText(username,pagewidth/2,pageheight/2,title);

                                myPdfDocument.finishPage(myPageone);

//                String extstoragedir = Environment.getExternalStorageDirectory().toString();
//                File fol = new File(extstoragedir, "pdf");
//                File folder=new File(fol,"pdf");
//                if(!folder.exists()) {
//                    boolean bool = folder.mkdir();
//                }
//                try {
//                    final File file = new File(folder, "cirtifical_of_SQLLearn.pdf");
//                    file.createNewFile();
//                    try{
//                        myPdfDocument.writeTo(new FileOutputStream(file));
//
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }catch (IOException e){
//                    Log.i("error",e.getLocalizedMessage());
//                }



                                String directory_path = Environment.getExternalStorageDirectory().getPath() + "/SQL Learn/";
                                File file = new File(directory_path);
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                String targetPdf = directory_path + pdfname;
                                File filePath = new File(targetPdf);
                                if (!filePath.exists()) {
                                    try {
                                        filePath.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try{
                                        myPdfDocument.writeTo(new FileOutputStream(filePath));
                                        // Toast.makeText(CirtificateActivity.this, "Create PDF", Toast.LENGTH_LONG).show();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                                else{
                                    filePath.delete();
                                    try {
                                        filePath.createNewFile();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try{
                                        myPdfDocument.writeTo(new FileOutputStream(filePath));
                                        //Toast.makeText(CirtificateActivity.this, "Create PDF", Toast.LENGTH_LONG).show();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //Toast.makeText(CirtificateActivity.this, "PDF not created.", Toast.LENGTH_LONG).show();
                                }


                                pdfView.setVisibility(View.VISIBLE);
                               pdfView.fromFile(filePath).load();
//               File file = new File(Environment.getExternalStorageDirectory(),"/cirtificate.pdf");
//                if(!file.exists()){
//                    try {
//                        file.createNewFile();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    try{
//                        myPdfDocument.writeTo(new FileOutputStream(file));
//
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }


                                myPdfDocument.close();

                            }
                        });

                    }else {
                        cirtificatebtn.setAlpha(0.5f);
                        cirtificatebtn.setEnabled(false);
                        text.setText("Your FinalScore OR Total QuizScore OR Total QueryScore is less than 70, So first complete you score and get cirtificate");
//                        Toast.makeText(getActivity(),"Your Total Score is < 70 .", LENGTH_LONG).show();
                    }
                }else {
                    cirtificatebtn.setAlpha(0.5f);
                    cirtificatebtn.setEnabled(false);
                    text.setText("In your Scorecard any one of the your score is zero , So first complete the score.");
//                    Toast.makeText(getActivity(),"Some Score is Zero", LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        return  root;
    }
}
