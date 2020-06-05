package com.pd.chatapp.student.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.pd.chatapp.Question;
import com.pd.chatapp.R;
import com.pd.chatapp.UserDetails;
import com.pd.chatapp.student.QuestionAdapter;

public class tab1 extends Fragment {

    RecyclerView mrecyclerView;
    QuestionAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.tab1, container,false);

        mrecyclerView = v.findViewById(R.id.rvTab1);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        mSwipeRefreshLayout = v.findViewById(R.id.srlTab1);
        //tvNoQuestions = v.findViewById(R.id.tvNoQuestions);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.show();
        FirebaseRecyclerOptions<Question> options =
                new FirebaseRecyclerOptions.Builder<Question>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Question").child(UserDetails.username).orderByChild("status").equalTo("submitted"), Question.class)
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
        adapter = new QuestionAdapter(getActivity(), options);
        mrecyclerView.setAdapter(adapter);
        pd.dismiss();


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
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.show();
        FirebaseRecyclerOptions<Question> options =
                new FirebaseRecyclerOptions.Builder<Question>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Question").child(UserDetails.username).orderByChild("status").equalTo("submitted"), Question.class)
                        .build();

        adapter = new QuestionAdapter(getActivity(), options);
        mrecyclerView.setAdapter(adapter);
        adapter.startListening();
        pd.dismiss();
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

