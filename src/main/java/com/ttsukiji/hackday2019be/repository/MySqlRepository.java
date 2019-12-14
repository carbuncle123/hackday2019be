package com.ttsukiji.hackday2019be.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySqlRepository implements DictionaryRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final String UPDATE_QUERY = "UPDATE category SET category_id = :category_id WHERE word = :word";
    private static final String INSERT_QUERY = "INSERT INTO category(word, category_id) VALUES(:word, :category_id)";
    private static final String SELECT_CATEGORY_QUERY = "SELECT category_id FROM category WHERE word = :word";
    private static final String SELECT_WORDS_QUERY = "SELECT word FROM category WHERE category_id = :category_id LIMIT 100";

    @Override
    public Optional<Integer> categorize(final String word) {
        final SqlParameterSource param = new MapSqlParameterSource().addValue("word", word);
        final List<Integer> result = template.queryForList(SELECT_CATEGORY_QUERY, param, Integer.class);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public List<String> getWords(final int categoryId) {
        final SqlParameterSource param = new MapSqlParameterSource().addValue("category_id", categoryId);
        return template.queryForList(SELECT_WORDS_QUERY, param, String.class);
    }

    @Override
    public void update(final String word, final int categoryId) {
        final List<String> result = template.queryForList(SELECT_CATEGORY_QUERY, new MapSqlParameterSource().addValue("word", word), String.class);
        final String upsertQuery = (result.isEmpty()) ? INSERT_QUERY : UPDATE_QUERY;
        final SqlParameterSource param = new MapSqlParameterSource()
                .addValue("word", word)
                .addValue("category_id", categoryId);
        template.update(upsertQuery, param);
    }
}
