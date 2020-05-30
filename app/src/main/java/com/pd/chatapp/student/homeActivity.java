package com.pd.chatapp.student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pd.chatapp.R;

public class homeActivity extends AppCompatActivity {
CardView cvAskDoubt, cvMore, cvFAQs, cvHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cvAskDoubt = findViewById(R.id.cardAskDoubt);
        cvMore = findViewById(R.id.cardMore);
        cvFAQs = findViewById(R.id.cardFAQs);
        cvHistory = findViewById(R.id.cardHistory);

        cvAskDoubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(homeActivity.this)
                        .setMessage("Please read FAQs before asking new doubt.\nYou might find your question answered there.")
                        .setCancelable(false)
                        .setPositiveButton("I have read FAQs", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(homeActivity.this, askDoubt.class);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("Show me the FAQs", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(homeActivity.this, startStudent.class);
                                intent.putExtra("Page", 2);
                                startActivity(intent);

                            }
                        })
                        .show();
            }
        });

        cvFAQs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeActivity.this, startStudent.class);
                intent.putExtra("Page", 2);
                startActivity(intent);

            }
        });

        cvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeActivity.this, startStudent.class);
                intent.putExtra("Page", 3);
                startActivity(intent);

            }
        });

        cvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homeActivity.this, startStudent.class);
                intent.putExtra("Page", 4);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        homeActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
