package com.pd.chatapp.student.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.pd.chatapp.R;
import com.pd.chatapp.FAQ;
import com.pd.chatapp.student.FAQAdapter;

public class chemistryFAQFragment extends Fragment {
    RecyclerView mrecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    FAQAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chemistry_faq, container,false);

        mrecyclerView = v.findViewById(R.id.rvChemFAQs);
        System.out.println("Null or Not ????????     "+ getParentFragment().getActivity());
        if (getParentFragment().getActivity() != null)
        {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((Context) getParentFragment().getActivity());//0
            mrecyclerView.setLayoutManager(layoutManager);
        }
        else
        {
            System.out.println("NULLLLLLLLLLLLLL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
        }
        FirebaseRecyclerOptions<FAQ> options =
                new FirebaseRecyclerOptions.Builder<FAQ>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FAQs").child("Chemistry"), FAQ.class)
                        .build();

        adapter = new FAQAdapter(options, getContext());
        mrecyclerView.setAdapter(adapter);
        return v;
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

