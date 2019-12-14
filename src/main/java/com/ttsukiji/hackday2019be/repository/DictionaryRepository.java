package com.ttsukiji.hackday2019be.repository;


import java.util.List;
import java.util.Optional;

public interface DictionaryRepository {
    Optional<Integer> categorize(final String word);
    List<String> getWords(final int categoryId);
    void update(final String word, final int categoryId);
}
