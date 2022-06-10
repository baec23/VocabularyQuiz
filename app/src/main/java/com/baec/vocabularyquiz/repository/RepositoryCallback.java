package com.baec.vocabularyquiz.repository;

public interface RepositoryCallback<T> {
    void onComplete(T result);
}