package com.pd.chatapp.teacher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.pd.chatapp.Question;
import com.pd.chatapp.R;

public class QuestionAdapter extends FirebaseRecyclerAdapter<Question, QuestionAdapter.QuestionViewHolder> {

    public QuestionAdapter(@NonNull FirebaseRecyclerOptions<Question> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuestionViewHolder holder, int position, @NonNull Question question) {
        holder.tvQuestion.setText(question.getQuestion());
        holder.tvUser.setText(question.getAskedBy());
        holder.tvTime.setText(question.getTime());
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_layout_teacher, parent, false);

        return new QuestionViewHolder(view);
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{

        TextView tvQuestion, tvUser, tvTime;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUser = itemView.findViewById(R.id.tvUser);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvQuestion = itemView.findViewById(R.id.tvQues);
        }
    }
}
