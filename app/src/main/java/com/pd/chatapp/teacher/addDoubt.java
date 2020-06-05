package com.pd.chatapp.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pd.chatapp.FAQ;
import com.pd.chatapp.R;

public class addDoubt extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] Subjects = {"Physics","Chemistry"};
    String[] Physics = {"Solid State Physics","Dielectrics, Magnetic and Superconducting Properties of Materials", "Quantum and Opto-electronics","Sensors and Transducers", "Optics", "Electrodynamics"};
    String[] Chemistry = {"Water and Green Chemistry","Energy", "Polymer Chemistry","Nano science and Nanotechnology" ,"Spectroscopy and Instrumental Methods of Analysis", "Synthetic Organic Reactions and Bio-inorganic Chemistry"};
    //String[] noSubject = {"Please select subject first !"};
    static String sub, chap;
    DatabaseReference reff, refMain;
    public static void setSubject(String s) { sub = s; }

    public static String getSubject() { return sub; }

    public static void setChapter(String s) { chap = s; }

    public static String getChapter() { return chap; }

    Button btnAsk;
    EditText etQue, etAns;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        sub = "Physics";
        chap = "";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faq);

        final Spinner spinSubject = (Spinner)findViewById (R.id.spSubject);
        final Spinner spinChapter = (Spinner) findViewById (R.id.spChapter);
        ArrayAdapter<String> subjectList =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,Subjects);
        final ArrayAdapter<String> chapterListPhysics =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,Physics);
        final ArrayAdapter<String> chapterListChemistry =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,Chemistry);
        spinSubject.setAdapter(subjectList);
        //spinChapter.setAdapter(chapterListPhysics);
        btnAsk = findViewById(R.id.btnAsk);
        etAns = findViewById(R.id.etAddSolution);
        etQue = findViewById(R.id.etAddDoubt);



        spinSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
               if(position == 0)
               {
                   spinChapter.setAdapter(chapterListPhysics);
                   setSubject("Physics");
               }
               else
                   {
                   spinChapter.setAdapter(chapterListChemistry);
                   setSubject("Chemistry");
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
                //spinChapter.setAdapter(chapterListPhysics);
                setSubject("No subject selected !");
            }

        });

        spinChapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long arg3) {
                //spinSubject.setSelection(position);
                if(getSubject().equals("Physics"))
                {
                    setChapter(Physics[position]);
                }
                else if(getSubject().equals("Chemistry"))
                {
                    setChapter(Chemistry[position]);
                }
                else
                    setChapter("Can't select chapter");
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                setChapter("No chapter selected");
            }

        });











        btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( etQue.getText().toString().length() == 0)
                {
                    etQue.setError("Please enter the question !");
                    etQue.requestFocus();
                    return;
                }
                if( etAns.getText().toString().length() == 0)
                {
                    etAns.setError("Please enter the solution !");
                    etAns.requestFocus();
                    return;
                }

                reff = FirebaseDatabase.getInstance().getReference().child("FAQs");

                if(getSubject().equals("Physics"))
                    refMain = reff.child("Physics");
                else
                    refMain = reff.child("Chemistry");


                //Map<Integer, FAQ> faq = new HashMap<>();
                String question, answer, chapter;
                question = etQue.getText().toString();
                answer = etAns.getText().toString();
                chapter = getChapter();

                FAQ faq1 = new FAQ(question, answer, chapter);
                faq1.setAnswer(answer);
                faq1.setChapter(chapter);
                faq1.setQuestion(question);

                refMain.push().setValue(faq1);
                Toast.makeText(addDoubt.this, "FAQ added !!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(addDoubt.this, teacherList.class);
                String Sub = getSubject();
                intent.putExtra("subject", Sub);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    public void onNothingSelected(AdapterView<?> parent){
        Toast.makeText(this, "Choose Subject :", Toast.LENGTH_SHORT).show();
    }
}
