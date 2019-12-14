package com.ttsukiji.hackday2019be.service;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import com.ttsukiji.hackday2019be.domain.CategorizeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategorizeWithWordService implements QueryCategorizeService {
    private Tokenizer tokenizer;
    private int limit;
    private List<String> targets = Arrays.asList("形容詞", "形容動詞", "名詞");

    @Autowired
    public CategorizeWithWordService(@Value("${tokenize.limit}") final int limit/*,
                                     @Value("${tokenize.targetPartOfSpeech}") final List<String> targets*/) {
        this.tokenizer = new Tokenizer();
        this.limit = limit;
        //this.targets = targets;
    }

    @Override
    public CategorizeResult categorize(final String query) {
        final List<Token> tokens = this.tokenizer.tokenize(query)
                .stream()
                .filter(this::isTarget)
                .limit(limit).collect(toList());

        final HashMap<String, Integer> detail = new HashMap<>();
        tokens.forEach(t -> detail.put(t.getBaseForm(), 2));

        return new CategorizeResult(1, detail);
    }

    private boolean isTarget(final Token token) {
        return targets.contains(token.getPartOfSpeechLevel1());
    }
}
