package com.pd.chatapp.student;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.pd.chatapp.Question;
import com.pd.chatapp.R;
import com.pd.chatapp.UserDetails;

public class QuestionAdapter2 extends FirebaseRecyclerAdapter<Question, QuestionAdapter2.QuestionViewHolder> {
    Activity activity;
    public QuestionAdapter2(Activity act, @NonNull FirebaseRecyclerOptions<Question> options) {
        super(options);
        activity = act;
    }

    @Override
    protected void onBindViewHolder(@NonNull QuestionViewHolder holder, int position, @NonNull final Question question) {
        holder.tvQuestion.setText(question.getQuestion());
        holder.tvAskedTo.setText(question.getAskedTo());
        //holder.tvTime.setText(question.getTime());

        if(question.getStatus().equals("pending"))
        {
            //holder.itemView.setVisibility(View.INVISIBLE);
            holder.tvStatus.setText("Not Answered");
            holder.tvStatus.setTextColor(Color.RED);

            holder.tvNote.setText("Faculty has not answered your doubt yet.");


            //holder.btnMarkAsFAQ.setVisibility(View.INVISIBLE);
            return;
        }
        else
        {
            //holder.itemView.setVisibility(View.INVISIBLE);
            holder.tvStatus.setText("Answered");
            holder.tvStatus.setTextColor(Color.parseColor("#076012"));


        }


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
                .inflate(R.layout.question_layout2, parent, false);

        return new QuestionViewHolder(view);
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{

        TextView tvQuestion, tvAskedTo, tvNote, tvStatus, tvPrompt;
        Button btnChat;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAskedTo = itemView.findViewById(R.id.tvAskedTo);
            tvStatus  = itemView.findViewById(R.id.tvStatus);
            tvQuestion = itemView.findViewById(R.id.tvQuestionAsked);
            tvNote = itemView.findViewById(R.id.tvNote);

        }
    }
}
