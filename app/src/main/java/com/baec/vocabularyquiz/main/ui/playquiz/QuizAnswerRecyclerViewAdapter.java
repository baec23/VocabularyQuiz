package com.baec.vocabularyquiz.main.ui.playquiz;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.baec.vocabularyquiz.R;
import com.baec.vocabularyquiz.databinding.ObjQuizanswerBinding;
import com.baec.vocabularyquiz.model.WordAnswer;
import com.baec.vocabularyquiz.util.diffutil.WordAnswerDiffUtil;

import java.util.List;

public class QuizAnswerRecyclerViewAdapter extends RecyclerView.Adapter<QuizAnswerRecyclerViewAdapter.ViewHolder> {
    private List<WordAnswer> answers;
    private OnItemClickListener<WordAnswer> onAnswerClickListener;

    public QuizAnswerRecyclerViewAdapter(List<WordAnswer> answers, OnItemClickListener<WordAnswer> onAnswerClickListener) {
        this.answers = answers;
        this.onAnswerClickListener = onAnswerClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ObjQuizanswerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WordAnswer wordAnswer = answers.get(position);
        switch (wordAnswer.getAnswerGuessState()) {
            case NOT_GUESSED:
                holder.cv_card.setCardBackgroundColor(ContextCompat.getColor(holder.cv_card.getContext(), R.color.card_notGuessed));
                break;
            case CORRECT:
                holder.cv_card.setCardBackgroundColor(ContextCompat.getColor(holder.cv_card.getContext(), R.color.card_correct));
                break;
            case INCORRECT:
                holder.cv_card.setCardBackgroundColor(ContextCompat.getColor(holder.cv_card.getContext(), R.color.card_incorrect));
                break;
        }
        holder.tv_answer.setText(wordAnswer.getAnswer());
        if (onAnswerClickListener != null)
            holder.cv_card.setOnClickListener(view -> onAnswerClickListener.onItemClick(wordAnswer));
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public void setAnswers(List<WordAnswer> newAnswersList) {
        Log.d("DEBUG", "ADAPTER: SET ANSWERS");
        WordAnswerDiffUtil diffUtil = new WordAnswerDiffUtil(answers, newAnswersList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtil);
        diffResult.dispatchUpdatesTo(this);
        answers = newAnswersList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cv_card;
        private final TextView tv_answer;

        public ViewHolder(@NonNull ObjQuizanswerBinding binding) {
            super(binding.getRoot());
            cv_card = binding.quizAnswerCvCard;
            tv_answer = binding.quizAnswerTvAnswer;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tv_answer.getText() + "'";
        }
    }
}
