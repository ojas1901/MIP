package com.pd.chatapp.student;

//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.pd.chatapp.student.ui.tab1;
import com.pd.chatapp.student.ui.tab2;

//Extending FragmentStatePagerAdapter
public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    private String[] tabTitles = new String[]{"Answered Doubts", "Unanswered Doubts"};
    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                tab1 tab1 = new tab1();
                return tab1;
            case 1:
                tab2 tab2 = new tab2();
                return tab2;
            default:
                tab1 Tab1 = new tab1();
                return Tab1;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}