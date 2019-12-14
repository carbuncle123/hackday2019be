package com.ttsukiji.hackday2019be.service;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;
import com.ttsukiji.hackday2019be.domain.CategorizeResult;
import com.ttsukiji.hackday2019be.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class DictionaryCategorizeService implements CategorizeService {
    private Tokenizer tokenizer;
    private int limit;
    private List<String> targets = Arrays.asList("形容詞", "形容動詞", "名詞");

    private DictionaryRepository repository;

    public static final int NOT_FOUND_ID = -1;

    @Autowired
    public DictionaryCategorizeService(@Qualifier("mySqlRepository") final DictionaryRepository repository,
                                       @Value("${tokenize.limit}") final int limit
                                     /*@Value("${tokenize.targetPartOfSpeech}") final List<String> targets*/) {
        this.repository = repository;
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
        for (Token token: tokens) {
            final Optional<Integer> categoryId = this.repository.categorize(token.getBaseForm());
            detail.put(token.getSurface(), categoryId.orElse(NOT_FOUND_ID));
        }

        // 多数決
        final List<Integer> ids = detail.values().stream().filter(id -> id != NOT_FOUND_ID).collect(toList());
        return new CategorizeResult(mode(ids), detail);
    }

    private boolean isTarget(final Token token) {
        return targets.contains(token.getPartOfSpeechLevel1());
    }

    public static int mode(final List<Integer> ids) {
        final Map<Integer, Integer> counts = new HashMap<>(); // {id: count}
        for (int id: ids) {
            final int count = counts.getOrDefault(id, 0) + 1;
            counts.put(id, count);
        }

        int maxCount = -1;
        int maxId = NOT_FOUND_ID;
        for (Map.Entry<Integer, Integer> entry: counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxId = entry.getKey();
            }
        }
        return maxId;
    }
}
