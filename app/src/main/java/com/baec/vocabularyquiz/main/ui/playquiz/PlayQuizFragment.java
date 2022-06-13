package com.baec.vocabularyquiz.main.ui.playquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baec.vocabularyquiz.databinding.FragmentQuizBinding;
import com.baec.vocabularyquiz.model.WordAnswer;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PlayQuizFragment extends Fragment {
    private FragmentQuizBinding binding;
    private PlayQuizViewModel playQuizViewModel;
    private QuizAnswerRecyclerViewAdapter adapter;

    private TextView tv_word;
    private RecyclerView rv_answers;
    private Button bt_next;

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
        bt_next = binding.quizBtNext;
        init();

        QuizGameState gameState = playQuizViewModel.getQuizGameState();
        if (gameState.getLoadingState().getValue() == QuizGameState.LoadingState.NOT_LOADED) {
            playQuizViewModel.loadWord();
            bt_next.setVisibility(View.INVISIBLE);
        }

        gameState.getLoadingState().observe(getViewLifecycleOwner(), loadingState -> {
            if (loadingState == QuizGameState.LoadingState.LOADED) {
                bt_next.setVisibility(View.INVISIBLE);
                tv_word.setText(gameState.getCurrQuizWord().getWord());
                adapter.setAnswers(gameState.getCurrWordAnswers());

            } else if (loadingState == QuizGameState.LoadingState.ANSWERED) {
                bt_next.setVisibility(View.VISIBLE);
            }
        });

        playQuizViewModel.isAnswerStatusChanged().observe(getViewLifecycleOwner(), isChanged -> {
            if(isChanged) {
                adapter.notifyDataSetChanged();
            }
        });

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playQuizViewModel.loadWord();
            }
        });
        return binding.getRoot();
    }

    private void init() {
        rv_answers.setLayoutManager(new LinearLayoutManager(requireContext()));
        if(adapter == null) {
            adapter = new QuizAnswerRecyclerViewAdapter(new ArrayList<>(), new OnItemClickListener<WordAnswer>() {
                @Override
                public void onItemClick(WordAnswer clickedItem) {
                    playQuizViewModel.onAnswerClick(clickedItem);
                }
            });
            rv_answers.setAdapter(adapter);
        }
    }
}