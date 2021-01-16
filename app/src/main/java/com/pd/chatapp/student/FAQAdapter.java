package com.pd.chatapp.student;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.pd.chatapp.FAQ;
import com.pd.chatapp.R;

import static java.security.AccessController.getContext;

public class FAQAdapter extends FirebaseRecyclerAdapter<FAQ, FAQAdapter.FAQViewHolder> {
    Context mContext;
    public FAQAdapter(@NonNull FirebaseRecyclerOptions<FAQ> options, Context mContext) {
        super(options);
        this.mContext = mContext;
    }

    @Override
    protected void onBindViewHolder(@NonNull FAQViewHolder holder, int position, @NonNull FAQ faq) {
        holder.tvQuestion.setText(faq.getQuestion());
        holder.tvAnswer.setText(faq.getAnswer());
        holder.tvChapter.setText(faq.getChapter());

        final String question = faq.getQuestion();
        final String answer = faq.getAnswer();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = "Question: " + question + "\n\n" + "Answer: " + answer + "\n";
                ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("FAQ Answer", content);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(mContext, "Answer copied to clipboard !", Toast.LENGTH_SHORT).show();
            }
        });
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
