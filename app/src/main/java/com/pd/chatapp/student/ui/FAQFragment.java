package com.pd.chatapp.student.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pd.chatapp.R;
import com.pd.chatapp.student.chemistryFAQ;
import com.pd.chatapp.student.physicsFAQ;
import com.pd.chatapp.teacher.addDoubt;

public class FAQFragment extends Fragment {
    ImageView imChemistry, imPhysics;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_faq, container,false);

        imChemistry = v.findViewById(R.id.imChemistry);
        imPhysics = v.findViewById(R.id.imPhysics);

        imChemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getContext(), chemistryFAQ.class));

            }
        });

        imPhysics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), physicsFAQ.class));

            }
        });

        imPhysics.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(getContext(), addDoubt.class));

                return true;
            }
        });
        return v;
    }
}

