package com.ttsukiji.hackday2019be.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySqlCategorizeRepository implements WordCategorizeRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final String QUERY = "SELECT category_id FROM category WHERE word = :word";

    @Override
    public Optional<Integer> categorize(final String word) {
        final SqlParameterSource param = new MapSqlParameterSource().addValue("word", word);
        final List<Integer> result = jdbcTemplate.queryForList(QUERY, param, Integer.class);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }
}
