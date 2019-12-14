package com.ttsukiji.hackday2019be.repository;

import java.util.Optional;

public interface WordCategorizeRepository {
    Optional<Integer> categorize(final String word);
}
