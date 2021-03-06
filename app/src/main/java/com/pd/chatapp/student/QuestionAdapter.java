package com.pd.chatapp.student;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.pd.chatapp.Chat;
import com.pd.chatapp.Question;
import com.pd.chatapp.R;
import com.pd.chatapp.UserDetails;
import com.pd.chatapp.teacher.answerDoubt;

import static androidx.core.content.ContextCompat.startActivity;

public class QuestionAdapter extends FirebaseRecyclerAdapter<Question, QuestionAdapter.QuestionViewHolder> {
    Activity activity;
    public QuestionAdapter(Activity act, @NonNull FirebaseRecyclerOptions<Question> options) {
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

            holder.btnChat.setVisibility(View.INVISIBLE);
            holder.tvPrompt.setVisibility(View.INVISIBLE);

            //holder.btnMarkAsFAQ.setVisibility(View.INVISIBLE);
            return;
        }
        else
        {
            //holder.itemView.setVisibility(View.INVISIBLE);
            holder.tvStatus.setText("Answered");
            holder.tvStatus.setTextColor(Color.parseColor("#076012"));
            holder.tvPrompt.setTextColor(Color.BLUE);

        }


        holder.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDetails.chatWith = question.getAskedTo();
                activity.startActivity(new Intent(activity, Chat.class));
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String que, chap, ans;

                que = question.getQuestion();
                chap = question.getChapter();
                ans = question.getAnswer();
                Intent intent = new Intent(activity, viewAnswer.class);
                intent.putExtra("Question", que);
                intent.putExtra("Chapter", chap);
                intent.putExtra("Answer", ans);

                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

        TextView tvQuestion, tvAskedTo, tvNote, tvStatus, tvPrompt;
        Button btnChat;
        CardView cardView;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAskedTo = itemView.findViewById(R.id.tvAskedTo);
            tvStatus  = itemView.findViewById(R.id.tvStatus);
            tvQuestion = itemView.findViewById(R.id.tvQuestionAsked);
            tvPrompt = itemView.findViewById(R.id.tvTapToViewAnswer);
            tvNote = itemView.findViewById(R.id.tvNote);
            cardView = itemView.findViewById(R.id.cvQuestionStudent);
            btnChat = itemView.findViewById(R.id.btnChat);
        }
    }
}
