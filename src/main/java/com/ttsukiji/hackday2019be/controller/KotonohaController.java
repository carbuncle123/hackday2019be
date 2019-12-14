package com.ttsukiji.hackday2019be.controller;

import com.ttsukiji.hackday2019be.domain.CategorizeResult;
import com.ttsukiji.hackday2019be.service.QueryCategorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class KotonohaController {

    private QueryCategorizeService categorizeWithWordService;

    private QueryCategorizeService categorizeWithModelService;

    @Autowired
    public KotonohaController(@Qualifier("categorizeWithWordService") final QueryCategorizeService categorizeWithWordService,
                              @Qualifier("categorizeWithModelService") final QueryCategorizeService categorizeWithModelService) {
        this.categorizeWithWordService = categorizeWithWordService;
        this.categorizeWithModelService = categorizeWithModelService;
    }

    @CrossOrigin
    @GetMapping
    @RequestMapping("/categorize")
    public CategorizeResult categorize(@RequestParam(name = "query") final String query) {
        return categorizeWithWordService.categorize(query);
    }

    @CrossOrigin
    @GetMapping
    @RequestMapping("/infer")
    public CategorizeResult infer(@RequestParam(name = "query") final String query) {
        return categorizeWithModelService.categorize(query);
    }
}
