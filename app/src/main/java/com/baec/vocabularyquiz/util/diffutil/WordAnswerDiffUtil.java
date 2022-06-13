package com.baec.vocabularyquiz.util.diffutil;

import androidx.recyclerview.widget.DiffUtil;

import com.baec.vocabularyquiz.model.WordAnswer;

import java.util.List;

public class WordAnswerDiffUtil extends DiffUtil.Callback {
    private final List<WordAnswer> oldList;
    private final List<WordAnswer> newList;

    public WordAnswerDiffUtil(List<WordAnswer> oldList, List<WordAnswer> newList)
    {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize()
    {
        return oldList.size();
    }

    @Override
    public int getNewListSize()
    {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition)
    {
        return oldList.get(oldItemPosition).getAnswer().equals(newList.get(newItemPosition).getAnswer());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition)
    {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
