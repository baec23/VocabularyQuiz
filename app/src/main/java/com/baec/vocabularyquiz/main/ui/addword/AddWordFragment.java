package com.baec.vocabularyquiz.main.ui.addword;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.baec.vocabularyquiz.databinding.FragmentAddwordBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddWordFragment extends Fragment {
    private FragmentAddwordBinding binding;
    private AddWordViewModel addWordViewModel;

    private EditText et_word;
    private EditText et_answer;
    private Button bt_addWord;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addWordViewModel = new ViewModelProvider(this).get(AddWordViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAddwordBinding.inflate(inflater, container, false);
        et_word = binding.addWordEtWord;
        et_answer = binding.addWordEtAnswer;
        bt_addWord = binding.addWordBtAddWord;
        et_word.addTextChangedListener(getWordTextWatcher());
        et_answer.addTextChangedListener(getAnswerTextWatcher());

        addWordViewModel.isValidationOkay().observe(getViewLifecycleOwner(), isOkay -> {
            bt_addWord.setEnabled(isOkay);
        });

        addWordViewModel.isWordAdded().observe(getViewLifecycleOwner(), isAdded -> {
            if (isAdded) {
                et_word.setText("");
                et_answer.setText("");
            }
        });

        bt_addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWordViewModel.onAddWordClicked();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private TextWatcher getWordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                addWordViewModel.onWordTextChanged(s.toString());
            }
        };
    }

    private TextWatcher getAnswerTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                addWordViewModel.onAnswerTextChanged(s.toString());
            }
        };
    }
}