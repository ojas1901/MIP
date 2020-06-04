package com.pd.chatapp.student;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.pd.chatapp.Chat;
import com.pd.chatapp.R;
import com.pd.chatapp.UserDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class teacherList extends AppCompatActivity {
    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd, pd2;
    String Subject, time, question, chapter, nodeDate;
    Firebase referenceStudent, referenceFaculty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        usersList = findViewById(R.id.teacherList);
        noUsersText = findViewById(R.id.tvNoUsers);

        Subject = getIntent().getStringExtra("Subject");
        time = getIntent().getStringExtra("Date");
        nodeDate = getIntent().getStringExtra("Node Date");
        question = getIntent().getStringExtra("Question");
        chapter = getIntent().getStringExtra("Chapter");


        Firebase.setAndroidContext(this);
        referenceStudent = new Firebase("https://androidchatapp-aa4b9.firebaseio.com/Question/" + UserDetails.username);


        pd = new ProgressDialog(teacherList.this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://androidchatapp-aa4b9.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(teacherList.this);
        rQueue.add(request);
        pd.dismiss();

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String Faculty  = al.get(position);
                new AlertDialog.Builder(teacherList.this)
                        .setMessage("Submit Your Doubt ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final ProgressDialog pd2 = new ProgressDialog(teacherList.this);
                                pd2.setMessage("Submitting...");
                                pd2.show();

                                doOnConfirmation(Faculty);

                                pd2.dismiss();

                                Toast.makeText(teacherList.this, "Doubt Registered", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(teacherList.this, homeActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                //UserDetails.chatWith = al.get(position);

            }
        });
    }

    public void doOnConfirmation(String Faculty)
    {
        referenceFaculty = new Firebase("https://androidchatapp-aa4b9.firebaseio.com/Question/" + Faculty);

        Map<String, String> map = new HashMap<String, String>();
        map.put("Chapter", chapter);
        map.put("Subject", Subject);
        map.put("Question", question);
        map.put("Answer", "");
        map.put("askedBy", UserDetails.username);
        map.put("askedTo", Faculty);
        map.put("isAccepted", "true");
        map.put("status", "not answered");
        map.put("time", time);

        referenceStudent.push().setValue(map);
        referenceFaculty.push().setValue(map);

    }

    public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while(i.hasNext()){
                key = i.next().toString();

                if(!key.equals(UserDetails.username))
                {
                    if(obj.getJSONObject(key).getString("type").equals("Faculty"))
                    al.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalUsers <=1){
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
        }


    }
}