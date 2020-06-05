package com.pd.chatapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Chat extends AppCompatActivity {
    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        layout = findViewById(R.id.layout1);
        layout_2 = findViewById(R.id.layout2);
        sendButton = findViewById(R.id.sendButton);
        messageArea = findViewById(R.id.messageArea);
        scrollView = findViewById(R.id.scrollView);

        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://androidchatapp-aa4b9.firebaseio.com/Messages/" + UserDetails.username + "/" + UserDetails.chatWith);
        reference2 = new Firebase("https://androidchatapp-aa4b9.firebaseio.com/Messages/" + UserDetails.chatWith + "/" + UserDetails.username);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();

                if(!messageText.equals("") && !messageText.equals("\n") && !messageText.matches("[\\n\\r]+") && !messageText.trim().isEmpty()){
                    Map<String, String> map = new HashMap<String, String>();

                    String datetime;

                    DateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm");
                    Date dateobj = new Date();
                    datetime = df.format(dateobj);

                    map.put("message", messageText);
                    map.put("user", UserDetails.username);
                    map.put("time", datetime);

                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");
                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();

                if(userName.equals(UserDetails.username)){
                    addMessageBox(message, 1);
                }
                else{
                    addMessageBox(message, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addMessageBox(String message, int type){
        TextView textView = new TextView(Chat.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 7.0f;
        lp2.setMargins(5,10,5,10);

        if(type == 1) {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_out);
            textView.setPadding(20,15,20,15);
            textView.setTextColor(Color.WHITE);
        }
        else{
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_in);
            textView.setPadding(20,15,20,15);
            textView.setTextColor(Color.BLACK);

        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);

        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}