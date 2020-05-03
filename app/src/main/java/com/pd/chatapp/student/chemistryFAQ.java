package com.pd.chatapp.student;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.pd.chatapp.FAQ;
import com.pd.chatapp.R;

public class chemistryFAQ extends AppCompatActivity {
    RecyclerView mrecyclerView;
    FAQAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemistry_faq);

        mrecyclerView = findViewById(R.id.rvChemFAQs);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<FAQ> options =
                new FirebaseRecyclerOptions.Builder<FAQ>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FAQs").child("Chemistry"), FAQ.class)
                        .build();

        adapter = new FAQAdapter(options);
        mrecyclerView.setAdapter(adapter);

    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
