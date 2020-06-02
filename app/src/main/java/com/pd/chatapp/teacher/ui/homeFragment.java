package com.pd.chatapp.teacher.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.pd.chatapp.Question;
import com.pd.chatapp.R;
import com.pd.chatapp.UserDetails;
import com.pd.chatapp.student.FAQAdapter;
import com.pd.chatapp.teacher.QuestionAdapter;

public class homeFragment extends Fragment {

    RecyclerView mrecyclerView;
    QuestionAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView tvNoQuestions;
    static int totalQuestions = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_teacher, container,false);

        mrecyclerView = v.findViewById(R.id.rvTeacherHome);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        mSwipeRefreshLayout = v.findViewById(R.id.srlTeacherHome);
        //tvNoQuestions = v.findViewById(R.id.tvNoQuestions);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        rootRef.child("Question").child(UserDetails.username);
//        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                if (snapshot.getValue() == null) {
//                    // The child exists
//                    //Toast.makeText(getActivity(), "Null !", Toast.LENGTH_SHORT).show();
//
//                }
//                else {
//                    //Toast.makeText(getActivity(), "Not Null !", Toast.LENGTH_SHORT).show();
//                    totalQuestions = 10;
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        FirebaseRecyclerOptions<Question> options =
                new FirebaseRecyclerOptions.Builder<Question>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Question").child(UserDetails.username), Question.class)
                        .build();


//        if(totalQuestions == 0){
//            tvNoQuestions.setVisibility(View.VISIBLE);
//            mrecyclerView.setVisibility(View.GONE);
//        }
//        else{
//            tvNoQuestions.setVisibility(View.GONE);
//            mrecyclerView.setVisibility(View.VISIBLE);
//
//        }
        adapter = new QuestionAdapter(options);
        mrecyclerView.setAdapter(adapter);



        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                doOnRefresh();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        return v;
    }

    public void doOnRefresh(){
        FirebaseRecyclerOptions<Question> options =
                new FirebaseRecyclerOptions.Builder<Question>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Question").child(UserDetails.username), Question.class)
                        .build();

        adapter = new QuestionAdapter(options);
        mrecyclerView.setAdapter(adapter);
        adapter.startListening();
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

