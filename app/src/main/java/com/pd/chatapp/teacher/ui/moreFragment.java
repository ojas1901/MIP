package com.pd.chatapp.teacher.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.pd.chatapp.R;

public class moreFragment extends Fragment {
    TextView tvProfile, tvSettings, tvAboutUs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_more_teacher, container,false);
        tvAboutUs = v.findViewById(R.id.tvMenuAboutUs);
        tvSettings = v.findViewById(R.id.tvMenuSettings);
        tvProfile = v.findViewById(R.id.tvMenuProfile);

        tvAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Developed By: \nRajan Gaul (@mahanvyakti) \nOjas Kulkarni (@ojas1901)", Toast.LENGTH_SHORT).show();
            }
        });
        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getView(), "Page coming soon!", Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(getView(), "Page coming soon!", Snackbar.LENGTH_SHORT)
                        .show();
            }
        });

        return v;
    }
}

