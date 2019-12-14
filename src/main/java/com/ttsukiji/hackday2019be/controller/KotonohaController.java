package com.ttsukiji.hackday2019be.controller;

import com.ttsukiji.hackday2019be.domain.CategorizeResult;
import com.ttsukiji.hackday2019be.service.QueryCategorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorize")
public class KotonohaController {

    private QueryCategorizeService service;

    @Autowired
    public KotonohaController(@Qualifier("categorizeWithWordService") final QueryCategorizeService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping
    public CategorizeResult categorize(@RequestParam(name = "query") final String query) {
        return service.categorize(query);
    }
}
