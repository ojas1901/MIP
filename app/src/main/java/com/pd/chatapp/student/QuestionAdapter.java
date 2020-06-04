package com.pd.chatapp.student;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.pd.chatapp.Chat;
import com.pd.chatapp.Question;
import com.pd.chatapp.R;
import com.pd.chatapp.UserDetails;

import static androidx.core.content.ContextCompat.startActivity;

public class QuestionAdapter extends FirebaseRecyclerAdapter<Question, QuestionAdapter.QuestionViewHolder> {

    public QuestionAdapter(@NonNull FirebaseRecyclerOptions<Question> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuestionViewHolder holder, int position, @NonNull final Question question) {
        holder.tvQuestion.setText(question.getQuestion());
        holder.tvUser.setText(question.getAskedTo());
        //holder.tvTime.setText(question.getTime());
        holder.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDetails.chatWith = question.getAskedTo();
 //               startActivity(new Intent(getActivity().this, Chat.class));
            }
        });
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_layout, parent, false);

        return new QuestionViewHolder(view);
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{

        TextView tvQuestion, tvUser;
        //TextView tvTime;
        Button btnChat;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUser = itemView.findViewById(R.id.tvAskedTo);
            //tvTime = itemView.findViewById(R.id.tvTime);
            tvQuestion = itemView.findViewById(R.id.tvQuestionAsked);
            btnChat = itemView.findViewById(R.id.btnChat);
        }
    }
}
