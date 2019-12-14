package com.ttsukiji.hackday2019be.repository;


import java.util.List;

public interface DictionaryEditRepository {
    List<String> getWords(final int categoryId);
    void update(final String word, final int categoryId);
}
