package com.pd.chatapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class askDoubt extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] Subjects = {"Physics","Chemistry"};

    String[] Physics = {"Solid State Physics","Dielectrics, Magnetic and Superconducting Properties of Materials", "Quantum and Opto-electronics","Sensors and Transducers", "Optics", "Electrodynamics"};
    String[] Chemistry = {"Water and Green Chemistry","Energy", "Polymer Chemistry","Nano science and Nanotechnology" ,"Spectroscopy and Instrumental Methods of Analysis", "Synthetic Organic Reactions and Bio-inorganic Chemistry"};
    String[] noSubject = {"Please select subject first !"};
    static String sub = "Physics";
    static String chap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        sub = "Physics";
        chap = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_doubt);

        Spinner spinSubject = findViewById(R.id.spSubject);
        ArrayAdapter<String> subject = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Subjects);
        subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSubject.setAdapter(subject);
        spinSubject.setOnItemSelectedListener(this);

        sub = spinSubject.getSelectedItem().toString();
        Toast.makeText(this, "Entered in the activity- "+chap, Toast.LENGTH_SHORT).show();
        Spinner spinChapter = findViewById(R.id.spChapter);
        if(sub.equals("Physics"))
        {
            ArrayAdapter<String> chapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Physics);
            chapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinChapter.setAdapter(chapter);
            spinChapter.setOnItemSelectedListener(this);
        }
        else if(sub.equals("Chemistry"))
        {
            ArrayAdapter<String> chapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Chemistry);
            chapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinChapter.setAdapter(chapter);
            spinChapter.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        }
        else
            sub = "Please select subject first !";

        if(sub.equals("Please select subject first !")){
            Toast.makeText(this, "Select Subject first !!!", Toast.LENGTH_SHORT).show();
            ArrayAdapter<String> chapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, noSubject);
            chapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinChapter.setAdapter(chapter);
            spinChapter.setOnItemSelectedListener(this);

        }

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id ){
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        if(spin.getId() == R.id.spSubject)
        {
            Toast.makeText(this, "Your choose :" + Subjects[position], Toast.LENGTH_SHORT).show();
            sub = Subjects[position];
        }
        if(spin2.getId() == R.id.spChapter)
        {
            if(sub == "Physics") {
                Toast.makeText(this, "Your choose :" + Physics[position], Toast.LENGTH_SHORT).show();
                chap = Physics[position];
            }
            else if(sub == "Chemistry") {
                Toast.makeText(this, "Your choose :" + Chemistry[position], Toast.LENGTH_SHORT).show();
                chap = Chemistry[position];
            }
            else if(sub == "Please select subject first !")
            {
                Toast.makeText(this, "Your choose :" + Chemistry[position], Toast.LENGTH_SHORT).show();
                chap ="";
            }
        }

    }

    public void onNothingSelected(AdapterView<?> parent){
        Toast.makeText(this, "Choose Countries :", Toast.LENGTH_SHORT).show();
    }
}
