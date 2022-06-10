package com.baec.vocabularyquiz.main.ui.playquiz;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baec.vocabularyquiz.databinding.ObjQuizanswerBinding;

import java.util.List;

public class QuizAnswerRecyclerViewAdapter extends RecyclerView.Adapter<QuizAnswerRecyclerViewAdapter.ViewHolder> {
    private List<String> answers;

    public QuizAnswerRecyclerViewAdapter(List<String> answers){
        this.answers = answers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ObjQuizanswerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_answer;

        public ViewHolder(@NonNull ObjQuizanswerBinding binding) {
            super(binding.getRoot());
            tv_answer = binding.quizAnswerTvAnswer;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tv_answer.getText() + "'";
        }
    }
}
