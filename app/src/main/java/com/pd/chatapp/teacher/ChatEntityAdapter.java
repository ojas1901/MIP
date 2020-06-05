package com.pd.chatapp.teacher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.pd.chatapp.Chat;
import com.pd.chatapp.ChatEntity;
import com.pd.chatapp.Question;
import com.pd.chatapp.R;
import com.pd.chatapp.UserDetails;

import java.util.ArrayList;

public class ChatEntityAdapter extends RecyclerView.Adapter<ChatEntityAdapter.ChatEntityHolder>  {
    Activity activity;
    ArrayList<String> Userlist;


    public ChatEntityAdapter(Activity act, ArrayList<String> ul)
    {
        Userlist = ul;
        activity = act;
    }


    public void onBindViewHolder(@NonNull ChatEntityHolder holder, int position) {
        final String user = Userlist.get(position);
        //Toast.makeText(activity, "Inside on Bind View Holder", Toast.LENGTH_SHORT).show();

        holder.tvUsers.setText(Userlist.get(position));

        holder.cvChatCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDetails.chatWith = user;
                activity.startActivity(new Intent(activity, Chat.class));
            }
        });
    }

    @NonNull
    public ChatEntityAdapter.ChatEntityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_entity, parent, false);
        //Toast.makeText(activity, "Returning View", Toast.LENGTH_SHORT).show();

        return new ChatEntityHolder(view);
    }

    @Override
    public int getItemCount() {
        return Userlist.size();
    }

    class ChatEntityHolder extends RecyclerView.ViewHolder{

        TextView  tvUsers;
        CardView cvChatCard;
        //Button btnAnswer, btnMarkAsFAQ;
        public ChatEntityHolder(@NonNull View itemView) {
            super(itemView);

            tvUsers = itemView.findViewById(R.id.tvUserChat);
            cvChatCard = itemView.findViewById(R.id.cvChatCard);
        }


    }
}
