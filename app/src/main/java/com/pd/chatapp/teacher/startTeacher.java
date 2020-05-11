package com.pd.chatapp.teacher;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pd.chatapp.R;
import com.pd.chatapp.teacher.ui.historyFragment;
import com.pd.chatapp.teacher.ui.homeFragment;
import com.pd.chatapp.teacher.ui.moreFragment;

public class startTeacher extends AppCompatActivity {

    FloatingActionButton fabAskDoubt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_teacher);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_teacher);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_teacher, new homeFragment()).commit();

        /*fabAskDoubt = findViewById(R.id.fabAskQuestion);

        fabAskDoubt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(startTeacher.this, addDoubt.class));
            }
        });*/
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startTeacher.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener  = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            boolean add= false;
            switch (item.getItemId())
            {
                case R.id.nav_home_teacher:
                    selectedFragment = new homeFragment();
                    break;
                case R.id.nav_addfaq_teacher:
                    startActivity(new Intent(startTeacher.this, addDoubt.class));
                    add = true;
                    break;
                case R.id.nav_history_teacher:
                    selectedFragment = new historyFragment();
                    break;
                case R.id.nav_more_teacher:
                    selectedFragment = new moreFragment();
                    break;

            }
            if(! add)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_teacher, selectedFragment).commit();

            return true;
        }
    };
}
