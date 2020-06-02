package com.pd.chatapp.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.pd.chatapp.FAQ;
import com.pd.chatapp.Question;
import com.pd.chatapp.R;

public class QuestionAdapter extends FirebaseRecyclerAdapter<Question, QuestionAdapter.QuestionViewHolder> {

    public QuestionAdapter(@NonNull FirebaseRecyclerOptions<Question> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuestionViewHolder holder, int position, @NonNull Question question) {
        holder.tvQuestion.setText(question.getQuestion());
        holder.tvAnswer.setText(question.getAnswer());
        holder.tvChapter.setText(question.getChapter());
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faq_layout, parent, false);

        return new QuestionViewHolder(view);
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{

        TextView tvQuestion, tvAnswer, tvChapter;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAnswer = itemView.findViewById(R.id.tvAC);
            tvChapter = itemView.findViewById(R.id.tvCC);
            tvQuestion = itemView.findViewById(R.id.tvQC);
        }
    }
}
