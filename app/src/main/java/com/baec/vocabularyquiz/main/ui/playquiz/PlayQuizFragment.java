package com.baec.vocabularyquiz.main.ui.playquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baec.vocabularyquiz.databinding.FragmentQuizBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlayQuizFragment extends Fragment {
    private FragmentQuizBinding binding;
    private PlayQuizViewModel playQuizViewModel;

    private TextView tv_word;
    private RecyclerView rv_answers;

    public static PlayQuizFragment newInstance() {
        return new PlayQuizFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playQuizViewModel = new ViewModelProvider(this).get(PlayQuizViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        tv_word = binding.quizTvWord;
        rv_answers = binding.quizRvAnswers;
        rv_answers.setLayoutManager(new LinearLayoutManager(requireContext()));
        //rv_answers.setAdapter(new QuizAnswerRecyclerViewAdapter());
        return binding.getRoot();
    }
}