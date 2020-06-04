package com.pd.chatapp.student.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Toolbar;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

//import com.firebase.client.collection.LLRBNode;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.pd.chatapp.R;
import com.pd.chatapp.student.Pager;

//import androidx.appcompat.widget.Toolbar;

public class historyFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    TabLayout tabLayout;
    TabItem tab1, tab2;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_history, container, false);

        Toolbar toolbar = v.findViewById(R.id.tbtoolbar);
        //setSupportActionBar(toolbar);

        //Initializing the tablayout
        tabLayout = v.findViewById(R.id.tabs);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Answered Doubts"));
        tabLayout.addTab(tabLayout.newTab().setText("Unanswered Doubts"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //tabLayout.setTabTextColors(Color.BLACK, Color.MAGENTA);

        //Initializing viewPager
        viewPager = v.findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

