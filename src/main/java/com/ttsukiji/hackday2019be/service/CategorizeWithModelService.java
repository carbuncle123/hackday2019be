package com.ttsukiji.hackday2019be.service;

import com.ttsukiji.hackday2019be.domain.CategorizeResult;
import com.ttsukiji.hackday2019be.repository.QueryInferModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategorizeWithModelService implements QueryCategorizeService {
    private QueryInferModelRepository repository;

    private static final int NEUTRAL_ID = 0;
    private static final int POSITIVE_ID = 1;
    private static final int NEGATIVE_ID = 2;

    @Autowired
    public CategorizeWithModelService(@Qualifier("queryInferModelRepositoryImpl") final QueryInferModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategorizeResult categorize(final String query) {
        final double score = repository.inferScore(query);
        // モデルを使う場合はdetailはempty(tokenごとの解析をしない)
        final Map<String, Integer> detail = new HashMap<>();
        int categoryId = NEUTRAL_ID;

        if (score > 0) {
            categoryId = POSITIVE_ID;
        } else if (score < 0) {
            categoryId = NEGATIVE_ID;
        }

        return new CategorizeResult(categoryId, detail);
    }
}
