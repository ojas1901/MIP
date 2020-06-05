package com.pd.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    EditText etusername, etpassword, etemail;
    Button registerButton;
    String user, pass ,email;
    TextView login;
    ArrayList<String> al = new ArrayList<>();
    String typeStudent = "Student", typeFaculty = "Faculty";
    static String type="";
    String FacEmails[] = new String[]{"archana.sharma@somaiya.edu","bharati.c@somaiya.edu","jitendrasatam@somaiya.edu","pushpendrarai@somaiya.edu","santoshmani@somaiya.edu","shrikantchawade@somaiya.edu","surenpatwardhan@somaiya.edu","druman@somaiya.edu"};


    static  public String getType() {
        return type;
    }

    static public void setType(String t) {
        type = t;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        etemail = findViewById(R.id.email);
        registerButton = findViewById(R.id.registerButton);
        login = findViewById(R.id.login);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
                Register.this.finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = etusername.getText().toString();
                pass = etpassword.getText().toString();
                email = etemail.getText().toString();



                if(user.equals("")){
                    etusername.setError("can't be blank");
                }
                else if(pass.equals("")){
                    etpassword.setError("can't be blank");
                }
                else if(!user.matches("[@.A-Za-z0-9]+( )?[@.A-Za-z0-9]+")){
                    etusername.setError("only alphabet or number allowed");
                }
                else if(user.length()<5){
                    etusername.setError("at least 5 characters long");
                }
                else if(pass.length()<5){
                    etpassword.setError("at least 5 characters long");
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    setType(typeStudent);
                    for(String f: FacEmails)
                    {
                        if(f.equals(email)) {
                            setType(typeFaculty);
                            break;
                        }
                    }

                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                    String url = "https://androidchatapp-aa4b9.firebaseio.com/users.json";
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://androidchatapp-aa4b9.firebaseio.com/users");

                            if(s.equals("null"))
                            {
                                reference.child(user).child("password").setValue(pass);
                                reference.child(user).child("email").setValue(email);
                                reference.child(user).child("type").setValue(getType());
                                reference.child(user).child("subject").setValue("NA");
                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            }
                            else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(user)) {
                                        reference.child(user).child("password").setValue(pass);
                                        reference.child(user).child("email").setValue(email);
                                        reference.child(user).child("type").setValue(getType());
                                        reference.child(user).child("subject").setValue("NA");

                                        Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Register.this, "username already exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
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
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    rQueue.add(request);
                }
            }
        });
    }
}


/*
  String emailCheckurl = "https://androidchatapp-aa4b9.firebaseio.com/Faculty.json";
                    StringRequest request0 = new StringRequest(Request.Method.GET, emailCheckurl, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s)
                        {

                            try {
                                Firebase fref = new Firebase("https://androidchatapp-aa4b9.firebaseio.com/Faculty");
                                boolean fac = false;
                                JSONObject obj = new JSONObject(s);
                                Iterator i = obj.keys();
                                String key = "";
                                String nodeEmail;
                                while(i.hasNext()){
                                    key = i.next().toString();
                                    nodeEmail  = fref.child(key).child("email").toString();
                                    if(nodeEmail.equals(email)) {
                                        fac = true;
                                        break;
                                    }
                                }
                                if(fac)
                                    setType(typeFaculty);
                                else
                                    setType(typeStudent);
                                Toast.makeText(Register.this, "Type Set", Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                        }
                    });
                    pd.dismiss();
 rQueue.add(request0);
* */