package com.pd.chatapp.teacher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.pd.chatapp.R;
import com.pd.chatapp.UserDetails;
import com.pd.chatapp.student.homeActivity;
import com.pd.chatapp.student.teacherList;

import java.util.Map;

public class answerDoubt extends AppCompatActivity {
    TextView tvQuestionT, tvChapterT, tvHeading;
    Button btnAnswer;
    String chapter, question, answer, askedBy, time, status;
    Firebase referenceStudent, referenceFaculty;
    Query queryRef1, queryRef2;
    EditText etAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_doubt);

        chapter= getIntent().getStringExtra("Chapter");
        question= getIntent().getStringExtra("Question");
        askedBy = getIntent().getStringExtra("Asked By");
        time = getIntent().getStringExtra("Time");
        status = getIntent().getStringExtra("Status");

        btnAnswer   = findViewById(R.id.btnAnswer);
        tvChapterT  = findViewById(R.id.tvChapterT);
        tvQuestionT = findViewById(R.id.tvQuestionT);
        tvHeading = findViewById(R.id.tvAnswerDoubt);
        etAnswer    = findViewById(R.id.etAddSolutionT);
        tvChapterT.setText(chapter);
        tvQuestionT.setText(question);


        if(status.equals("submitted"))
        {
            etAnswer.setEnabled(false);
            answer = getIntent().getStringExtra("Answer");
            etAnswer.setText(answer);
            etAnswer.setTextColor(Color.BLACK);
            btnAnswer.setVisibility(View.INVISIBLE);
            tvHeading.setText("Question and Answer");
            return;
        }



        Firebase.setAndroidContext(this);

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer = etAnswer.getText().toString();

                if(answer.length() == 0)
                {
                    etAnswer.setError("Please provide an answer !!");
                    etAnswer.requestFocus();
                    return;
                }


                if(answer.equals(" ") || answer.equals("\n") || answer.matches("[\\n\\r]+") || answer.trim().isEmpty())
                {
                    etAnswer.setError("Answer cannot be only blank spaces !!");
                    etAnswer.requestFocus();
                    return;
                }

                new AlertDialog.Builder(com.pd.chatapp.teacher.answerDoubt.this)
                        .setMessage("Submit the Answer ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final ProgressDialog pd2 = new ProgressDialog(com.pd.chatapp.teacher.answerDoubt.this);
                                pd2.setMessage("Submitting...");
                                pd2.show();

                                doOnConfirmation();

                                pd2.dismiss();

                                Toast.makeText(com.pd.chatapp.teacher.answerDoubt.this, "Answer submitted !!", Toast.LENGTH_LONG).show();

                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();













//                queryRef1.addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(DataSnapshot dataSnapshot, String previousChild) {
//                        //System.out.println(dataSnapshot.getValue());
//                        Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
//
//                        String time1 = String.valueOf(value.get("time"));
//                        //System.out.println(dataSnapshot.getKey() + "is" + value.get("fullName").toString());
//                        if (time1.equals(time)) {
//                            answer = etAnswer.getText().toString();
//                            Toast.makeText(answerDoubt.this, time, Toast.LENGTH_SHORT).show();
//                            referenceFaculty.child("Answer").setValue(answer);
//                            System.out.println("Name" + value.get("time"));
//                            //referenceFaculty.child();
//                        }
//
//
//                        else{
//                            Toast.makeText(answerDoubt.this, "Question not Found In Database !", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//
//                    }
//                });

            }
    });
}


    public void doOnConfirmation()
    {
        referenceFaculty = new Firebase("https://androidchatapp-aa4b9.firebaseio.com/Question/" + UserDetails.username);
        referenceStudent = new Firebase("https://androidchatapp-aa4b9.firebaseio.com/Question/" + askedBy);

        queryRef1 = referenceFaculty.orderByChild("time").equalTo(time);
        queryRef2 = referenceStudent.orderByChild("time").equalTo(time);

        queryRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String key = snap.getKey();


                    referenceFaculty.child(key).child("Answer").setValue(answer);
                    referenceFaculty.child(key).child("status").setValue("submitted");

                    //Toast.makeText(answerDoubt.this, key, Toast.LENGTH_SHORT).show();
                }

//
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        queryRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String key = snap.getKey();


                    referenceStudent.child(key).child("Answer").setValue(answer);
                    referenceStudent.child(key).child("status").setValue("submitted");

                    //Toast.makeText(answerDoubt.this, key, Toast.LENGTH_SHORT).show();
                }

//
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}


