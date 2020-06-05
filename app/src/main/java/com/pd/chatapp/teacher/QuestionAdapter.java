package com.pd.chatapp.teacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
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

public class QuestionAdapter extends FirebaseRecyclerAdapter<Question, QuestionAdapter.QuestionViewHolder> {
    Activity activity;



    public QuestionAdapter(Activity act, @NonNull FirebaseRecyclerOptions<Question> options) {
        super(options);
        activity = act;
    }

    @Override
    protected void onBindViewHolder(@NonNull QuestionViewHolder holder, int position, @NonNull final Question question) {
        holder.tvQuestion.setText(question.getQuestion());
        holder.tvUser.setText(question.getAskedBy());
        holder.tvTime.setText(question.getTime());


        if(question.getStatus().equals("submitted"))
        {
            //holder.itemView.setVisibility(View.INVISIBLE);
            holder.tvStatus.setText("Answered");
            holder.tvStatus.setTextColor(Color.parseColor("#076012"));
            holder.btnAnswer.setText("View");

            holder.btnAnswer.setGravity(Gravity.CENTER);
            holder.btnMarkAsFAQ.setVisibility(View.INVISIBLE);

        }
        else
        {
            //holder.itemView.setVisibility(View.INVISIBLE);
            holder.tvStatus.setText("Not Answered");
            holder.tvStatus.setTextColor(Color.RED);
            holder.btnMarkAsFAQ.setVisibility(View.INVISIBLE);

        }
        holder.btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        String que, chap, askedBy, time, status, answer;

                        que = question.getQuestion();
                        chap = question.getChapter();
                        askedBy = question.getAskedBy();
                        time = question.getTime();
                        status = question.getStatus();
                        answer = question.getAnswer();

                Intent intent = new Intent(activity, answerDoubt.class);
                intent.putExtra("Question", que);
                intent.putExtra("Chapter", chap);
                intent.putExtra("Asked By", askedBy);
                intent.putExtra("Time", time);
                intent.putExtra("Status", status);
                intent.putExtra("Answer", answer);


                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
                });
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_layout_teacher, parent, false);

        return new QuestionViewHolder(view);
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{

        TextView tvQuestion, tvUser, tvTime, tvStatus;
        Button btnAnswer, btnMarkAsFAQ;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUser = itemView.findViewById(R.id.tvUser);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvQuestion = itemView.findViewById(R.id.tvQues);
            btnAnswer = itemView.findViewById(R.id.btnAnswer);
            btnMarkAsFAQ = itemView.findViewById(R.id.btnMarkAsFAQ);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }


    }
}
