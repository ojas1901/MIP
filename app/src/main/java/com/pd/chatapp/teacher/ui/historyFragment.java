package com.pd.chatapp.teacher.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pd.chatapp.R;
import com.pd.chatapp.UserDetails;
import com.pd.chatapp.teacher.ChatEntityAdapter;

import java.util.ArrayList;
//import com.pd.chatapp.student.QuestionAdapter2;

public class historyFragment extends Fragment {

    TextView tvNoChats;
    RecyclerView mrecyclerView;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressDialog pd;
    static boolean isPresent = false;

    static ArrayList<String> Userlist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_history_teacher, container, false);
        final View V = v;

        Userlist = new ArrayList<String>();
        mrecyclerView = v.findViewById(R.id.rvTeacherHistory);
        tvNoChats = v.findViewById(R.id.tvNoChats);
        mSwipeRefreshLayout = v.findViewById(R.id.srlTeacherHistory);
        //tvNoQuestions = v.findViewById(R.id.tvNoQuestions);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.show();

        isPresent =  false;
        Query reference = FirebaseDatabase.getInstance().getReference().child("Messages").child(UserDetails.username).orderByKey();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String username  = dsp.getKey();
                    //String scores   = dsp.child("scorehigh").getValue().toString();
                    isPresent = false;
                    for(int i = 0; i<Userlist.size(); i++)
                    {
                        if(Userlist.get(i).equals(username))
                        {
                            isPresent = true;
                            break;
                        }
                    }

                    if(! isPresent)
                        Userlist.add(username); //add unique results into array list

                }
                if (Userlist.size() > 0 ) {

                    tvNoChats.setVisibility(View.GONE);
                    mrecyclerView.setVisibility(View.VISIBLE);
                    mrecyclerView.setLayoutManager(new LinearLayoutManager(V.getContext()));
                    adapter = new ChatEntityAdapter(getActivity(), Userlist);
                    mrecyclerView.setAdapter(adapter);
                    pd.dismiss();
                }
                else
                {
                    tvNoChats.setVisibility(View.VISIBLE);
                    mrecyclerView.setVisibility(View.GONE);
                    pd.dismiss();
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        String l = String.valueOf(Userlist.size());
//        Toast.makeText(getActivity(), l , Toast.LENGTH_SHORT).show();

//        if(totalQuestions == 0){
//            tvNoQuestions.setVisibility(View.VISIBLE);
//            mrecyclerView.setVisibility(View.GONE);
//        }
//        else{
//            tvNoQuestions.setVisibility(View.GONE);
//            mrecyclerView.setVisibility(View.VISIBLE);
//
//        }

//        while (! gotResponse) {
//
//        }



        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                doOnRefresh(V);
                mSwipeRefreshLayout.setRefreshing(false);
//              String l = String.valueOf(Userlist.size());
//              Toast.makeText(getActivity(), l , Toast.LENGTH_SHORT).show();

            }
        });
        return v;
    }

    public void doOnRefresh(View v){

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading...");
        pd.show();

        final View V = v;

        Query reference = FirebaseDatabase.getInstance().getReference().child("Messages").child(UserDetails.username).orderByKey();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String username  = dsp.getKey();
                    //String scores   = dsp.child("scorehigh").getValue().toString();
                    isPresent = false;
                    for(int i = 0; i<Userlist.size(); i++)
                    {
                        if(Userlist.get(i).equals(username))
                        {
                            isPresent = true;
                            break;
                        }
                    }
                    if(! isPresent)
                        Userlist.add(username); //add result into array list
                }
                if (Userlist.size() > 0 ) {

                    tvNoChats.setVisibility(View.GONE);
                    mrecyclerView.setVisibility(View.VISIBLE);
                    mrecyclerView.setLayoutManager(new LinearLayoutManager(V.getContext()));
                    adapter = new ChatEntityAdapter(getActivity(), Userlist);
                    mrecyclerView.setAdapter(adapter);
                    pd.dismiss();
                }
                else
                {
                    tvNoChats.setVisibility(View.VISIBLE);
                    mrecyclerView.setVisibility(View.GONE);
                    pd.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        while (! gotResponse) {
//
//        }
//        mrecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
//        adapter = new ChatEntityAdapter(getActivity(), Userlist);
//        mrecyclerView.setAdapter(adapter);


        pd.dismiss();

    }

    @Override
    public void onStart() {
        super.onStart();
        //adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        //adapter.stopListening();
    }

}
