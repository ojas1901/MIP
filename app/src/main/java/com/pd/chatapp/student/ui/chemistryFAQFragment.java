package com.pd.chatapp.student.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.pd.chatapp.R;

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
                historyFragment history = new historyFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.add(R.id.fragment_container, history ).commit();
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

            }
        });

        return v;
    }
}

