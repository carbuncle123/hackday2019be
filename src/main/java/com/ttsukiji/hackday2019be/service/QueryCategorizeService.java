package com.ttsukiji.hackday2019be.service;

import com.ttsukiji.hackday2019be.domain.CategorizeResult;


public interface QueryCategorizeService {
    CategorizeResult categorize(final String query);
}
