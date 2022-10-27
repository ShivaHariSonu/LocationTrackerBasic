package com.nsl.locationtracker.repository;



import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class ESRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ESRepository.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public SearchResponse find(SearchRequest searchRequest, String errorMessage) {
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse;
        } catch (IOException e) {
            LOGGER.error(errorMessage, e);
        }
        return null;
    }
}
