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
import com.pd.chatapp.R;

public class FAQAdapter extends FirebaseRecyclerAdapter<FAQ, FAQAdapter.FAQViewHolder> {

    public FAQAdapter(@NonNull FirebaseRecyclerOptions<FAQ> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FAQViewHolder holder, int position, @NonNull FAQ faq) {
        holder.tvQuestion.setText(faq.getQuestion());
        holder.tvAnswer.setText(faq.getAnswer());
        holder.tvChapter.setText(faq.getChapter());
    }

    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faq_layout, parent, false);

        return new FAQViewHolder(view);
    }

    class FAQViewHolder extends RecyclerView.ViewHolder{

        TextView tvQuestion, tvAnswer, tvChapter;

        public FAQViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAnswer = itemView.findViewById(R.id.tvAC);
            tvChapter = itemView.findViewById(R.id.tvCC);
            tvQuestion = itemView.findViewById(R.id.tvQC);
        }
    }
}
