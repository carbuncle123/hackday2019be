package com.ttsukiji.hackday2019be.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class QueryInferModelRepositoryImpl implements QueryInferModelRepository {

    private RestTemplate template;
    private String url;

    public QueryInferModelRepositoryImpl(@Value("${model.url}") final String url) {
        this.template = new RestTemplate();
        this.url = url;
    }

    @Override
    public double inferScore(final String query) {
        final Response response = template.getForObject(url +"?q=" + query, Response.class);
        if (response != null) {
            return response.getScore();
        }
        return 0.0;
    }

    @Data
    @NoArgsConstructor
    @ToString
    public static class Response {
        private double score;
    }
}
