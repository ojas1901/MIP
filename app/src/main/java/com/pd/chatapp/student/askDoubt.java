package com.pd.chatapp.student;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pd.chatapp.Doubt;
import com.pd.chatapp.R;
import com.pd.chatapp.Register;
import com.pd.chatapp.UserDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class askDoubt extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] Subjects = {"Physics","Chemistry"};
    String[] Physics = {"Solid State Physics","Dielectrics, Magnetic and Superconducting Properties of Materials", "Quantum and Opto-electronics","Sensors and Transducers", "Optics", "Electrodynamics"};
    String[] Chemistry = {"Water and Green Chemistry","Energy", "Polymer Chemistry","Nano science and Nanotechnology" ,"Spectroscopy and Instrumental Methods of Analysis", "Synthetic Organic Reactions and Bio-inorganic Chemistry"};
    String[] noSubject = {"Please select subject first !"};
    static String sub, chap;


    public static void setSubject(String s) { sub = s; }
    public static String getSubject() { return sub; }
    public static void setChapter(String s) { chap = s; }
    public static String getChapter() { return chap; }

    Button btnAsk;
    EditText etDoubt;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        sub = "Physics";
        chap = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_doubt);

        final Spinner spinSubject = (Spinner)findViewById (R.id.spSubject);
        final Spinner spinChapter = (Spinner) findViewById (R.id.spChapter);
        ArrayAdapter<String> subjectList =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,Subjects);
        final ArrayAdapter<String> chapterListPhysics =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,Physics);
        final ArrayAdapter<String> chapterListChemistry =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,Chemistry);
        spinSubject.setAdapter(subjectList);
        //spinChapter.setAdapter(chapterListPhysics);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();



        btnAsk = findViewById(R.id.btnAsk);
        etDoubt = findViewById(R.id.etDoubt);
        spinSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
               if(position == 0)
               {
                   spinChapter.setAdapter(chapterListPhysics);
                   setSubject("Physics");
               }
               else
                   {
                   spinChapter.setAdapter(chapterListChemistry);
                   setSubject("Chemistry");
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
                //spinChapter.setAdapter(chapterListPhysics);
                setSubject("No subject selected !");
            }

        });

        spinChapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
                //spinSubject.setSelection(position);
                if(getSubject().equals("Physics"))
                {
                    setChapter(Physics[position]);
                }
                else if(getSubject().equals("Chemistry"))
                {
                    setChapter(Chemistry[position]);
                }
                else
                    setChapter("Can't select chapter");
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                setChapter("No chapter selected");
            }

        });
        Firebase.setAndroidContext(this);
        btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(etDoubt.getText().toString().length() == 0)
                {
                    etDoubt.setError("Please enter your doubt !");
                    etDoubt.requestFocus();
                    return;
                }



                final ProgressDialog pd = new ProgressDialog(askDoubt.this);
                pd.setMessage("Loading...");
                pd.show();
                final String user = UserDetails.username;
                RequestQueue rQueue = Volley.newRequestQueue(askDoubt.this);
                String url = "https://androidchatapp-aa4b9.firebaseio.com/Question.json";
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        Firebase reference = new Firebase("https://androidchatapp-aa4b9.firebaseio.com/Question");
                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy+HH:mm:ss");
                        Date dateobj = new Date();
                        String date = df.format(dateobj);

                        if(s.equals("null"))
                        {
                            reference.child(user).child(date).child("Chapter").setValue(getChapter());
                            reference.child(user).child(date).child("Subject").setValue(getSubject());
                            reference.child(user).child(date).child("Text").setValue(etDoubt.getText().toString());
                            Toast.makeText(askDoubt.this, "Doubt Registered", Toast.LENGTH_LONG).show();
                        }
                        else {
                            try {
                                JSONObject obj = new JSONObject(s);


                                    reference.child(user).child(date).child("Chapter").setValue(getChapter());
                                    reference.child(user).child(date).child("Subject").setValue(getSubject());
                                    reference.child(user).child(date).child("Text").setValue(etDoubt.getText().toString());
                                    Toast.makeText(askDoubt.this, "Doubt Registered", Toast.LENGTH_LONG).show();


                            } catch (JSONException e) {
                                Toast.makeText(askDoubt.this, "Something Went Wrong !!", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }

                        pd.dismiss();
                    }

                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("Volley Error " + volleyError );
                        pd.dismiss();
                    }
                });
                rQueue.add(request);

                Intent intent = new Intent(askDoubt.this, teacherList.class);
                String Sub = getSubject();
                intent.putExtra("Subject", Sub);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void onNothingSelected(AdapterView<?> parent){
        Toast.makeText(this, "Choose  :", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
