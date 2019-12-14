package com.ttsukiji.hackday2019be.service;

import com.ttsukiji.hackday2019be.domain.CategorizeResult;


public interface CategorizeService {
    CategorizeResult categorize(final String query);
}
